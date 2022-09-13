/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
//import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Fichier;
import sn.gainde2000.pi.core.entity.Rapport;
import sn.gainde2000.pi.core.service.IActionService;
import sn.gainde2000.pi.core.service.IEvenementService;
import sn.gainde2000.pi.core.service.IFichierService;
import sn.gainde2000.pi.core.service.INotificationDestinataireService;
import sn.gainde2000.pi.core.service.impl.RapportServiceImpl;
import sn.gainde2000.pi.core.service.impl.UtilisateurImpl;
import sn.gainde2000.pi.core.tools.SendNotification;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import net.sf.jasperreports.engine.JRException;


/**
 *
 * @author Sagueye
 */
@RestController
public class FichierController {

    @Autowired
    IFichierService fichierService;
    @Autowired 
	 IActionService   actionService;
    
    @Autowired
    RapportServiceImpl rapportService;
	 @Autowired
	INotificationDestinataireService notDesService;
    
    @Autowired
	 IEvenementService evService;

    @Autowired
    ApplicationContext context; 
    
    @Autowired
    UtilisateurImpl userService;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    SendNotification sendNotification;

    @Autowired
    @Qualifier("dataSourceJasper")
    private DataSource dataSourceJasper;

    @PostMapping("fichier/delete")
    public ServeurResponse deletefichier(@RequestBody Fichier fichier) {
        ServeurResponse response = new ServeurResponse();
        fichierService.supprimer(fichier);
        response.setData(fichier);
        response.setStatut(true);
        return response;
    }

    @GetMapping("fichier")
    public ServeurResponse getFichier() {    	
        ServeurResponse response = new ServeurResponse();
        List<Fichier> f = fichierService.getAllFichier();
        response.setData(f);
        response.setStatut(true);
        return response;
    }

    @GetMapping("fichierByApp")
    public ServeurResponse getFichierByApp() {    
        ServeurResponse response = new ServeurResponse();
        List<Fichier> f =fichierService.getAllFichierLibre();
        response.setData(f);
        response.setStatut(true);
        return response;
    }
     /* @GetMapping("fichierByAppOuLibre/{id}")
     public ServeurResponse getWorkflowLibreOuSpecifique(@PathVariable Long id){
          ServeurResponse response = new ServeurResponse();
          List<Fichier> f = fichierService.getAllFichierByIdAppOuLibre(id);
          response.setData(f);
          response.setStatut(true);
          return response;
     } */
    
    
        
    @PostMapping(path = "fichier/pdf")
    public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        //Get JRXML template from resources folder
        Long fichierId = Long.parseLong(request.getParameter("fichier"));
        
        Map<String, Object> data = new ObjectMapper().readValue(
                request.getParameter("data"), new TypeReference<Map<String, Object>>() {
        }
        );

        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
            //System.out.println(entry.getKey() + "----------------------param----------------" + entry.getValue());
        }
        List<Object> var1 = new ArrayList<Object>();
        var1.add(data);
        
//	    Invoice invoice = invoiceRepo.getOne(id);
        //File pdfFile = File.createTempFile("repport", ".pdf");
//	    log.info(String.format("Invoice pdf path : %s", pdfFile.getAbsolutePath()));
        final Map<String, Object> parameters = new HashMap<>();

        try {

            Rapport rapport = rapportService.findById(fichierId);

            byte[] file = rapport.getRptJrxmlFile();
            //String fileName = rapport.getRptNom();         
            InputStream inputStream = new ByteArrayInputStream(file);

            //         InputStream inputStream = resource.getInputStream();
            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint = new JasperPrint();
            if ((report.getQuery() != null) && (report.getQuery().getText().length() != 0)) {
                Connection dataSource = this.dataSourceJasper.getConnection();
                jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
            } else {
                JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
                params.put("datasource", dataSource);
                jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
            }
            
            //Send Notification
            sendNotification.checkedNotification("gen_fichier_pdf");
            
            //System.out.println("-------------------getCatalog---------------------" + dataSource);
            //params.put(JRParameter.REPORT_CONNECTION, dataSource);
            //Make jasperPrint
            //Media Type
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            //Export PDF Stream
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            System.out.println("-------------------getResponse---------------------" + response);
        } catch (IOException | JRException | SQLException e) {
            System.out.println("-------------------Muticatch Exception---------------------" + e.getMessage());
        } catch (Exception e) {
            System.out.println("-------------------Exception---------------------" + e.toString());
        }
        /*} catch (final Exception e) {
//	        log.error(String.format("An error occured during PDF creation: %s", e));
	    }*/

//	    byte[] bytes = Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));
//
//
//	    
//	    String folder = "src/test/resources/";
//	    Path path = Paths.get(folder);
//	    Files.write(path, bytes);
        //return bytes;	        	        	    	    
    }

    //@GetMapping(path = "fichier/pdf1")
    //@ResponseBody
//    public void getPdf(@PathVariable String jrxml, HttpServletResponse response) throws Exception {
    public void getPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Get JRXML template from resources folder
//        Resource resource = context.getResource("classpath:reports/" + jrxml + ".jrxml");
        Resource resource = context.getResource("classpath:reports/car_list.jrxml");
        Map<String, Object> data = new ObjectMapper().readValue(
                request.getParameter("data"), new TypeReference<Map<String, Object>>() {
        }
        );
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        List<Object> var1 = new ArrayList<Object>();
        //Compile to jasperReport
        InputStream inputStream = resource.getInputStream();

        JasperReport report = JasperCompileManager.compileReport(inputStream);

        JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @PostMapping(path = "fichier/excel")
    @ResponseBody
    public void getExcel1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long fichierId = Long.parseLong(request.getParameter("fichier"));
        Map<String, Object> data = new ObjectMapper().readValue(
                request.getParameter("data"), new TypeReference<Map<String, Object>>() {
        }
        );

        Map<String, Object> params = new HashMap<>();
        List<Object> var1 = new ArrayList<Object>();
        var1.add(data);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }

        try {
            Rapport rapport = rapportService.findById(fichierId);
            byte[] file = rapport.getRptJrxmlFile();
            InputStream inputStream = new ByteArrayInputStream(file);
            JasperReport jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream)); // compile report

//    	    Map<String, Object> params = new HashMap<>(); // init map with report's parameters
            params.put(JRParameter.REPORT_LOCALE, Locale.US);
            params.put(JRParameter.IS_IGNORE_PAGINATION, true);
            JasperPrint jasperPrint = new JasperPrint();
            if ((jasperReport.getQuery() != null) && (jasperReport.getQuery().getText().length() != 0)) {
                Connection dataSource = this.dataSourceJasper.getConnection();
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            } else {
                JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
                params.put("datasource", dataSource);
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            }

            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);  // prepare report - passs parameters and jdbc connection
            response.setHeader("Content-Disposition", "attachment;filename" + "test" + ".xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            final OutputStream ops = response.getOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // set compiled report as input
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ops));  // set output file via path with filename
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true); // setup configuration
            configuration.setDetectCellType(true);

            exporter.setConfiguration(configuration); // set configuration
            exporter.exportReport();
        } catch (IOException | JRException e) {
            System.out.println("-------------------Muticatch Exception---------------------" + e.getMessage());
        } catch (Exception e) {
            System.out.println("-------------------Exception---------------------" + e.getMessage());
        }

    }
    /* @RequestMapping(value = "fichierLierApp", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
     public ServeurResponse LierFichierEtApp(HttpServletRequest request) {
          ServeurResponse response = new ServeurResponse();
          String idApp = (String) request.getParameter("idApp");
          String idFichier = (String) request.getParameter("idFichier");
          // System.out.println("appp id:" + idApp);
          // System.out.println("workflow id:" + idWorkflow);
          fichierService.lierFichier(idApp, idFichier);
          response.setStatut(true);
          response.setDescription("liaison effectué avec succès");

          return response;
     }
     @GetMapping("fichierLierApp/enlever/{idFichier}")
     public ServeurResponse EnleverLiaisonWorkflowEtApp(@PathVariable String idFichier ) {
          ServeurResponse response = new ServeurResponse();
         // String idApp = (String) request.getParameter("idApp");
          // System.out.println("appp id:" + idApp);
           System.out.println("fichier id:" + idFichier);
          fichierService.enleverLiasonFichier(idFichier);
          response.setStatut(true);
          response.setDescription("liaison enlevé avec succès");

          return response;
     }
     
      @GetMapping("fichierByAppId/{id}")
    public ServeurResponse getFichierByApp(@PathVariable Long id) {
        ServeurResponse response = new ServeurResponse();
        List<Fichier> f =fichierService.getAllFichierByIdApp(id);
        response.setData(f);
        response.setStatut(true);
        return response;
    }
    */

}
