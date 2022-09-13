/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author indiaye
 */
public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;
    /*"Le mot de passe doit comporter entre 8 et 20 caractères dont au moins une minuscule, une majuscule et un chiffre!");*/
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9*$-+?_&=!%@#\\[\\]\\\"{}/])(?=.*?\\W).{8,20})";

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
}
