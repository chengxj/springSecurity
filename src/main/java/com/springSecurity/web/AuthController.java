package com.springSecurity.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	
	protected static Logger logger = Logger.getLogger("AuthController");	
	
	@RequestMapping(value = "auth/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required = false) boolean error,
			ModelMap model){
		logger.debug("Received request to show login page");  		  
        if (error == true) { 
            model.put("error", "You have entered an invalid username or password!");  
        } else {  
            model.put("error", "");  
        }  
        return "login";
	}	
	
    @RequestMapping(value = "auth/denied", method = RequestMethod.GET)  
    public String getDeniedPage() {  
        logger.debug("Received request to show denied page");  
        return "denied";  
    }

}
