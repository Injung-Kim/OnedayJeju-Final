package jeju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.service.face.ReviewService;

@Controller
@RequestMapping(value="/review/comment")
public class RvCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(RvCommentController.class);
	
	
	@Autowired private ReviewService reviewService;
	
	@RequestMapping(value="/list")
	public @ResponseBody List<RvComment> commentList(Review viewReview){
		
		System.out.println("dvgvyvyfvfvu"+ viewReview);
		
		//댓글리스트 전달
		List<RvComment> commentList = reviewService.getCommentList(viewReview);
		
		return commentList;
	}
	
	
	@RequestMapping(value="/insert")
	public @ResponseBody void insert(RvComment rvcomment, Model model, HttpSession session) {
		
		rvcomment.setUserId( (String) session.getAttribute("id"));
		reviewService.insert(rvcomment);
		
		
	}
	
	
	//댓글 수정
	@RequestMapping(value="/update")
	public @ResponseBody void commentUpdateCommit(RvComment rvcomment ) {
		logger.info("modi Write");
		
		reviewService.commentUpdateCommit(rvcomment);
		
	}
	
	@RequestMapping(value="/cancel")
	public @ResponseBody void commentCancel(RvComment rvcomment) {
		reviewService.commentCancel(rvcomment);
	}
}
