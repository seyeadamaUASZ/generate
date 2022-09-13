package sn.gainde2000.pi.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author indiaye
 */
@ConfigurationProperties("app")
@Validated
public class AppProperties {                    
    private String jbpmserver;
    private String urlsms;
    private String signaturesms;
    public String hostmail;
    private String pwdmail;
    private String frommail;
    private String urlapp;

    public String getJbpmserver() {
        return jbpmserver;
    }

    public void setJbpmserver(String jbpmserver) {
        this.jbpmserver = jbpmserver;
    }

    public String getUrlsms() {
        return urlsms;
    }

    public void setUrlsms(String urlsms) {
        this.urlsms = urlsms;
    }

    public String getSignaturesms() {
        return signaturesms;
    }

    public void setSignaturesms(String signaturesms) {
        this.signaturesms = signaturesms;
    }        

    public  String getHostmail() {
        return hostmail;
    }

    public void setHostmail(String hostmail) {
        this.hostmail = hostmail;
    }   

    public String getPwdmail() {
        return pwdmail;
    }

    public void setPwdmail(String pwdmail) {
        this.pwdmail = pwdmail;
    }

    public String getFrommail() {
        return frommail;
    }

    public void setFrommail(String frommail) {
        this.frommail = frommail;
    }

	public String getUrlapp() {
		return urlapp;
	}

	public void setUrlapp(String urlapp) {
		this.urlapp = urlapp;
	}    
    
       
}
