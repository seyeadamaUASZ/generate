import { Application } from './application';

export class Workflow{
    wkfId:number;
    wkfArtifactId:String;
    name:String;
    description:String;
    wkfConteneur:String;
    wkfEtat:String;
    groupId:String;
    version:String;
    wkfProcess_id: String;
    wkfProcess_inst_id: String;
    wkfAppId:Application;
    wkfSecteur:String;
    wkfCalltoaction: String;
    wkfLabelwdgt: String;
}