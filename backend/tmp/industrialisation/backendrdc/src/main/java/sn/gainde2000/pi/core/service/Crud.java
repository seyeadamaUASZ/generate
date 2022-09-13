package sn.gainde2000.pi.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import org.springframework.beans.factory.annotation.Autowired;
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
import sn.gainde2000.pi.core.tools.StringProcess;

public class Crud {
   public String repo="/tmp/industrialisation";
   public String packagename="sn.gainde2000.pi.core";
   public String importKey="import";
   public String packageKey="package";
   public String dossierPackage="backendrdc/src/main/java/sn/gainde2000/pi/core/";
   @Autowired
    IThemeService iThemeService;
 

	 public Crud() {
		 String SE = System.getProperty("os.name").toLowerCase();
		 if (SE.indexOf("nux") >= 0) {
			 	this.repo="/tmp/industrialisation";
			 	
	        } else {
	        	this.repo=System.getProperty("user.dir")+"/tmp/industrialisation";
	        }
	 	}
	 
	
	public String getMappingObjet(String nomObjet) {
		//String mots = nomObjet.substring(3);
		String mots ="";
		String[] tab = nomObjet.split("_");
		 for(int i=1; i<tab.length; i++) {
			 mots =mots+tab[i];
		 }
       return mots;
	}
	
	public String getMappingChamps(String champs) {
		 StringProcess uppercasefunct = new StringProcess();
		String[] tab = champs.split("_");
		  String mots= tab[0]+uppercasefunct.capitalize(tab[1]);
		  return mots;
	}
	 //debut back
		/*+++++++++++++++++++ Generation des fichiers backend model+++++++++++++++++++++++++*/
		

		public void generationFileModelBack(String composant, List<Champs> listChamps) throws IOException {
			 StringProcess uppercasefunct = new StringProcess();
	 			File packentity= new File(this.repo+"/"+this.dossierPackage+"entity");
	 	            packentity.mkdirs();
	 	            File controllerfiles = new File(packentity, "" + uppercasefunct.capitalize(composant) + ".java");
	 	            controllerfiles.createNewFile();
	 	            String filejava = this.packageKey + " "+ this.packagename+".entity;\n" +
	 	                    "\n" +
	 	                    "import java.io.Serializable;\n" +
							"import java.math.BigInteger;\n" +
							"import javax.persistence.Basic;\n" +
							"import javax.persistence.Column;\n" +
							"import javax.persistence.Entity;\n" +
							"import javax.persistence.GeneratedValue;\n" +
							"import javax.persistence.GenerationType;\n" +
							"import javax.persistence.Id;\n" +
							"import javax.persistence.Table;\n" +
							"import javax.validation.constraints.NotNull;\n"
							+ "import javax.persistence.JoinColumn;\r\n" + 
							"import javax.persistence.ManyToOne;\n" +
	 	                    "import javax.persistence.Temporal;\r\n" + 
	 	                    "import javax.persistence.TemporalType;\n"
	 	                    +"import sn.gainde2000.pi.core.*;\n"
	 	                    + "import java.util.Date;\n" +
	 	                    "@Entity\n" +
	 	                    "@Table(name = \"" + uppercasefunct.capitalize(composant) + "\")\n" +
							"public class "+uppercasefunct.capitalize(composant)+" implements Serializable {\n"+
	 	                    "    private static final long serialVersionUID = 1L;\n";

	 	            filejava = filejava+ "@Id\n" +
	 	                    "@GeneratedValue(strategy = GenerationType.AUTO)\n" +
	 	                    "@Basic(optional = false)\n" +
	 	                    "@NotNull\n" +
	 	                    "@Column(name = \"id\")\n" +
	 	                    "private Long id;\n";
	 	            /*generation declaration des attributs*/
	 	           int i=0;
	 	             for ( i = 0; i < listChamps.size(); i++) {
	 	                           
	 	                            
	 	                            if (listChamps.get(i).getChpType().equals("text") || 
	 	                            		listChamps.get(i).getChpType().equals("textarea")||
	 	                            		listChamps.get(i).getChpType().equals("phone")||
	 	                            		listChamps.get(i).getChpType().equals("email")||
	 	                            		listChamps.get(i).getChpType().equals("radio")||
	 	                            		listChamps.get(i).getChpType().equals("checkbox")||
	 	                            		listChamps.get(i).getChpType().equals("autocomplete")||
	 	                            		listChamps.get(i).getChpType().equals("textarea")){
	 	                            	filejava = filejava+ "@Column(name = \""+listChamps.get(i).getChpNom()+"\")\n";
	 	                                filejava = filejava+ "private String "+listChamps.get(i).getChpNom()+";\n" + "";
	 	                            }else if (listChamps.get(i).getChpType().equals("number")){
	 	                            	 filejava = filejava+ "@Column(name = \""+listChamps.get(i).getChpNom()+"\")\n";
	 	                                filejava = filejava+ "private Long "+listChamps.get(i).getChpNom()+";\n" + "";
	 	                            }
	 	                           else if (listChamps.get(i).getChpType().equals("relation")){
	 	                                filejava = filejava+ "@ManyToOne\r\n" + 
	 	                                		"@JoinColumn(name=\""+getMappingObjet(listChamps.get(i).getChpNom()).toString()+"\", nullable=false)\n"
	 	                                		+ "private "+ uppercasefunct.capitalize(getMappingObjet(listChamps.get(i).getChpNom()).toString()) +" "+getMappingObjet(listChamps.get(i).getChpNom()).toString()+";\n" + "";
	 	                            }
	 	                            else if (listChamps.get(i).getChpType().equals("date")){
	 	                            	 filejava = filejava+ "@Column(name = \""+listChamps.get(i).getChpNom()+"\")\n";
	 	                                filejava = filejava+ "@Temporal(TemporalType.TIMESTAMP)\n" +
	 	                                        "private Date "+listChamps.get(i).getChpNom()+";";
	 	                            }
	 	                            else{
	 	                                filejava = filejava+ " ";
	 	                            }

	 	                    filejava = filejava+ "\n" + "";

	 	                }
	 	          
	 	            /*generation declaration des getters setters*/
	 	            String filejavagettersetter = "";
	 	           int j=0;
	 	           for ( j = 0; j < listChamps.size(); j++) {
	 	                
	 	                		if (listChamps.get(j).getChpType().equals("text") ||
	 	                				listChamps.get(j).getChpType().equals("textarea")||
	 	                				listChamps.get(j).getChpType().equals("email")||
	 	                				listChamps.get(j).getChpType().equals("phone")||
	 	                				listChamps.get(j).getChpType().equals("autocomplete")||
	 	                				listChamps.get(j).getChpType().equals("radio")||
	 	                				listChamps.get(j).getChpType().equals("checkbox")){
	 	                		filejavagettersetter = filejavagettersetter+ "public String get"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"() {\n" +
	 	                        "        return "+listChamps.get(j).getChpNom()+";\n" +
	 	                        "    }\n" +
	 	                        "    public void set"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"(String "+listChamps.get(j).getChpNom()+") {\n" +
	 	                        "        this."+listChamps.get(j).getChpNom()+" = "+listChamps.get(j).getChpNom()+";\n" +
	 	                        "    }   \n" + "";
	 	                		}
	 	                		else if(listChamps.get(j).getChpType().equals("number")){
		 	                		filejavagettersetter = filejavagettersetter+ "public Long get"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"() {\n" +
		 	                        "        return "+listChamps.get(j).getChpNom()+";\n" +
		 	                        "    }\n" +
		 	                        "    public void set"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"(Long "+listChamps.get(j).getChpNom()+") {\n" +
		 	                        "        this."+listChamps.get(j).getChpNom()+" = "+listChamps.get(j).getChpNom()+";\n" +
		 	                        "    }   \n" + "";
		 	                		}
	 	                		
	 	                		else if(listChamps.get(j).getChpType().equals("relation")){
		 	                		filejavagettersetter = filejavagettersetter+ "public "+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+" get"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+"() {\n" +
		 	                        "        return "+getMappingObjet(listChamps.get(j).getChpNom()).toString()+";\n" +
		 	                        "    }\n" +
		 	                        "    public void set"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+"("+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+" "+getMappingObjet(listChamps.get(j).getChpNom()).toString()+") {\n" +
		 	                        "        this."+getMappingObjet(listChamps.get(j).getChpNom()).toString()+" = "+getMappingObjet(listChamps.get(j).getChpNom()).toString()+";\n" +
		 	                        "    }   \n" + "";
		 	                		}
	 	                		else if(listChamps.get(j).getChpType().equals("date")){
		 	                		filejavagettersetter = filejavagettersetter+ "public Date get"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"() {\n" +
		 	                        "        return "+listChamps.get(j).getChpNom()+";\n" +
		 	                        "    }\n" +
		 	                        "    public void set"+uppercasefunct.capitalize(listChamps.get(j).getChpNom())+"(Date "+listChamps.get(j).getChpNom()+") {\n" +
		 	                        "        this."+listChamps.get(j).getChpNom()+" = "+listChamps.get(j).getChpNom()+";\n" +
		 	                        "    }   \n" + "";
		 	                		}
	 	                		else{
	 	                			filejavagettersetter = filejavagettersetter+ " ";
 	                            }
	 	                    filejavagettersetter = filejavagettersetter+ "\n" + "";

	 	            }
	 	          filejavagettersetter = filejavagettersetter+ "public Long getId() {\r\n" + 
	 	            		"		return id;\r\n" + 
	 	            		"	}\r\n" + 
	 	            		"	public void setId(Long id) {\r\n" + 
	 	            		"		this.id = id;\r\n" + 
	 	            		"	}   \r\n" + 
	 	            		"\n";
	 	          
	 	            filejava = filejava+filejavagettersetter+ "\n" + "}";
	 	            FileWriter myWriterentity = new FileWriter(packentity+"\\" + composant + ".java");
	 	            myWriterentity.write("\n" +filejava);
	 	            myWriterentity.close();
	           
		}
		/*+++++++++++++++++++ Fin generation des fichiers model+++++++++++++++++++++++++*/
     /*+++++++++++++++++++ Generation des fichiers repository+++++++++++++++++++++++++*/
		
		public void generationFileRepositoryBack(String nomrepo) throws IOException {
			StringProcess uppercasefunct = new StringProcess();
	            File packrepo = new File(this.repo+"/"+this.dossierPackage+"repository");
	            packrepo.mkdirs();
	            File repofiles = new File(packrepo, ""+uppercasefunct.capitalize(nomrepo) +"Repository.java");
	            repofiles.createNewFile();
	            String repositoryfile =  this.packageKey+ " "+this.packagename+".repository;\n" +
	                    "\n" +
	                    this.importKey+" "+this.packagename+".entity."+uppercasefunct.capitalize(nomrepo)+";\n" +
	                    "import org.springframework.data.jpa.repository.JpaRepository;\n" +
	                    "import org.springframework.stereotype.Repository;\n" +
	                    "\n" +
	                    "@Repository\n" +
	                    "public interface "+uppercasefunct.capitalize(nomrepo)+"Repository extends JpaRepository<"+uppercasefunct.capitalize(nomrepo)+", Long> {\n" +
	                    "\n" +
	                    "}";

	            FileWriter myWriterrepo = new FileWriter(packrepo+"\\" + nomrepo + "Repository.java");
	            myWriterrepo.write("\n" +repositoryfile);
	            myWriterrepo.close();
		}
		/*+++++++++++++++++++ Fin generation des fichiers repository+++++++++++++++++++++++++*/

     /*+++++++++++++++++++ Generation des fichiers service+++++++++++++++++++++++++*/
		
		public void generationFileServiceBack(String nomserviceimpl) throws IOException {
			 //String nomserviceimpl = form.getFrmNom();
			StringProcess uppercasefunct = new StringProcess();
	            File packservice = new File(this.repo+"/"+this.dossierPackage+"service");
	            packservice.mkdirs();
	            
	            File serviceimplfiles = new File(packservice, "I"+uppercasefunct.capitalize(nomserviceimpl)+"Service.java");
	            serviceimplfiles.createNewFile();
	            String servicefileimpl = this.packageKey+" "+ this.packagename+".service;\n" +
	                    this.importKey+" "+this.packagename+".entity."+uppercasefunct.capitalize(nomserviceimpl)+";\n" +
	                    "import java.util.List;\n" +
	                    "\n" +
	                    "public interface I"+uppercasefunct.capitalize(nomserviceimpl)+"Service {\n" +
	                    "\n" +
	                    "List<"+uppercasefunct.capitalize(nomserviceimpl)+"> findAll();\n" +
	                     uppercasefunct.capitalize(nomserviceimpl) +" save("+uppercasefunct.capitalize(nomserviceimpl)+" "+nomserviceimpl.toLowerCase()+");\n"+
	                     "void delete("+uppercasefunct.capitalize(nomserviceimpl)+" "+nomserviceimpl.toLowerCase()+");\n"+
	                    "}";

	            FileWriter myWriteservicesimpl = new FileWriter(packservice+"\\I"+uppercasefunct.capitalize(nomserviceimpl)+"Service.java");
	            myWriteservicesimpl.write("\n" +servicefileimpl);
	            myWriteservicesimpl.close();

	           File packservi =  new File(this.repo+"/"+this.dossierPackage+"service/impl");
			   packservi.mkdirs();
	            File servicefiles = new File(packservi,uppercasefunct.capitalize(nomserviceimpl)+"ServiceImpl.java");
	            servicefiles.createNewFile();
	            String servicefile = this.packageKey+" "+ this.packagename+".service.impl;\n" +
	                    "\n" +
	                    this.importKey+" "+this.packagename+".entity."+uppercasefunct.capitalize(nomserviceimpl)+";\n" +
	                    this.importKey+" "+this.packagename+".service.I"+uppercasefunct.capitalize(nomserviceimpl)+"Service;\n" +
	                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
	                    "import org.springframework.stereotype.Service;\n" +
	                    this.importKey+" "+this.packagename+".repository."+uppercasefunct.capitalize(nomserviceimpl)+"Repository;"+
	                    "\n" +
	                    "import java.util.List;\n" +
	                    "\n" +
	                    "@Service\n" +
	                    "public class "+uppercasefunct.capitalize(nomserviceimpl)+"ServiceImpl implements I"+uppercasefunct.capitalize(nomserviceimpl)+"Service {\n" +
	                    "\n" +
	                    "    @Autowired\n" +
	                    "    private "+uppercasefunct.capitalize(nomserviceimpl)+"Repository "+nomserviceimpl.toLowerCase()+"Repository;\n" +
	                    "\n" +
	                    "    @Override\n" +
	                    "    public List<"+uppercasefunct.capitalize(nomserviceimpl)+"> findAll() {\n" +
	                    "\n" +
	                    "        return "+nomserviceimpl.toLowerCase()+"Repository.findAll();\n" +
	                    "    }\n" +
	                    "    @Override\n" +
	                    "    public "+uppercasefunct.capitalize(nomserviceimpl)+" save("+uppercasefunct.capitalize(nomserviceimpl)+" "+nomserviceimpl.toLowerCase()+") {\n" +
	                    "\n" +
	                    "        return "+nomserviceimpl.toLowerCase()+"Repository.save("+nomserviceimpl.toLowerCase()+");\n" +
	                    "    }\n" +
	                    "    @Override\n" +
	                    "    public void delete("+uppercasefunct.capitalize(nomserviceimpl)+" "+nomserviceimpl.toLowerCase()+") {\n" +
	                    "\n" +
	                    "  "+nomserviceimpl.toLowerCase()+"Repository.delete("+nomserviceimpl.toLowerCase()+");\n" +
	                    "    }\n" +
	                    "}";

	            FileWriter myWriteservices = new FileWriter(packservi+"\\"+uppercasefunct.capitalize(nomserviceimpl)+"ServiceImpl.java");
	            myWriteservices.write("\n" +servicefile);
	            myWriteservices.close();

		}
		 /*+++++++++++++++++++ Fin generation des fichiers service+++++++++++++++++++++++++*/
     /*++++++++++++++++++++++++generation controlleur++++++++++++++++++++++++++++++++++*/
		public void generationFileControllerBack(String nomcontroller) throws IOException {
			StringProcess uppercasefunct = new StringProcess();
	            File packcontroller = new File(this.repo+"/"+this.dossierPackage+"controller");
	            packcontroller.mkdirs();
	            File cntrlfiles = new File(packcontroller, ""+uppercasefunct.capitalize(nomcontroller)+"Controller.java");
	            cntrlfiles.createNewFile();

	            String controllerfile  =  this.packageKey+" "+this.packagename+".controller;\n" +
	                    "\n" +
	                    this.importKey+" "+this.packagename+".entity."+uppercasefunct.capitalize(nomcontroller)+";\n" +
	                    this.importKey+" "+this.packagename+".service.I"+nomcontroller+"Service;\n" +
	                    "import java.util.Optional;\n" +
	                    "\n" +
	                    "import org.springframework.beans.factory.annotation.Autowired;\n" +
	                    "import org.springframework.web.bind.annotation.CrossOrigin;\n" +
	                    "import org.springframework.web.bind.annotation.GetMapping;\n" +
	                    "import org.springframework.web.bind.annotation.PathVariable;\n" +
	                    "import org.springframework.web.bind.annotation.PostMapping;\n" +
	                    "import org.springframework.web.bind.annotation.RequestBody;\n" +
	                    "import org.springframework.web.bind.annotation.RestController;\n" +
	                    "import javax.validation.constraints.NotNull;"+
	                    "\n" +
	                    this.importKey+" "+this.packagename+".ServeurResponse.ServeurResponse;" +
	                    "\n" +
	                    "@RestController\n" +
	                    "public class "+uppercasefunct.capitalize(nomcontroller)+"Controller {\n" +
	                    "\n" +
	                    " @Autowired \n" +
	                    "I"+uppercasefunct.capitalize(nomcontroller)+"Service "+nomcontroller.toLowerCase()+"Service;\n" +
	                    "@GetMapping(\""+nomcontroller.toLowerCase()+"/list\")\n" +
	                    "    public ServeurResponse getAll"+nomcontroller+"() {\n" +
	                    "        Iterable<"+uppercasefunct.capitalize(nomcontroller)+"> "+nomcontroller.toLowerCase()+" = "+nomcontroller.toLowerCase()+"Service.findAll();\n" +
	                    "        ServeurResponse response = new ServeurResponse();\n" +
	                    "        if ("+nomcontroller.toLowerCase()+" == null) {\n" +
	                    "            response.setStatut(false);\n" +
	                    "            response.setData(null);\n" +
	                    "            response.setDescription(\"Aucune élèment trouvé.\");\n" +
	                    "\n" +
	                    "        } else {\n" +
	                    "            \n" +
	                    "            response.setStatut(true);\n" +
	                    "            response.setData("+nomcontroller.toLowerCase()+");\n" +
	                    "           response.setDescription(\"Données récupérées.\");\n" +
	                    "        }\n" +
	                    "        return response;\n" +
	                    "   }\n" +
	                    " @PostMapping(\""+nomcontroller.toLowerCase()+"/create\")\n" +
	                    "public ServeurResponse create(@RequestBody "+uppercasefunct.capitalize(nomcontroller)+" "+nomcontroller.toLowerCase()+") {\n" +
	                    "\n" +
	                    "ServeurResponse response = new ServeurResponse();\n" +
	                    "\n" +
	                    ""+nomcontroller.toLowerCase()+"Service.save("+nomcontroller.toLowerCase()+");\n" +
	                    "response.setStatut(true);\n" +
	                    "response.setDescription(\"Enregistrement réussi\");\n" +
	                    "response.setData("+nomcontroller.toLowerCase()+");\n" +
	                    "\n" +
	                    "return response;\n" +
	                    "}\n" +
	                    "@PostMapping(\""+nomcontroller.toLowerCase()+"/update\")\n" +
	                    "     public ServeurResponse update(@RequestBody "+uppercasefunct.capitalize(nomcontroller)+" "+nomcontroller.toLowerCase()+") {\n" +
	                    "          ServeurResponse response = new ServeurResponse();\n" +
	                    "\n" +
	                    "          "+nomcontroller.toLowerCase()+"Service.save("+nomcontroller.toLowerCase()+");\n" +
	                    "          response.setStatut(true);\n" +
	                    "               response.setDescription(\"Données mis à jour\");\n" +
	                    "          return response;\n" +
	                    "     }\n" +
	                    " @PostMapping(\""+nomcontroller.toLowerCase()+"/delete\")\n" +
	                    "     public ServeurResponse delete(@RequestBody "+uppercasefunct.capitalize(nomcontroller)+" "+nomcontroller.toLowerCase()+") {\n" +
	                    "          ServeurResponse response = new ServeurResponse();\n" +
	                    "          "+nomcontroller.toLowerCase()+"Service.delete("+nomcontroller.toLowerCase()+");\n" +
	                    "          response.setStatut(true);\n" +
	                    "          response.setDescription(\"Données supprimé avec succès\");\n" +
	                    "          return response;\n" +
	                    "     }\n" +
	                    "}";

	            FileWriter myWritecontroller = new FileWriter(packcontroller+"\\"+nomcontroller+"Controller.java");
	            myWritecontroller.write("\n" +controllerfile);
	            myWritecontroller.close();


	           
		}
		///fin back
	 
    //zipper le projet
	 public ZipOutputStream zipper(Long id,String nomApp,String desc) throws IOException {
		  String dossier_src = this.repo;
	        String fichier_zip_desc =this.repo+"\\"+id+"-"+nomApp+".zip";
		    List<String> liste;
		 final int BUFFER = 2048;

	     File node = new File(dossier_src);
	     liste = new ArrayList<>();
	     listeFichiers(node,liste,dossier_src);
	  
	   
	        byte buffer[] = new byte[BUFFER];
	      
	        FileOutputStream fileos = new FileOutputStream(fichier_zip_desc);
	        ZipOutputStream zipos = new ZipOutputStream(fileos);
	      
	        for(String file : liste){
	           ZipEntry zipentry= new ZipEntry(file);
	           zipos.putNextEntry(zipentry);
	           FileInputStream in =
	                      new FileInputStream(dossier_src + File.separator + file);
	 
	           int lec;
	           while ((lec = in.read(buffer)) > 0) {
	               zipos.write(buffer, 0, lec);
	           }
	 
	           in.close();
	           System.out.println("Fichier "+file+" ajouté");
	        }
	      
	        zipos.closeEntry();
	        zipos.close();

	        System.out.println("Fichier compressé créé!");
       return zipos;
	     

	   
	 }
	 
	 //preparation zip
	 public static List listeFichiers(File node, List<String> liste,String dossier_src){
		 
	        //ajouter les fichiers
	        if(node.isFile()){
	           String file = node.getAbsoluteFile().toString();
	           String chemin = file.substring(dossier_src.length()+1, file.length());
	           liste.add(chemin);
	        }
	 
	        if(node.isDirectory()){
	           String[] sousFichier = node.list();
	           for(String filename : sousFichier){
	              listeFichiers(new File(node, filename),liste,dossier_src);
	           }
	        }
	        return liste;
	    }
	 
	public boolean createProjet(String name) {
			int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
			try {
				if (SE.indexOf("nux") >= 0) {
					 Process process = new ProcessBuilder("cmd", "/C",
							this.repo+"/createprojet.sh "+name)
							  .inheritIO().start();

					exitValue=process.waitFor();
			        } else {
			        	 Process process = new ProcessBuilder("cmd", "/C",
								 this.repo+"/createprojet.bat "+name)
								  .inheritIO().start();

						exitValue=process.waitFor();
			        }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return exitValue==0;
	 }
	 
	public boolean createComposant(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/component.sh "+name.toLowerCase()+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();
		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/component.bat "+name.toLowerCase()+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	
	 
		public boolean deleteComposant(String nomProjet,String name) {
			int exitValue=1;
			String SE = System.getProperty("os.name").toLowerCase();
			try {
				
				if (SE.indexOf("nux") >= 0) {
					Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/supprimer.sh src/app/"+name.toLowerCase()+"/"+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
			        } else {
			       
			        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/supprimer.bat src/app/"+name.toLowerCase()+"/"+" "+this.repo+"/"+nomProjet).inheritIO().start();
						exitValue=process.waitFor();
			        }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return exitValue==0;
		}
	
	public boolean createComposantadd(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/component.sh "+name.toLowerCase()+"/components/add"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();
		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/component.bat "+name.toLowerCase()+"/components/add"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createComposantview(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/component.sh "+name.toLowerCase()+"/components/view"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();
		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/component.bat "+name.toLowerCase()+"/components/view"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createRouting(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/createrouting.sh "+name.toLowerCase()+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();

		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/createrouting.bat "+name.toLowerCase()+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createComposantList(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/component.sh "+name.toLowerCase()+"/components/list"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();

		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/component.bat "+name.toLowerCase()+"/components/list"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createComposantEdit(String nomProjet,String name) {
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/component.sh "+name.toLowerCase()+"/components/edit"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
				exitValue=process.waitFor();

		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/component.bat "+name.toLowerCase()+"/components/edit"+name+" "+this.repo+"/"+nomProjet).inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createService(String name, String nomProjet) throws IOException {
		
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/service.sh "+name.toLowerCase()+" "+this.repo+"/"+nomProjet)
						.inheritIO().start();
				exitValue=process.waitFor();

		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/service.bat "+name.toLowerCase()+" "+this.repo+"/"+nomProjet)
							.inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
	public boolean createModel(String name, String nomProjet) throws IOException {
		
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/createclass.sh "+name.toLowerCase()+" "+this.repo+"/"+nomProjet)
						.inheritIO().start();
				exitValue=process.waitFor();
		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/createclass.bat "+name.toLowerCase()+" "+this.repo+"/"+nomProjet)
							.inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
public boolean deleteModel(String name, String nomProjet) throws IOException {
		
		int exitValue=1;
		String SE = System.getProperty("os.name").toLowerCase();
		try {
			if (SE.indexOf("nux") >= 0) {
				Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/supprimer.sh src/app/"+name.toLowerCase()+"/model/"+name.toLowerCase()+"/"+" "+this.repo+"/"+nomProjet)
						.inheritIO().start();
				exitValue=process.waitFor();
		        } else {
		        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/supprimer.bat src/app/"+name.toLowerCase()+"/model/"+name.toLowerCase()+"/"+"  "+this.repo+"/"+nomProjet)
							.inheritIO().start();
					exitValue=process.waitFor();
		        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return exitValue==0;
	}
public boolean deleteService(String name, String nomProjet) throws IOException {
	
	int exitValue=1;
	String SE = System.getProperty("os.name").toLowerCase();
	try {
		if (SE.indexOf("nux") >= 0) {
			Process process = new ProcessBuilder("/bin/bash", "-c",this.repo+"/supprimer.sh src/app/"+name.toLowerCase()+"/service/"+name.toLowerCase()+"/"+" "+this.repo+"/"+nomProjet)
					.inheritIO().start();
			exitValue=process.waitFor();
	        } else {
	        	Process process = new ProcessBuilder("cmd", "/C", this.repo+"/supprimer.bat src/app/"+name.toLowerCase()+"/service/"+name.toLowerCase()+"/"+"  "+this.repo+"/"+nomProjet)
						.inheritIO().start();
				exitValue=process.waitFor();
	        }
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (InterruptedException e) {
		e.printStackTrace();
	}
	return exitValue==0;
}
	 public void updateComponent(String composant,String nomProjet,List<Champs> listChamps) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/add-"+composant.toLowerCase()+"/"+"add-"+composant.toLowerCase()+
					  ".component.ts");
			  myWriter.write("import { "+uppercasefunct.capitalize(composant)+"Service } from '../../service/"+composant.toLowerCase()+".service';\r\n" + 
			  		"import { "+uppercasefunct.capitalize(composant)+" } from '../../model/"+composant.toLowerCase()+"';\r\n" + 
			  		"import { Component, OnInit } from '@angular/core';\r\n" + 
			  		"import { Router } from '@angular/router';\r\n" + 
			  		"import { FormBuilder, Validators } from '@angular/forms';\r\n" + 
			  		"import { MatDialogRef, MatSnackBar } from '@angular/material';"+
			  		"\r\n" + 
			  		"@Component({\r\n" + 
			  		"  selector: 'app-add-"+composant.toLowerCase()+"',\r\n" + 
			  		"  templateUrl: './add-"+composant.toLowerCase()+".component.html',\r\n" + 
			  		"  styleUrls: ['./add-"+composant.toLowerCase()+".component.scss']\r\n" + 
			  		"})\r\n"
			  		+  
			  		"export class Add"+uppercasefunct.capitalize(composant)+"Component implements OnInit {\r\n" + 
			  		"result:any;\n"
			  		+ "resp:any;\n");
			  for (int j = 0; j < listChamps.size(); j++)
			    {
				  if (listChamps.get(j).getChpType().equals("relation")){
				  myWriter.write(""+getMappingObjet(listChamps.get(j).getChpNom().toLowerCase()).toString()+":any\n");
				  }
			    }
			  
			  myWriter.write("\r\n");
			  myWriter.write(""+uppercasefunct.capitalize(composant)+"Form = this.formbuild.group({");
			  for (int i = 0; i < listChamps.size(); i++)
  			    {
				  if(listChamps.get(i).getChpType().equals("relation")) {
					  myWriter.write(getMappingObjet(listChamps.get(i).getChpNom()).toString()+" :['', Validators.required],\r\n");
				  }else {
					  myWriter.write(listChamps.get(i).getChpNom()+" :['', Validators.required],\r\n");
				  }
  			       

  			    } 
			  myWriter.write(" });");
			  myWriter.write("  constructor(private "+composant.toLowerCase()+"Service: "+uppercasefunct.capitalize(composant)+"Service,\r\n" + 
			  		"    private router: Router,"
			  		+ " private formbuild: FormBuilder, \r\n" + 
			  		"    private _snackBar: MatSnackBar,\r\n" + 
			  		"    public dialogRef: MatDialogRef<Add"+uppercasefunct.capitalize(composant)+"Component >) { }\r\n" + 
			  		"\r\n" + 
			  		"  ngOnInit() {\r\n");
			  	for (int j = 0; j < listChamps.size(); j++)
			  		{
				  if (listChamps.get(j).getChpType().equals("relation")){
					  myWriter.write("this.get"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+"();");
				  }
				  }
			  	myWriter.write("  }\r\n" + 
			  		"\r\n" + 
			  		"   onSubmit() {\r\n" + 
			  		"    this."+composant.toLowerCase()+"Service.create"+uppercasefunct.capitalize(composant)+"(this."+uppercasefunct.capitalize(composant)+"Form.value).subscribe(data => {\r\n" + 
			  		"  \r\n"
			  		+ "  this.result=data \n\r" + 
			  		"      if (this.result.statut) {\r\n" + 
			  		"        this._snackBar.open(this.result.description, 'Verification', {\r\n" + 
			  		"          duration: 2000,\r\n" + 
			  		"        });\r\n" + 
			  		"\r\n" + 
			  		"        this."+uppercasefunct.capitalize(composant)+"Form.reset();\r\n" + 
			  		"        this.closeDialog();\r\n" + 
			  		"      }\r\n" + 
			  		"    }, error => {\r\n" + 
			  		"      alert('"+uppercasefunct.capitalize(composant)+" invalide');\r\n" + 
			  		"    });\r\n" + 
			  		"  }\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"  closeDialog() {\r\n" + 
			  		"    this.dialogRef.close();\r\n" + 
			  		"  }\r\n");
			  for (int j = 0; j < listChamps.size(); j++)
			    {
				  if (listChamps.get(j).getChpType().equals("relation")){
						 myWriter.write("  get"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+"(){\r\n" + 
							  		"         this."+composant.toLowerCase()+"Service.get"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(j).getChpNom()).toString())+"().subscribe(data=>{"
							  				+ "this.resp=data;\n"
							  				+ "this."+getMappingObjet(listChamps.get(j).getChpNom().toLowerCase()).toString()+"=this.resp.data})\r\n" + 
							  		"}\r\n");
	                   }

			    } 
			  myWriter.write("}");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 public void updateComponentCss(String composant,String nomProjet) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/add-"+composant.toLowerCase()+"/"+"add-"+composant.toLowerCase()+
					  ".component.scss");
			  myWriter.write(".image{\r\n" + 
			  		"  background: url(\"/assets/images/gainde/ia.jpg\");\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#test {\r\n" + 
			  		"  background-image: url();\r\n" + 
			  		"  background-size: cover;\r\n" + 
			  		"  /* background: url(http://www.petite-annonce-gratuite.com/layout_images/carte-france.png) no-repeat;\r\n" + 
			  		"  width: 100%;\r\n" + 
			  		"  height: 390px;*/\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"mat-grid-tile {\r\n" + 
			  		"  background: rgb(238, 239, 240);\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"@media screen and (min-width: 100%) and (max-width: 100%) {\r\n" + 
			  		"  #test {\r\n" + 
			  		"      width: 100%;\r\n" + 
			  		"      background-size: cover;\r\n" + 
			  		"  }\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		"body{\r\n" + 
			  		"  background-image: url(\"/assets/images/gainde/ia1.jpg\");\r\n" + 
			  		"  background-size: cover;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		".right{\r\n" + 
			  		"  margin-left: 20%;\r\n" + 
			  		"}\r\n" + 
			  		".top{\r\n" + 
			  		"  margin-top: 11%;\r\n" + 
			  		"}\r\n" + 
			  		".text{\r\n" + 
			  		"  font-size: 11px;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".center{\r\n" + 
			  		"  margin-bottom: 2%;\r\n" + 
			  		"  display: block;\r\n" + 
			  		"  margin:auto;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-page{\r\n" + 
			  		" // height: 90vh;\r\n" + 
			  		"//  width: 100%;\r\n" + 
			  		"  margin-top: -10%;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-spacer{\r\n" + 
			  		"  height: 12vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-container{\r\n" + 
			  		"  margin: auto;\r\n" + 
			  		"  width: 300px;\r\n" + 
			  		"  padding: 1vh;\r\n" + 
			  		"  background-color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-form{\r\n" + 
			  		"  display: flex;\r\n" + 
			  		"  flex-direction: column;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#login-fab {\r\n" + 
			  		"border-radius: 50%;\r\n" + 
			  		"  height: 56px;\r\n" + 
			  		"  margin: auto;\r\n" + 
			  		"  min-width: 56px;\r\n" + 
			  		"  width: 56px;\r\n" + 
			  		"  overflow: hidden;\r\n" + 
			  		"  background: #1e88e5;\r\n" + 
			  		"  box-shadow: 0 1px 1.5px 0 rgba(0,0,0,.12), 0 1px 1px 0 rgba(0,0,0,.24);\r\n" + 
			  		"  margin-top: -35px;\r\n" + 
			  		"  text-align: center;\r\n" + 
			  		"  left: 0;\r\n" + 
			  		"  right: 0;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#lock-icon{\r\n" + 
			  		"  line-height: 56px;\r\n" + 
			  		"  color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		".card-heading{\r\n" + 
			  		"  text-align: center;\r\n" + 
			  		"  color: rgba(0, 0, 0, 0.31);\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login{\r\n" + 
			  		"  padding-top: 2vh;\r\n" + 
			  		"  padding-bottom: 2vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login > button{\r\n" + 
			  		"  width: 40%;\r\n" + 
			  		"}\r\n" + 
			  		"button{\r\n" + 
			  		"  margin: 5%;\r\n" + 
			  		"}\r\n" + 
			  		".buttons .register{\r\n" + 
			  		"  display: flex;\r\n" + 
			  		"  padding-top: 4vh;\r\n" + 
			  		"  padding-bottom: 2vh;\r\n" + 
			  		"  justify-content: space-between;\r\n" + 
			  		"}\r\n" + 
			  		".app-header{\r\n" + 
			  		"  color: #fff;\r\n" + 
			  		"      background-color: #1e88e5;\r\n" + 
			  		"      text-align: center;\r\n" + 
			  		"      margin-top: -3px;\r\n" + 
			  		"      padding: 2px;\r\n" + 
			  		"  -webkit-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  -moz-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  }\r\n" + 
			  		"");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 public void updateComponentList(String composant,String nomProjet, List<Champs> listChamps) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/list-"+composant+"/"+"list-"+composant+
					  ".component.ts");
			  myWriter.write("import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';\r\n" + 
			  		"import { Validators, FormBuilder } from '@angular/forms';\r\n" + 
			  		"import { ActivatedRoute, Router } from '@angular/router';\r\n" + 
			  		"import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';\r\n" + 
			  		"import { Add"+uppercasefunct.capitalize(composant)+"Component } from '../add-"+composant.toLowerCase()+"/add-"+composant.toLowerCase()+".component';\r\n" + 
			  		"import { Edit"+uppercasefunct.capitalize(composant)+"Component } from '../edit-"+composant.toLowerCase()+"/edit-"+composant.toLowerCase()+".component';\r\n" +
			  		"import { View"+uppercasefunct.capitalize(composant)+"Component } from '../view-"+composant.toLowerCase()+"/view-"+composant.toLowerCase()+".component';\r\n" + 
			  		"import { "+uppercasefunct.capitalize(composant)+"Service } from '../../service/"+composant+".service';\r\n" + 
			  		"import { "+uppercasefunct.capitalize(composant)+"} from '../../model/"+composant.toLowerCase()+"';\r\n" + 
			  		"import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/sharedcomponent/confirm-dialog/confirm-dialog.component';\r\n" + 
			  		"import { TranslateService } from '@ngx-translate/core';\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"@Component({\r\n" + 
			  		"	selector: 'list-"+composant+"',\r\n" + 
			  		"	templateUrl: './list-"+composant+".component.html',\r\n" + 
			  		"	styleUrls: ['./list-"+composant+".component.scss']\r\n" + 
			  		"})\r\n" + 
			  		"export class List"+uppercasefunct.capitalize(composant)+"Component implements AfterViewInit {\r\n" + 
			  		"	@ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;\r\n" + 
			  		"	@ViewChild(MatSort) sort: MatSort;\r\n" + 
			  		"	form;\r\n result:any\r\n" + 
			  		"	dataSource: MatTableDataSource<"+uppercasefunct.capitalize(composant)+">;\r\n" + 
			  		"   langue;\r\n" + 
			  		"	constructor(private "+composant.toLowerCase()+"Service: "+uppercasefunct.capitalize(composant)+"Service, private dialogRef: MatDialog,\r\n" + 
			  		"		private route: ActivatedRoute,\r\n" + 
			  		"		private formbuild: FormBuilder,\r\n" + 
			  		"		private _snackBar: MatSnackBar, \r\n" + 
			  		"		private translate: TranslateService,\r\n" + 
			  		"		private router: Router) {\r\n" + 
			  		"	}\r\n" + 
			  		"	displayedColumns: string[] = [");
			  		   for (int k = 0; k < listChamps.size(); k++)
	 	   			    {
			  			   if(listChamps.get(k).getChpType().equals("relation")) {
			  				myWriter.write("'"+ getMappingObjet(listChamps.get(k).getChpNom()).toString()+"',");
			  			   }
			  			   else {
			  				 myWriter.write("'"+listChamps.get(k).getChpNom()+"',");
			  			   }
	 	
	 	   			    }
			  		   
			  		 
			  		 myWriter.write("'action'];\r\n"); 
			  		 
			  		 
			  		myWriter.write("\r\n" + 
			  		"	applyFilter(event: Event) {\r\n" + 
			  		"		const filterValue = (event.target as HTMLInputElement).value;\r\n" + 
			  		"		this.dataSource.filter = filterValue.trim().toLowerCase();\r\n" + 
			  		"	}\r\n" + 
			  		"\r\n" + 
			  		"	ngAfterViewInit() {\r\n" + 
			  		"		this.list"+uppercasefunct.capitalize(composant)+"();\r\n" + 
			  		"	}\r\n" + 
			  		"	ngOnInit() {\r\n" + 
			  		"		}\r\n" + 
			  		"\r\n" + 
			  		"	list"+uppercasefunct.capitalize(composant)+"() {\r\n" + 
			  		"		this."+composant.toLowerCase()+"Service.get"+uppercasefunct.capitalize(composant)+"All().subscribe(data => {\r\n"
			  				+ "this.form = data" + 
			  				"\r\n"+
			  		"			if (this.form.statut) {\r\n" + 
			  		"				//console.log('------------------------------');\r\n" + 
			  		"				console.log(this.form);\r\n" + 
			  		"				this.dataSource = new MatTableDataSource<"+uppercasefunct.capitalize(composant)+">(this.form.data);\r\n" + 
			  		"				this.dataSource.paginator = this.paginator;\r\n" + 
			  		"				this.dataSource.sort = this.sort;\r\n" + 
			  		"			} else {\r\n" + 
			  		"				window.alert(this.form.description);\r\n" + 
			  		"			}\r\n" + 
			  		"		})\r\n" + 
			  		"	}\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"	openDialogAdd(): void {\r\n" + 
			  		"		const dialog = this.dialogRef.open(Add"+uppercasefunct.capitalize(composant)+"Component, {\r\n" + 
			  		"			width: '700px',\r\n" + 
			  		"\r\n" + 
			  		"		}).afterClosed().subscribe(result => {\r\n" + 
			  		"			//location.reload();\r\n" + 
			  		"			this.list"+uppercasefunct.capitalize(composant)+"();\r\n" + 
			  		"		});\r\n" + 
			  		"\r\n" + 
			  		"	}\r\n" + 
			  		"	openDialogUpdate(username) {\r\n" + 
			  		"		console.log(username);\r\n" + 
			  		"		const dialog1 = this.dialogRef.open(Edit"+uppercasefunct.capitalize(composant)+"Component, {\r\n" + 
			  		"			width: '700px',\r\n" + 
			  		"			data: username\r\n" + 
			  		"		}).afterClosed().subscribe(result => {\r\n" + 
			  		"			//location.reload();\r\n" + 
			  		"			this.list"+uppercasefunct.capitalize(composant)+"();\r\n" + 
			  		"		});\r\n" + 
			  		"	}\r\n" +
			  		"openDialogDeleteUser(username) {\r\n" + 
			  		"		const message = \"utilisateur.alert-suppression\";\r\n" + 
			  		"		const dialogData = new ConfirmDialogModel(\"utilisateur.titre-suppression\", message);\r\n" + 
			  		"		const dialogRef = this.dialogRef.open(ConfirmDialogComponent, {\r\n" + 
			  		"			maxWidth: \"400px\",\r\n" + 
			  		"			data: dialogData\r\n" + 
			  		"		});\r\n" + 
			  		"		dialogRef.afterClosed().subscribe(dialogResult => {\r\n" + 
			  		"			if (dialogResult === true) {\r\n" + 
			  		"				this.delete(username);\r\n" + 
			  		"			}\r\n" + 
			  		"		});\r\n" + 
			  		"	}\r\n"+
			  		" delete(data) {\r\n" + 
			  		"		let messageSuccess;\r\n" + 
			  		"		let messageError;\r\n" + 
			  		"		this.translate.get('"+composant.toLowerCase()+".confirm-suppression').subscribe((res: string) => {\r\n" + 
			  		"			messageSuccess = res;\r\n" + 
			  		"		});\r\n" + 
			  		"		this.translate.get('"+composant.toLowerCase()+".erreur-suppression').subscribe((res: string) => {\r\n" + 
			  		"			messageError = res;\r\n" + 
			  		"		});\r\n" + 
			  		"		this."+composant.toLowerCase()+"Service.delete"+uppercasefunct.capitalize(composant)+"(data).subscribe(data => {\r\n" + 
			  		"				this.result=data\r\n"+
			  		"			if (this.result.statut) {\r\n" + 
			  		"				this._snackBar.open(messageSuccess, 'Verification', {\r\n" + 
			  		"					duration: 2000,\r\n" + 
			  		"				});\r\n" + 
			  		"			} else {\r\n" + 
			  		"				this._snackBar.open(messageError, 'Verification', {\r\n" + 
			  		"					duration: 2000,\r\n" + 
			  		"				});\r\n" + 
			  		"			}\r\n" + 
			  		"			this.list"+uppercasefunct.capitalize(composant)+"();\r\n" + 
			  		"		});\r\n" + 
			  		"	}\r\n"
			  		+ "openDialogView(username) {\r\n" + 
			  		"		console.log(username);\r\n" + 
			  		"		const dialog1 = this.dialogRef.open(View"+uppercasefunct.capitalize(composant)+"Component, {\r\n" + 
			  		"			width: '700px',\r\n" + 
			  		"			data: username\r\n" + 
			  		"		}).afterClosed().subscribe(result => {\r\n" + 
			  		"			//location.reload();\r\n" + 
			  		"			this.list"+uppercasefunct.capitalize(composant)+"();\r\n" + 
			  		"		});\r\n" + 
			  		"	}"+
			  	
			  		"}");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 public void updateComponentListCss(String composant,String nomProjet) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/list-"+composant.toLowerCase()+"/"+"list-"+composant.toLowerCase()+
					  ".component.scss");
			  myWriter.write("@import \"~bootstrap/scss/bootstrap\";\r\n" + 
			  		".position-relative {\r\n" + 
			  		"	position: relative;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".add-contact {\r\n" + 
			  		"	position: absolute;\r\n" + 
			  		"    right: 25px;\r\n" + 
			  		"    top: 30px;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".t{\r\n" + 
			  		"  //@extend: 1px solid;\r\n" + 
			  		"  background-color: rgb(162, 160, 175);\r\n" + 
			  		" }\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		" .petit-card{\r\n" + 
			  		"   margin: auto;\r\n" + 
			  		"   border-left: 60px solid;\r\n" + 
			  		"   border-color: rgb(128, 128, 173);\r\n" + 
			  		" //  border: 1px solid;\r\n" + 
			  		" }\r\n" + 
			  		" \r\n" + 
			  		"table {\r\n" + 
			  		"    width: 100%;\r\n" + 
			  		"   margin-top: 30px;\r\n" + 
			  		"  }\r\n" + 
			  		"  .fab{\r\n" + 
			  		"      width: 12%;\r\n" + 
			  		"      margin: 0.5%;\r\n" + 
			  		"  }\r\n" + 
			  		"  th.mat-sort-header-sorted {\r\n" + 
			  		"    color: black;\r\n" + 
			  		"  }\r\n" + 
			  		"  .mat-form-field {\r\n" + 
			  		"    font-size: 14px;\r\n" + 
			  		"    width: 20%;\r\n" + 
			  		"    margin-top: 30px;\r\n" + 
			  		"    margin-left: 10px;\r\n" + 
			  		"\r\n" + 
			  		"  }\r\n" + 
			  		" \r\n" + 
			  		" \r\n" + 
			  		"  .header{\r\n" + 
			  		"  margin-top: 10px;\r\n" + 
			  		"  display: block;\r\n" + 
			  		"  margin: auto;\r\n" + 
			  		"  text-align: center;\r\n" + 
			  		"  }\r\n" + 
			  		"  .foot{\r\n" + 
			  		"    background:rgb(90, 90, 158);\r\n" + 
			  		"    text-align: center;\r\n" + 
			  		"  }\r\n" + 
			  		"  .mymargin{\r\n" + 
			  		"   margin: 7px;\r\n" + 
			  		"  }\r\n" + 
			  		"  .container {\r\n" + 
			  		"    display: grid;\r\n" + 
			  		"    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));\r\n" + 
			  		"    grid-gap: 0.5em;\r\n" + 
			  		"    padding: 0.5em 0.5em;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"h3 {\r\n" + 
			  		"  font-weight: normal;\r\n" + 
			  		"  color: #616161;\r\n" + 
			  		"  font-size: 16px;\r\n" + 
			  		"  margin: 8px;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".entete{\r\n" + 
			  		"  width: 100%;\r\n" + 
			  		"  background: rgb(233, 230, 230);\r\n" + 
			  		" // margin-top: -5%;\r\n" + 
			  		"  position: relative;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"// .number{\r\n" + 
			  		"\r\n" + 
			  		"//   text-align: justify;\r\n" + 
			  		"//   margin: auto;\r\n" + 
			  		"//  font-family: cursive;\r\n" + 
			  		"//   font-size:200%;\r\n" + 
			  		"// }\r\n" + 
			  		"// .type{\r\n" + 
			  		"//   margin-right: 4px;\r\n" + 
			  		"//   text-align: justify;\r\n" + 
			  		"//   margin: auto; \r\n" + 
			  		"//   font-family: fantasy;\r\n" + 
			  		"\r\n" + 
			  		"// }\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		".card {\r\n" + 
			  		"  box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.14);\r\n" + 
			  		"  border: 0;\r\n" + 
			  		"  margin-bottom: 30px;\r\n" + 
			  		"  margin-top: 30px;\r\n" + 
			  		"  border-radius: 6px;\r\n" + 
			  		"  color: #333333;\r\n" + 
			  		"  background: #fff;\r\n" + 
			  		"  width: 100%;\r\n" + 
			  		"  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);\r\n" + 
			  		"  position: relative;\r\n" + 
			  		"    display: -webkit-box;\r\n" + 
			  		"    display: flex;\r\n" + 
			  		"    -webkit-box-orient: vertical;\r\n" + 
			  		"    -webkit-box-direction: normal;\r\n" + 
			  		"    flex-direction: column;\r\n" + 
			  		"    min-width: 0;\r\n" + 
			  		"    word-wrap: break-word;\r\n" + 
			  		"    background-color: #fff;\r\n" + 
			  		"    background-clip: border-box;\r\n" + 
			  		"    border: 1px solid #eeeeee;\r\n" + 
			  		"    border-radius: 0.25rem;\r\n" + 
			  		"   \r\n" + 
			  		"\r\n" + 
			  		"}");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 public void updateRoutingForm(String nomprojet,String composant) throws IOException {
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
					this.repo+"/"+nomprojet+"/src/app/"+composant.toLowerCase()+"/"+composant.toLowerCase()+"-routing.module.ts");
		 myWriter.write("import { NgModule } from '@angular/core';\r\n" + 
		 		"import { Routes, RouterModule } from '@angular/router';\r\n" + 
		 		"import { "+uppercasefunct.capitalize(composant)+"Component } from './"+composant.toLowerCase()+".component';\r\n" + 
		 		"import { List"+uppercasefunct.capitalize(composant)+"Component } from './components/list-"+composant.toLowerCase()+"/list-"+composant.toLowerCase()+".component';\r\n" + 
		 		"\r\n" + 
		 		"const routes: Routes = [\r\n" + 
		 		"  {\r\n" + 
		 		"    path: '', component: "+uppercasefunct.capitalize(composant)+"Component,\r\n" + 
		 		"    children: [\r\n" + 
		 		"      { path: '', component: List"+uppercasefunct.capitalize(composant)+"Component },\r\n" + 
		 		"    ],\r\n" + 
		 		"\r\n" + 
		 		"  },\r\n" + 
		 		"  { path: '**', redirectTo: '' }\r\n" + 
		 		"\r\n" + 
		 		"];\r\n" + 
		 		"\r\n" + 
		 		"@NgModule({\r\n" + 
		 		"  imports: [RouterModule.forChild(routes)],\r\n" + 
		 		"  exports: [RouterModule]\r\n" + 
		 		"})\r\n" + 
		 		"export class "+uppercasefunct.capitalize(composant)+"RoutingModule { }");
			myWriter.close();
	 }
	 
	 public void updateRoutingApp(String nomprojet,String composant,List<Formulaire> listFormulaire)  throws IOException{
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
					this.repo+"/"+nomprojet+"/src/app/app-routing.module.ts");
		 myWriter.write("import { NgModule } from '@angular/core';\r\n" + 
		 		"import { Routes, RouterModule } from '@angular/router';\r\n" + 
		 		"import { LoginComponent } from './login/login.component';\r\n" + 
		 		"//import { HomeComponent } from './home/home.component';\r\n" + 
		 		"import { GuardGuard } from './utilisateur/services/guard.guard';\r\n" + 
		 		"import { ExecutionComponent } from './workflow/execution/execution.component';\r\n" + 
		 		"const routes: Routes = [\r\n" + 
		 		"  { path: 'login',loadChildren: './login/login.module#LoginModule' },\r\n" + 
		 		"  { path: 'home',canActivate: [GuardGuard], loadChildren: './home/home.module#HomeModule' },\r\n" + 
		 		"  { path: 'application',canActivate: [GuardGuard], loadChildren: './application/application.module#ApplicationModule' },\r\n" + 
		 		"  { path: 'utilisateur',canActivate: [GuardGuard], loadChildren: './utilisateur/utilisateur.module#UtilisateurModule' },\r\n" + 
		 		"  { path: 'workflow', canActivate: [GuardGuard], loadChildren: './workflow/workflow.module#WorkflowModule' },\r\n" + 
		 		"  { path: 'parametrage', canActivate: [GuardGuard], loadChildren: './parametrage/parametrage.module#ParametrageModule' },\r\n" + 
		 		"  { path: 'formulaire', canActivate: [GuardGuard], loadChildren: './formulaire/formulaire.module#FormulaireModule' },\r\n" + 
		 		"  { path: 'fichier', canActivate: [GuardGuard], loadChildren: './fichier/fichier.module#FichierModule' },\r\n" + 
		 		"   { path: 'workflow/execution', component: ExecutionComponent },\r\n");
		  for (int k = 0; k < listFormulaire.size(); k++) {
		 		myWriter.write("{ path: '"+listFormulaire.get(k).getFrmNom().toLowerCase()+"', canActivate: [GuardGuard], loadChildren: './"+listFormulaire.get(k).getFrmNom().toLowerCase()+"/"+listFormulaire.get(k).getFrmNom().toLowerCase()+".module#"+uppercasefunct.capitalize(listFormulaire.get(k).getFrmNom())+"Module' },\r\n" );
		  }
		 		
       myWriter.write("\r\n" + 
		 		"  { path: '**', redirectTo: '/login', pathMatch: 'full' },\r\n" + 
		 		"];\r\n" + 
		 		"\r\n" + 
		 		"@NgModule({\r\n" + 
		 		"  imports: [RouterModule.forRoot(routes)],\r\n" + 
		 		"  exports: [RouterModule],\r\n" + 
		 		"  providers: [GuardGuard]\r\n" + 
		 		"})\r\n" + 
		 		"export class AppRoutingModule { }\r\n" + 
		 		"");
		 myWriter.close();
	 }
	 public void updateComposantFormTs(String nomprojet,String composant)throws IOException  {
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
					this.repo+"/"+nomprojet+"/src/app/"+composant.toLowerCase()+"/"+composant.toLowerCase()+".component.ts");
		 myWriter.write("import { Component, OnInit } from '@angular/core';\r\n" + 
		 		"import { MatIconRegistry } from '@angular/material';\r\n" + 
		 		"import { DomSanitizer } from '@angular/platform-browser';\r\n" + 
		 		"\r\n" + 
		 		"@Component({\r\n" + 
		 		"  selector: 'app-"+composant.toLowerCase()+"',\r\n" + 
		 		"  template: `\r\n" + 
		 		"  <app-sidenav></app-sidenav>\r\n" + 
		 		"  `,\r\n" + 
		 		"  styles: []\r\n" + 
		 		"})\r\n" + 
		 		"export class "+uppercasefunct.capitalize(composant)+"Component implements OnInit {\r\n" + 
		 		"\r\n" + 
		 		"  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {\r\n" + 
		 		"    iconRegistry.addSvgIconSet(sanitizer.bypassSecurityTrustResourceUrl('assets/avatars.svg'));\r\n" + 
		 		"   }\r\n" + 
		 		"\r\n" + 
		 		"  ngOnInit() {\r\n" + 
		 		"  }\r\n" + 
		 		"\r\n" + 
		 		"}");
		 myWriter.close();
	 }
	
	 public void updateComponentAddHtml(String nomprojet,String composant,List<Champs> listChamps,  IValue iValue) throws IOException {
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
	   					this.repo+"/"+nomprojet+"/src/app/"+composant.toLowerCase()+"/components/add-"+composant.toLowerCase()+"/"+"add-"+composant.toLowerCase()+
	   							".component.html");
	   			myWriter.write("<div class=\"login-page\">\r\n" + 
	   					"    <div class=\"login-spacer\"></div>\r\n" + 
	   					"    <div class=\"\">\r\n" + 
	   					"      <div id=\"login-fab\" class=\"mdl-color--accent mdl-color-text--white\">\r\n" + 
	   					"        <i id=\"lock-icon\" class=\"material-icons\">account_balance</i>\r\n" + 
	   					"      </div>\r\n" + 
	   					"      <h2 class=\"card-heading\">\r\n" + 
	   					"        "+composant.toLowerCase()+"</h2>\r\n" + 
	   					"      <form class=\"\" [formGroup]=\""+uppercasefunct.capitalize(composant)+"Form\" (ngSubmit)=\"onSubmit()\">");
	   			for (int k = 0; k < listChamps.size(); k++)
	   			{
	   				List<ValueChamps> valueChamps= iValue.listByChampId(listChamps.get(k).getChpId());
	   			    if(listChamps.get(k).getChpType().equals("autocomplete")) {
	   			    	
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"             <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"                  <mat-select placeholder="+listChamps.get(k).getChpPlaceholder()+" formControlName=\""+listChamps.get(k).getChpNom()+"\"required>\r\n");
	   			    	for (int w = 0; w < valueChamps.size(); w++) {
	   			    		myWriter.write("<mat-option  value=\""+valueChamps.get(w).getValue()+"\">"+valueChamps.get(w).getLabel()+"</mat-option>\r\n");
	   			    	}
	   			    			
	   			    	myWriter.write(" </mat-select>\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }
	   			    else if(listChamps.get(k).getChpType().equals("relation")) {
	   			    	
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"             <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"                  <mat-select placeholder="+listChamps.get(k).getChpPlaceholder()+" formControlName=\""+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"\"required>\r\n"
	   			    	
	   			    		    +"<mat-option  *ngFor=\"let item of "+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"\"  [value]=\"item\">{{item."+getMappingChamps(listChamps.get(k).getChpChamps()).toString()+"}}</mat-option>\r\n");
	   			    
	   			    			
	   			    	myWriter.write(" </mat-select>\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }
	   			    
	   			    else if(listChamps.get(k).getChpType().equals("radio")) {
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"         <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"<mat-radio-group  formContolName= \""+listChamps.get(k).getChpNom()+"\">"
	   			          );
	   			    	for (int x = 0; x < valueChamps.size(); x++) {
	   			    		myWriter.write("<mat-radio-button  value=\""+valueChamps.get(x).getValue()+"\">"+valueChamps.get(x).getLabel()+"</mat-radio-button >\r\n");
	   			    	}
	   			    	myWriter.write(" </mat-radio-group >\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }else if(listChamps.get(k).getChpType().equals("checkbox")) {
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"         <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"<section  formContolName= \""+listChamps.get(k).getChpNom()+"\">"
	   			          );
	   			    	for (int x = 0; x < valueChamps.size(); x++) {
	   			    		myWriter.write("<mat-checkbox  value=\""+valueChamps.get(x).getValue()+"\">"+valueChamps.get(x).getLabel()+"</mat-checkbox >\r\n");
	   			    	}
	   			    	myWriter.write(" </section >\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }  
	   			    
	   			    else if(listChamps.get(k).getChpType().equals("textarea")) {
		   				myWriter.write(" <mat-form-field>\r\n" + 
		   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
		   						"          <textarea matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
		   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required> </textarea>\r\n" + 
		   						"        </mat-form-field>\r\n");
	 
		   			    }
	   			 else if(listChamps.get(k).getChpType().equals("date")) {
		   				myWriter.write(" <mat-form-field appearance=\"fill\">\r\n"+
		   								"<mat-label>"+listChamps.get(k).getChpPlaceholder()+"</mat-label>\r\n" +  
		   						"          <input matInput [matDatepicker]=\"picker\" \r\n" + 
		   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required>\r\n" + 
		   						"<mat-datepicker-toggle matSuffix [for]=\"picker\"></mat-datepicker-toggle>\r\n" + 
		   						"  <mat-datepicker #picker startView=\"year\" [startAt]=\"startDate\"></mat-datepicker>\r\n"+
		   						"        </mat-form-field>\r\n");
	 
		   			    }
	   			    
	   			else if(listChamps.get(k).getChpType().equals("email")) {
	   				myWriter.write(" <mat-form-field>\r\n" + 
	   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   						"          <input matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
	   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$\">\r\n" + 
	   						"        </mat-form-field>\r\n");
	   			}
	   			    else {
	   				myWriter.write(" <mat-form-field>\r\n" + 
	   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   						"          <input matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
	   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required >\r\n" + 
	   						"        </mat-form-field>\r\n");
 
	   			    }
	   			} 
	   			
	   			myWriter.write(" <div class=\"buttons\">\r\n" + 
	   					"          <div class=\"login\">\r\n" + 
	   					"            <button mat-raised-button color=\"primary\" type=\"submit\">{{'btn_valider' | translate}}</button>\r\n" + 
	   					"            <button mat-raised-button color=\"warn\" (click)=\"closeDialog()\" type=\"button\"\r\n" + 
	   					"              routerLink=\"/dashboard\">{{'btn_fermer' | translate}}</button>\r\n" + 
	   					"          </div>\r\n" + 
	   					"        </div>\r\n" + 
	   					"      </form>\r\n" + 
	   					"    </div>\r\n" + 
	   					"  </div>");
	   			myWriter.close();
	 }
	 public void updateComponentListHtml(String composant,String nomProjet, List<Champs> listChamps) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/list-"+composant.toLowerCase()+"/"+"list-"+composant.toLowerCase()+
					  ".component.html");
			  myWriter.write("<mat-tab-group>\r\n" + 
			  		"    <mat-tab>\r\n" + 
			  		"        <ng-template mat-tab-label>\r\n" + 
			  		"            <mat-icon color=\"primary\">portrait</mat-icon>&nbsp;\r\n" + 
			  		"            "+composant+"\r\n" + 
			  		"        </ng-template>\r\n" + 
			  		"        <mat-card class=\"\">\r\n" + 
			  		"            <mat-card-header class=\"card-header\">\r\n" + 
			  		"                <span class=\"header\">"+composant+"</span>\r\n" + 
			  		"            </mat-card-header>\r\n" + 
			  		"            <mat-form-field class=\"filtre\">\r\n" + 
			  		"                <mat-label>{{'utilisateur.filtre' | translate}}</mat-label>\r\n" + 
			  		"                <input matInput (keyup)=\"applyFilter($event)\" placeholder=\"Search\">\r\n" + 
			  		"            </mat-form-field>\r\n" + 
			  		"            <button mat-mini-fab color=\"primary\" class=\"add-contact\" (click)=\"openDialogAdd()\"\r\n" + 
			  		"                matTooltip=\"{{'utilisateur.ajout' | translate}}\">\r\n" + 
			  		"                <mat-icon>person_add</mat-icon>\r\n" + 
			  		"            </button>\r\n" + 
			  		"            <div class=\"\">\r\n" + 
			  		"                <table mat-table class=\" mat-elevation-z8\" [dataSource]=\"dataSource\" matSort>\r\n" );
					         for (int k = 0; k < listChamps.size(); k++)
		  			           {
		  			             if(listChamps.get(k).getChpType().equals("relation")) {
		  			               myWriter.write("<ng-container matColumnDef=\""+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"\">\r\n" + 
		  							  		"                        <th mat-header-cell *matHeaderCellDef mat-sort-header>"+getMappingObjet(listChamps.get(k).getChpNom()).toString()+" </th>\r\n" + 
		  							  		"                       <td mat-cell *matCellDef=\"let element\"> {{element."+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"."+getMappingChamps(listChamps.get(k).getChpChamps()).toString()+"}} </td>\r\n" + 
		  							  		"                    </ng-container>\r\n" 
		  							  		);
		  			             }else {
		  			              myWriter.write("<ng-container matColumnDef=\""+listChamps.get(k).getChpNom()+"\">\r\n" + 
					  		"                        <th mat-header-cell *matHeaderCellDef mat-sort-header>"+listChamps.get(k).getChpNom()+" </th>\r\n" + 
					  		"                       <td mat-cell *matCellDef=\"let element\"> {{element."+listChamps.get(k).getChpNom()+"}} </td>\r\n" + 
					  		"                    </ng-container>\r\n" 
					  		);
		  			          }
		  			          }
	
						myWriter.write(" <ng-container matColumnDef=\"action\">\r\n" + 
					  		"                        <th mat-header-cell *matHeaderCellDef><span style=\"margin-left: 13%;\">\r\n" + 
					  		"                                {{'register.action' | translate}}</span> </th>\r\n" + 
					  		"                        <td mat-cell *matCellDef=\"let element\" class=\"fab\">\r\n" + 
					  		"                            <mat-icon matTooltip=\"Details\" color=\"primary\" (click)=\"openDialogView(element)\">\r\n" + 
					  		"                                visibility\r\n" + 
					  		"                            </mat-icon>\r\n" + 
					  		"				<mat-icon matTooltip=\"Details\" color=\"primary\" (click)=\"openDialogUpdate(element)\">\r\n" + 
								  		"                                edit\r\n" + 
								  		"                            </mat-icon>\r\n" + 
					  		"                            <!-- <mat-icon color=\"secondary\" (click)=\"statut(element)\">lock</mat-icon> -->\r\n" + 
					  		"                            <mat-icon color=\"warn\" matTooltip=\"Delete\" (click)=\"openDialogDeleteUser(element)\">delete\r\n" + 
					  		"                            </mat-icon>\r\n" +  
					  		
					  		"                        </td>\r\n" + 
					  		"                    </ng-container>\r\n" +
					  		
					  		"                    <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\r\n" + 
					  		"                    <tr mat-row *matRowDef=\"let row; columns: displayedColumns;\"></tr>\r\n" + 
					  		"                </table>\r\n" + 
					  		"\r\n" + 
					  		"                <mat-paginator [pageSizeOptions]=\"[5, 10, 20]\" showFirstLastButtons></mat-paginator>\r\n" + 
					  		"            </div>\r\n" + 
					  		"\r\n" + 
					  		"        </mat-card>\r\n" + 
					  		"\r\n" + 
					  		"    </mat-tab>\r\n" +  
					  		"</mat-tab-group>");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 
	 //update html edit
	 public void updateComponentEditHtml(String composant,String nomProjet, List<Champs> listChamps,IValue iValue) throws IOException {
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
	   					this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/edit-"+composant.toLowerCase()+"/"+"edit-"+composant.toLowerCase()+
	   							".component.html");
	   			myWriter.write("<div class=\"login-page\">\r\n" + 
	   					"    <div class=\"login-spacer\"></div>\r\n" + 
	   					"    <div class=\"\">\r\n" + 
	   					"      <div id=\"login-fab\" class=\"mdl-color--accent mdl-color-text--white\">\r\n" + 
	   					"        <i id=\"lock-icon\" class=\"material-icons\">account_balance</i>\r\n" + 
	   					"      </div>\r\n" + 
	   					"      <h2 class=\"card-heading\">\r\n" + 
	   					"        update "+composant.toLowerCase()+"</h2>\r\n" + 
	   					"      <form class=\"\" [formGroup]=\""+uppercasefunct.capitalize(composant)+"Form\" (ngSubmit)=\"onSubmit()\">");
	   			for (int k = 0; k < listChamps.size(); k++)
	   			{
	   			List<ValueChamps> valueChamps= iValue.listByChampId(listChamps.get(k).getChpId());
	   				if(listChamps.get(k).getChpType().equals("autocomplete")) {
	   			    	
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"             <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"                  <mat-select placeholder="+listChamps.get(k).getChpPlaceholder()+" formControlName=\""+listChamps.get(k).getChpNom()+"\"required>\r\n");
	   			    	for (int w = 0; w < valueChamps.size(); w++) {
	   			    		myWriter.write("<mat-option  value=\""+valueChamps.get(w).getValue()+"\">"+valueChamps.get(w).getLabel()+"</mat-option>\r\n");
	   			    	}
	   			    			
	   			    	myWriter.write(" </mat-select>\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }
	   			 else if(listChamps.get(k).getChpType().equals("relation")) {
	   			    	
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"             <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"                  <mat-select placeholder="+listChamps.get(k).getChpPlaceholder()+" formControlName=\""+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"\"required>\r\n"
	   			    	
	   			    		    +"<mat-option  *ngFor=\"let item of "+getMappingObjet(listChamps.get(k).getChpNom().toLowerCase()).toString()+"\"  [value]=\"item\">{{item."+getMappingChamps(listChamps.get(k).getChpChamps()).toString()+"}}</mat-option>\r\n");
	   			    
	   			    			
	   			    	myWriter.write(" </mat-select>\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }
	   			    
	   				else if(listChamps.get(k).getChpType().equals("radio")) {
	   					
	   			    	myWriter.write("  <mat-form-field>\r\n" + 
	   			    			"         <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   			    			"<mat-radio-group  formContolName= \""+listChamps.get(k).getChpNom()+"\">"
	   			          );
	   			    	for (int x = 0; x < valueChamps.size(); x++) {
	   			    		myWriter.write("<mat-radio-button  value=\""+valueChamps.get(x).getValue()+"\">"+valueChamps.get(x).getLabel()+"</mat-radio-button >\r\n");
	   			    	}
	   			    	myWriter.write(" </mat-radio-group >\r\n" + 
	   			    			"      </mat-form-field>");
	   			    }
	   				else if(listChamps.get(k).getChpType().equals("textarea")) {
		   				myWriter.write(" <mat-form-field>\r\n" + 
		   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
		   						"          <textarea matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
		   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required> </textarea>\r\n" + 
		   						"        </mat-form-field>\r\n");
	 
		   			    }
	   			 else if(listChamps.get(k).getChpType().equals("date")) {
		   				myWriter.write(" <mat-form-field appearance=\"fill\">\r\n"+
		   								"<mat-label>"+listChamps.get(k).getChpPlaceholder()+"</mat-label>\r\n" +  
		   						"          <input matInput [matDatepicker]=\"picker\" \r\n" + 
		   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required>\r\n" + 
		   						"<mat-datepicker-toggle matSuffix [for]=\"picker\"></mat-datepicker-toggle>\r\n" + 
		   						"  <mat-datepicker #picker startView=\"year\" [startAt]=\"startDate\"></mat-datepicker>\r\n"+
		   						"        </mat-form-field>\r\n");
	 
		   			    }
	   			else if(listChamps.get(k).getChpType().equals("number")) {
	   				myWriter.write(" <mat-form-field>\r\n" + 
	   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   						"          <input matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
	   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required>\r\n" + 
	   						"        </mat-form-field>\r\n");
	   			}
	   				else {
	   				myWriter.write(" <mat-form-field>\r\n" + 
	   						"          <mat-icon matSuffix>"+listChamps.get(k).getChpIcon()+"</mat-icon>\r\n" + 
	   						"          <input matInput placeholder=\""+listChamps.get(k).getChpPlaceholder()+"\" type=\""+listChamps.get(k).getChpType()+"\"\r\n" + 
	   						"            formControlName=\""+listChamps.get(k).getChpNom()+"\" required>\r\n" + 
	   						"        </mat-form-field>\r\n");
	   			    }
	   		
	   			}
	   			myWriter.write(" <div class=\"buttons\">\r\n" + 
	   					"          <div class=\"login\">\r\n" + 
	   					"            <button mat-raised-button color=\"primary\" type=\"submit\">{{'btn_valider' | translate}}</button>\r\n" + 
	   					"            <button mat-raised-button color=\"warn\" (click)=\"closeDialog()\" type=\"button\"\r\n" + 
	   					"              routerLink=\"/dashboard\">{{'btn_fermer' | translate}}</button>\r\n" + 
	   					"          </div>\r\n" + 
	   					"        </div>\r\n" + 
	   					"      </form>\r\n" + 
	   					"    </div>\r\n" + 
	   					"  </div>");
	   			myWriter.close();
	 }
	 
	 public void updateComponentEditTs(String composant,String nomProjet,List<Champs> listChamps) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/edit-"+composant.toLowerCase()+"/"+"edit-"+composant.toLowerCase()+
					  ".component.ts");
			  myWriter.write("import { "+uppercasefunct.capitalize(composant)+"Service } from '../../service/"+composant.toLowerCase()+".service';\r\n" + 
			  		"import { "+uppercasefunct.capitalize(composant)+" } from '../../model/"+composant.toLowerCase()+"';\r\n" + 
			  		"import { Component, OnInit,Inject } from '@angular/core';\r\n" + 
			  		"import { Router } from '@angular/router';\r\n" + 
			  		"import { FormBuilder, Validators } from '@angular/forms';\r\n" + 
			  		"import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';"+
			  		"\r\n" + 
			  		"@Component({\r\n" + 
			  		"  selector: 'app-edit-"+composant.toLowerCase()+"',\r\n" + 
			  		"  templateUrl: './edit-"+composant.toLowerCase()+".component.html',\r\n" + 
			  		"  styleUrls: ['./edit-"+composant.toLowerCase()+".component.scss']\r\n" + 
			  		"})\r\n"
			  		+  
			  		"export class Edit"+uppercasefunct.capitalize(composant)+"Component implements OnInit {\r\n" + 
			  		"result:any;" +
			  		"\r\n");
			  myWriter.write(""+uppercasefunct.capitalize(composant)+"Form = this.formbuild.group({\r\n"+
			  		"id :['', Validators.required],\r\n");
			  for (int i = 0; i < listChamps.size(); i++)
			    {
				  if(listChamps.get(i).getChpType().equals("relation")) {
					  myWriter.write(getMappingObjet(listChamps.get(i).getChpNom()).toString()+" :['', Validators.required],\r\n");
				  }else {
			       myWriter.write(listChamps.get(i).getChpNom()+" :['', Validators.required],\r\n");
				  }
			    } 
			  myWriter.write(" });");
			  myWriter.write("  constructor(private "+composant.toLowerCase()+"Service: "+uppercasefunct.capitalize(composant)+"Service,\r\n" + 
			  		"    private router: Router,"
			  		+ " private formbuild: FormBuilder, \r\n" + 
			  		"    private _snackBar: MatSnackBar,\r\n" + 
			  		"    public dialogRef: MatDialogRef<Edit"+uppercasefunct.capitalize(composant)+"Component>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }\r\n" + 
			  		"\r\n" + 
			  		"  ngOnInit() {\r\n" + 
			  			"this.initView();\r\n"+
			  		"  }\r\n" + 
			  		"initView() {   \r\n" + 
			  		"    this."+uppercasefunct.capitalize(composant)+"Form.setValue({ \r\n" + 
			  		"     id: this.donnee.id,\r\n");
			  for (int j = 0; j < listChamps.size(); j++) {
				  if(listChamps.get(j).getChpType().equals("relation")) {
					 
					  myWriter.write( getMappingObjet(listChamps.get(j).getChpNom()).toString()+":this.donnee."+ getMappingObjet(listChamps.get(j).getChpNom()).toString()+",\r\n");
				  }else {
					  myWriter.write(listChamps.get(j).getChpNom()+":this.donnee."+listChamps.get(j).getChpNom()+",\r\n");
				  }
				  
			  		
			  }	
            myWriter.write(" });  \r\n" + 
			  		"  }"+
			  		"\r\n" + 
			  		"   onSubmit() {\r\n" + 
			  		"    this."+composant.toLowerCase()+"Service.create"+uppercasefunct.capitalize(composant)+"(this."+uppercasefunct.capitalize(composant)+"Form.value).subscribe(data => {\r\n" + 
			  		"  \r\n"
			  		+ "  this.result=data \n\r" + 
			  		"      if (this.result.statut) {\r\n" + 
			  		"        this._snackBar.open(this.result.description, 'Verification', {\r\n" + 
			  		"          duration: 2000,\r\n" + 
			  		"        });\r\n" + 
			  		"\r\n" + 
			  		"        this."+uppercasefunct.capitalize(composant)+"Form.reset();\r\n" + 
			  		"        this.closeDialog();\r\n" + 
			  		"      }\r\n" + 
			  		"    }, error => {\r\n" + 
			  		"      alert('"+uppercasefunct.capitalize(composant)+" invalide');\r\n" + 
			  		"    });\r\n" + 
			  		"  }\r\n" + 
			  		"\r\n" + 
			  		"\r\n" + 
			  		"  closeDialog() {\r\n" + 
			  		"    this.dialogRef.close();\r\n" + 
			  		"  }\r\n" + 
			  		"}");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 
	 public void updateComponentEditCss(String composant,String nomProjet) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/edit-"+composant.toLowerCase()+"/"+"edit-"+composant.toLowerCase()+
					  ".component.scss");
			  myWriter.write(".image{\r\n" + 
			  		"  background: url(\"/assets/images/gainde/ia.jpg\");\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#test {\r\n" + 
			  		"  background-image: url();\r\n" + 
			  		"  background-size: cover;\r\n" + 
			  		"  /* background: url(http://www.petite-annonce-gratuite.com/layout_images/carte-france.png) no-repeat;\r\n" + 
			  		"  width: 100%;\r\n" + 
			  		"  height: 390px;*/\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"mat-grid-tile {\r\n" + 
			  		"  background: rgb(238, 239, 240);\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"@media screen and (min-width: 100%) and (max-width: 100%) {\r\n" + 
			  		"  #test {\r\n" + 
			  		"      width: 100%;\r\n" + 
			  		"      background-size: cover;\r\n" + 
			  		"  }\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		"body{\r\n" + 
			  		"  background-image: url(\"/assets/images/gainde/ia1.jpg\");\r\n" + 
			  		"  background-size: cover;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		".right{\r\n" + 
			  		"  margin-left: 20%;\r\n" + 
			  		"}\r\n" + 
			  		".top{\r\n" + 
			  		"  margin-top: 11%;\r\n" + 
			  		"}\r\n" + 
			  		".text{\r\n" + 
			  		"  font-size: 11px;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".center{\r\n" + 
			  		"  margin-bottom: 2%;\r\n" + 
			  		"  display: block;\r\n" + 
			  		"  margin:auto;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-page{\r\n" + 
			  		" // height: 90vh;\r\n" + 
			  		"//  width: 100%;\r\n" + 
			  		"  margin-top: -10%;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-spacer{\r\n" + 
			  		"  height: 12vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-container{\r\n" + 
			  		"  margin: auto;\r\n" + 
			  		"  width: 300px;\r\n" + 
			  		"  padding: 1vh;\r\n" + 
			  		"  background-color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-form{\r\n" + 
			  		"  display: flex;\r\n" + 
			  		"  flex-direction: column;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#login-fab {\r\n" + 
			  		"border-radius: 50%;\r\n" + 
			  		"  height: 56px;\r\n" + 
			  		"  margin: auto;\r\n" + 
			  		"  min-width: 56px;\r\n" + 
			  		"  width: 56px;\r\n" + 
			  		"  overflow: hidden;\r\n" + 
			  		"  background: #1e88e5;\r\n" + 
			  		"  box-shadow: 0 1px 1.5px 0 rgba(0,0,0,.12), 0 1px 1px 0 rgba(0,0,0,.24);\r\n" + 
			  		"  margin-top: -35px;\r\n" + 
			  		"  text-align: center;\r\n" + 
			  		"  left: 0;\r\n" + 
			  		"  right: 0;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#lock-icon{\r\n" + 
			  		"  line-height: 56px;\r\n" + 
			  		"  color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		".card-heading{\r\n" + 
			  		"  text-align: center;\r\n" + 
			  		"  color: rgba(0, 0, 0, 0.31);\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login{\r\n" + 
			  		"  padding-top: 2vh;\r\n" + 
			  		"  padding-bottom: 2vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login > button{\r\n" + 
			  		"  width: 40%;\r\n" + 
			  		"}\r\n" + 
			  		"button{\r\n" + 
			  		"  margin: 5%;\r\n" + 
			  		"}\r\n" + 
			  		".buttons .register{\r\n" + 
			  		"  display: flex;\r\n" + 
			  		"  padding-top: 4vh;\r\n" + 
			  		"  padding-bottom: 2vh;\r\n" + 
			  		"  justify-content: space-between;\r\n" + 
			  		"}\r\n" + 
			  		".app-header{\r\n" + 
			  		"  color: #fff;\r\n" + 
			  		"      background-color: #1e88e5;\r\n" + 
			  		"      text-align: center;\r\n" + 
			  		"      margin-top: -3px;\r\n" + 
			  		"      padding: 2px;\r\n" + 
			  		"  -webkit-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  -moz-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"  }\r\n" + 
			  		"");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 public void updateComponentViewTs(String composant,String nomProjet,List<Champs> listChamps) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/view-"+composant.toLowerCase()+"/"+"view-"+composant.toLowerCase()+
					  ".component.ts");
			  myWriter.write("import { "+uppercasefunct.capitalize(composant)+"Service } from '../../service/"+composant.toLowerCase()+".service';\r\n" + 
			  		"import { "+uppercasefunct.capitalize(composant)+" } from '../../model/"+composant.toLowerCase()+"';\r\n" + 
			  		"import { Component, OnInit,Inject } from '@angular/core';\r\n" + 
			  		"import { Router } from '@angular/router';\r\n" + 
			  		"import { FormBuilder, Validators } from '@angular/forms';\r\n" + 
			  		"import { MatDialogRef, MatSnackBar,MAT_DIALOG_DATA } from '@angular/material';"+
			  		"\r\n" + 
			  		"@Component({\r\n" + 
			  		"  selector: 'app-view-"+composant.toLowerCase()+"',\r\n" + 
			  		"  templateUrl: './view-"+composant.toLowerCase()+".component.html',\r\n" + 
			  		"  styleUrls: ['./view-"+composant.toLowerCase()+".component.scss']\r\n" + 
			  		"})\r\n"
			  		+  
			  		"export class View"+uppercasefunct.capitalize(composant)+"Component implements OnInit {\r\n" + 
			  		"result:any;" +
			  		"\r\n");
			  
			  
			 
			  myWriter.write("  constructor(private "+composant.toLowerCase()+"Service: "+uppercasefunct.capitalize(composant)+"Service,\r\n" + 
			  		"    private router: Router,"
			  		+ " private formbuild: FormBuilder, \r\n" + 
			  		"    private _snackBar: MatSnackBar,\r\n" + 
			  		"    public dialogRef: MatDialogRef<View"+uppercasefunct.capitalize(composant)+"Component>,@Inject(MAT_DIALOG_DATA) public donnee: any) { }\r\n" + 
			  		"\r\n" + 
			  		"  ngOnInit() {\r\n" + 
			  			"this.detail();\r\n"+
			  		"  }\r\n" + 
			  		"detail() {   \r\n"
			  		+ "this.result = this.donnee;" + 
			  	 
			  		"  }"+
			  		"\r\n" + 
			  		"  closeDialog() {\r\n" + 
			  		"    this.dialogRef.close();\r\n" + 
			  		"  }\r\n" + 
			  		"}");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	    }
	 
	 public void updateComponentViewCss(String composant,String nomProjet) {
	    	String rep = this.repo ; 
			  try {
			 
				  StringProcess uppercasefunct = new StringProcess();
			  FileWriter myWriter = new FileWriter(
			 this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/view-"+composant.toLowerCase()+"/"+"view-"+composant.toLowerCase()+
					  ".component.scss");
			  myWriter.write(".image{\r\n" + 
			  		"    background: url(\"/assets/images/gainde/ia.jpg\");\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#test {\r\n" + 
			  		"    background-image: url();\r\n" + 
			  		"    background-size: cover;\r\n" + 
			  		"    /* background: url(http://www.petite-annonce-gratuite.com/layout_images/carte-france.png) no-repeat; \r\n" + 
			  		"    width: 100%;\r\n" + 
			  		"    height: 390px;*/\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"mat-grid-tile {\r\n" + 
			  		"    background: rgb(238, 239, 240);\r\n" + 
			  		"  }\r\n" + 
			  		"\r\n" + 
			  		"@media screen and (min-width: 100%) and (max-width: 100%) {\r\n" + 
			  		"    #test {\r\n" + 
			  		"        width: 100%;\r\n" + 
			  		"        background-size: cover;\r\n" + 
			  		"    }\r\n" + 
			  		"    \r\n" + 
			  		"}\r\n" + 
			  		"body{\r\n" + 
			  		"    background-image: url(\"/assets/images/gainde/ia1.jpg\");\r\n" + 
			  		"    background-size: cover;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		".right{\r\n" + 
			  		"    margin-left: 20%;\r\n" + 
			  		"}\r\n" + 
			  		".top{\r\n" + 
			  		"    margin-top: 11%;\r\n" + 
			  		"}\r\n" + 
			  		".text{\r\n" + 
			  		"    font-size: 11px;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".center{\r\n" + 
			  		"    margin-bottom: 2%;\r\n" + 
			  		"    display: block;\r\n" + 
			  		"    margin:auto;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-page{\r\n" + 
			  		"   // height: 90vh;\r\n" + 
			  		"  //  width: 100%;\r\n" + 
			  		"    margin-top: -10%;\r\n" + 
			  		"\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-spacer{\r\n" + 
			  		"    height: 12vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-container{\r\n" + 
			  		"    margin: auto;\r\n" + 
			  		"    width: 300px;\r\n" + 
			  		"    padding: 1vh;\r\n" + 
			  		"    background-color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".login-form{\r\n" + 
			  		"    display: flex;\r\n" + 
			  		"    flex-direction: column;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#login-fab {\r\n" + 
			  		"	border-radius: 50%;\r\n" + 
			  		"    height: 56px;\r\n" + 
			  		"    margin: auto;\r\n" + 
			  		"    min-width: 56px;\r\n" + 
			  		"    width: 56px;\r\n" + 
			  		"    overflow: hidden;\r\n" + 
			  		"    background: #1e88e5;\r\n" + 
			  		"    box-shadow: 0 1px 1.5px 0 rgba(0,0,0,.12), 0 1px 1px 0 rgba(0,0,0,.24);\r\n" + 
			  		"    margin-top: -35px;\r\n" + 
			  		"    text-align: center;\r\n" + 
			  		"    left: 0;\r\n" + 
			  		"    right: 0;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		"#lock-icon{\r\n" + 
			  		"    line-height: 56px;\r\n" + 
			  		"    color: #fff;\r\n" + 
			  		"}\r\n" + 
			  		".card-heading{\r\n" + 
			  		"    text-align: center;\r\n" + 
			  		"    color: rgba(0, 0, 0, 0.31);\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login{\r\n" + 
			  		"    padding-top: 2vh;\r\n" + 
			  		"    padding-bottom: 2vh;\r\n" + 
			  		"}\r\n" + 
			  		"\r\n" + 
			  		".buttons .login > button{\r\n" + 
			  		"    width: 40%;\r\n" + 
			  		"}\r\n" + 
			  		"button{\r\n" + 
			  		"    margin: 5%;\r\n" + 
			  		"}\r\n" + 
			  		".buttons .register{\r\n" + 
			  		"    display: flex;\r\n" + 
			  		"    padding-top: 4vh;\r\n" + 
			  		"    padding-bottom: 2vh;\r\n" + 
			  		"    justify-content: space-between;\r\n" + 
			  		"}\r\n" + 
			  		".app-header{\r\n" + 
			  		"    color: #fff;\r\n" + 
			  		"        background-color: #1e88e5;\r\n" + 
			  		"        text-align: center;\r\n" + 
			  		"        margin-top: -3px;\r\n" + 
			  		"        padding: 2px;\r\n" + 
			  		"    -webkit-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"    -moz-box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"    box-shadow: 2px 4px 58px -20px rgba(0,0,0,1);\r\n" + 
			  		"    }\r\n" + 
			  		"");
					  
  					myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
	 }
	 public void updateComponentViewHtml(String composant,String nomProjet, List<Champs> listChamps) throws IOException {
		 StringProcess uppercasefunct = new StringProcess();
		 FileWriter myWriter = new FileWriter(
	   					this.repo+"/"+nomProjet+"/src/app/"+composant.toLowerCase()+"/components/view-"+composant.toLowerCase()+"/"+"view-"+composant.toLowerCase()+
	   							".component.html");
	   			myWriter.write("  <mat-card-header>\r\n" + 
	   					"    <mat-card-subtitle>Detail</mat-card-subtitle>\r\n" + 
	   					"\r\n" + 
	   					"  </mat-card-header>\r\n "
	   					+ "<mat-card-content>\r\n" + 
	   					"    <div class=\"row\">\r\n" + 
	   					"      <div class=\"col-sm-6 col-lg-6 col-md-6\">");
	   			for (int k = 0; k < listChamps.size(); k++)
	   			{
	   			  if(listChamps.get(k).getChpType().equals("relation")) {
	   				myWriter.write("<mat-label>"+getMappingObjet(listChamps.get(k).getChpNom()).toString()+": {{result."+getMappingObjet(listChamps.get(k).getChpNom()).toString()+"."+getMappingChamps(listChamps.get(k).getChpChamps()).toString()+"}}</mat-label><br>\r\n" + 
	   						"        <hr>");
	   			  }else {
	   				myWriter.write("<mat-label>"+listChamps.get(k).getChpNom()+": {{result."+listChamps.get(k).getChpNom()+"}}</mat-label><br>\r\n" + 
	   						"        <hr>");
	   			  }
	   			
	   			}
	   			myWriter.write(" </div>\r\n" + 
	   					"    </div>\r\n" + 
	   					"  </mat-card-content>\r\n" + 
	   					"  <mat-card-actions>\r\n" + 
	   					"    <button mat-button color=\"primary\" (click)=\"closeDialog()\">{{'btn_fermer' | translate}}</button>\r\n" + 
	   					"\r\n" + 
	   					"  </mat-card-actions>");
	   			myWriter.close();
	 }
	 //update service
		public void updateService(String taskName,String nomProjet,List<Champs> listChamps) {
			 //String rep = this.repo ;
			 StringProcess uppercasefunct = new StringProcess();
			  try {
			 
			  
			  FileWriter myWriter = new FileWriter(
			  this.repo+"/"+nomProjet+"/src/app/"+taskName.toLowerCase()+"/service/"+taskName.toLowerCase()+
			  ".service.ts");
			  myWriter.write("import { Injectable } from '@angular/core';\r\n" + 
			  		"import { HttpClient} from '@angular/common/http';\r\n"
			  		+ "import { environment } from 'src/environments/environment';\r\n" + 
			  		"@Injectable({\r\n" + 
			  		"  providedIn: 'root'\r\n" + 
			  		"})\r\n"
			  		+ 
			  		"export class "+uppercasefunct.capitalize(taskName)+"Service {\r\n" + 
			  		 "api = environment.apii;" + 
			  		"\r\n" + 
			  		"  constructor(private http:HttpClient) {"+
			  		"  }\r\n" + 
			  		"  \r\n" + 
			  		"  create"+uppercasefunct.capitalize(taskName)+"(data){\r\n" + 
			  		"       return this.http.post(this.api+\""+taskName.toLowerCase()+"/create\",data)\r\n" + 
			  		"  }\r\n" + 
			  		"  get"+uppercasefunct.capitalize(taskName)+"All(){\r\n" + 
			  		"        return this.http.get(this.api+\""+taskName.toLowerCase()+"/list\")\r\n" + 
			  		"  } \r\n" + 
			  		"  delete"+uppercasefunct.capitalize(taskName)+"(data){\r\n" + 
			  		"    return this.http.post(this.api+\""+taskName.toLowerCase()+"/delete\",data)\r\n" + 
			  		"}"+
			  		
			  		"\n");
			  for (int i = 0; i < listChamps.size(); i++)
			    {
				 if (listChamps.get(i).getChpType().equals("relation")){
					 myWriter.write("  get"+uppercasefunct.capitalize(getMappingObjet(listChamps.get(i).getChpNom()).toString())+"(){\r\n" + 
						  		"        return this.http.get(this.api+\""+getMappingObjet(listChamps.get(i).getChpNom()).toString()+"/list\")\r\n" + 
						  		"}\r\n");
                   }

			    } 
			  myWriter.write("}\r\n");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file."); }
			  catch (IOException e) {
				  System.out.println("An error occurred.");
				  e.printStackTrace(); 
				}
		}
		//update model
				public void updateModel(String taskName,String nomProjet,List<Champs> listChamps) {
					StringProcess uppercasefunct = new StringProcess();
					 String rep = this.repo ; 
					  try {
					 
					  
					  FileWriter myWriter = new FileWriter(
					  this.repo+"/"+nomProjet+"/src/app/"+taskName.toLowerCase()+"/model/"+taskName.toLowerCase()+
					  ".ts");
					  myWriter.write("export class "+uppercasefunct.capitalize(taskName)+" {");
						for (int k = 0; k < listChamps.size(); k++)
		 	   			{
						 if(listChamps.get(k).getChpType().equals("relation")) {
							  myWriter.write(getMappingObjet(listChamps.get(k).getChpNom()).toString()+":any;");
						 }else {
							  myWriter.write(listChamps.get(k).getChpNom()+":any;");
						 }
		 	   		

		 	   			}
		 	   			myWriter.write("}");
					  myWriter.close();
					  System.out.println("Successfully wrote to the file."); }
					  catch (IOException e) {
						  System.out.println("An error occurred.");
						  e.printStackTrace(); 
						}
				}
				
				// fichier de configuration des donnees statiques
				public void initfichierstatique() throws IOException {
					File packentity= new File(this.repo+"/configapp");
	 	            packentity.mkdirs();
	 	           File controllerfiles = new File(packentity, "configdatastatique.sql");
	 	            controllerfiles.createNewFile();
	 	           FileWriter myWriter = new FileWriter(this.repo+"/configapp/configdatastatique.sql");
	 	           myWriter.write("INSERT INTO `tp_profil` (`pro_id`, `pro_description`, `pro_libelle`) VALUES(1, 'Admin applicatif', 'administrateur fontionnel');\r\n");
	 	           myWriter.write("INSERT INTO `td_utilisateur` (`uti_id`, `uti_adresse`, `uti_app_id`, `uti_date_creation`, `uti_date_modification`, `uti_email`, `uti_nom`, `uti_password`, `uti_prenom`, `uti_telephone`, `uti_username`, `uti_actived`, `uti_premier_connect`, `uti_pro_id`, `uti_couleur`, `uti_lng_id`, `uti_thm_id`) VALUES(1, 'admin',NULL, NULL, NULL, 'admin@gainde2000', '', '$2a$10$q/kuau7WLiaYidFNF3QtEO5o860/RKJPayVyNV5qv6RNMvNNvc7ZK', 'admin', '777777777', 'admin', 1, 0, 2, NULL, NULL, 1);\r\n");
	 	           myWriter.write("INSERT INTO `tp_menu` (`men_id`, `men_icone`, `men_icone_color`, `men_id_parent`, `men_nom`, `men_path`) VALUES ('1', 'supervisor_account', 'primary', NULL, 'utilisateur', 'utilisateur');\r\n");
	 	           myWriter.write("INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('1', 'create_profil', 'Créer un profil', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('2', 'edit_profil', 'Modifier un profil', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('3', 'delete_profil', 'Supprimer un profil', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('4', 'create_user', 'Créer un utilisateur', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('5', 'edit_user', 'Modifier un utilisateur', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('6', 'delete_user', 'Supprimer un utilisateur', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('7', 'enable_user', 'Activer/desactiver un utilisateur', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('8', 'set_acces', 'Définir les droits d\\'acces', '1');\r\n" + 
		 	   					  "INSERT INTO `tp_action` (`act_id`, `act_code`, `act_nom`, `act_men_id`) VALUES ('9', 'edit_acces', 'Modifier les droits d\\'acces', '1');\r\n"); 
	 	          myWriter.write( "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (1,1, 1);\r\n" + 
		 	   					  "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (2,1, 2);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (3,1, 3);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (4,1, 4);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (5,1, 5);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (6,1, 6);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (7,1, 7);\r\n" + 
		 	   				      "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (8,1, 8);\r\n" + 
		 	   					  "INSERT INTO `tr_privilege` (`pri_id`,`pri_pro_id`, `pri_act_id`) VALUES (9,1, 9);");
	 	         myWriter.close();
				  System.out.println("Ficher de configuration des donnees statique crees");
				}
				//configuration application
				public void initialiserApp(
						Application app, 
						List<Theme> listeTheme,
						List<Workflow> listeWorkflow,
						List<Formulaire> listeFormulaire,
						List<Widget> listeWidget,
						List<Langue> listeLangue,
						List<PwdCriteria> listePwdCritaire,
						List<Fichier> listeFichier,
						List<Parametre> listeParametre
						) throws IOException {
					
					 File packentity= new File(this.repo+"/configapp");
		 	            packentity.mkdirs();
		 	           File controllerfiles = new File(packentity, "configdatadynamique.sql");
		 	            controllerfiles.createNewFile();
		 	            
		 	         // List<Theme> listeTheme = iThemeService.getListTheme();
					  try {
					 
					  
					  FileWriter myWriter = new FileWriter(
					  this.repo+"/configapp/configdatadynamique.sql");
					  //recuperation de L'application
					  myWriter.write("INSERT INTO `td_application` (`app_id`, `app_adresse`, `app_date_creation`, `app_email`, `app_ninea`, `app_nom`, `app_nom_entreprise`, `app_secteur`, `app_telephone_fixe`, `app_telephone_mobile`, `parametre`, `app_uti_id`, `app_etape_creation`) VALUES\r\n" + 
							  "	("+app.getAppId()+", '"+app.getAppAdresse()+"', NULL, '"+app.getAppEmail()+"', '"+app.getAppNinea()+"', '"+app.getAppNomEntreprise()+"', '"+app.getAppSecteur()+"', '"+app.getAppTelephoneFixe()+"', '"+app.getAppTelephoneMobile()+"', 'oo', NULL, NULL, NULL);\r\n" + 
							  "\r\n");
					
						//liste des widgets
						for (int a = 0; a < listeWidget.size(); a++)
		 	   			{
							 myWriter.write("INSERT INTO `tp_widget` (`wdg_id`, `wdg_code`, `wdg_description`, `wdg_nom`, `wdg_ordre`, `wdg_longueur`, `wdg_type`) VALUES("+listeWidget.get(a).getWdgId()+", '"+listeWidget.get(a).getWdgCode()+"', '"+listeWidget.get(a).getWdgDescription()+"','"+listeWidget.get(a).getWdgNom()+"','"+listeWidget.get(a).getWdgOrdre()+"','"+listeWidget.get(a).getWdgLongueur()+"','"+listeWidget.get(a).getWdgType()+"');\r\n");
		 	   	
		 	   			}
						//liste des langues
						for (int b = 0; b < listeLangue.size(); b++)
		 	   			{
							 myWriter.write("INSERT INTO `tp_langue` (`lng_id`, `lng_code`, `lng_disposition_text`, `lng_langue`)VALUES("+listeLangue.get(b).getLngId()+", '"+listeLangue.get(b).getLngCode()+"', '"+listeLangue.get(b).getLngDispositionText()+"','"+listeLangue.get(b).getLngLangue()+"');\r\n");
		 	   	
		 	   			}
								
						//parametre par defaut
						for (int e = 0; e < listeParametre.size(); e++)
		 	   			{
							 myWriter.write("INSERT INTO `tp_parametre` (`param_id`, `param_uti_username`, `param_lng_id`, `param_thm_id`, `param_img_id`) VALUES("+listeParametre.get(e).getParamId()+", 'admin', null,'"+listeParametre.get(e).getParam_lng_id().getLngId()+"','"+listeParametre.get(e).getParam_thm_id().getThmId()+"');\r\n");
		 	   	
		 	   			}
					
						//liste des themes
						for (int k = 0; k < listeTheme.size(); k++)
		 	   			{
							 myWriter.write("INSERT INTO `tp_theme` (`thm_id`, `thm_accent`, `thm_primary`, `thm_is_dark`, `thm_is_default`, `thm_name`) VALUES("+listeTheme.get(k).getThmId()+", '"+listeTheme.get(k).getThmAccent()+"', '"+listeTheme.get(k).getThmPrimary()+"', b'0', b'0', '"+listeTheme.get(k).getThmName()+"');\r\n");
		 	   	
		 	   			}
					
					    // liste des formulaire
						for (int j = 0; j < listeFormulaire.size(); j++)
		 	   			{
							
							 myWriter.write("INSERT INTO `tp_formulaire` (`frm_id`, `frm_app_id`, `frm_description`, `frm_nom`, `frm_status`, `frm_valider`) VALUES("+listeFormulaire.get(j).getFrmId()+", '"+listeFormulaire.get(j).getFrmAppId()+"', '"+listeFormulaire.get(j).getFrmDescription()+"', '"+listeFormulaire.get(j).getFrmNom()+"', "+listeFormulaire.get(j).getFrmStatus()+", '"+listeFormulaire.get(j).getFrmValider()+"');\r\n");
		 	   	
		 	   			}
				        //liste des workflows
						for (int i = 0; i < listeWorkflow.size(); i++)
		 	   			{
							
							 myWriter.write("INSERT INTO `tp_workflow` (`wkf_id`, `wkf_app_id`, `wkf_artifact_id`, `wkf_conteneur`, `wkf_nom`, `description`,`name`,`version`,`wkf_etat`, `wkf_process_id`) VALUES("+listeWorkflow.get(i).getWkfId()+", '"+listeWorkflow.get(i).getWkfAppId().getAppId()+"', '"+listeWorkflow.get(i).getWkfArtifactId()+"', '"+listeWorkflow.get(i).getWkfConteneur()+"', '"+listeWorkflow.get(i).getName()+"', '"+listeWorkflow.get(i).getDescription()+"',"
							 		+ "'"+listeWorkflow.get(i).getName()+"','"+listeWorkflow.get(i).getVersion()+"','"+listeWorkflow.get(i).getWkfEtat()+"','"+listeWorkflow.get(i).getWkfProcess_id()+"');\r\n");
		 	   	
		 	   			}
						//critaire mot de passe 
						for (int c = 0; c < listePwdCritaire.size(); c++)
		 	   			{
							
							 myWriter.write("INSERT INTO `tp_pwd_criteria` (`pwd_id`, `pwd_car_min`, `pwd_dig_min`, `pwd_dure`, `pwd_maj_min`, `pwd_spc_min`)  VALUES("+listePwdCritaire.get(c).getPwdId()+", '"+listePwdCritaire.get(c).getPwdCarMin()+"', '"+listePwdCritaire.get(c).getPwdDigMin()+"', '"+listePwdCritaire.get(c).getPwdDure()+"', '"+listePwdCritaire.get(c).getPwdMajMin()+"', '"+listePwdCritaire.get(c).getPwdSpcMin()+"');\r\n");
		 	   	
		 	   			}
						//Liste fichier
						for (int d = 0; d < listeFichier.size(); d++)
		 	   			{
							
							 myWriter.write("INSERT INTO `td_fichier` (`fhr_id`, `fhr_app_id`, `fhr_jrxml_file_id`, `fhr_nom`, `fhr_type`) VALUES("+listeFichier.get(d).getFhrId()+", '"+listeFichier.get(d).getFhrAppId()+"', '"+listeFichier.get(d).getFhrJrxmlFileId()+"', '"+listeFichier.get(d).getFhrNom()+"', '"+listeFichier.get(d).getFhrType()+"');\r\n");
		 	   	
		 	   			}
		
						
					  myWriter.close();
					  System.out.println("Successfully wrote to the file."); }
					  catch (IOException e) {
						  System.out.println("An error occurred.");
						  e.printStackTrace(); 
						}
				}
				
	
		
}
