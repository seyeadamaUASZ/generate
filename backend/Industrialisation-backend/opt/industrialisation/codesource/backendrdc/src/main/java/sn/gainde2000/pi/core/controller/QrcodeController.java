package sn.gainde2000.pi.core.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.QrCode;
import sn.gainde2000.pi.core.service.IChampsQrcodeService;
import sn.gainde2000.pi.core.service.IQrCodeService;
import sn.gainde2000.pi.core.tools.Qrcode;
import sn.gainde2000.pi.core.service.impl.QrCodeServiceImpl;

@RestController
@RequestMapping("/barcodes")
public class QrcodeController {

    @Autowired
    IQrCodeService qrcodeService;
    Qrcode qrcode = new Qrcode();
    
    @Autowired
    IChampsQrcodeService champsQrcodeService;

    /**
     * Créer un QRcode
     *
     * @param qrcod
     * @return ServeurResponse
     * @throws Exception
     */
    @PostMapping("/qrcode")
    public ServeurResponse create(@RequestBody QrCode qrcod) {
        ServeurResponse response = new ServeurResponse();
        qrcod.setQrcValider("Modeliser");
        qrcodeService.saveQrCode(qrcod);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        return response;
    }

    /**
     * listes des qrcodes
     *
     * @return ServeurResponse
     */
    @GetMapping("qrcode/listQrcodeNotModeliser")
    public ServeurResponse getAllQrCode() {
        Iterable<QrCode> qr = qrcodeService.listQrcodeNotModeliser();

        ServeurResponse response = new ServeurResponse();
        if (qr == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(qr);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    @GetMapping("qrcode/QrcodeModeliser")
    public ServeurResponse getAllQrCodeModeliser() {
        Iterable<QrCode> qr = qrcodeService.listQrcodeModeliser();

        ServeurResponse response = new ServeurResponse();
        if (qr == null) {
            response.setStatut(false);
            response.setData(null);
            response.setDescription("Aucune élèment trouvé.");

        } else {

            response.setStatut(true);
            response.setData(qr);
            response.setDescription("Données récupérées.");
        }
        return response;
    }

    @PostMapping("qrcode/valider")
    public ServeurResponse valider(@RequestBody QrCode qrcode) {
        ServeurResponse response = new ServeurResponse();
        qrcode.setQrcStatus(false);
        qrcode.setQrcValider("Valider");
        qrcodeService.saveQrCode(qrcode);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        response.setData(qrcode);

        return response;
    }

    @PostMapping("qrcode/modeliser")
    public ServeurResponse modeliser(@RequestBody QrCode qrcode) {
        ServeurResponse response = new ServeurResponse();
        QrCode q = qrcodeService.findByQrcId(qrcode.getQrcId());
        q.setQrcValider("Modeliser");
        qrcodeService.saveQrCode(q);
        response.setStatut(true);
        response.setDescription("Rapport valider");
        response.setData(q);

        return response;
    }

    /**
     * Affiche l'image du QRcode
     *
     * @param id
     * @return ServeurResponse
     * @throws IOException
     * @throws DataFormatException
     */
    @GetMapping(path = {"/{id}"})
    public ServeurResponse getImage(@PathVariable("id") Long id) throws IOException, DataFormatException {
        ServeurResponse response = new ServeurResponse();
        QrCode retrievedImage = qrcodeService.findByQrcId(id);

        response.setDescription("++++Affichage reussi!!++++");
        response.setData(retrievedImage);
        return response;
    }

    /**
     * Décompresse l'image
     *
     * @param data
     * @return static byte[]
     * @throws DataFormatException
     */
    public static byte[] decompressBytes(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        }
        return outputStream.toByteArray();
    }

    /**
     * Compresse l'image ud QRcode
     *
     * @param data
     * @return static byte[]
     */
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressé la taille de l'image- " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    /**
     * Télécharge l'image du QRcode
     *
     * @param qrcodeid
     * @param response
     * @return ResponseEntity<ByteArrayResource>
     */
    /*@GetMapping("/downloadFile/{qrcodeid}")
    public ResponseEntity<ByteArrayResource> downloadQrCode(@PathVariable Long qrcodeid, HttpServletResponse response) {
        QrCode qr = qrcodeService.findByQrcId(qrcodeid);
        File file = new File(qr.getQrcNom().toLowerCase() + ".png");
        System.out.println(file.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(qr.getQrcQrcodeByte()));
        
    }*/
    /**
     * Supprime le QRcode
     *
     * @param qrcode
     * @return ServeurResponse
     */
    @PostMapping("qrcode/delete")
    public ServeurResponse deleteQrcode(@RequestBody QrCode qrcode) {
        ServeurResponse response = new ServeurResponse();
        qrcodeService.supprimer(qrcode);
        response.setStatut(true);
        return response;
    }

    @PostMapping("qrcode/supprime")
    public ServeurResponse deleteRapport(@RequestBody QrCode qrcod) {
        ServeurResponse response = new ServeurResponse();
        champsQrcodeService.supprimerByQrcode(qrcod.getQrcId());
        qrcodeService.supprimer(qrcod);
        response.setStatut(true);
        return response;
    }

}
