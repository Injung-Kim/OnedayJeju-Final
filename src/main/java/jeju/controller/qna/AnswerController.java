package jeju.controller.qna;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jeju.dto.qna.Answer;
import jeju.dto.qna.FileTB;
import jeju.service.face.QnAService;
import jeju.util.Paging;

@Controller
@RequestMapping(value="/qna")
public class AnswerController {
	@Autowired QnAService qnaService;
	private Logger logger = LoggerFactory.getLogger(AnswerController.class);
	@RequestMapping(value="/write/answer", method = RequestMethod.POST)
	@Transactional
	public String writeAnswer(
			Answer answer
			, HttpSession session
			, @RequestParam(value = "ansFiles", required = false) List<MultipartFile> files
			) {
		logger.info("post요청성공");
		//파일 처리하기
		List<FileTB> list = qnaService.createFile(files);
		//답변글 생성하기
		qnaService.createAnswer(list, session, answer);
		
		return "redirect:/qna/view?qstNo=" + answer.getQstNo();
	}
	
	@RequestMapping(value="/delete/answer", method = RequestMethod.GET)
	@Transactional
	public String deleteAnswer(FileTB ansNo, int qstNo) {
		//첨부파일 삭제하기
		qnaService.removeFiles(ansNo);
		
		//답변글 삭제하기
		qnaService.deleteAnswer(ansNo);
		
		return "redirect:/qna/view?qstNo="+ qstNo;
	}
	@RequestMapping(value="/update/answer", method = RequestMethod.POST)
	@Transactional
	public String updateAnswer(
		Answer answer
		, @RequestParam(value="ansFiles", required = false) List<MultipartFile> files
		) {
		//저장한 첨부파일 업데이트
		FileTB ansNo = new FileTB();
		ansNo.setAnsNo(answer.getAnsNo());
		qnaService.removeFiles(ansNo);
		
		//첨부파일 생성
		List<FileTB> filetable = qnaService.createFile(files);
		
		//답변글 업데이트
		qnaService.updateAnswer(answer, filetable);
		
		return "redirect:/qna/view?qstNo="+answer.getQstNo();
	}
	//좋아요 ajax 
	@RequestMapping(value="/answer/like", method = RequestMethod.GET)
	public ModelAndView inputLikes(
			Answer answer
			, HttpSession session
			, ModelAndView mav
			) {
		answer.setUserNo((Integer)session.getAttribute("uno"));
		logger.info("answer : {}", answer);
		//좋아요 변경하기(등록시 true, 취소시 false)
		boolean result = qnaService.modifyLike(answer);
		//좋아요 수 조회
		int cntLikes = qnaService.getCntAnsLikes(answer);
		
		mav.addObject("result", result);
		mav.addObject("cntLikes", cntLikes);
		mav.setViewName("jsonView");
		return mav;
	}
	//마이페이지 답변글 조회
	@RequestMapping(value="/mypage/answer", method=RequestMethod.GET)
	public ModelAndView mypage(
			HttpSession session
			, Paging paging
			, ModelAndView mav
			) {
		int userNo = (int)session.getAttribute("uno");
		Answer answer = new Answer();
		answer.setUserNo(userNo);
		
		//유저가 작성한 답변글 갯수 조회
		paging.setTotalCount(qnaService.selectCntAnswerByuno(answer));
		//페이지 생성
		Paging listPaging = new Paging(paging.getTotalCount(), paging.getCurPage());
		//로그인한 유저가 작성한 답변글 리스트 불러오기
		List<HashMap<String, Object>> list = qnaService.getansListByUserno(listPaging, answer);
		logger.info("anslist : {}", list);
		
		mav.addObject("paging", paging);
		mav.addObject("result", list);
		mav.setViewName("/qna/ansbyUno");
		
		return mav;
	}
	//마이페이지 좋아요한 답변글 조회
	@RequestMapping(value="/mypage/like", method = RequestMethod.GET)
	public ModelAndView mypageLike(
			HttpSession session
			, Paging paging
			, ModelAndView mav
			) {
		int userNo = (int)session.getAttribute("uno");
		Answer answer = new Answer();
		answer.setUserNo(userNo);
		
		//유저가 좋아요를 누른 답변글 갯수 조회
		paging.setTotalCount(qnaService.getCntAnsLikedByuno(answer));
		//페이지 생성
		Paging listPaging = new Paging(paging.getTotalCount(), paging.getCurPage());		
		//로그인한 유저가 좋아요를 누른 답변글 리스트 불러오기
		List<HashMap<String, Object>> list = qnaService.getansLikedListByUno(listPaging, answer);
		
		logger.debug("LIkedList : {}", list);
		mav.addObject("paging", paging);
		mav.addObject("result", list);
		mav.setViewName("/qna/ansLikedbyUno");
		
		return mav;
	}
}
