package sn.gainde2000.pi.workflowextractor.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/*")
public class CreateFold {
    @Autowired
    Environment environment;

    private XPathExpression expr;
    public static String GLOBAL_PID = "";
    public static String GLOBAL_CID = "";
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;



    @CrossOrigin(origins = "*")
    @GetMapping("/component/{packagejbpm}/{containerId}")
    public String generateApp(@PathVariable("packagejbpm")String packagejbpm,@PathVariable("containerId")String containerId) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        this.CreateControllersAndServices(containerId, packagejbpm);
         this.CreateListModels(containerId, packagejbpm);
     TaskController tc = new TaskController();
      tc.createElement(containerId);

        return "ok";
    }

    @GetMapping({"/creation/param", "/creation/param/{packagejbpm}/{containers}"})
    @ResponseBody
    public Object GenerateBusinessApplication(@PathVariable(required = false) String containers, @PathVariable(required = false) String packagejbpm ) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
         this.CreateControllersAndServices(containers, packagejbpm);
        return this.CreateListModels(containers, packagejbpm);
    }

     public Object CreateControllersAndServices(@PathVariable(required = false) String containers, @PathVariable(required = false) String packagejbpm ) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        Utilitaire utilitaire = new Utilitaire();
        String artfactid = environment.getProperty("gaindeapp.artfactid");
        String folderName = artfactid.replace(".","\\")+"\\"+packagejbpm;
        ////System.out.println(artfactid);
        String folder = environment.getProperty("gaindeApp.folder");
        File pack = new File(folder + "" + packagejbpm + "");
        pack.mkdirs();
         File packdto = new File(folder + "" +folderName+"\\DTO");
         packdto.mkdirs();
        File packcontroller = new File(folder + "" +folderName+"\\Controllers");
        packcontroller.mkdirs();
        File packservices = new File(folder + "" +folderName+"\\Services");
        packservices.mkdirs();
        File f = new File(folder + "" + folderName + "");
       // //System.out.println("foldername : " + folderName);
        if (f.exists() && f.isDirectory()) {
            File nouveaupath = f;
           // //System.out.println("File : " + nouveaupath);

            RestTemplate restTemplate = new RestTemplate();
            // create auth credentials
            String authStr = "wbadmin:wbadmin";
            String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Credits);
            // create request
            HttpEntity request = new HttpEntity(headers);
            // request url
            String url1 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{containers}/processes?page=0&pageSize=10&sortOrder=true";
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers.set("X-Request-Source", "Desktop");
            ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class, containers);

            String xml = response1.getBody();
            //System.out.println("Request Successful.");
            InputSource inputXML1 = new InputSource(new StringReader(xml));
            XPath xPath1 = XPathFactory.newInstance().newXPath();
            InputSource inputXML2 = new InputSource(new StringReader(xml));
            XPath xPath2 = XPathFactory.newInstance().newXPath();
            GLOBAL_PID = xPath1.evaluate("/process-definitions/processes/process-id", inputXML1);
            GLOBAL_CID = xPath2.evaluate("/process-definitions/processes/container-id", inputXML2);
            ////System.out.println(GLOBAL_PID);
           // //System.out.println(GLOBAL_CID);

            RestTemplate restTemplate2 = new RestTemplate();
            // create auth credentials
            String authStr2 = "wbadmin:wbadmin";
            String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
            // create headers
            HttpHeaders headers2 = new HttpHeaders();
            headers2.add("Authorization", "Basic " + base64Credits2);
            // create request
            HttpEntity request2 = new HttpEntity(headers2);
            //String url2 = "http://127.0.0.1:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes/definitions/{GLOBAL_PID}/entities";
            String url2 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/forms/processes/{GLOBAL_PID}?lang=en&type=ANY&marshallContent=true";
            headers2.setContentType(MediaType.APPLICATION_XML);
            headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers2.set("X-Request-Source", "Desktop");
            ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class, GLOBAL_CID, GLOBAL_PID);
            try {
                JSONObject xmlJSONObj = XML.toJSONObject(response2.getBody());
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);

               // //System.out.println(jsonPrettyPrintString);
                JSONObject req = new JSONObject(jsonPrettyPrintString);
                JSONArray recs = req.getJSONArray("array");
                for (int i = 0; i < recs.length(); ++i) {

                    JSONObject obj1 = recs.getJSONObject(i);
                    JSONObject model = obj1.getJSONObject("model");
                    //System.out.println(String.format("model => %s", model.getString("formModelType")));
                    if (model.getString("formModelType").equals("org.kie.workbench.common.forms.data.modeller.model.DataObjectFormModel")){
                        RestTemplate restTemplate3 = new RestTemplate();
                        // create auth credentials
                        String authStr3 = "wbadmin:wbadmin";
                        String base64Credits3 = Base64.getEncoder().encodeToString(authStr3.getBytes());
                        // create headers
                        HttpHeaders headers3 = new HttpHeaders();
                        headers2.add("Authorization", "Basic " + base64Credits3);
                        // create request
                        HttpEntity request3 = new HttpEntity(headers2);
                        String str =  packagejbpm.toLowerCase();
                        //System.out.println(packagejbpm);
                        //System.out.println(str);

                        String url3 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes/definitions/{GLOBAL_PID}/tasks/users";
                        headers3.setContentType(MediaType.APPLICATION_XML);
                        headers3.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
                        headers3.set("X-Request-Source", "Desktop");
                        ResponseEntity<String> response3 = restTemplate3.exchange(url3, HttpMethod.GET, request3, String.class, GLOBAL_CID, GLOBAL_PID);

                        JSONObject xmlJSONObj1 = XML.toJSONObject(response3.getBody());
                        String jsonentities = xmlJSONObj1.toString(PRETTY_PRINT_INDENT_FACTOR);
                        //System.out.println(jsonentities);
                        String json = jsonentities;

                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject merchantData = (JSONObject) jsonObject.get("user-task-definitions");
                        JSONArray recs001 = merchantData.getJSONArray("task");
                        utilitaire.generateServiceAndController(recs001,nouveaupath,artfactid,packagejbpm);


                    }else if(model.getString("formModelType").equals("org.kie.workbench.common.forms.jbpm.model.authoring.process.BusinessProcessFormModel")){

                        //System.out.println("blank");

                    }




                }


            } catch (JSONException je) {
                //System.out.println(je.toString());
            }

            //return response2.getBody();

        }
        return "Vos package : " + packagejbpm + " sont pret";
    }

    public Object CreateListModels(String containers, String packagejbpm ) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        String artfactid = environment.getProperty("gaindeapp.artfactid");
        String folder = environment.getProperty("gaindeApp.folder");
        String folderNameModel = artfactid.replace(".","\\")+"\\"+packagejbpm;
        File pack = new File(folder + "" + packagejbpm + "");
        pack.mkdirs();
        File packmodel = new File(folder + "" + folderNameModel + "\\Models");
        packmodel.mkdirs();
        File f = new File(folder + "" + folderNameModel + "");
        if (f.exists() && f.isDirectory()) {
            File nouveaupath = f;
         //   //System.out.println("File : " + nouveaupath);

            RestTemplate restTemplate = new RestTemplate();
            // create auth credentials
            String authStr = "wbadmin:wbadmin";
            String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Credits);
            // create request
            HttpEntity request = new HttpEntity(headers);
            // request url
            String url1 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{containers}/processes?page=0&pageSize=10&sortOrder=true";
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers.set("X-Request-Source", "Desktop");
            ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class, containers);

            String xml = response1.getBody();
           // //System.out.println("Request Successful.");
            InputSource inputXML1 = new InputSource(new StringReader(xml));
            XPath xPath1 = XPathFactory.newInstance().newXPath();
            InputSource inputXML2 = new InputSource(new StringReader(xml));
            XPath xPath2 = XPathFactory.newInstance().newXPath();
            GLOBAL_PID = xPath1.evaluate("/process-definitions/processes/process-id", inputXML1);
            GLOBAL_CID = xPath2.evaluate("/process-definitions/processes/container-id", inputXML2);
            ////System.out.println(GLOBAL_PID);
            ////System.out.println(GLOBAL_CID);

            RestTemplate restTemplate2 = new RestTemplate();
            // create auth credentials
            String authStr2 = "wbadmin:wbadmin";
            String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
            // create headers
            HttpHeaders headers2 = new HttpHeaders();
            headers2.add("Authorization", "Basic " + base64Credits2);
            // create request
            HttpEntity request2 = new HttpEntity(headers2);
            //String url2 = "http://127.0.0.1:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes/definitions/{GLOBAL_PID}/entities";
            String url2 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/forms/processes/{GLOBAL_PID}?lang=en&type=ANY&marshallContent=true";
            headers2.setContentType(MediaType.APPLICATION_XML);
            headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers2.set("X-Request-Source", "Desktop");
            ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class, GLOBAL_CID, GLOBAL_PID);

            try {
                JSONObject xmlJSONObj = XML.toJSONObject(response2.getBody());
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);

                ////System.out.println(jsonPrettyPrintString);
                JSONObject req = new JSONObject(jsonPrettyPrintString);
                JSONArray recs = req.getJSONArray("array");
                for (int i = 0; i < recs.length(); ++i) {

                    JSONObject obj1 = recs.getJSONObject(i);
                    JSONObject model = obj1.getJSONObject("model");
                    //t.println(String.format("model => %s", model.getString("formModelType")));
                    if (model.getString("formModelType").equals("org.kie.workbench.common.forms.data.modeller.model.DataObjectFormModel")){
                        RestTemplate restTemplate3 = new RestTemplate();
                        // create auth credentials
                        String authStr3 = "wbadmin:wbadmin";
                        String base64Credits3 = Base64.getEncoder().encodeToString(authStr3.getBytes());
                        // create headers
                        HttpHeaders headers3 = new HttpHeaders();
                        headers2.add("Authorization", "Basic " + base64Credits3);
                        // create request
                        HttpEntity request3 = new HttpEntity(headers2);
                        String str =  packagejbpm.toLowerCase();
                       // //System.out.println(packagejbpm);
                        ////System.out.println(str);
                        /*+++++++++++++++++++++++++++++extract entities debut*/
                        String url3 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes/definitions/{GLOBAL_PID}/variables";

                        headers3.setContentType(MediaType.APPLICATION_JSON);
                        headers3.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                        headers3.set("X-Request-Source", "Desktop");
                        ResponseEntity<String> response3 = restTemplate3.exchange(url3, HttpMethod.GET, request3, String.class, GLOBAL_CID, GLOBAL_PID);

                        JSONObject xmlJSONObj1 = XML.toJSONObject(response3.getBody());
                        String jsonentities = xmlJSONObj1.toString(PRETTY_PRINT_INDENT_FACTOR);
                        ////System.out.println(" Test : "+jsonentities);
                        JSONObject jsonObject = new JSONObject(jsonentities);
                        JSONObject merchantData = (JSONObject) jsonObject.get("process-variables");
                        JSONObject merchantData01 = (JSONObject) merchantData.get("variables");
                        JSONArray recst001 = merchantData01.getJSONArray("entry");
                        JSONArray recst0012 = new JSONArray();
                        for (int h = 0; h < recst001.length(); ++h) {
                            JSONObject val = recst001.getJSONObject(h);

                            //val.remove("key");
                            ////System.out.println(val);
                           String val02 = val.getString("value");
                            recst0012.put(val02);
                            ////System.out.println(val02);


                        }

                        List<Object> list = recst0012.toList();
                        List<Object> listfilter = list.stream().distinct().collect(Collectors.toList());
                       // //System.out.println(listfilter);
                        for (Object s: listfilter) {
                            //Do your stuff here
                             String rs = (String) s;
                             String rs1 = rs.replace(".","/");
                           // //System.out.println(rs1);
                            String targetString = rs1;
                            int lastSlashIndex = targetString.lastIndexOf('/');
                            String everythingAfterTheFinalSlash = targetString.substring(lastSlashIndex + 1);
                            ////System.out.println(everythingAfterTheFinalSlash);
                            File dirModels = new File(nouveaupath + "\\Models");
                            File modelTask = new File(dirModels, "" + everythingAfterTheFinalSlash + "Model.java");
                            modelTask.createNewFile();

                            String url10 = "http://10.3.20.62:8080/business-central/org.kie.bc.KIEWebapp/defaulteditor/download?path=default://master@MySpace/"+packagejbpm+"/src/main/java/com/myspace/"+packagejbpm.toLowerCase()+"/"+everythingAfterTheFinalSlash+".java";

                            headers3.setContentType(MediaType.APPLICATION_XML);
                            headers3.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
                            headers3.set("X-Request-Source", "Desktop");
                            ResponseEntity<String> response10 = restTemplate3.exchange(url10, HttpMethod.GET, request3, String.class, GLOBAL_CID, GLOBAL_PID);
                            ////System.out.println(response10.getBody());
                            FileWriter myWritermodel = new FileWriter(dirModels+"\\" + everythingAfterTheFinalSlash + ".java");
                            myWritermodel.write(response10.getBody());
                            myWritermodel.close();

                        }

                    }else if(model.getString("formModelType").equals("org.kie.workbench.common.forms.jbpm.model.authoring.process.BusinessProcessFormModel")){
                        //System.out.println("Blank");
                    }




                }


            } catch (JSONException je) {
                //System.out.println(je.toString());
            }

            //return response2.getBody();

        }
        return "Vos package : " + packagejbpm + " sont pret";
    }

}


