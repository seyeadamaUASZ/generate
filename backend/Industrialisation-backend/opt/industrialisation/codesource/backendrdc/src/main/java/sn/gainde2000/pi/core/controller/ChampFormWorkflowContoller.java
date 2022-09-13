package sn.gainde2000.pi.core.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.ChampFormWorkflow;
import sn.gainde2000.pi.core.entity.Champs;
import sn.gainde2000.pi.core.entity.JbpmFormInfos;
import sn.gainde2000.pi.core.entity.StatusFrmWorkflow;
import sn.gainde2000.pi.core.entity.Workflow;
import sn.gainde2000.pi.core.service.Crud;
import sn.gainde2000.pi.core.service.CrudFormulaireWorkflow;
import sn.gainde2000.pi.core.service.IChampFormWorkflowService;
import sn.gainde2000.pi.core.service.IJbpmFormInfos;
import sn.gainde2000.pi.core.service.IWorkflowService;
import sn.gainde2000.pi.core.service.impl.JbpmFormInfosImpl;

@RestController
public class ChampFormWorkflowContoller {
	   
	@Autowired
	private IChampFormWorkflowService champFormWorkflowService;
	@Autowired
	private IJbpmFormInfos formInfos;
	@Autowired
	JbpmFormInfosImpl jbpmFormInfosService; 
	@Autowired
	IWorkflowService iWorkflowService;

	
	 public String repo="/opt/industrialisation/codesource";
	public ChampFormWorkflowContoller() {
		  String SE = System.getProperty("os.name").toLowerCase();
		 if (SE.indexOf("nux") >= 0) {
			 	this.repo="/opt/industrialisation/codesource";
			 	
	        } else {
	        	this.repo=System.getProperty("user.dir")+"/opt/industrialisation/codesource";
	        
	        }
	}
	public String getMappingObjet(String nomObjet) {
		//String mots = nomObjet.substring(3);
		String mots ="";
		String[] tab = nomObjet.split("-");
		
			 mots =mots+tab[0].replace('"', ' ');
		 
       return mots;
	}
    @GetMapping("champForm/listbyForm/{idFrm}")
    public ServeurResponse getAllChamps(@PathVariable("idFrm") Long idFrm) {
        Iterable<ChampFormWorkflow> champs = champFormWorkflowService.getListChampsWorkflowByForm(idFrm);
        ServeurResponse response = new ServeurResponse();
        if (champs == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucun élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(champs);
            response.setDescription("Données récupérées.");
        }
        return response;
    }
	
    @GetMapping("champForm/create/{nameprojet}/{idFrm}")
    public ServeurResponse create(@PathVariable("nameprojet") String nameprojet,@PathVariable("idFrm") Long idFrm) throws IOException {
    	CrudFormulaireWorkflow crudFormulaireWorkflow = new CrudFormulaireWorkflow();
    	
        ServeurResponse response = new ServeurResponse();
       JbpmFormInfos formInf = formInfos.findByJfrmId(idFrm);
       Workflow  workflow =iWorkflowService.findByWkfId(formInf.getJfrmIdworkflow());
        JsonParser parser = new JsonParser();
        //System.getProperty("user.dir")+"/opt/DateRV-taskform.frm"
        try { 
           JsonElement jsontree = parser.parse(new FileReader( System.getProperty("user.dir")+"/"+formInf.getJfrmUrlfile()));
           JsonObject je = jsontree.getAsJsonObject();
           //System.out.println(getMappingObjet(je.get("name").toString()));
           JsonArray ja = je.getAsJsonArray("fields");
          // System.out.println(ja.toString());
           File comp = new File(this.repo + "/" + nameprojet + "/src/app/" + workflow.getName().toLowerCase());
           File compo = new File(this.repo + "/" + nameprojet + "/src/app/" + workflow.getName().toLowerCase()+"/components/"+formInf.getJfrmFormulaire());
           if(comp.isDirectory()) {
        	   if(compo.isDirectory()) {
    			   crudFormulaireWorkflow.deleteComposant(nameprojet,formInf.getJfrmFormulaire(),workflow.getName().toLowerCase());
    			   crudFormulaireWorkflow.deleteComposant(nameprojet,"add-"+formInf.getJfrmFormulaire(),workflow.getName().toLowerCase());
    			   crudFormulaireWorkflow.deleteComposant(nameprojet,"view-"+formInf.getJfrmFormulaire(),workflow.getName().toLowerCase());
        	   }
<<<<<<< HEAD
        	   if(crudFormulaireWorkflow.createComposant(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&& crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
=======
        	   if(formInf.getJfrmPrimaire()) {
        		   if(crudFormulaireWorkflow.createComposant(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())){
        			   
        		   }
        	   }
        	   if(
        			   crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        			   crudFormulaireWorkflow.createComposantView(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())
        			   &&crudFormulaireWorkflow.createModel(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase())
        			   &&crudFormulaireWorkflow.createService(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase())
        			   ) {
            	   System.out.println("rang moy gaw");
               }
           }
           else {
<<<<<<< HEAD
        	   if(crudFormulaireWorkflow.createComposant(nameprojet, workflow.getName())&& crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
=======
        	   /*if(crudFormulaireWorkflow.createComposant(nameprojet, workflow.getName())&& crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        			   crudFormulaireWorkflow.createComposantView(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
        			   crudFormulaireWorkflow.createModule(nameprojet, workflow.getName())
        			   &&crudFormulaireWorkflow.createService(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase())){
        		   System.out.println("rang moy gaw");
<<<<<<< HEAD
        	   }
    		 
        	   if(crudFormulaireWorkflow.createComposant(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
        			   crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
        			   crudFormulaireWorkflow.createComposantView(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())
        			   &&crudFormulaireWorkflow.createModel(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase())) {
            	   System.out.println("rang moy gaw");
               }
        	  
           }
         
          
=======
        	   }*/
    		 if(crudFormulaireWorkflow.createModule(nameprojet, workflow.getName())) {
    			 if(formInf.getJfrmPrimaire()) {
    				 if(crudFormulaireWorkflow.createComposant(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())) {
    					 System.out.println("rang moy gaw Component"+formInf.getJfrmFormulaire());
    				 }
    				 
    			 }
    			 if(crudFormulaireWorkflow.createComposantAdd(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())&&
          			   crudFormulaireWorkflow.createComposantView(nameprojet,workflow.getName(), formInf.getJfrmFormulaire())
          			   &&crudFormulaireWorkflow.createModel(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase()) 
          			   &&crudFormulaireWorkflow.createService(formInf.getJfrmFormulaire(), nameprojet,workflow.getName().toLowerCase())) {
              	   System.out.println("rang moy gaw");
                 }
    		 }
        	  
        	  
           }
         
         
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
          List<JbpmFormInfos> listforms= formInfos.getListJbpmFormInfos(workflow.getWkfConteneur());
          crudFormulaireWorkflow.updateComponentAddHtml(nameprojet, formInf.getJfrmFormulaire(), ja,workflow.getName());
          crudFormulaireWorkflow.updateComponentAddCss(formInf.getJfrmFormulaire(), nameprojet, workflow.getName());
          crudFormulaireWorkflow.updateComponent(formInf.getJfrmFormulaire(), nameprojet, ja, workflow.getName());
          crudFormulaireWorkflow.updateComposantFormTs(nameprojet, workflow.getName());
           crudFormulaireWorkflow.generationFileModelBack(formInf.getJfrmFormulaire(), ja);
           crudFormulaireWorkflow. generationFileRepositoryBack(formInf.getJfrmFormulaire());
           crudFormulaireWorkflow.generationFileServiceBack(formInf.getJfrmFormulaire());
           crudFormulaireWorkflow.generationFileControllerBack(formInf.getJfrmFormulaire(),ja);
<<<<<<< HEAD
           crudFormulaireWorkflow.updateComponentListHtml(formInf.getJfrmFormulaire(), nameprojet, ja, formInf,workflow.getName().toLowerCase());
           crudFormulaireWorkflow.updateComponentList(formInf.getJfrmFormulaire(), nameprojet, ja, formInf,workflow.getName().toLowerCase());
           crudFormulaireWorkflow.updateComponentListCss(formInf.getJfrmFormulaire(), nameprojet, workflow.getName());
           crudFormulaireWorkflow.updateModel(formInf.getJfrmFormulaire(), nameprojet, ja,workflow.getName());
           crudFormulaireWorkflow.updateRoutingForm(nameprojet,workflow.getName(),listforms);
           crudFormulaireWorkflow.updateService(formInf.getJfrmFormulaire(), nameprojet, ja,workflow.getName());
           crudFormulaireWorkflow.updateComponentListCss(formInf.getJfrmFormulaire(), nameprojet, workflow.getName());
=======
           if(formInf.getJfrmPrimaire()) {
               crudFormulaireWorkflow.updateComponentListHtml(formInf.getJfrmFormulaire(), nameprojet, ja, formInf,workflow.getName().toLowerCase(),listforms);
               crudFormulaireWorkflow.updateComponentList(formInf.getJfrmFormulaire(), nameprojet, ja, formInf,workflow.getName().toLowerCase(),listforms);
               crudFormulaireWorkflow.updateComponentListCss(formInf.getJfrmFormulaire(), nameprojet, workflow.getName());
           }
    
           crudFormulaireWorkflow.updateModel(formInf.getJfrmFormulaire(), nameprojet, ja,workflow.getName());
           crudFormulaireWorkflow.updateRoutingForm(nameprojet,workflow.getName(),listforms);
           crudFormulaireWorkflow.updateService(formInf.getJfrmFormulaire(), nameprojet, ja,workflow.getName());

           crudFormulaireWorkflow.updateModule(nameprojet, workflow.getName(), listforms);

           //crudFormulaireWorkflow.updateComponentListCss(formInf.getJfrmFormulaire(), nameprojet, workflow.getName());
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
           
          for(int i=0; i<ja.size(); i++) {
        	  JsonObject json = (JsonObject) ja.get(i);
        	  ChampFormWorkflow champFormWorkflow  = new ChampFormWorkflow();
        	  champFormWorkflow.setChpWFrmId(idFrm);
        	  champFormWorkflow.setChpWCode(json.get("code").getAsString());
        	  champFormWorkflow.setChpWLabel(json.get("label").getAsString());
        	  if(json.get("maxLength")!=null) {
        	  champFormWorkflow.setChpWMaxLength(json.get("maxLength").getAsDouble());
        	  }
        	  champFormWorkflow.setChpWName(json.get("name").getAsString());
        	  if(json.get("placeHolder")!=null) {
        	  champFormWorkflow.setChpWPlaceholder(json.get("placeHolder").getAsString());
        	  }
        	  
        	  champFormWorkflowService.save(champFormWorkflow);
        	  
          }
          formInf.setStatusFrmWorkflow(StatusFrmWorkflow.GENERER);
          jbpmFormInfosService.save(formInf);
        }  
        catch (JsonIOException e) {
           e.printStackTrace();
       } catch (JsonSyntaxException e) {
           e.printStackTrace();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");

        return response;
    }
	
   
}
