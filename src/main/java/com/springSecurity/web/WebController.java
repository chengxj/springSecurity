package com.springSecurity.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class WebController {
	
	protected static Logger logger = Logger.getLogger("WebController");
	
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage(){
		logger.debug("Received request to show common page");
		return "common";
	}
	 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)  
    public String getAadminPage() {  
        logger.debug("Received request to show admin page");  
        return "admin";
    }

}
