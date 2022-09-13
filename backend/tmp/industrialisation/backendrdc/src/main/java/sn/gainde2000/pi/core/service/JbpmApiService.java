package sn.gainde2000.pi.core.service;

import org.apache.tomcat.websocket.server.UriTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;
import sn.gainde2000.pi.core.entity.SecteurItem;
import sn.gainde2000.pi.core.entity.Workflow;
import sn.gainde2000.pi.core.entity.WorkflowItem;
//import sun.jvm.hotspot.utilities.Assert;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.config.AppProperties;
import sn.gainde2000.pi.core.entity.WorkflowSecteur;


@Service
public class JbpmApiService {
      
    @Autowired
    private AppProperties app;
    public Object RecupSpace() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        // create auth credentials
        String authStr = "wbadmin:wbadmin";
        System.out.println("-------------------Jbpmserver-----------------------"+app.getJbpmserver());
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);
        // create request
        HttpEntity request = new HttpEntity(headers);
        // request url
        String url1 = app.getJbpmserver()+"/business-central/rest/spaces/";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class);

        String json = response1.getBody();
        System.out.println(json);
        return json;

    }
    public Object RecupOutput(String processInstanceId) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
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
        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/queries/processes/instances/{processInstanceId}/variables/instances";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class, processInstanceId);

        String json = response1.getBody();
        System.out.println(json);
        return json;

    }

    public Object RecupBpm(String containerId, String processInstanceId) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
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
        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/{containerId}/images/processes/instances/{processInstanceId}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class, containerId,processInstanceId);

        String json = response1.getBody();
        System.out.println(json);
        return json;

    }

    public Object RecupTaskAll() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {

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
        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/queries/tasks/instances/";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response0 = new RestTemplate().exchange(url1, HttpMethod.GET, request, String.class);
        String json = response0.getBody();
        /*JSONObject req = new JSONObject(json);
        JSONObject locs1 = req.getJSONObject("result");
        JSONObject locs = locs1.getJSONObject("kie-containers");
        JSONArray recs = locs.getJSONArray("kie-container");
        String containerid = "";
        for (int j = 0; j < recs.length(); j++) {
            JSONObject data2 = recs.getJSONObject(j);
            String str = data2.getString("container-id");
            containerid  =str;
        }*/

        return json;
    }
    public Object RecupTask(String user, String pass) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {

        RestTemplate restTemplate = new RestTemplate();
        // create auth credentials
        String authStr = ""+user+":"+pass+"";
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);
        // create request
        HttpEntity request = new HttpEntity(headers);
        // request url
       // http://10.3.20.62:8080/kie-server/services/rest/server/queries/tasks/instances/owners?user=joseph&page=0&sortOrder=true
        String url1 = app.getJbpmserver()+"kie-server/services/rest/server/queries/tasks/instances/owners?status=Reserved&status=InProgress&status=Completed&user={user}&page=0&pageSize=1000&sortOrder=true";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response0 = new RestTemplate().exchange(url1, HttpMethod.GET, request, String.class,user);
        String json = response0.getBody();
        /*JSONObject req = new JSONObject(json);
        JSONObject locs1 = req.getJSONObject("result");
        JSONObject locs = locs1.getJSONObject("kie-containers");
        JSONArray recs = locs.getJSONArray("kie-container");
        String containerid = "";
        for (int j = 0; j < recs.length(); j++) {
            JSONObject data2 = recs.getJSONObject(j);
            String str = data2.getString("container-id");
            containerid  =str;
        }*/
        return json;
    }

    public Object  generateurFormProcess(String containerId,String processId) throws JSONException {

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

        String url2 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/{containerId}/forms/processes/{processId}?lang=en&type=ANY&marshallContent=true";

        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers2.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId,processId);

        String data =response2.getBody();
        JSONArray jsonarray = new JSONArray(data);
        String champs = "";
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String name = jsonobject.getString("fields");
            champs = name;
        }
        return champs;

    }
    public Object  generateurFormTask(String containerId,String processId) throws JSONException {

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

        String url2 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/{containerId}/forms/tasks/{processId}?lang=en&filter=true&type=ANY&marshallContent=true";
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers2.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId,processId);

        String data =response2.getBody();
        JSONArray jsonarray = new JSONArray(data);
        String champs = "";
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String name = jsonobject.getString("fields");
            champs = name;
        }
        return champs;

    }
    public Object RecupContainer(String artifactId) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
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


        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers?artifactId={artifactId}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response0 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class,artifactId);
        String json = response0.getBody();
        JSONObject req = new JSONObject(json);
        JSONObject locs1 = req.getJSONObject("result");
        JSONObject locs = locs1.getJSONObject("kie-containers");
        JSONArray recs = locs.getJSONArray("kie-container");
        String containerid = "";
        for (int j = 0; j < recs.length(); j++) {
            JSONObject data2 = recs.getJSONObject(j);
            String str = data2.getString("container-id");
            containerid  =str;
        }

        return containerid;
    }
    public Object ListOfprocess(String containerid) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
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


        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/{containerid}/processes?page=0&pageSize=10&sortOrder=true";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class,containerid);
        String json = response1.getBody();
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("processes");
        String processid = "";
        for (int i = 0; i < arr.length(); i++) {
            String process_id = arr.getJSONObject(i).getString("process-id");
            processid = process_id;
        }
        return processid;
    }
    public Object ListOfContainer() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        RestTemplate restTemplate = new RestTemplate();       
        // create auth credentials
        String authStr = "wbadmin:wbadmin";
        System.out.println("----------------------Jbpmserver------------------"+app.getJbpmserver());
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);
        // create request
        HttpEntity request = new HttpEntity(headers);
        // request url

        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class);

        String json = response1.getBody();

        return json;
    }
    public Object ExtractAllContainerId() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
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

        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/queries/processes/definitions?page=0&pageSize=10&sortOrder=true";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class);

        String json = response1.getBody();

        return json;
    }

    public Object ExtractAllTaskInfo(String containerId,String processId ) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
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

            String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/{containerId}/processes/definitions/{processId}/entities";
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Request-Source", "Desktop");
            ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, request, String.class, containerId,processId);

            String json = response1.getBody();

        return json;
    }


        public Long ExecuteOneWorkflow(String container, String process) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        RestTemplate restTemplate = new RestTemplate();
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
            restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        // create auth credentials
        String authStr = "wbadmin:wbadmin";
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);
        // create request
        HttpEntity request = new HttpEntity(headers);
        // request url

        String url1 = app.getJbpmserver()+"/kie-server/services/rest/server/containers/"+container+"/processes/"+process+"/instances";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
            // create a map for post parameters
            Map<String, Object> map = new HashMap<>();
            map.put("containerId", container);
            map.put("processId", process);

            // build the request
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

            // send POST request
            ResponseEntity<Workflow> response1 = restTemplate.postForEntity(url1, entity, Workflow.class);

            // check response status code
            if (response1.getStatusCode() == HttpStatus.CREATED) {
                Long idp = response1.getBody().getWkfId();
                return idp;
            } else {
                return null;
            }


    }

    public Object ReclameOneTask(String container,String owner,String pass, Integer idprocess) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        // create auth credentials
        String authStr = ""+owner+":"+pass+"";
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);

        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(app.getJbpmserver()+"/kie-server/services/rest/server/containers/{container}/tasks/{idprocess}/states/started?user={owner}").build()
                        .expand(container, idprocess,owner)
                        .encode();
        URI uri = uriComponents.toUri();
        System.out.println("++++++++++++++++++++++++++++++++++++++"+uri);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        HttpEntity<String> entity = new HttpEntity<String>(owner.toString(), headers);

        //ResponseEntity<String> response =  restTemplate.postForEntity(uri, entity, String.class);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
    }
    public Object ExecuteOneTask(String container,String owner,String pass, Integer idprocess, Object champrocess) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        // create auth credentials
        String authStr = ""+owner+":"+pass+"";
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);

        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(app.getJbpmserver()+"/kie-server/services/rest/server/containers/{container}/tasks/{idprocess}/states/completed?user={owner}&auto-progress=true").build()
                        .expand(container, idprocess,owner)
                        .encode();
        URI uri = uriComponents.toUri();
        System.out.println("++++++++++++++++++++++++++++++++++++++"+uri);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        HttpEntity<String> entity = new HttpEntity<String>(champrocess.toString(), headers);

        //ResponseEntity<String> response =  restTemplate.postForEntity(uri, entity, String.class);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public Object AddInputProcess(String container, Long idprocess, Object champrocess) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        // create auth credentials
        String authStr = "wbadmin:wbadmin";
        String base64Credits = Base64.getEncoder().encodeToString(authStr.getBytes());
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credits);

        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(app.getJbpmserver()+"/kie-server/services/rest/server/containers/{container}/processes/instances/{idprocess}/variables").build()
                        .expand(container, idprocess)
                        .encode();

        URI uri = uriComponents.toUri();
        System.out.println(uri);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        HttpEntity<String> entity = new HttpEntity<String>(champrocess.toString(), headers);

        ResponseEntity<String> response =  restTemplate.postForEntity(uri, entity, String.class);
        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
    }


    public Object CreateOneSpace(SecteurItem secteurItem) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
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

        String url1 = app.getJbpmserver()+"/business-central/rest/spaces/";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        // create a map for post parameters

        // create a post object
        SecteurItem wspace = new SecteurItem(secteurItem.getName(),"wbadmin");

        // build the request
        HttpEntity<SecteurItem> entity = new HttpEntity<>(wspace, headers);
        // send POST request
        ResponseEntity<SecteurItem> response =  restTemplate.postForEntity(url1, entity, SecteurItem.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("+++++++++++++++++++++++++++++++++++"+wspace);
            return response.getBody();
        } else {
            return null;
        }

    }

    public Object CreateOneWorkflow(WorkflowItem workflowItem) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
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

        String url1 = app.getJbpmserver()+"/business-central/rest/spaces/MySpace/projects/";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        // create a map for post parameters

        // create a post object
        WorkflowItem wpost = new WorkflowItem(workflowItem.getName(), "sn.gainde2000.pi","1.0.0-SNAPSHOT",workflowItem.getDescription());

        // build the request
        HttpEntity<WorkflowItem> entity = new HttpEntity<>(wpost, headers);
        // send POST request
        ResponseEntity<WorkflowItem> response =  restTemplate.postForEntity(url1, entity, WorkflowItem.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }
        // create a post object
       /* WorkflowItem wpost = new WorkflowItem(name, groupId, version, description);
        // build the request
        HttpEntity<WorkflowItem> entity = new HttpEntity<>(wpost, headers);
        // send PUT request to update post with `id` 10

        ResponseEntity<WorkflowItem> response =  restTemplate.exchange(url1, HttpMethod.POST, entity, WorkflowItem.class);
        return response.getBody() ;*/



    }



}
