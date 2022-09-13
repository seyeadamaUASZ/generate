/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.ServeurResponse;

import java.util.List;

/**
 *
 * @author yougadieng
 */
public class ServeurResponse {
    private boolean statut ;
    private String description ;
    private Object data ;
    private List<String> notifInfos;

    public ServeurResponse() {
    }
    
<<<<<<< HEAD
    public ServeurResponse(boolean statut, String description, Object data) {
=======
    public ServeurResponse(boolean statut, String description, Object data, List<String> notifInfos) {
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
		super();
		this.statut = statut;
		this.description = description;
		this.data = data;
<<<<<<< HEAD
	}
=======
                this.notifInfos = notifInfos;
	}
    
    
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

	public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getNotifInfos() {
        return notifInfos;
    }

    public void setNotifInfos(List<String> notifInfos) {
        this.notifInfos = notifInfos;
    }    

}
