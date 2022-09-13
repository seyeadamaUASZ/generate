/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author indiaye
 */
@RestController
@CrossOrigin("*")
public class AppErrorHandlerController implements ErrorController {
    static final String ERROR_PATH = "/error";
    public AppErrorHandlerController() {}

    @GetMapping(value = "error")
    public String handleError(HttpServletRequest request) {        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);        
        if (status != null) {        
            Integer statusCode = Integer.valueOf(status.toString());           
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {               
                return "401";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            }
        }
        return "error";
    }
    
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
