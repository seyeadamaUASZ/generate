/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.sql.Connection;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
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
import sn.gainde2000.pi.core.service.IFichierService;
import sn.gainde2000.pi.core.service.IRapportService;
import sn.gainde2000.pi.core.service.IUtilisateur;
import sn.gainde2000.pi.core.service.impl.RapportServiceImpl;
import sn.gainde2000.pi.core.service.impl.UtilisateurImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sagueye
 */
@RestController
public class FichierController {

    @Autowired
    IFichierService fichierService;
    @Autowired
    IActionService actionService;

    @Autowired
    IRapportService rapportService;
    /*@Autowired
    INotificationDestinataireService notDesService;

    @Autowired
    IEvenementService evService;*/

    @Autowired
    ApplicationContext context;

    @Autowired
    IUtilisateur userService;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /*
    @Autowired
    SendNotification sendNotification;*/

    @Autowired
    @Qualifier("dataSourceJasper")
    private DataSource dataSourceJasper;

    /**
     * Suppresion d'un fichier
     *
     * @param fichier
     * @return
     */
    @PostMapping("fichier/delete")
    public ServeurResponse deletefichier(@RequestBody Fichier fichier) {
        ServeurResponse response = new ServeurResponse();
        fichierService.supprimer(fichier);
        response.setData(fichier);
        response.setStatut(true);
        return response;
    }

    /**
     * Recuperer listes de toutes les fichiers.
     *
     * @return
     */
    @GetMapping("fichier")
    public ServeurResponse getFichier() {
        ServeurResponse response = new ServeurResponse();
        List<Fichier> f = fichierService.getAllFichier();
        response.setData(f);
        response.setStatut(true);
        return response;
    }

    /**
     * Recuperer un fichier d'une application créée.
     *
     * @return
     */
    @GetMapping("fichierByApp")
    public ServeurResponse getFichierByApp() {
        ServeurResponse response = new ServeurResponse();
        List<Fichier> f = fichierService.getAllFichierLibre();
        response.setData(f);
        response.setStatut(true);
        return response;
    }

    /**
     * Export en pdf et envoie de message.
     *
     * @param request
     * @param response
     * @param image
     */
    @PostMapping(path = "fichier/pdf")
    public void report(HttpServletRequest request, HttpServletResponse response) {

        try {
            Long fichierId = Long.parseLong(request.getParameter("fichier"));

            Map<String, Object> data = new ObjectMapper().readValue(
                    request.getParameter("data"), new TypeReference<Map<String, Object>>() {
            }
            );

            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
            List<Object> var1 = new ArrayList<Object>();
            var1.add(data);

            final Map<String, Object> parameters = new HashMap<>();

            try {

                Rapport rapport = rapportService.findById(fichierId);

                byte[] file = rapport.getRptJrxmlFile();
                InputStream inputStream = new ByteArrayInputStream(file);
             
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

                //sendNotification.checkedNotification("gen_fichier_pdf");
                response.setContentType(MediaType.APPLICATION_PDF_VALUE);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            } catch (IOException | JRException | SQLException e) {
            } catch (Exception e) {
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * convertir en pdf le fichier jrxml
     *
     * @param request
     * @param response
     */
    //@PostMapping(path = "fichier/pdfqrcode")
    public void getPdf(HttpServletRequest request, HttpServletResponse response) {
        try {
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

            InputStream inputStream = resource.getInputStream();

            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
            params.put("datasource", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * exporter en Excel et envoyer le message
     *
     * @param request
     * @param response
     */
    @PostMapping(path = "fichier/excel")
    @ResponseBody
    public void getExcel1(HttpServletRequest request, HttpServletResponse response) {
        try {
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

                response.setHeader("Content-Disposition", "attachment;filename" + "test" + ".xlsx");
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                final OutputStream ops = response.getOutputStream();
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ops));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(true);
                configuration.setDetectCellType(true);

                //sendNotification.checkedNotification("gen_fichier_excel");

                exporter.setConfiguration(configuration);
                exporter.exportReport();
            } catch (IOException | JRException e) {
            } catch (Exception e) {
            }

        } catch (JsonProcessingException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Export en pdf et envoie de message.
     *
     * @param request
     * @param response
     * @param image
     */
    @PostMapping(path = "fichierQrcode/pdf")
    public void reportQrcodeFichier(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "data1") String image) {

        try {
            Long fichierId = Long.parseLong(request.getParameter("fichier"));

            Map<String, Object> data = new ObjectMapper().readValue(
                    request.getParameter("data"), new TypeReference<Map<String, Object>>() {
            }
            );

            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
            List<Object> var1 = new ArrayList<Object>();
            var1.add(data);

            final Map<String, Object> parameters = new HashMap<>();

            try {

                Rapport rapport = rapportService.findById(fichierId);

                byte[] file = rapport.getRptJrxmlFile();
                InputStream inputStream = new ByteArrayInputStream(file);
             
                JasperReport report = JasperCompileManager.compileReport(inputStream);

                JasperPrint jasperPrint = new JasperPrint();
                if ((report.getQuery() != null) && (report.getQuery().getText().length() != 0)) {
                    Connection dataSource = this.dataSourceJasper.getConnection();
                    params.put("Data", image);
                    jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

                } else {
                    JRDataSource dataSource = new JRBeanCollectionDataSource(var1);
                    params.put("datasource", dataSource);
                    jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

                }

                //sendNotification.checkedNotification("gen_fichier_pdf");
                response.setContentType(MediaType.APPLICATION_PDF_VALUE);
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            } catch (IOException | JRException | SQLException e) {
            } catch (Exception e) {
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
