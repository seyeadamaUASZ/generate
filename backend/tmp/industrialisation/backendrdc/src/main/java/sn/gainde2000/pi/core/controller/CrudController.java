package sn.gainde2000.pi.core.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sn.gainde2000.pi.config.AppProperties;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Application;
import sn.gainde2000.pi.core.entity.Champs;
import sn.gainde2000.pi.core.entity.Fichier;
import sn.gainde2000.pi.core.entity.Formulaire;
import sn.gainde2000.pi.core.entity.Langue;
import sn.gainde2000.pi.core.entity.Parametre;
import sn.gainde2000.pi.core.entity.PwdCriteria;
import sn.gainde2000.pi.core.entity.Theme;
import sn.gainde2000.pi.core.entity.ValueChamps;
import sn.gainde2000.pi.core.entity.Widget;
import sn.gainde2000.pi.core.entity.Workflow;
import sn.gainde2000.pi.core.repository.ChampsRepository;
import sn.gainde2000.pi.core.repository.FormulaireRepository;
import sn.gainde2000.pi.core.service.Crud;
import sn.gainde2000.pi.core.service.IFichierService;
import sn.gainde2000.pi.core.service.IFormulaireService;
import sn.gainde2000.pi.core.service.IPwdCriteriaService;
import sn.gainde2000.pi.core.service.IThemeService;
import sn.gainde2000.pi.core.service.IValue;
import sn.gainde2000.pi.core.service.IWidgetService;
import sn.gainde2000.pi.core.service.IWorkflowService;
import sn.gainde2000.pi.core.service.IlangueService;
import sn.gainde2000.pi.core.service.IparametreService;
import sn.gainde2000.pi.core.service.impl.ApplicationServiceImpl;
import sn.gainde2000.pi.core.service.impl.ValueServiceImpl;
import sn.gainde2000.pi.core.tools.StringProcess;


@RestController
@CrossOrigin("*")
public class CrudController {
   @Autowired
   private ChampsRepository champsRepository;
   @Autowired
   FormulaireRepository formulaireRepository;
   @Autowired
   ApplicationServiceImpl applicationServiceImpl;
   @Autowired
   IThemeService iThemeService;
	@Autowired
	IWorkflowService iworkflow;
	@Autowired
	IFormulaireService iformulaire;
	@Autowired
	IValue iValue;
	@Autowired
	@Qualifier("dataSourceJasper")
	DataSource dataSourceJasper;
	@Autowired
	IWidgetService iWidgetService;
	@Autowired
	IlangueService ilangueService;
	@Autowired
	IPwdCriteriaService iPwdCriteriaService;
	@Autowired
	IFichierService iFichierService;
	@Autowired
	IparametreService iparametreService;
	@Autowired
    private ValueServiceImpl valueServiceImpl;
   public String repo="/tmp/industrialisation";
   
	  public CrudController() {
		  String SE = System.getProperty("os.name").toLowerCase();
			 if (SE.indexOf("nux") >= 0) {
				 	this.repo="/tmp/industrialisation";
				 	
		        } else {
		        	this.repo=System.getProperty("user.dir")+"/tmp/industrialisation";
		        	
		        }
			 
}
	  
	  public void showTables() throws Exception {
		  //System.out.print("test++++++++++++++++++++++++++++++"+this.dataSourceJasper);

	        DatabaseMetaData metaData = dataSourceJasper.getConnection().getMetaData();

	        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });

	        while (tables.next()) {

	            String tableName=tables.getString("TABLE_NAME");

	            //System.out.println(tableName);

	            ResultSet columns = metaData.getColumns(null,  null,  "td_application", "%");

	            while (columns.next()) {

	                String columnName=columns.getString("COLUMN_NAME");

	               System.out.println("\t" + columnName);

	            }

	        }

	    }
	  
	  @GetMapping("gettable")
	  public ServeurResponse getTable() throws SQLException {
		 ArrayList<String> listeTable= new ArrayList<>();
		  DatabaseMetaData metaData = this.dataSourceJasper.getConnection().getMetaData();
		  ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
		  while (tables.next()) {

	            String tableName=tables.getString("TABLE_NAME");
	           //String mots = tableName.substring(3);
	           // System.out.println(mots);
	            listeTable.add(tableName);
	        }
		
		  ServeurResponse response = new ServeurResponse();
       
          response.setStatut(true);
          response.setData(listeTable);
          response.setDescription("Liste des tables de la base de donnée");

     
      return response;
	  }
	  
	  @GetMapping("getcolonne/{table}")
	  public ServeurResponse getColonne(@PathVariable("table")String table) throws SQLException {
		 ArrayList<String> listeColonne= new ArrayList<>();
		  DatabaseMetaData metaData = this.dataSourceJasper.getConnection().getMetaData();
		  ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
		
		  ResultSet columns = metaData.getColumns(null,  null,  table, "%");

          while (columns.next()) {

              String columnName=columns.getString("COLUMN_NAME");

              listeColonne.add(columnName);

          }

		  ServeurResponse response = new ServeurResponse();
       
          response.setStatut(true);
          response.setData(listeColonne);
          response.setDescription("Liste des colonne de la table "+table);

     
      return response;
	  }
	  
	  
		
		@GetMapping("zipper/{idApp}")
	    public  ResponseEntity<byte[]>  zipperProjet(@PathVariable("idApp")Long idApp) throws IOException {
			 Application app=  applicationServiceImpl.findByappId(idApp);
		//app.setAppLien("C:\\Users\\asow\\Desktop\\Projet_industrialisation\\Alysow");
	         Crud crud = new Crud();
	         ZipOutputStream zipos = crud.zipper(idApp,app.getAppNom(),app.getAppLien());
	         ObjectMapper objectMapper = new ObjectMapper();
	         String json = objectMapper.writeValueAsString(zipos);
	         byte[] isr = json.getBytes();
	         HttpHeaders respHeaders = new HttpHeaders();
	         respHeaders.setContentLength(isr.length);
			 respHeaders.setContentType(new MediaType("text", "json"));
			 respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			 respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=");
				return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
	        
	    }
	@GetMapping("front/{nomProjet}")
	    public ServeurResponse generedCrud(@PathVariable("nomProjet")String nomprojet) {
	         Crud crud = new Crud();
	        ServeurResponse response = new ServeurResponse();
	        if (crud.createProjet(nomprojet)) {
	            response.setStatut(true);
	            response.setDescription("Le projet a ete bien cree");

	        } else {
	            
	            response.setStatut(false);
	            response.setDescription("Probleme sur la creation du projet");
	        }
	        return response;
	    }
 //configuration base de donnees by id application
	@GetMapping("configapp/{id}")
    public ServeurResponse congigappli(@PathVariable("id")Long id){
         Crud crud = new Crud();
         Application app=  applicationServiceImpl.findByappId(id);
         List<Theme> listeTheme = iThemeService.getListTheme();
         List<Workflow> listWorkflow = iworkflow.findBywkfAppId(id);
         List<Formulaire> listFormulaire = iformulaire.findByfrmAppId(id);
         List<Widget> listeWidget = iWidgetService.getListWidget();
		 List<Langue> listeLangue= ilangueService.getListLang();
		 List<PwdCriteria> listePwdCritaire = iPwdCriteriaService.getListPwd();
		 List<Fichier> listeFichier = iFichierService.getAllFichierByIdApp(id);
		 List<Parametre> listeParametre = iparametreService.getListParam();
        ServeurResponse response = new ServeurResponse();
           try {
        	   crud.initfichierstatique();
        	   crud.initialiserApp(app,listeTheme,listWorkflow,listFormulaire,listeWidget ,listeLangue,listePwdCritaire,listeFichier,listeParametre);
			  response.setStatut(true);
	            response.setDescription("fichier de configuaration cree");

		} catch (IOException e) {
		
			e.printStackTrace();
			  response.setStatut(false);
	            response.setDescription("fichier de configuaration non cree");

		}
          
      
        return response;
    }
	//suppression code sources formulaire by id formulaire 
	 @GetMapping("supprimer/{nomProjet}/{idFormulaire}")
	    public ServeurResponse supprimerComposant(@PathVariable("nomProjet")String nomprojet,@PathVariable("idFormulaire")Long idFormulaire) throws IOException {
	    	Crud crud = new Crud();
	         List<Champs> listChamps= this.champsRepository.listFieldByFrmId(idFormulaire);
	         Formulaire form = formulaireRepository.findByfrmId(idFormulaire);
	         for(int i=0; i<listChamps.size(); i++) {
	        	 List<ValueChamps> valeurs = valueServiceImpl.listByChampId(listChamps.get(i).getChpId());
	         	for(int j=0; j<valeurs.size(); j++) {
	         		valueServiceImpl.delete(valeurs.get(j));
	         	}
	        	 this.champsRepository.delete(listChamps.get(i));
	        	
	         }
	         String composant = form.getFrmNom();
	        ServeurResponse response = new ServeurResponse();
	        formulaireRepository.delete(form);
	        File f= new File(this.repo+"/"+nomprojet);
	        File comp = new File(this.repo+"/"+nomprojet+"/src/app/"+composant);
	        File model = new File(this.repo+"/"+nomprojet+"/src/app/model/"+composant);
	        File service = new File(this.repo+"/"+nomprojet+"/src/app/service/"+composant);
	    	if(comp.isDirectory() || model.isDirectory() || service.isDirectory()) {
        		crud.deleteComposant(nomprojet,composant);
        		crud.deleteModel(composant, nomprojet);
        		crud.deleteService(composant, nomprojet);
        			response.setStatut(true);
 	            response.setDescription("Le composant a ete bien supprimer"+composant);
	    	}
        		
	    	
	    	else {
	    		response.setStatut(false);
 	            response.setDescription("Les composant n'est pas encore generer");
	    	}
	    	return response;
	    }
	
	
	  //endpoit pour la creation d'un component et modification
	  @GetMapping("composant/{nomProjet}/{idApplication}")
	    public ServeurResponse generedComposant(@PathVariable("nomProjet")String nomprojet,@PathVariable("idApplication")Long idApplication) throws IOException {
	         Crud crud = new Crud();
	         ServeurResponse response = new ServeurResponse();
	         List<Formulaire> listFormulaire = iformulaire.findByfrmAppId(idApplication);
	         Application app=  applicationServiceImpl.findByappId(idApplication);
	         List<Theme> listeTheme = iThemeService.getListTheme();
	         List<Workflow> listWorkflow = iworkflow.findBywkfAppId(idApplication);
	         List<Widget> listeWidget = iWidgetService.getListWidget();
			List<Langue> listeLangue= ilangueService.getListLang();
			List<PwdCriteria> listePwdCritaire = iPwdCriteriaService.getListPwd();
			List<Fichier> listeFichier = iFichierService.getAllFichierByIdApp(idApplication);
			List<Parametre> listeParametre = iparametreService.getListParam();
			crud.initfichierstatique();
	         crud.initialiserApp(app,listeTheme,listWorkflow,listFormulaire,listeWidget ,listeLangue,listePwdCritaire,listeFichier,listeParametre);
	       for (int w = 0; w < listFormulaire.size(); w++) {
	         List<Champs> listChamps= this.champsRepository.listFieldByFrmId(listFormulaire.get(w).getFrmId());
	         Formulaire form = formulaireRepository.findByfrmId(listFormulaire.get(w).getFrmId());
	         String composant = form.getFrmNom();
	       
	        File f= new File(this.repo+"/"+nomprojet);
	        File comp = new File(this.repo+"/"+nomprojet+"/src/app/"+composant.toLowerCase());
	        StringProcess uppercasefunct = new StringProcess();
	        if (f.isDirectory()) {
	        	
	        	if(comp.isDirectory()) {
	        		System.out.println("Debut suppression");
	        		crud.deleteComposant(nomprojet,composant.toLowerCase());
	        		System.out.println("+++++++++++++fin suppression");
	        	}
	       
	 	        if (
	 	        		crud.createComposant(nomprojet, uppercasefunct.capitalize(composant)) &&
	 	        		crud.createComposantadd(nomprojet, uppercasefunct.capitalize(composant)) &&
	 	        		crud.createComposantview(nomprojet, uppercasefunct.capitalize(composant)) &&
	 	        		crud.createComposantEdit(nomprojet, uppercasefunct.capitalize(composant))&&
	 	        		crud.createComposantList(nomprojet, uppercasefunct.capitalize(composant))&&
	 	        		crud.createService(uppercasefunct.capitalize(composant), nomprojet)&&
	 	        		crud.createModel(uppercasefunct.capitalize(composant), nomprojet) &&
	 	        		crud.createRouting(nomprojet, uppercasefunct.capitalize(composant))
	 	        		) {
	 	        	//String rep = System.getProperty("user.dir") ;
	 	        	
	 	   		try {
	 	   		form.setFrmStatus(true);
	             formulaireRepository.save(form);
	              crud. generationFileModelBack(composant,listChamps);
	              crud.generationFileControllerBack(composant);
	              crud.generationFileServiceBack(composant);
	              crud.generationFileRepositoryBack(composant);
	 	   			//File file = new File(System.getProperty("user.dir")+"/"+nomprojet+"/src/app/"+composant);
	 	   			//file.createNewFile(); file.mkdir();

	 	   	       crud.updateComponentAddHtml(nomprojet, composant, listChamps,iValue);
	 	   			crud.updateComponent(composant,nomprojet,listChamps);
	 	   			crud.updateService(composant, nomprojet,listChamps);
	 	   			crud.updateComponentCss(composant, nomprojet);
	 	   			crud.updateComponentList(composant, nomprojet,listChamps);
	 	   		    crud.updateComponentListCss(composant,nomprojet);
	 	   		   crud.updateComponentListHtml(composant,nomprojet,listChamps);
	 	   		   crud.updateComponentEditHtml(composant,nomprojet,listChamps,iValue);
	 	   		  crud.updateComponentEditTs(composant,nomprojet, listChamps) ;
	 	   		  crud.updateComponentEditCss(composant, nomprojet);
	 	   			crud.updateModel(composant, nomprojet, listChamps);
	 	   		  crud.updateRoutingForm(nomprojet,composant);
	 	   		   crud.updateComposantFormTs(nomprojet,composant);
	 	   		   crud.updateRoutingApp(nomprojet,composant,listFormulaire); 
	 	   		 crud.updateComponentViewCss(composant,nomprojet);
	 	   		crud.updateComponentViewHtml(composant,nomprojet,listChamps);
	 	   	 crud.updateComponentViewTs(composant,nomprojet, listChamps) ;

	 	   			System.out.println("Successfully wrote to the file."); }
	 	   		catch (IOException e) {
	 	   			System.out.println("An error occurred.");
	 	   			e.printStackTrace();
	 	   		}
	 	            response.setStatut(true);
	 	            response.setDescription("Le composant a ete bien cree"+composant);
	 	        
	 	        } else {
	 	            
	 	            response.setStatut(false);
	 	            response.setDescription("Le composant n'est pas ete cree");
	 	        }
	        		
	        	
	        	
	        } else {
	            
	            response.setStatut(false);
	            response.setDescription("Probleme sur la creation du projet");
	        }
	       
	      
	       }
	       return response;
	    }


}
