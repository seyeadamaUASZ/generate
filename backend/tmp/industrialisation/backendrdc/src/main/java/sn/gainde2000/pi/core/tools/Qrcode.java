package sn.gainde2000.pi.core.tools;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Qrcode {

    public static BufferedImage generateUPCABarcodeImage(String barcodeText) throws Exception {
        UPCAWriter barcodeWriter = new UPCAWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.UPC_A, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generatePDF417BarcodeImage(String barcodeText) throws Exception {
        PDF417Writer barcodeWriter = new PDF417Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.PDF_417, 700, 700);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {

       Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
          hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter barcodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200, hints);
        
        MatrixToImageConfig conf = new MatrixToImageConfig(0xFF40BAD0, -1);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, conf);

       // BufferedImage overlay = ImageIO.read(new File("Capture.png"));
        //Calculate the delta height and width
        //int deltaHeight = image.getHeight() - overlay.getHeight();
        //int deltaWidth = image.getWidth() - overlay.getWidth();

        // Initialize combined image
        //BufferedImage combined = new BufferedImage(bitMatrix.getHeight(), bitMatrix.getWidth(), BufferedImage.TYPE_BYTE_INDEXED);
        //Graphics2D g = (Graphics2D) combined.getGraphics();

        // Write QR code to new image at position 0/0
        //g.drawImage(image, 0, 0, null);
        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //g.drawImage(overlay, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

     // return combined;

         return image;
    }

}
