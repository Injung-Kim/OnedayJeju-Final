package jeju.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlanBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanBoardController.class);
	
	@Autowired PlanBoardService planBoardService;
}
