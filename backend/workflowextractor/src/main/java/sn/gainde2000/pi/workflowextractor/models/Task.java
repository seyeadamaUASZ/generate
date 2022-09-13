package sn.gainde2000.pi.workflowextractor.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Task {
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	//creation du projet 
	public boolean createProjet(String name){
		int exitValue=1;
		/* Création et lancement de processus qui fait la creation */
		
		try {
			 Process process = new ProcessBuilder("cmd", "/C",
					  System.getProperty("user.dir")+"/createprojet.bat "+name)
					  .inheritIO().start();

			exitValue=process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	 
	public void createComponent(String taskName,String nomProjet) throws IOException {
		   String rep = System.getProperty("user.dir") ; 
		  try {
		  File file = new File(System.getProperty("user.dir")+"/"+nomProjet+"/src/app/"+taskName);
		  //file.createNewFile(); file.mkdir();
		  
		  FileWriter myWriter = new FileWriter(
		  System.getProperty("user.dir")+"/"+nomProjet+"/src/app/"+taskName+"/"+taskName+
		  ".component.html");
		  myWriter.write("   <div>"+taskName+"</div>");
		  myWriter.close();
		  System.out.println("Successfully wrote to the file."); }
		  catch (IOException e) {
			  System.out.println("An error occurred.");
			  e.printStackTrace(); 
			}
	}
	
	public boolean createService(String name, String nomProjet) throws IOException {
		
		int exitValue=1;
		
		try {
			Process process = new ProcessBuilder("cmd", "/C", System.getProperty("user.dir")+"/service.bat "+name+" "+nomProjet)
					.inheritIO().start();
			exitValue=process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	
	public boolean ping(String name,String nomProjet){
		int exitValue=1;
		/* Création et lancement de processus qui fait le ping */
		try {
			Process process = new ProcessBuilder("cmd", "/C", System.getProperty("user.dir")+"/component.bat "+name+" "+nomProjet).inheritIO().start();
			exitValue=process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	//update service
	public void updateService(String taskName,String nomProjet) {
		 String rep = System.getProperty("user.dir") ; 
		  try {
		 
		  
		  FileWriter myWriter = new FileWriter(
		  System.getProperty("user.dir")+"/"+nomProjet+"/src/app/service/"+taskName+
		  ".service.ts");
		  myWriter.write("import { Injectable } from '@angular/core';\r\n" + 
		  		"\r\n" + 
		  		"@Injectable({\r\n" + 
		  		"  providedIn: 'root'\r\n" + 
		  		"})\r\n" + 
		  		"export class "+taskName+"Service {\r\n" + 
		  		"\r\n" + 
		  		"  constructor(private http:HttpClient) { \r\n" + 
		  		"  }\r\n" + 
		  		"  \r\n" + 
		  		"  "+taskName+"(data){\r\n" + 
		  		"       return htt.post(this.url/"+taskName+",data)\r\n" + 
		  		"  }\r\n" + 
		  		"  getAll(){\r\n" + 
		  		"        return htt.get(this.url)\r\n" + 
		  		"  } \r\n" + 
		  		"}\r\n" + 
		  		"");
		  myWriter.close();
		  System.out.println("Successfully wrote to the file."); }
		  catch (IOException e) {
			  System.out.println("An error occurred.");
			  e.printStackTrace(); 
			}
	}
	
	//generateur de formulaire

	public void  generateurForm(String containerId,String processId,String taskName,String nomProjet) {

		RestTemplate restTemplate2 = new RestTemplate();

		// create auth credentials
		String authStr2 = "wbadmin:wbadmin";
		String base64Credits2 = Base64.getEncoder().encodeToString(authStr2.getBytes());
		// create headers
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Basic " + base64Credits2);
		// create request
		HttpEntity request2 = new HttpEntity(headers2);
		String url2 = "http://10.3.20.62:8080/kie-server/services/rest/server/containers/{GLOBAL_CID}/forms/processes/{GLOBAL_PID}?lang=en&type=ANY&marshallContent=true";
		headers2.setContentType(MediaType.APPLICATION_XML);
		headers2.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		headers2.set("X-Request-Source", "Desktop");
		ResponseEntity<String> response2 = restTemplate2.exchange(url2, HttpMethod.GET, request2, String.class,containerId, processId);
		JSONObject xmlJSONObj1 = XML.toJSONObject(response2.getBody());
		String jsonPrettyPrintString = xmlJSONObj1.toString(PRETTY_PRINT_INDENT_FACTOR);

		//System.out.println(jsonPrettyPrintString);
		JSONObject req = new JSONObject(jsonPrettyPrintString);
		JSONArray recs = req.getJSONArray("array");

		JSONObject jsonObj = recs.getJSONObject(0);
		System.out.println(jsonObj.get("fields"));
		JSONArray jsonfields = new JSONArray(jsonObj.get("fields").toString());
		//System.out.println(jsonfields);

		String rep = System.getProperty("user.dir") ;
		try {
			File file = new File(System.getProperty("user.dir")+"/"+nomProjet+"/src/app/"+taskName);
			//file.createNewFile(); file.mkdir();

			FileWriter myWriter = new FileWriter(
					System.getProperty("user.dir")+"/"+nomProjet+"/src/app/"+taskName+"/"+taskName+
							".component.html");
			myWriter.write("<div>");
			for (int i = 0; i < jsonfields.length(); i++)
			{
				JSONObject jsonObjectField = jsonfields.getJSONObject(i);

				System.out.println(jsonObjectField);

				myWriter.write("<input type="+"\""+jsonObjectField.getString("code")+"\""+" name="+"\""+jsonObjectField.getString("name")+"\""+" ([ngModel])="+"\""+jsonObjectField.getString("name")+"\""+">");

			}
			myWriter.write("<button type='submit'>Valider</button></div>");
			myWriter.close();
			//update component
			this.updateComponent(taskName,nomProjet);

			System.out.println("Successfully wrote to the file."); }
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
    public void updateComponent(String taskName,String nomProjet) {
    	String rep = System.getProperty("user.dir") ; 
		  try {
		 
		  
		  FileWriter myWriter = new FileWriter(
		 System.getProperty("user.dir")+"/"+nomProjet+"/src/app/"+taskName+"/"+taskName+
				  ".component.ts");
		  myWriter.write("import { Component, OnInit } from '@angular/core';\r\n" + 
		  		"\r\n" + 
		  		"@Component({\r\n" + 
		  		"  selector: 'app-process',\r\n" + 
		  		"  templateUrl: './"+taskName+".component.html',\r\n" + 
		  		"  styleUrls: ['./"+taskName+".component.css']\r\n" + 
		  		"})\r\n" + 
		  		"export class "+taskName+"Component implements OnInit {\r\n" + 
		  		"\r\n" + 
		  		"  constructor(private service:"+taskName+"Service) { }\r\n" + 
		  		"\r\n" + 
		  		"  ngOnInit(): void {\r\n" + 
		  		"  }\r\n" + 
		  		"   "+taskName+"(){\r\n" + 
		  		"      this.service."+taskName+"(value).subscibe(res=>{\r\n" + 
		  		"         \r\n" + 
		  		"      })\r\n" + 
		  		"   }\r\n" + 
		  		"   \r\n" + 
		  		"   getAll(){\r\n" + 
		  		"      this.service.getAll().subscibe(res=>{\r\n" + 
		  		"         \r\n" + 
		  		"      })\r\n" + 
		  		"   }\r\n" + 
		  		"}\r\n" + 
		  		"");
		  myWriter.close();
		  System.out.println("Successfully wrote to the file."); }
		  catch (IOException e) {
			  System.out.println("An error occurred.");
			  e.printStackTrace(); 
			}
    }
}
