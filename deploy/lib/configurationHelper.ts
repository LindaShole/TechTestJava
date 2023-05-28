import {env} from "process";
import * as fs from "fs";

const yaml = require('js-yaml')

export class ConfigurationHelper{
    static loadConfigurations(){
        const environment = env.ENVIRONMENT as string;
        this.validateEnvironmentName(environment);
        return yaml.load(fs.readFileSync(`config/${environment}.yml`,'utf-8'));
    }

    private static validateEnvironmentName (environment : string): void{

        if(environment === undefined){
            throw Error('Environment context not supplied')
        }
        if(["dev","staging","prod"].indexOf(environment) === -1){
            throw Error(`Unknown environment name '${environment}' supplied.`);
        }
    }

}

