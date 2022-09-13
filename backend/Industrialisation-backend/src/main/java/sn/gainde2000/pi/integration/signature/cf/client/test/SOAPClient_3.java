package sn.gainde2000.pi.integration.signature.cf.client.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.ws.security.SecurityConstants;

import sn.gainde2000.pi.integration.signature.cf.client.callback.GaindeClientCallback;
import sn.gainde2000.pi.integration.signature.confiancefactory.wsdl_rasign.IRegistSignPort;
import sn.gainde2000.pi.integration.signature.confiancefactory.wsdl_rasign.RASign;
import sn.gainde2000.pi.integration.signature.confiancefactory.wsdl_rasign.SignatureResult;


public class SOAPClient_3 {

	//private final String serviceURL = "http://localhost:8080/RASignv1/RA_Sign/services";
	//private final String serviceURL = "http://10.4.0.9:8080/RASignv1/RA_Sign/services";
	//private final String serviceURL = "http://10.6.1.11:8080/RASignv1/RA_Sign/services";
	//private final String serviceURL = "http://10.6.1.11:8080/ETAFI/RA_Sign/services";
	private final String serviceURL = "http://rasign.gainde2000.sn:8080/ETAFI/RA_Sign/services";


    private IRegistSignPort port;

    public SOAPClient_3() {
        try {

        	URL wsdlLocation = new URL(serviceURL + "?wsdl");
            RASign service = new RASign(wsdlLocation);
            port = (IRegistSignPort) service.getPort(IRegistSignPort.class);
            ((BindingProvider) port).getRequestContext().put(SecurityConstants.CALLBACK_HANDLER, new GaindeClientCallback());
            ((BindingProvider) port).getRequestContext().put(SecurityConstants.SIGNATURE_PROPERTIES,wss4jInProperties());
            ((BindingProvider) port).getRequestContext().put(SecurityConstants.ENCRYPT_PROPERTIES,wss4jInProperties() );
            ((BindingProvider) port).getRequestContext().put(SecurityConstants.SIGNATURE_USERNAME, "clientkey");
            ((BindingProvider) port).getRequestContext().put(SecurityConstants.ENCRYPT_USERNAME, "serverkey");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } // the constructor throws no exception.
    public Properties wss4jInProperties() {
		Properties properties = new Properties();
		properties.put("org.apache.wss4j.crypto.merlin.provider", "org.apache.wss4j.common.crypto.Merlin");
		properties.put("org.apache.wss4j.crypto.merlin.keystore.type", "jks");
		properties.put("org.apache.wss4j.crypto.merlin.keystore.password", "VERITAS");
		properties.put("org.apache.wss4j.crypto.merlin.keystore.file", "certs/test/client_new2.keystore");
		return properties;
	}
    
    public SignatureResult callMethd_Signer(long userID,byte[]document) {
    	byte[] byteArrayDocument=null;
    	try {
			//byteArrayDocument = Files.readAllBytes(Paths.get("E://signature//signature.pdf"));
    		byteArrayDocument =document;
			System.out.println("byteArrayDocument.length: "+byteArrayDocument.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    	return  port.signatureBeneficiaire(userID, byteArrayDocument);
    			//("lambda user9", "122019", "142578458", 2384759,byteArrayDocument); 
    }
    
    public String callMethd_Enregistrer() {   	

    	return  port.enregistrerBeneficiaire("Maimounata DERA", "104160", "CNI2755198502292", 2384759);
    			//("lambda user10", "122019", "142578458", 2384759,byteArrayDocument); user id:3247343
    	        //("lambda user11", "122019", "142578458", 2384759,byteArrayDocument); user id:3247362
    }
    
    public String callMethd_EnregistrerEyone() {   	
    	byte[] byteArrayScan=null;
    	try {
    		byteArrayScan = Files.readAllBytes(Paths.get("C:\\Users\\mmbaye\\Documents\\Moi\\CI_NEW_3_MM.pdf"));
			System.out.println("byteArrayDocument.length: "+byteArrayScan.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return  port.enroleeyone("DEPOT ETAFI DGID", "122019", "15042020", byteArrayScan, 2384759);
    			//.enroleeyone("lambda user19", "122019", "142578458", byteArrayScan, 2384759);
    			//.enregistrerBeneficiaire("lambda user14", "122019", "142578458", 2384759);

    }
    
    public  void writeBytesToFile(String fileOutput, byte[] bytes)
            throws IOException {

            try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
                fos.write(bytes);
            }

        }


    
}
