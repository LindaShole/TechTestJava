#!/usr/bin/env node
import { App } from 'aws-cdk-lib';
import { env } from 'process';
import 'source-map-support/register';
import {Infrastructure} from "../lib/infrastructure-stack";

if (env.DOCKER_IMAGE == undefined) {
    throw "No DOCKER_IMAGE specified, please set the 'DOCKER_IMAGE' variable";
}

if (env.ENVIRONMENT == undefined) {
    throw "No ENVIRONMENT specified, please set the 'ENVIRONMENT' variable";
}

if (env.AWS_DEFAULT_ACCOUNT == undefined) {
    throw "No AWS_DEFAULT_ACCOUNT specified, please set the 'AWS_DEFAULT_ACCOUNT' variable";
}

if (env.AWS_DEFAULT_REGION == undefined) {
    throw "No AWS_DEFAULT_REGION specified, please set the 'AWS_DEFAULT_REGION' variable";
}

let props = {
    env: {
        account: env.AWS_DEFAULT_ACCOUNT,
        region: env.AWS_DEFAULT_REGION,
    }
};

const app = new App();

new Infrastructure(app, `play-integrity-stack-${env.ENVIRONMENT}`, env.ENVIRONMENT as string, props);