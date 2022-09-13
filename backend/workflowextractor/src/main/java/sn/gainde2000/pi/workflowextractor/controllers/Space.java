package sn.gainde2000.pi.workflowextractor.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/*")

public class Space {

    @CrossOrigin(origins = "*")
    @GetMapping({"/getspace"})
    @ResponseBody
    public String SpaceList() {
        RestTemplate restTemplate2 = new RestTemplate();

        // create auth credentials
        String authStr2 = "wbadmin:wbadmin";
        String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
        // create headers
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Basic " + base64Credits2);
        headers2.add("content-type", "application/json");
        // create request
        HttpEntity request2 = new HttpEntity(headers2);
        String url2 = "http://10.3.20.62:8080/business-central/rest/controller/management/servers/";
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers2.set("X-Request-Source", "Desktop");
        ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class);

        String data = response2.getBody();
        JSONObject json = new JSONObject(data);
        JSONArray jsonArr = new JSONArray(json.get("server-template").toString());
        JSONObject jsonObj = jsonArr.getJSONObject(0);
        JSONArray jsonArr1 = new JSONArray(jsonObj.get("container-specs").toString());
        return jsonArr1.toString(0);

    }
}



