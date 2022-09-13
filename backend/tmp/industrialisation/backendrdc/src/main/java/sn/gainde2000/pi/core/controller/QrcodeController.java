package sn.gainde2000.pi.core.controller;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
import sn.gainde2000.pi.core.entity.Image;
import sn.gainde2000.pi.core.entity.QrCode;

import sn.gainde2000.pi.core.tools.Qrcode;
import sn.gainde2000.pi.core.service.impl.QrCodeServiceImpl;

@RestController
@RequestMapping("/barcodes")
public class QrcodeController {

    @Autowired
    QrCodeServiceImpl qrcodeService;

    Qrcode qrcode = new Qrcode();
/*
    @GetMapping(value = "/zxing/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(qrcode.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(qrcode.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(qrcode.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingPDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(qrcode.generatePDF417BarcodeImage(barcode));
    }
   

    @PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(qrcode.generateQRCodeImage(barcode));
    }
     */
    
    //QRGen
   /* @PostMapping(value = "/qrgen/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(qrcode.generateQRCodeImage(barcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }*/

    //Ajouter qrcode

    @PostMapping("/qrcode")
    public ServeurResponse create(@RequestBody QrCode qrcod) throws Exception {
    	BufferedImage bufferedImage = qrcode.generateQRCodeImage(qrcod.getQrcContenu());
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        ImageIO.write(bufferedImage, "jpg", bos );
        byte [] data = bos.toByteArray();
    	qrcod.setQrcQrcodeByte(data);
        ServeurResponse response = new ServeurResponse();

        qrcodeService.saveQrCode(qrcod);
        response.setStatut(true);
        response.setDescription("Enregistrement réussi");
        ///response.setData(qrcode);

        return response;
    }
    
    //listes des qrcodes
    @GetMapping("listqrcodes")
    public ServeurResponse getAllQrCode() {
        Iterable<QrCode> qr = qrcodeService.getListQrCode();
       
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

    @GetMapping(path = {"/{id}"})
    public ServeurResponse getImage(@PathVariable("id") Long id) throws IOException, DataFormatException {
        ServeurResponse response = new ServeurResponse();
        QrCode retrievedImage = qrcodeService.findByQrcId(id);
      
        response.setDescription("++++Affichage reussi!!++++");
        response.setData(retrievedImage);
        return response;
    }
    
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
    @GetMapping("/downloadFile/{qrcodeid}")
	public ResponseEntity<ByteArrayResource> downloadQrCode(@PathVariable Long qrcodeid,  HttpServletResponse response){
		QrCode qr = qrcodeService.findByQrcId(qrcodeid);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+qr.getQrcNom()+"\"")
				.body(new ByteArrayResource(qr.getQrcQrcodeByte()));
                
               
	}

}
