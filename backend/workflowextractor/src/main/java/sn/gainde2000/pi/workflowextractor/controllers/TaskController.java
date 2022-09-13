package sn.gainde2000.pi.workflowextractor.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.xml.sax.SAXException;
import sn.gainde2000.pi.workflowextractor.models.Task;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

@RestController
public class TaskController {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	    //creation des composants elle prend en parametre le nom du projet, le processe Id et le nom du projet


		@CrossOrigin(origins = "*")
	   @GetMapping("/component/{containerId}")
	public String createElement(@PathVariable("containerId")String containerId) throws IOException {

		Task t = new Task();
		Utilitaire uti = new Utilitaire();
		String processId = uti.getProcessId(containerId);
		String nameProjet = uti.getNameProjet(containerId);
		t.createProjet(nameProjet);
		//String param="container";
		File f = new File (System.getProperty("user.dir")+"/"+nameProjet);
		if (f.isDirectory()){
			RestTemplate restTemplate2 = new RestTemplate();

			// create auth credentials
			String authStr2 = "wbadmin:wbadmin";
			String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
			// create headers
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Basic " + base64Credits2);
			// create request
			HttpEntity request2 = new HttpEntity(headers2);
			String url2 = "http://10.3.20.62:8082/kie-server/services/rest/server/containers/{GLOBAL_CID}/processes/definitions/{GLOBAL_PID}/entities";
			headers2.setContentType(MediaType.APPLICATION_XML);
			headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
			headers2.set("X-Request-Source", "Desktop");
			ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId, processId);

			JSONObject xmlJSONObj1 = XML.toJSONObject(response2.getBody());
			String jsonentities = xmlJSONObj1.toString(PRETTY_PRINT_INDENT_FACTOR);
			String json = jsonentities;
			JSONObject jsonObject = new JSONObject(json);
			JSONObject merchantData = (JSONObject) jsonObject.get("process-associated-entities");
			JSONObject merchantData01 = (JSONObject) merchantData.get("associated-entities");
			JSONArray recs001 = merchantData01.getJSONArray("entry");
			for (int h = 0; h < recs001.length(); ++h) {
				JSONObject rec001 = recs001.getJSONObject(h);
				String id002 = rec001.getString("key");

				System.out.println("Mon répertoire existe");
				//t.updateService("task");
				boolean trouve=t.ping(id002.toLowerCase(),nameProjet);
				if(trouve) {
					boolean servie= t.createService(id002,nameProjet);
					if(servie) {
						//t.createComponent(param);
						t.generateurForm(containerId,processId,id002.toLowerCase(),nameProjet);
						t.updateService(id002.toLowerCase(),nameProjet);
						//return "sucess pour la creation du projet";
					}
					else {
						//return " not sucess le projet existe deja";
					}
				}

			}

		}else{
			return ("Mon répertoire n' existe pas mon frere");

		}
		return "Les composants ont ete bien crees";




	}

}
