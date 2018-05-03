/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author julian
 */
public class Validator {

    @Getter
    private static HashMap<String, String> definedRules
            = new HashMap<String, String>() {
        {
            put("string", "[a-zA-Z0-9\\S]\\w+");
            put("email", "([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+)");
            put("empty", ".*\\S.*");
        }
    };
    
    
    public static boolean applyRule(String value, String ruleName) {
        
        if(definedRules.containsKey(ruleName)){
            
            Pattern pattern = Pattern.compile(definedRules.get(ruleName));
            
            Matcher matcher = pattern.matcher(value);
            
            return matcher.matches();
            
        }
        
        return false;
    }

}
