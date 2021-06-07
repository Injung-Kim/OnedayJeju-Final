package jeju.controller.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jeju.dto.qna.FileTB;
import jeju.dto.qna.Question;
import jeju.service.face.QnAService;
import jeju.util.Paging;

@Controller
@RequestMapping(value="/qna")
public class QuestionController {
	@Autowired QnAService qnaService;
	private Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	//질문글 리스트 불러오기
	@RequestMapping(value="/list")
	public void listAll(Paging paging, Model model) {
		//질문 게시글 갯수 구하기
		paging.setTotalCount(qnaService.getCntQuestions());
		//페이징 생성하기
		Paging qnaPaging = qnaService.getPaging(paging);
		List<Question> list = qnaService.getQuestionList(qnaPaging);
		logger.debug("list : {}", list);	
		//질문글 리스트 전달
		model.addAttribute("questionList", list);
	}
	
	//질문글 상세정보
	@RequestMapping(value="/view")
	public void qstView(int qstNo, ModelMap map, Model model, Paging paging) {
		//질문글 번호를 통한 상세조회
		HashMap<String, Object> view =  qnaService.getQuestion(qstNo);
		
		//질문글에 등록된 해시태그 정보
		List<HashMap<String, Object>> tag = qnaService.getHashtags(qstNo);
		logger.debug("조회한 정보 : {}", view);
		for(HashMap<String, Object> tagName : tag) {
			logger.debug("해시태그 이름 : {}", tagName.get("TAG_NAME"));
		}
		
		//답변글 게시글 수 구하기
		int totalCount = qnaService.getCntAnswers(qstNo);
		paging.setTotalCount(totalCount);
		//페이징 생성하기
		Paging ansPaging = qnaService.getPaging(paging);
		Map<String, Object> ansParam = new HashMap<String, Object>();
		ansParam.put("qstNo", qstNo);
		ansParam.put("ansPaging", ansPaging);
		logger.debug("파라미터 hashmap : {}", ansParam);
		
		//답변글 정보(페이징 반영)
		List<HashMap<String, Object>> list = qnaService.getAnswers(ansParam);
		logger.debug("답변글 리스트 : {}", list);
		
		//질문글 정보 전달
		map.addAllAttributes(view);
		//등록된 해시태그 리스트 전달
		model.addAttribute("tagList", tag);
		
		//답변글 정보 전달
		model.addAttribute("answers", list);
		//답변글 갯수 전달
		model.addAttribute("ansTotal", totalCount);
	}
	//질문글 작성하기
	@RequestMapping(value="/write/question", method=RequestMethod.POST)
	@Transactional
	public String writeQuestion(
			@RequestParam(value="upload", required = false) List<MultipartFile> file
			, HttpSession session
			, Question question
			, int[] tagNo
			) {
		logger.debug("태그번호 받아오기 : {}", tagNo);
		
		// 파일 처리하기
		List<FileTB> filetable = qnaService.createFile(file);
		
		// 작성자 정보 가져오기
		question.setUserNo((int)session.getAttribute("uno"));
		logger.debug("질문글 정보 : {}", question);
		
		// 질문글 생성하기
		qnaService.createQuestion(question, filetable, tagNo);
		
		return "redirect:/qna/list";
	}
	//질문글 삭제하기
	@RequestMapping(value="/delete/question", method = RequestMethod.GET)
	@Transactional
	public String deleteQuestion(int qstNo) {
		//1. 저장한 첨부파일 삭제
		qnaService.removeFiles(qstNo);
		//2. 질문글 삭제 -  답변글, 태그명, 첨부파일 모두 삭제됨
		qnaService.deleteQuestion(qstNo);
		
		return "redirect:/qna/list";
	}
}
