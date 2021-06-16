package jeju.controller;

import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Review;
import jeju.dto.RvComment;
import jeju.service.face.ReviewService;
import jeju.util.RvPaging;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	//logger 객체
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/list")
	public void list(RvPaging inData, Model model) {
		logger.info("/Review/list [GET]");
		
		//페이징계산
		RvPaging paging = reviewService.getPaging( inData );
		paging.setSearch( inData.getSearch() );
		
		
		
		//게시글 목록 조회
		List<Review> rvlist = reviewService.list( paging );
		for( int i=0; i<rvlist.size(); i++ ) {
			logger.debug( rvlist.get(i).toString() );
		}
		logger.info("rvlist : {}", rvlist);
		
		//모델값 전달
		model.addAttribute("review", rvlist);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value="/view")
	public String view(Review viewReview, Model model) {
		logger.info("review : {}",viewReview.toString());
		
		if(viewReview.getRvNo() < 1) {
			return "redirect:/review/list";
		}
		
		//상세 정보 전달
		viewReview = reviewService.view(viewReview);
		logger.debug("상세보기 : {}", viewReview.toString());
		model.addAttribute("view", viewReview);
		
		//게시글 상세 정보 전달
		viewReview = reviewService.view(viewReview);
		logger.debug("상세보기 : {}", viewReview.toString());
		model.addAttribute("view", viewReview);
		
//		//댓글리스트 전달
//		List<RvComment> commentList = reviewService.getCommentList(viewReview);
//		model.addAttribute("commentList", commentList);
		
		return "review/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void write() {logger.info("/write GET");}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writeProc(Review review, HttpSession session, MultipartFile file) {
		
		
		
		//작성자 ID 추가 세션
		review.setUserId((String) session.getAttribute("id"));
		
		logger.info("글쓰기 : {}", review);
		
		reviewService.write(review, file);
		
		return "redirect:/review/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Review review, Model model) {
		logger.debug("rvNo : {}", review.toString());
		
		// 게시글 번호가 1보다 작으면 목록으로 보내기
		if(review.getRvNo() < 1) {
			return "redirect:/review/list";
		}
		
		// 게시글 상세 정보 전달
		review = reviewService.view(review);
		logger.debug("상세보기 : {}", review.toString());
		model.addAttribute("view", review);
		
		
		return "review/update";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateProcess(Review review, MultipartFile file, HttpSession session) {
		logger.debug("글수정 : {}", file);
		
		System.out.println("aaaaaaaaaaaaa");
		
		//작성자 ID, NICK 추가 - 세션
		review.setUserId((String) session.getAttribute("id"));
		
		logger.debug("글수정 : {}", review);
		
		reviewService.update(review, file);
		
		return "redirect:/review/view?rvNo="+review.getRvNo();
	}
	
	@RequestMapping(value="delete")
	public String deleteProc(Review review) {
		reviewService.delete(review);
		
		System.out.println("test");
		
		return "redirect:/review/list";
	}
	
}
