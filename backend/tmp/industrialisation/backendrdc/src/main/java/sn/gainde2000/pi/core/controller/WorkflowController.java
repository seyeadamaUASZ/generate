package sn.gainde2000.pi.core.controller;

import java.io.IOException;
import java.io.ObjectInput;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.xml.sax.SAXException;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.*;
import sn.gainde2000.pi.core.service.JbpmApiService;
import sn.gainde2000.pi.core.service.impl.JbpmUserServiceImpl;
import sn.gainde2000.pi.core.service.impl.WorkflowSecteurServiceImpl;
import sn.gainde2000.pi.core.service.impl.WorkflowServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

@RestController
@CrossOrigin("*")
public class WorkflowController {

 
     @Autowired
     WorkflowServiceImpl workflowService;
     @Autowired
     WorkflowSecteurServiceImpl workflowSecteurService;
     @Autowired
     JbpmApiService jbpmApiService;
     @Autowired
     JbpmUserServiceImpl jbpmUserService;

     @GetMapping("workflowname/{containerId}")
     public ServeurResponse GetWorkflowName(@PathVariable String containerId) {
          ServeurResponse response = new ServeurResponse();
          response.setData(workflowService.findByWkfConteneur(containerId));
          response.setStatut(true);
          response.setDescription("Nom du workflow");

          return response;
     }
     @GetMapping("getspacejbpm")
     public Object GetWorkflowSpace() throws SAXException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException, JSONException {
          Object spacedata = jbpmApiService.RecupSpace();
          return jbpmApiService.RecupSpace();
     }
     @GetMapping("getoutputbpm/{processInstanceId}")
     public Object GetWorkflowOutput(@PathVariable String processInstanceId) throws SAXException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException, JSONException {
          Object bpmdata = jbpmApiService.RecupOutput(processInstanceId);
          return jbpmApiService.RecupOutput(processInstanceId);
     }
     @GetMapping("displaybpm/{containerId}/{processInstanceId}")
     public ServeurResponse GetWorkflowBpm(@PathVariable String containerId,@PathVariable String processInstanceId) throws SAXException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException, JSONException {
          ServeurResponse response = new ServeurResponse();
          Object bpmdata = jbpmApiService.RecupBpm(containerId,processInstanceId);
          response.setData(bpmdata);
          response.setStatut(true);
          response.setDescription("Nom du workflow");
          return response;
     }
     @GetMapping("processinfo/{containerId}")
     public ServeurResponse GetCurrentProcessInstInd(@PathVariable String containerId) throws SAXException, TransformerException, IOException, XPathExpressionException, ParserConfigurationException, JSONException {
          ServeurResponse response = new ServeurResponse();
          Object CurrentInstInd = jbpmApiService.ListOfprocess(containerId);
          response.setData(CurrentInstInd);
          response.setStatut(true);
          response.setDescription("Process instance Id");
          return response;
     }

     //Liste des workflows
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

    //Liste des workflows
    @GetMapping("workflowsbysector/{secteur}")
    public ServeurResponse getWorkflowDisplayBySector(@PathVariable String secteur) {
        Iterable<Workflow> workflow = workflowService.getWorkflowBySector(secteur);

        ServeurResponse response = new ServeurResponse();
        if (workflow == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {
            System.out.println("******************************************"+workflow);
            response.setStatut(true);
            response.setData(workflow);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
     //Liste des workflows
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
     //Ajouter workflow
     @PostMapping("workflowsecteur")
     public ServeurResponse createsecteur(@RequestBody WorkflowSecteur workflowSecteur) {

          ServeurResponse response = new ServeurResponse();
          workflowSecteurService.save(workflowSecteur);
          try {
               System.out.println("+++++++++++++++++++++++++++++++++++"+workflowSecteur);
               this.CreateWorkflowsSecteur(new SecteurItem(workflowSecteur.getWsSecteur(),"wbadmin"));
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
          response.setData(workflowSecteurService);

          return response;
     }
     @PostMapping("workflow")
     public ServeurResponse create(@RequestBody Workflow workflow) {

          ServeurResponse response = new ServeurResponse();


          if (workflowService.findByName(workflow.getName()) != null) {
               response.setStatut(false);
               response.setDescription("Ce workflow exist deja");
          }else{
               workflow.setWkfEtat("1");
               workflowService.save(workflow);
               try {
                    this.CreateWorkflows(new WorkflowItem(workflow.getName(), workflow.getGroupId(), workflow.getVersion(), workflow.getDescription()));
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

     @PostMapping("modeliser")
     public ServeurResponse modeliser(@RequestBody Workflow workflow) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException, JSONException {
          ServeurResponse response = new ServeurResponse();

          Workflow findWorkflow = workflowService.findByName(workflow.getName());
          if (findWorkflow != null) {
               findWorkflow.setWkfEtat("2");
               Object containerid = this.GetOneContainer(workflow.getName());
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

     //recuperation par id
     @GetMapping("workflow/{id}")
     public Workflow findById(@PathVariable Long id) {
          return workflowService.findByWkfId(id);
     }

     @RequestMapping(value = "workflowLierApp", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
     public ServeurResponse LierWorkflowEtApp(HttpServletRequest request) {
          ServeurResponse response = new ServeurResponse();
          String idApp = (String) request.getParameter("idApp");
          String idWorkflow = (String) request.getParameter("idWorkflow");
          // System.out.println("appp id:" + idApp);
          // System.out.println("workflow id:" + idWorkflow);
          workflowService.lierWorkflow(idApp, idWorkflow);
          response.setStatut(true);
          response.setDescription("liaison effectué avec succès");

          return response;
     }

     @GetMapping("workflowLierApp/enlever/{idWorkflow}")
     public ServeurResponse EnleverLiaisonWorkflowEtApp(@PathVariable String idWorkflow) {
          ServeurResponse response = new ServeurResponse();
          // String idApp = (String) request.getParameter("idApp");
          // System.out.println("appp id:" + idApp);
          System.out.println("workflow id:" + idWorkflow);
          workflowService.enleverLiasonWorkflow(idWorkflow);
          response.setStatut(true);
          response.setDescription("liaison enlevé avec succès");

          return response;
     }
     //Supprimer un workflow

     @PostMapping("workflow/delete")
     public ServeurResponse deleteWorkflow(@RequestBody Workflow workflow) {
          ServeurResponse response = new ServeurResponse();
          workflowService.delete(workflow);
          response.setStatut(true);
          return response;
     }

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


               this.workflowService.save(findWorkflow);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }

          return response;
     }

     @GetMapping("taskinfos/{containerId}/{processId}")
     @ResponseBody
     public Object getAllTaskinfos(@PathVariable String containerId, @PathVariable String processId) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {

          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.ExtractAllTaskInfo(containerId, processId);
     }

     @GetMapping("containerlist/")
     @ResponseBody
     public Object getAllContainerId() throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          Object containerdata = jbpmApiService.ExtractAllContainerId();

          return containerdata;
     }

     @GetMapping("listofcontainer")
     @ResponseBody
     public Object ListOfContainer() throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          Object containerdata = jbpmApiService.ListOfContainer();

          return containerdata;
     }

     @GetMapping("allrecuptask")
     public Object taskrecupall() throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          ServeurResponse response = new ServeurResponse();

          Object alltask =jbpmApiService.RecupTaskAll();
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
               response.setData(alltask);


          return jbpmApiService.RecupTaskAll();

     }
     @GetMapping("recuptask/{username}")
     public Object taskrecup(@PathVariable String username) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          ServeurResponse response = new ServeurResponse();
          JbpmUserMapping findjbpmuser = jbpmUserService.findByIndusUser(username);
          String user = "";
          String pass = "";
          if (findjbpmuser != null) {
               user = findjbpmuser.getIndusUser();
               pass = findjbpmuser.getJbpmPass();
               jbpmApiService.RecupTask(user, pass);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
               response.setData(findjbpmuser);
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }

          return jbpmApiService.RecupTask(user, pass);

     }

     @GetMapping("recupformprocess/{containerId}/{processId}")
     @ResponseBody
     public Object recupformprocess(@PathVariable String containerId, @PathVariable String processId) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.generateurFormProcess(containerId, processId);
     }

     @GetMapping("recupformtask/{containerId}/{processId}")
     @ResponseBody
     public Object recupformtask(@PathVariable String containerId, @PathVariable String processId) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.generateurFormTask(containerId, processId);
     }

     @PostMapping("executeworkflow")
     public Object ExecuteWorkflows(@RequestParam("workflow") String workflow, @RequestParam("form") String form) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
          ObjectMapper mapper = new ObjectMapper();
          mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
          ServeurResponse response = new ServeurResponse();
          //JbpmApiService jbpmApiService = new JbpmApiService();
          JSONObject obj = new JSONObject(workflow);
          String name;
          name = (String) obj.get("name");
          String container;
          container = (String) obj.get("wkfConteneur");
          String process;
          process = (String) obj.get("wkfProcess_id");
          Workflow findWorkflow = workflowService.findByName(name);
          Long idprocess = 1L;
          Object champrocess = "";
          if (findWorkflow != null) {
               findWorkflow.setWkfEtat("3");
               this.workflowService.save(findWorkflow);
               idprocess = jbpmApiService.ExecuteOneWorkflow(container, process);
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
               response.setData(findWorkflow);
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }
          champrocess = form;
          return this.Addprocess(container, idprocess, champrocess);
     }

     @PostMapping("executetask")
     public Object ExecuteTask(@RequestParam("workflow") String workflow, @RequestParam("form") String form) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {
          ObjectMapper mapper = new ObjectMapper();
          mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
          ServeurResponse response = new ServeurResponse();
          //JbpmApiService jbpmApiService = new JbpmApiService();
          JSONObject obj = new JSONObject(workflow);
          //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++" + obj);

          String owner;
          owner = (String) obj.get("taskactualowner");
          JbpmUserMapping findjbpmuser = jbpmUserService.findByIndusUser(owner);
          String pass = "";
          if (findjbpmuser != null) {
               pass = findjbpmuser.getJbpmPass();
               response.setStatut(true);
               response.setDescription("Enregistrement réussi!");
               response.setData(findjbpmuser);
          } else {
               response.setStatut(false);
               response.setDescription("Echec d'enregistrement!");
          }
          String container;
          container = (String) obj.get("wkfConteneur");
          Integer idprocess;
          idprocess = (Integer) obj.get("wkfTaskId");
          Object champrocess = "";
          champrocess = form;
          return jbpmApiService.ExecuteOneTask(container, owner, pass, idprocess, champrocess);

     }

     @GetMapping("taskreclame/{containerId}/{taskId}/{username}")
     @ResponseBody
     public Object ReclameTask(@PathVariable  String containerId,@PathVariable  Integer taskId,@PathVariable  String username) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, URISyntaxException, JSONException {

          ServeurResponse response = new ServeurResponse();

          Object reTask = jbpmApiService.ReclameOneTask(containerId, username, username, taskId);
          response.setStatut(true);
          response.setDescription("Enregistrement réussi!");
          response.setData(reTask);
          return reTask;

     }


     private Object CreateWorkflows(WorkflowItem workflowItem) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.CreateOneWorkflow(workflowItem);
     }
     private Object CreateWorkflowsSecteur(SecteurItem secteurItem) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.CreateOneSpace(secteurItem);
     }

     private Object GetOneContainer(String artifactId) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.RecupContainer(artifactId);
     }

     public Object getProcessinfos(String containerid) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {

          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.ListOfprocess(containerid);
     }

     public Object Addprocess(String container, Long idprocess, Object champrocess) throws ParserConfigurationException, TransformerException, SAXException, XPathExpressionException, IOException, JSONException {
          //JbpmApiService jbpmApiService = new JbpmApiService();
          return jbpmApiService.AddInputProcess(container, idprocess, champrocess);
     }

     @GetMapping("WorkflowByApp")
     public ServeurResponse getWorkflowByApp() {
          ServeurResponse response = new ServeurResponse();
          List<Workflow> f = workflowService.getListWorkflowLibre();
          response.setData(f);
          response.setStatut(true);
          return response;
     }

     @GetMapping("workflowsByAppId/{id}")
     public ServeurResponse getWorkflowByApp(@PathVariable Long id){
          ServeurResponse response = new ServeurResponse();
          List<Workflow> f = workflowService.getWorkflowByAppId(id);
          response.setData(f);
          response.setStatut(true);
          return response;
     } 
      @GetMapping("WorkflowByAppOuLibre/{id}")
     public ServeurResponse getWorkflowLibreOuSpecifique(@PathVariable Long id){
          ServeurResponse response = new ServeurResponse();
          List<Workflow> f = workflowService.getWorkflowByAppIdOuLibre(id);
          response.setData(f);
          response.setStatut(true);
          return response;
     } 
     
      @GetMapping("workflow/nombreWorkflow")
     public ServeurResponse getNombreWorkflow(){
          ServeurResponse response = new ServeurResponse();
          response.setData(workflowService.nombreWorkflow());
          response.setStatut(true);
          return response;
     }
}
