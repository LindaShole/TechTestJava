import {App, Duration, Stack, StackProps} from "aws-cdk-lib";
import {ConfigurationHelper} from "./configurationHelper";
import {env} from "process";
import {SecurityGroup, SubnetType, Vpc} from "aws-cdk-lib/aws-ec2";
import {
    AwsLogDriver,
    Cluster,
    ContainerImage,
    FargateService,
    FargateTaskDefinition,
    Protocol
} from "aws-cdk-lib/aws-ecs";
import {Role, ServicePrincipal} from "aws-cdk-lib/aws-iam";
import {LogGroup, RetentionDays} from "aws-cdk-lib/aws-logs";
import {Secret} from "aws-cdk-lib/aws-secretsmanager";
import {
    ApplicationListener,
    ApplicationProtocol,
    ApplicationTargetGroup,
    ListenerCondition
} from "aws-cdk-lib/aws-elasticloadbalancingv2";


export class Infrastructure extends Stack{
    constructor(scope: App, id : string, environment: string, props? : StackProps) {
        super(scope,id,props);

        const environmentConfig = ConfigurationHelper.loadConfigurations();

        const applicationVpc = Vpc.fromLookup(this, 'application-general-vpc', {
            tags: {
                'cv:resource:role': 'application-general'
            }
        });

        const cluster = new Cluster(this, `anyCompany-service`, {
            clusterName: `anyCompany`,
            containerInsights: true,
            vpc: applicationVpc
        });

        const executionRole = new Role(this, "roleEcsExecution", {
            assumedBy: new ServicePrincipal("ecs-tasks.amazonaws.com")
        });

        executionRole.addManagedPolicy({
            managedPolicyArn: 'arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy'
        });

        const anyCompanyServiceTask = new FargateTaskDefinition(this,'anyCompany-task',{
            memoryLimitMiB: 4096,
            cpu: 2048,
            executionRole: executionRole,
            taskRole: executionRole
        });

        let logGroup = new LogGroup(this, 'anyCompany-logGroup', {
            logGroupName: `anyCompany-service`,
            retention: RetentionDays.ONE_MONTH
        });

        anyCompanyServiceTask.addContainer('anyCompany-container', {
            image: ContainerImage.fromRegistry(env.DOCKER_IMAGE as string),
            essential: true,
            cpu: 1024,
            memoryLimitMiB: 2048,
            healthCheck: {
                command: ["CMD-SHELL", "curl -f http://localhost:8080/api/customer/ping || exit 1"]
            },
            portMappings: [
                {
                    containerPort: 8080,
                    protocol: Protocol.TCP
                }
            ],
            environment: {
                ENVIRONMENT: environment,
            },
            logging: new AwsLogDriver({
                streamPrefix: "anyCompany-service",
                logGroup: logGroup,
            }),
        });


        let anyCompanyEcsSg = new SecurityGroup(this, 'anyCompanySecurityGroup', {
            vpc: applicationVpc,
            allowAllOutbound: true,
            description: "Security Group for anyCompany ECS service, "
        })
        const anyCompany = new FargateService(this, 'anyCompanyService', {
            cluster: cluster,
            serviceName: 'anyCompany-service',
            taskDefinition: anyCompanyServiceTask,
            vpcSubnets: {
                subnetType: SubnetType.PRIVATE_WITH_NAT,
            },
            securityGroups: [anyCompanyEcsSg]
        });

        const anyCompanyTarget = new ApplicationTargetGroup(this, 'anyCompanyTarget', {
            healthCheck: {
                interval: Duration.seconds(10),
                path: '/api/customer/ping',
                port: '8080',
                timeout: Duration.seconds(5),
                unhealthyThresholdCount: 5,
                healthyThresholdCount: 2,
                healthyHttpCodes: "200",
                enabled: true
            },
            port: 8080,
            targetGroupName: `anyCompany-service-ecs`,
            protocol: ApplicationProtocol.HTTP,
            targets: [anyCompanyService],
            vpc: applicationVpc
        })

        const listener = ApplicationListener.fromLookup(this, 'application-general-listener', {
            listenerPort: 443,
            loadBalancerTags: {
                'cv:resource:role': 'application-general',
            }
        });

        listener.addTargetGroups('anyCompanyServiceTG', {
            conditions: [
                ListenerCondition.pathPatterns(['*/anyCompany*'])
            ],
            priority: 505,
            targetGroups: [anyCompanyTarget]
        });

        const scaling = anyCompanyService.autoScaleTaskCount({minCapacity: environmentConfig.taskMinScale, maxCapacity: environmentConfig.taskMaxScale});

        scaling.scaleOnCpuUtilization('ScaleOnSeventyFivePercentCPU', {
            targetUtilizationPercent: 40,
            scaleOutCooldown: Duration.seconds(5),
            scaleInCooldown: Duration.seconds(300)
        });

        scaling.scaleOnMemoryUtilization('ScaleOnSeventyFivePercentMem', {
            targetUtilizationPercent: 50,
            scaleOutCooldown: Duration.seconds(5),
            scaleInCooldown: Duration.seconds(300)
        });

        scaling.scaleOnRequestCount('ScaleOnSeventyFivePercentRequest', {
            requestsPerTarget: 500,
            targetGroup: anyCompanyTarget,
            scaleOutCooldown: Duration.seconds(5),
            scaleInCooldown: Duration.seconds(300)
        })
    }
}