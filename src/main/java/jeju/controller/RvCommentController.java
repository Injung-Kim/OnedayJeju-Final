package jeju.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jeju.dto.RvComment;
import jeju.service.face.ReviewService;

@Controller
@RequestMapping(value="/comment")
public class RvCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(RvCommentController.class);
	
	
	@Autowired private ReviewService reviewService;
	
	@RequestMapping(value="/insert")
	public String insert(RvComment rvcomment, Model model, HttpSession session) {
		
		rvcomment.setUserId( (String) session.getAttribute("id"));
		reviewService.insertComment(rvcomment);
		
		
		return "redirect:/review/view?rvNo=" + rvcomment.getRvNo();
	}
	
}
