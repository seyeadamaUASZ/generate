package sn.gainde2000.pi.core.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import java.io.IOException;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sn.gainde2000.pi.config.AppProperties;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse; 
import sn.gainde2000.pi.core.entity.JbpmFormInfos;
import sn.gainde2000.pi.core.entity.Task; 
import sn.gainde2000.pi.core.service.IJbpmUserService;
import sn.gainde2000.pi.core.service.IWorkflowService;
import sn.gainde2000.pi.core.service.IworkflowSecteur;
import sn.gainde2000.pi.core.service.JbpmApiService;
import sn.gainde2000.pi.core.service.impl.JbpmFormInfosImpl;

import com.google.gson.JsonElement;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.core.entity.JbpmFormInfos;
import sn.gainde2000.pi.core.entity.Task; 
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import java.util.Random;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.*;
import sn.gainde2000.pi.core.service.IJbpmUserService;
import sn.gainde2000.pi.core.service.IWorkflowService;
import sn.gainde2000.pi.core.service.IworkflowSecteur;
import sn.gainde2000.pi.core.service.JbpmApiService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
<<<<<<< HEAD
import org.apache.tomcat.util.json.JSONParser;
import org.aspectj.weaver.ast.Test;
import org.json.JSONArray;
=======
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import sn.gainde2000.pi.config.AppProperties;
import sn.gainde2000.pi.core.service.impl.JbpmFormInfosImpl;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 2a8b8243cda7dac57b35222ea8b77b41216b6658
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException; 
<<<<<<< HEAD
import sn.gainde2000.pi.core.service.impl.TaskServiceImpl; 
import sn.gainde2000.pi.core.service.ITaskService; 

=======
=======
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
>>>>>>> 2a8b8243cda7dac57b35222ea8b77b41216b6658
import sn.gainde2000.pi.core.service.impl.TaskServiceImpl; 
import sn.gainde2000.pi.core.service.ITaskService; 
import sn.gainde2000.pi.core.service.IProfile;
import sn.gainde2000.pi.core.service.ITaskStatusService;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

@RestController

public class WorkflowController {
    @Autowired
     ITaskService taskService;
<<<<<<< HEAD
=======
    @Autowired
     ITaskStatusService taskStatusService;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
     @Autowired
     IWorkflowService workflowService;
     @Autowired
     JbpmFormInfosImpl jbpmFormInfosService;    
     @Autowired
     TaskServiceImpl taskservice;    
     @Autowired
     IworkflowSecteur workflowSecteurService;
     @Autowired
     JbpmApiService jbpmApiService;
     @Autowired
     IJbpmUserService jbpmUserService;
<<<<<<< HEAD
=======
     @Autowired
    IProfile profileService;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    @Autowired
    private AppProperties app;
    /**
     * Recuperation de la liste des container
     * @param containerId
     * @return
     */
     
    @GetMapping("workflowtaskprofil/{containerId}")
     public ServeurResponse GetWorkflowTaskProfil(@PathVariable String containerId) {
          ServeurResponse response = new ServeurResponse();
           Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosBycontainer(containerId);
          // System.out.println(jbpmFormInfos);
          response.setData(jbpmFormInfos);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
    @GetMapping("listentitities/{containerId}/{processId}")
     public ServeurResponse GetEntitiesWorkflow(@PathVariable String containerId) {
          ServeurResponse response = new ServeurResponse();
           Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosBycontainer(containerId);
          // System.out.println(jbpmFormInfos);
          response.setData(jbpmFormInfos);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
     
      @GetMapping("formulaireworkflow/{containerId}")
     public ServeurResponse GetFormulaireWorkflow(@PathVariable String containerId) {
          ServeurResponse response = new ServeurResponse();
           Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosBycontainer(containerId);
          // System.out.println(jbpmFormInfos);
          response.setData(jbpmFormInfos);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
      @GetMapping("formulaireworkflowparid/{jfrmId}")
     public ServeurResponse GetFormulaireWorkflowbyId(@PathVariable Long jfrmId) {
          ServeurResponse response = new ServeurResponse();
           Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosById(jfrmId);
           //System.out.println(jbpmFormInfos);
          response.setData(jbpmFormInfos);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
     @GetMapping("downloadfrmfile/{frmfileid}")
   public ResponseEntity<Resource> downloadFrmFile(HttpServletResponse response,@PathVariable String frmfileid) throws IOException {
                String frmfilename = frmfileid.replace(app.getJbpmDirForm(),"");
                //System.out.println(app.getJbpmDirFile()+jbpmfileid+"/");
        /*File file = new File(app.getJbpmDirFile()+jbpmfileid+"/");
        File[] files = file.listFiles();
        for(File f: files){
            jbpmfilename = f.getName();
            //System.out.println("++++++++++++++++++++++++++++++"+f.getName());
        }
        */
      
            File file2 = new File(app.getJbpmDirForm()+frmfileid);
            
            String extension = ""; 
        int i = frmfilename.lastIndexOf('.');
            if (i > 0) {
              extension = frmfilename.substring(i+1);
            }
             HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+frmfilename);
             headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
            //System.out.print(file2.getName());
            Path path = Paths.get(frmfileid);
          //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
          InputStreamResource resource = new InputStreamResource(new FileInputStream(app.getJbpmDirForm()+frmfileid));
          
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file2.length())
                    .contentType(MediaType.parseMediaType("application/"+extension+""))
                    .body(resource);
                
	
    }
      @GetMapping("deletewrkflform/{jfrmId}")
     public ServeurResponse deleteWrkflForm(@PathVariable Long jfrmId) {
          ServeurResponse response = new ServeurResponse();
             jbpmFormInfosService.delete(jfrmId);
           //System.out.println(jbpmFormInfos);
          response.setStatut(true);
        return response;
     }
     
     @RequestMapping(value = "chargerjbpmform", method = RequestMethod.POST)
    @ResponseBody
    public Object chargerjbpmform(@RequestParam("workflowform") String workflowform,@RequestParam("containerId") String containerId, @RequestParam("idwrkf") Long idwrkf, @RequestPart("fichierform") MultipartFile fichierform ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
       ServeurResponse response = new ServeurResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        
       Random rand = new Random(); //instance of random class
      int upperbound = 99999;
        //generate random values from 0-24
      int int_random = rand.nextInt(upperbound); 
                //System.out.println(donneedocu);
               // System.out.println(System.getProperty("user.dir")+"/opt/formulaire/");  
                 Files.copy(fichierform.getInputStream(), Paths.get(app.getJbpmDirForm()+ int_random+"_"+fichierform.getOriginalFilename()));
 
            JSONObject obj = new JSONObject(workflowform);
            
            String urlform = app.getJbpmDirForm()+""+int_random+"_"+fichierform.getOriginalFilename();
            String nomform = "";
             nomform = (String) obj.get("nomform").toString();
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");
          JbpmFormInfos FormInfos = new JbpmFormInfos();
          FormInfos.setJfrmFormulaire(nomform);
          FormInfos.setJfrmWorkflow(containerId);
          FormInfos.setJfrmUrlfile(urlform);
          FormInfos.setJfrmJfrmPrimaire(positionform);
          FormInfos.setStatusFrmWorkflow(StatusFrmWorkflow.NOGENERER);

          FormInfos.setJfrmIdworkflow(idwrkf);

          response.setData(jbpmFormInfosService.save(FormInfos));
          response.setStatut(true);
          response.setDescription("Nom du formulaire");
         
        return FormInfos;
    }
     
    @GetMapping("listetaskprofil")
     public ServeurResponse GetlisteTaskProfil() {
          ServeurResponse response = new ServeurResponse(); 
           Iterable<Profil> Profil = profileService.getAllProfile();
          response.setData(Profil);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
     @GetMapping("listetaskparid/{idwrkf}")
     public ServeurResponse GetlisteTaskbyId(@PathVariable Long idwrkf) {
          ServeurResponse response = new ServeurResponse();
           Iterable<Task> task =  taskservice.getListTaskByTskWkfId(idwrkf); 
          response.setData(task);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
     @RequestMapping(value = "createtask", method = RequestMethod.POST)
    @ResponseBody
<<<<<<< HEAD
    public Object createTask(@RequestParam("taskgenform") String workflowform,@RequestParam("containerId") String containerId,@RequestParam("idwrkf") Long  idwrkf ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
=======
    public Object createTask(@RequestParam("taskgenform") String workflowform,@RequestParam("idwrkf") Long  idwrkf ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
       ServeurResponse response = new ServeurResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        
      
            JSONObject obj = new JSONObject(workflowform);
            String taskname = "";
            taskname = (String) obj.get("taskname").toString();
<<<<<<< HEAD
            String Tasknamesuivant = "";
            Tasknamesuivant = (String) obj.get("Tasknamesuivant").toString();
            
            String taskdescription = "";
            taskdescription = (String) obj.get("taskdescription").toString();
            
            String ownername = "";
             ownername = (String) obj.get("ownername").toString();
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");
            
          Task task  = new Task();
           task.setTaskOwner(ownername);
           task.setTskIsFirst(positionform);
           task.setTskName(taskname); 
           task.setTskDescription(taskdescription);
           task.setTaskContainerId(containerId); 
           task.setTskWkfId(idwrkf);
           task.setTskNameSuiv(Tasknamesuivant);
           //task.getTskIsFirst(positionform);
=======
             
             
            String taskdescription = "";
            taskdescription = (String) obj.get("taskdescription").toString();
            
             Long statusId = 1L;
            String pstatusId = "";
             pstatusId = (String) obj.get("statusId").toString(); 
        Integer statusIdcast=Integer.parseInt(pstatusId);  
        statusId = Long.valueOf(statusIdcast);
            Profil owner;
            Long ownername = 1L;
            String poowner = "";
             poowner = (String) obj.get("ownername").toString(); 
        Integer ownernamecast=Integer.parseInt(poowner);  
        ownername = Long.valueOf(ownernamecast);
        owner = new Profil(ownername);
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");
            
          Task task  = new Task(); 
           task.setPoOwner(owner);
           task.setTskIsFirst(positionform);
           task.setTskName(taskname); 
           task.setTskDescription(taskdescription);  
           task.setTskStatusId(statusId); 
           //task.getTskIsFirst(positionform); 
           task.setTskWkfId(idwrkf);
           /*task.setTskTskSuivId(Tasknamesuivant); 
            task.setIdStatusBefore(statusprecedant);
             task.setIdStatusAfter(statussuivant);
           //task.getTskIsFirst(positionform);*/ 
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
           taskservice.save(task);
          response.setData(task);
          response.setStatut(true);
          response.setDescription("Nom du formulaire");
         
        return task;
    }
    
    @RequestMapping(value = "updatejbpmform", method = RequestMethod.POST)
    @ResponseBody
    public ServeurResponse updateJbpmForm(@RequestParam("workflowform") String workflowform,@RequestParam("containerId") String containerId, @RequestParam("idwrkf") Long idwrkf, @RequestPart("fichierform") MultipartFile fichierform ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
       ServeurResponse response = new ServeurResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
     JSONObject obj = new JSONObject(workflowform);
     Long jfrmId = 1L;
            jfrmId = (Long) obj.get("jfrmId");
<<<<<<< HEAD
        Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosById(jfrmId);
=======
        JbpmFormInfos jbpmFormInfos = jbpmFormInfosService.findByJfrmId(jfrmId);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        if (jbpmFormInfos == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else { 
               
             String nomform = ""; 
             nomform = (String) obj.get("nomform").toString();
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");
               Random rand = new Random(); //instance of random class
            int upperbound = 99999;
              //generate random values from 0-24
            int int_random = rand.nextInt(upperbound); 
                //System.out.println(donneedocu);
               // System.out.println(System.getProperty("user.dir")+"/opt/formulaire/");  
                 Files.copy(fichierform.getInputStream(), Paths.get(app.getJbpmDirForm()+ int_random+"_"+fichierform.getOriginalFilename()));
  
<<<<<<< HEAD
            String urlform = "/opt/formulaire/"+int_random+"_"+fichierform.getOriginalFilename();
<<<<<<< HEAD
            System.out.println("++++++++"+jbpmFormInfos);
            JbpmFormInfos FormInfos = new JbpmFormInfos();
          FormInfos.setJfrmFormulaire(nomform);
          FormInfos.setJfrmWorkflow(containerId);
          FormInfos.setJfrmUrlfile(urlform);
          FormInfos.setJfrmJfrmPrimaire(positionform);
          FormInfos.setStatusFrmWorkflow(StatusFrmWorkflow.NOGENERER); 
          FormInfos.setJfrmIdworkflow(idwrkf); 
          response.setData(jbpmFormInfosService.save(FormInfos));
=======
=======
            String urlform = app.getJbpmDirForm()+""+int_random+"_"+fichierform.getOriginalFilename();
>>>>>>> 2a8b8243cda7dac57b35222ea8b77b41216b6658
            System.out.println("++++++++"+jbpmFormInfos); 
          jbpmFormInfos.setJfrmFormulaire(nomform);
          jbpmFormInfos.setJfrmWorkflow(containerId);
          jbpmFormInfos.setJfrmUrlfile(urlform);
          jbpmFormInfos.setJfrmJfrmPrimaire(positionform);
          jbpmFormInfos.setStatusFrmWorkflow(StatusFrmWorkflow.NOGENERER); 
          jbpmFormInfos.setJfrmIdworkflow(idwrkf); 
          response.setData(jbpmFormInfosService.save(jbpmFormInfos));
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
            response.setStatut(true);
            response.setData(jbpmFormInfos);
            response.setDescription("Données récupérées.");
        }
      
         
        return response;
    }
    
    @RequestMapping(value = "updateworkflowtask", method = RequestMethod.POST)
    @ResponseBody
<<<<<<< HEAD
    public ServeurResponse updateWorkflowTask(@RequestParam("taskgenform") String taskgenform,@RequestParam("idwrktsk") Long  idwrktsk ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
=======
    public ServeurResponse updateWorkflowTask(@RequestParam("taskgenform") String taskgenform,@RequestParam("idtsk") Long  idtsk ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
       ServeurResponse response = new ServeurResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
     JSONObject obj = new JSONObject(taskgenform); 
<<<<<<< HEAD
         Iterable<Task> task =  taskservice.getListTaskByTskId(idwrktsk);
        if (task == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else { 
               String taskname = "";
            taskname = (String) obj.get("taskname").toString();
            String Tasknamesuivant = "";
            Tasknamesuivant = (String) obj.get("Tasknamesuivant").toString();
            
            String taskdescription = "";
            taskdescription = (String) obj.get("taskdescription").toString();
            
            String ownername = "";
             ownername = (String) obj.get("ownername").toString();
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");
            
            Task taskupdate  = new Task();
           taskupdate.setTaskOwner(ownername);
           taskupdate.setTskIsFirst(positionform);
           taskupdate.setTskName(taskname); 
           taskupdate.setTskDescription(taskdescription); 
           taskupdate.setTskNameSuiv(Tasknamesuivant);
           //task.getTskIsFirst(positionform);
           taskservice.save(taskupdate);
          response.setData(taskupdate);
          response.setStatut(true);
          response.setDescription("Nom du formulaire");
=======
          Task  taskupdate =  taskservice.findByTskId(idtsk);
          
        if (taskupdate != null) {
           
            String taskname = "";
            taskname = (String) obj.get("taskname").toString(); 
              
            String taskdescription = "";
            taskdescription = (String) obj.get("taskdescription").toString(); 
             Profil owner;
            Long ownername = 1L;
            String poowner = "";
             poowner = (String) obj.get("ownername").toString(); 
        Integer ownernamecast=Integer.parseInt(poowner);  
        ownername = Long.valueOf(ownernamecast);
         owner = new Profil(ownername);
            Long statusId = 1L;
            String pstatusId = "";
             pstatusId = (String) obj.get("statusId").toString(); 
        Integer statusIdcast=Integer.parseInt(pstatusId);  
        statusId = Long.valueOf(statusIdcast);
        
          
        
        Long Tasknamesuivant = 1L;
            String  tsuivant = "";
              tsuivant = (String) obj.get("Tasknamesuivant").toString(); 
        Integer  tsuivantcast=Integer.parseInt(tsuivant);  
        Tasknamesuivant = Long.valueOf(tsuivantcast);
             boolean positionform ;
             positionform = (boolean) obj.get("positionform");  
           taskupdate.setPoOwner(owner);
           taskupdate.setTskIsFirst(positionform);
           taskupdate.setTskName(taskname);  
           taskupdate.setTskDescription(taskdescription); 
           taskupdate.setTskStatusId(statusId);    
            taskupdate.setTskTskSuivId(Tasknamesuivant);
            /*taskupdate.setIdStatusBefore(statusprecedant);
             taskupdate.setIdStatusAfter(statussuivant);*/ 
           this.taskservice.updateWrkTask(taskupdate);
          response.setData(taskupdate);
          response.setStatut(true);
          response.setDescription("task mise à jour");
        } else { 
               response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        }
      
         
        return response;
    }
     
<<<<<<< HEAD
=======
      @GetMapping("deletewrkfltask/{idtsk}")
     public ServeurResponse deleteWrkflTask(@PathVariable Long idtsk) {
          ServeurResponse response = new ServeurResponse();
             taskservice.delete(idtsk); 
          response.setStatut(true);
        return response;
     }
     
     @GetMapping("listetaskstatus")
     public ServeurResponse getlisteTaskStatus() {
          ServeurResponse response = new ServeurResponse(); 
           Object taskStatus =  taskStatusService.getAllTaskStatus();
          response.setData(taskStatus);
          response.setStatut(true);
          response.setDescription("Nom du formulaire"); 
          return response;
     }
     
     @RequestMapping(value = "createtaskstatus", method = RequestMethod.POST)
    @ResponseBody
    public Object createTaskStatus(@RequestParam("taskstatusform") String taskstatusform) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
       ServeurResponse response = new ServeurResponse(); 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        
      
            JSONObject obj = new JSONObject(taskstatusform);
            String taskstatusname = "";
            taskstatusname = (String) obj.get("taskstatusname").toString();
             
          TaskStatus taskstatus  = new TaskStatus();  
           taskstatus.setStsName(taskstatusname);
           
           taskStatusService.save(taskstatus);
          response.setData(taskstatus);
          response.setStatut(true);
          response.setDescription("Nom du formulaire");
         
        return taskstatus;
    }
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
     
     
     @GetMapping("workflowname/{containerId}")
     public ServeurResponse GetWorkflowName(@PathVariable String containerId) {
          ServeurResponse response = new ServeurResponse();
          response.setData(workflowService.findByWkfConteneur(containerId));
          response.setStatut(true);
          response.setDescription("Nom du workflow");

          return response;
     }
     
    

    /**
     * Récuperation de la liste des workflows
     * @return liste des workflows
     */
     @GetMapping("workflows")
     public ServeurResponse getAllWorkflow() {
          Iterable<Workflow> workflow = workflowService.getListWorkflow();
          ServeurResponse response = new ServeurResponse();
          if (workflow == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(workflow);
               response.setDescription("Données récupérées.");
          }
          return response;
     }

    /**
     * Recuperation des workflows par secteur
     * @param secteur
     * @return
     */
    @GetMapping("workflowsbysector/{secteur}")
    public ServeurResponse getWorkflowDisplayBySector(@PathVariable String secteur) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        ServeurResponse response = new ServeurResponse();
        
        Iterable<Workflow> workflow = workflowService.getWorkflowBySector(secteur);
         
        if (workflow == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else { 
            System.out.println("++++++++"+workflow);
            response.setStatut(true);
            response.setData(workflow);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    /**
     * Recuperation de la liste des secteur
     * @return
     */
     @GetMapping("workflowlistsecteur")
     public ServeurResponse getAllSecteurList() {
          Iterable<WorkflowSecteur> workflowSecteurs = workflowSecteurService.getListWorkflowSecteur();
          ServeurResponse response = new ServeurResponse();
          if (workflowSecteurs == null) {
               response.setStatut(false);
               response.setData(null);
               response.setDescription("Aucune élèment trouvé.");

          } else {

               response.setStatut(true);
               response.setData(workflowSecteurs);
               response.setDescription("Données récupérées.");
          }
          return response;
     }

    /**
     * Creation de secteur
     * @param workflowSecteur
     * @return
     */
     @PostMapping("workflowsecteur")
     public ServeurResponse createsecteur(@RequestBody WorkflowSecteur workflowSecteur) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
         ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true); 
        JSONObject obj = new JSONObject(workflowSecteur);
         ServeurResponse response = new ServeurResponse();
         String creator = "wbadmin";
         String codeSecteur;
        codeSecteur = (String) obj.get("codeSecteur").toString();
        // System.out.println("++++++++++++"+obj);
         workflowSecteurService.save(workflowSecteur);
           Object spacecreate = jbpmApiService.CreateOneSpace(codeSecteur,creator);
          response.setStatut(true);
          response.setDescription("Enregistrement réussi");
          response.setData("workflowSecteurService");

          return response;
     }

    /**
     * Creation de workflow
     * @param workflow
     * @return
     */
     @PostMapping("workflow")
     public ServeurResponse create(@RequestBody Workflow workflow) {
          ServeurResponse response = new ServeurResponse();
          Object respbody = "";
          if (workflowService.findByName(workflow.getName()) != null) {
               response.setStatut(false);
               response.setDescription("Ce workflow exist deja");
          }else{
               workflow.setWkfEtat("1");

               try {
                   respbody = this.createWorkflows(new WorkflowItem(workflow.getName(), workflow.getGroupId(), workflow.getVersion(), workflow.getDescription()),workflow.getWkfSecteur());
                    
                   if (respbody !=null && respbody !=""){
                       workflowService.save(workflow);
                   }else{
                       response.setStatut(false);
                       response.setDescription("Echec creation workflow");
                       response.setData(workflow);
                   }

               } catch (ParserConfigurationException e) {
                    e.printStackTrace();
               } catch (TransformerException e) {
                    e.printStackTrace();
               } catch (SAXException e) {
                    e.printStackTrace();
               } catch (XPathExpressionException e) {
                    e.printStackTrace();
               } catch (IOException e) {
                    e.printStackTrace();
               }
               response.setStatut(true);
               response.setDescription("Enregistrement réussi");
               response.setData(workflow);
          }
          return response;
     }

    /**
     * Compilation de workflow modeliser
     * @param workflow
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     * @throws TransformerException
     * @throws JSONException
     */
     @PostMapping("modeliser")
     public ServeurResponse modeliser(@RequestBody Workflow workflow) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException, JSONException {
          ServeurResponse response = new ServeurResponse();
          Workflow findWorkflow = workflowService.findByName(workflow.getName());
          if (findWorkflow != null) {
               findWorkflow.setWkfEtat("2");
               Object containerid = this.getOneContainer(workflow.getName());
               Object processid = this.getProcessinfos((String) containerid);
               findWorkflow.setWkfConteneur((String) containerid);
               findWorkflow.setWkfProcess_id((String) processid);
               this.workflowService.save(findWorkflow);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
               response.setData(findWorkflow);
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }
          return response;
     }

    /**
     * Recuperation de workflow par Id
     * @param id
     * @return
     */
     @GetMapping("workflow/{id}")
     public Workflow findById(@PathVariable Long id) {
          return workflowService.findByWkfId(id);
     }

    /**
     * Supprimer un workflow
     * @param workflow
     * @return
     */
     @PostMapping("workflow/delete")
     public ServeurResponse deleteWorkflow(@RequestBody Workflow workflow) {
          ServeurResponse response = new ServeurResponse();
          workflowService.delete(workflow);
          response.setStatut(true);
          return response;
     }

    /**
     * Mettre à jour un workflow
     * @param workflow
     * @return
     */
     @PostMapping("update")
     public ServeurResponse update(@RequestBody Workflow workflow) {
          ServeurResponse response = new ServeurResponse();
          Workflow findWorkflow = workflowService.findByWkfId(workflow.getWkfId());
          if (findWorkflow != null) {
               findWorkflow.setName(workflow.getName());
               findWorkflow.setDescription(workflow.getDescription());
               findWorkflow.setGroupId(workflow.getGroupId());
               findWorkflow.setVersion(workflow.getVersion());
              findWorkflow.setWkfCalltoaction(workflow.getWkfCalltoaction());
              findWorkflow.setWkfLabelwdgt(workflow.getWkfLabelwdgt());
              findWorkflow.setWkfSecteur(workflow.getWkfSecteur());
               this.workflowService.save(findWorkflow);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }
          return response;
     }

   /* private Object createWorkflowsSecteur(SecteurItem secteurItem) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
        return jbpmApiService.CreateOneSpace(secteurItem);
    }*/

    private Object createWorkflows(WorkflowItem workflowItem, String space) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
        return jbpmApiService.CreateOneWorkflow(workflowItem, space);
    }

    private Object getOneContainer(String artifactId) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
        return jbpmApiService.RecupContainer(artifactId);
    }

    public Object getProcessinfos(String containerid) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
        return jbpmApiService.ListOfprocess(containerid);
    }

      @GetMapping("workflow/nombreWorkflow")
     public ServeurResponse getNombreWorkflow(){
          ServeurResponse response = new ServeurResponse();
          response.setData(workflowService.nombreWorkflow());
          response.setStatut(true);
          return response;
     }
      
      //Liste des formulaire generer etb non generer   
     
      @GetMapping("formInfo/generer/{containerId}")
      public ServeurResponse getAllGenerer(@PathVariable String containerId) {
          Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosGenerer(containerId);
          ServeurResponse response = new ServeurResponse();
          if (jbpmFormInfos == null) {
              response.setStatut(false);
              response.setData(null);
              response.setDescription("Aucune élèment trouvé.");

          } else {
              
              response.setStatut(true);
              response.setData(jbpmFormInfos);
              response.setDescription("Données récupérées.");
          }
          return response;
      }
      @GetMapping("formInfo/nogenerer")
      public ServeurResponse getAllNoGenerer() {
          Iterable<JbpmFormInfos> jbpmFormInfos = jbpmFormInfosService.getListJbpmFormInfosNoGenerer();
          ServeurResponse response = new ServeurResponse();
          if (jbpmFormInfos == null) {
              response.setStatut(false);
              response.setData(null);
              response.setDescription("Aucune élèment trouvé.");

          } else {
              
              response.setStatut(true);
              response.setData(jbpmFormInfos);
              response.setDescription("Données récupérées.");
          }
          return response;
      }
      
       // @GetMapping("recuptask/{workflowId}/{profil}")
      private Task getTask(Long taskid) {
        return taskService.findByTskId(taskid);          
      }
      
      @GetMapping("recuptask/{workflowId}/{taskid}/{profil}")
      public ServeurResponse getAllTask(@PathVariable Long workflowId,@PathVariable Long profil, @PathVariable Long taskId) {
        Task task =  this.getTask(taskId);
        String form = task.getTskFormName();
        Iterable <?> allTask = taskService.getListTaskByTaskIdAndForm(taskId, form);
          ServeurResponse response = new ServeurResponse();
          if (allTask == null) {
              response.setStatut(false);
              response.setData(null);
              response.setDescription("Aucune élèment trouvé.");

          } else { 
              response.setStatut(true);
              response.setData(allTask);
              response.setDescription("Données récupérées.");
          }
          return response;
      }
      
      @GetMapping("executer/{workflowId}/{poOwner}/{containerId}/{processId}/{processInstId}")
      public ServeurResponse executeTask(@PathVariable("workflowId") Long workflowId, @PathVariable("poOwner") Long poOwner, @PathVariable("containerId") String containerId, @PathVariable("processId") String processId, @PathVariable("processInstId") Long processInstId) {
    	  
    	  //si premier tache startprocess
          Task task = taskService.findByWorkflowAndProfil(workflowId, poOwner);
          Object extask = null;
          if(task!=null) {
        	  try {
            if(task.getTskIsFirst()) {
               
				processInstId = jbpmApiService.startOneWorkflow(containerId, processId);
            }
           
				extask= executeTask(workflowId+"", task.getTskFormName());
			
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
            

          }

        

<<<<<<< HEAD
    	  return new ServeurResponse(extask!=null?true:false, "", extask);
=======
    	  return new ServeurResponse(extask!=null?true:false, "", extask,null);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
      }
      
      
		public Object executeTask(
				@RequestParam("workflow") String workflow, @RequestParam("form") String form ) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {

          ObjectMapper mapper = new ObjectMapper();
          mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
          ServeurResponse response = new ServeurResponse();
          JSONObject obj = new JSONObject(workflow);
          String owner;
          String usertsk;
          Long idfortask = 1L;
           owner = (String) obj.get("taskactualowner").toString();
           usertsk =  owner.replaceAll("\\[", "").replaceAll("\\]","");

          JbpmUserMapping findjbpmuser = jbpmUserService.findByIndusUser(usertsk.replace("\"",""));
          String container;
          container = (String) obj.get("wkfConteneur").toString();
          String taskid;
          taskid = (String) obj.get("wkfTaskId").toString();
          Integer taskidcast=Integer.parseInt(taskid);  
          idfortask = Long.valueOf(taskidcast);
          String processid;
          processid = (String) obj.get("wkfProcess_id").toString();
          Object champrocess = "";
          champrocess = form;
          String pass = "";
          Object extask = "";
          if (findjbpmuser != null) {

              pass = findjbpmuser.getJbpmPass(); 

              //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++"+champrocess);

              extask= jbpmApiService.ExecuteOneTask(container, usertsk.replace("\"",""), pass, idfortask, champrocess);
              
              response.setStatut(true);
              response.setDescription("Enregistrement réussi!");
              response.setData(extask);
          } else {
              response.setStatut(false);
              response.setDescription("Echec d'enregistrement!");
          }

          return extask;

      }

}