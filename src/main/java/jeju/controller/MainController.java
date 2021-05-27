package jeju.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/")
	public String main() { 
		return "main";
	}
	@RequestMapping(value="/admin")
	public String admin() {
		return "admin";
	}
}
