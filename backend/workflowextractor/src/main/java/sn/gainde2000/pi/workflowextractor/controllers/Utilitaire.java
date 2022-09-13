package sn.gainde2000.pi.workflowextractor.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sn.gainde2000.pi.workflowextractor.models.Field;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;


public class Utilitaire {

    public String getNameProjet(String containerId) {
        RestTemplate restTemplate2 = new RestTemplate();

        // create auth credentials
        String authStr2 = "wbadmin:wbadmin";
        String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
        // create headers
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Basic " + base64Credits2);
        headers2.add("content-type","application/json");
        // create request
        HttpEntity request2 = new HttpEntity(headers2);
        String url2 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes";
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers2.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId);

        String data =response2.getBody();

        JSONObject jsonArr = new JSONObject(data);
        //JSONArray jarr= new JSONArray(jsonArr.get("processes"));
        JSONArray jsontest = new JSONArray(jsonArr.get("processes").toString());
        JSONObject json = new JSONObject(jsontest.get(0).toString());
        //System.out.println(json.get("process-id"));
        String processName=json.get("process-name").toString();
        processName= processName.replace(" ","");
        String containerName=json.get("process-id").toString();
        containerName= containerName.replace("."+processName,"");
        //System.out.println(containerName);
        String nomprojet=containerName;
        return nomprojet.replace("_", "");
    }

    public String getProcessId(String containerId) {
        RestTemplate restTemplate2 = new RestTemplate();


        String authStr2 = "wbadmin:wbadmin";
    String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
    // create headers
    HttpHeaders headers2 = new HttpHeaders();
         headers2.add("Authorization", "Basic " + base64Credits2);
         headers2.add("content-type","application/json");
    // create request
    HttpEntity request2 = new HttpEntity(headers2);
    String url2 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes";
         headers2.setContentType(MediaType.APPLICATION_JSON);
         headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
         headers2.set("X-Request-Source", "Desktop");
    ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId);

    String data =response2.getBody();

    JSONObject jsonArr = new JSONObject(data);
    //JSONArray jarr= new JSONArray(jsonArr.get("processes"));
    JSONArray jsontest = new JSONArray(jsonArr.get("processes").toString());
    JSONObject json = new JSONObject(jsontest.get(0).toString());
    //System.out.println(json.get("process-id"));

        return json.get("process-id").toString();
}

    public String  getListArguments(JSONObject inputtasks){
        String listField = "";
        String laststr = "";
        JSONObject merchantData = (JSONObject) inputtasks.get("task-inputs");
        JSONArray recs001 = merchantData.getJSONArray("entry");
        for (int h = 0; h < recs001.length(); ++h) {
            JSONObject rec001 = recs001.getJSONObject(h);
            //System.out.println(rec001.get("value"));
            if (!rec001.get("value").equals("Object")){
                listField = listField+" "+rec001.get("value")+" "+rec001.get("key")+",";
                laststr = (String) listField.subSequence(0, listField.length()-1);

            }
        }

        return  laststr;
    }
    public String generateVar(JSONObject inputtasks){
        String listField = "";
        String laststr = "";
        JSONObject merchantData = (JSONObject) inputtasks.get("task-inputs");
        JSONArray recs001 = merchantData.getJSONArray("entry");
        for (int h = 0; h < recs001.length(); ++h) {
            JSONObject rec001 = recs001.getJSONObject(h);
            //System.out.println(rec001.get("value"));
            if (!rec001.get("value").equals("Object")){

                listField = listField+""+rec001.get("value")+" "+rec001.get("key")+"= new "+rec001.get("value")+"();\n";
                laststr = listField;

            }
        }

        laststr.subSequence(0, laststr.length()-1);
        return  laststr;
    }
    public String generateVarDto(JSONObject inputtasks){
        String listField = "";
        String laststr = "";
        JSONObject merchantData = (JSONObject) inputtasks.get("task-inputs");
        JSONArray recs001 = merchantData.getJSONArray("entry");
        for (int h = 0; h < recs001.length(); ++h) {
            JSONObject rec001 = recs001.getJSONObject(h);
            //System.out.println(rec001.get("value"));
            if (!rec001.get("value").equals("Object")){

                listField = listField+"public "+rec001.get("value")+" "+rec001.get("key")+";\n";
                laststr = listField;

            }
        }

        laststr.subSequence(0, laststr.length()-1);
        return  laststr;
    }
    public String generateArgs(JSONObject inputtasks){
        String listField = "";
        String laststr = "";
        JSONObject merchantData = (JSONObject) inputtasks.get("task-inputs");
        JSONArray recs001 = merchantData.getJSONArray("entry");
        for (int h = 0; h < recs001.length(); ++h) {
            JSONObject rec001 = recs001.getJSONObject(h);
            //System.out.println(rec001.get("value"));
            if (!rec001.get("value").equals("Object")){

                listField = listField+""+rec001.get("key")+",";
                laststr = (String) listField.subSequence(0, listField.length()-1);
            }
        }

        return  laststr;
    }
    public String  getReturnType(JSONObject inputtasks){
        String listField = "";
        String laststr = "";
        JSONObject merchantData = (JSONObject) inputtasks.get("task-outputs");
        JSONArray recs001 = merchantData.getJSONArray("entry");
        for (int h = 0; h < recs001.length(); ++h) {
            JSONObject rec001 = recs001.getJSONObject(h);
            //System.out.println(rec001.get("value"));
            if (!rec001.get("value").equals("Object")){
                listField = listField+" "+rec001.get("value")+" "+rec001.get("key")+",";
                laststr = listField;

            }
        }

        laststr.subSequence(0, laststr.length()-1);
        return  laststr;
    }

    public String getMethodName(JSONObject inputtasks){
       return inputtasks.getString("task-name");
    }


    public void generateServiceAndController(JSONArray task,File nouveaupath ,String artfactid,String packagejbpm){
        try {

            //System.out.println("task: "+task);
        for (int h = 0; h < task.length(); ++h) {
            JSONObject rec001 = task.getJSONObject(h);
           // this.getListArguments(rec001);
            String id002 = this.getMethodName(rec001);

            File dirDto = new File(nouveaupath + "\\DTO");
            //System.out.println("dirservice"+dirService);
            File dto = new File(dirDto, "" + id002 + "DTO.java");
            File repo = new File(dirDto, "" + id002 + "Mapper.java");
            dto.createNewFile();

            FileWriter myWriterdto = new FileWriter(dirDto+"\\" + id002 + "DTO.java");
            myWriterdto.write("package "+artfactid+"."+packagejbpm+".DTO;\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    "public class "+ id002 +"DTO {\n" +
                    "    \n" +
                    ""+this.generateVarDto(rec001)+"\n" +
                    "\n" +
                    "}");
            myWriterdto.close();
            FileWriter myWriterrepo = new FileWriter(dirDto+"\\" + id002 + "Respository.java");
            myWriterrepo.write("package "+artfactid+"."+packagejbpm+".DTO;\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    "public interface "+ id002 +"Respository extends JpaRepository<"+ id002 +", Long> {\n" +
                    "\n" +
                    "}");
            myWriterrepo.close();
            FileWriter myWritermapper = new FileWriter(dirDto+"\\" + id002 + "Mapper.java");
            myWritermapper.write("package "+artfactid+"."+packagejbpm+".DTO;\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    " @Mapper\n" +
                    "public interface "+ id002 +"Mapper {\n" +
                    "    ProductDTO to"+ id002 +"DTO("+ id002 +" "+ id002 +");\n" +
                    "\n" +
                    "    List<"+ id002 +"DTO> to"+ id002 +"DTOs(List<"+ id002 +"> "+ id002 +");\n" +
                    "\n" +
                    "    "+ id002 +" to"+ id002 +"("+ id002 +"DTO "+ id002 +"DTO);\n" +
                    "}\n" +
                    "}");
            myWritermapper.close();
            /*++++++++++++++++++++++++++++++++++++++++++++*/

            File dirService = new File(nouveaupath + "\\Services");
            //System.out.println("dirservice"+dirService);
            File service = new File(dirService, "" + id002 + "Services.java");

                service.createNewFile();

            FileWriter myWriterserv = new FileWriter(dirService+"\\" + id002 + "Services.java");
            myWriterserv.write("package "+artfactid+"."+packagejbpm+".Services;\n" +
                    "\n" +

                    "import java.util.List;\n" +
                    "import lombok.RequiredArgsConstructor;\n" +
                    "import org.springframework.stereotype.Service;\n" +
                    "\n" +
                    "import java.util.List;\n" +
                    "import java.util.Optional;\n" +
                    "\n" +
                    "@RequiredArgsConstructor\n" +
                    "\n" +
                     "\n" + " @Service\n" +
                    "public class "+ id002 +"Services {\n" +
                    "    \n" +
                    "    public JSONObject "+ id002 +"("+this.getListArguments(rec001)+"){\n" +
                    "\n" +
                    "        return null;\n" +
                    "       public List<"+ id002 +"> findAll() {\n" +
                    "        return "+ id002 +"Respository.findAll();\n" +
                    "    }\n" +
                    "    }\n" +
                    "\n" +
                    "}");
            myWriterserv.close();


            File dirControllers = new File(nouveaupath + "\\Controllers");
            File controller = new File(dirControllers, "" + id002 + "Controller.java");
            controller.createNewFile();
            FileWriter myWriter = new FileWriter(dirControllers+"\\" + id002 + "Controller.java");
            myWriter.write("" +"package "+artfactid+"."+packagejbpm+".Controllers;\n" +

                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
                    "import lombok.RequiredArgsConstructor;\n" +
                    "import lombok.extern.slf4j.Slf4j;\n" +
                    "import org.springframework.web.bind.annotation.*;\n" +
                    "import java.util.List;\n" +
                    "import java.util.Optional;\n" +
                    "import org.springframework.http.ResponseEntity;\n" +
                    "import org.springframework.web.bind.annotation.GetMapping;\n" +
                    "import org.springframework.web.bind.annotation.PostMapping;\n" +
                    "import org.springframework.web.bind.annotation.RequestBody;\n" +
                    "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                    "import org.springframework.web.bind.annotation.RequestParam;\n" +
                    "import org.springframework.web.bind.annotation.RestController;\n" +
                    "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                    " \n" +
                    "import sn.gainde2000.pi.Service_Maintenance.Services."+id002+"Services;\n" +
                    "\n" +
                    "@RestController\n" +
                    "@RequestMapping(\"/"+packagejbpm+"\")\n" +
                    "public class "+id002+"Controller {\n" +
                    "     @Autowired\n" +
                    "    private "+id002+"Services "+id002+"Service;\n" +
                    " \n" +
                    "    @PostMapping(\"/add"+id002+"\")\n" +
                    "     @ResponseBody\n" +
                    "    public ResponseEntity<?> "+id002+"(@RequestBody Simple"+id002+"DTO simple"+id002+"DTORequest){\n" +
                     "       "+this.generateVar(rec001)+"\n"+
                    "        return ResponseEntity.ok().body("+id002+"Service."+id002+"("+this.generateArgs(rec001)+"));\n"+
                    "    }\n" +
                    "@GetMapping\n" +
                            "    public ResponseEntity<List<"+id002+"DTO>> findAll() {\n" +
                            "        return ResponseEntity.ok("+id002+"Mapper.to"+id002+"DTOs("+id002+"Service.findAll()));\n" +
                            "    }\n" +
                            "\n" +
                            "    @PostMapping\n" +
                            "    public ResponseEntity<"+id002+"DTO> create(@RequestBody "+id002+"DTO "+id002+"DTO) {\n" +
                            "        "+id002+"Service.save("+id002+"Mapper.to"+id002+"("+id002+"DTO));\n" +
                            "\n" +
                            "        return ResponseEntity.status(HttpStatus.CREATED).body("+id002+"DTO);\n" +
                            "    }\n" +
                    "    \n" +
                    "      \n" +
                    "}");
            myWriter.close();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
