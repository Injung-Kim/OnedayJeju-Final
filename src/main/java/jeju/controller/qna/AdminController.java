package jeju.controller.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.service.face.QnAService;
import jeju.util.Paging;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired QnAService qnaService;
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	@RequestMapping(value="/qna/list", method=RequestMethod.GET)
	public void adminQnAList(
			Paging paging, Model model, String word	
			) {
		//질문 게시글 갯수 구하기
		paging.setTotalCount(qnaService.getCntQuestions(word));
		
		//페이징 생성하기
		Paging qnaPaging = new Paging(paging.getTotalCount(), paging.getCurPage());
		List<HashMap<String, Object>> map = qnaService.getQuestionList(qnaPaging, word);
		logger.info("list : {}", map);	
		//질문글 리스트 전달
		model.addAttribute("questionList", map);
		model.addAttribute("paging", qnaPaging);
	}
	@RequestMapping(value="/qna/view", method=RequestMethod.GET)
	public void adminQnAView(
		int qstNo
		, ModelMap map
		, Model model
		, Paging paging
		, HttpSession session
			) {
		//질문글 번호를 통한 상세조회
			HashMap<String, Object> view =  qnaService.getQuestion(qstNo);
			
			//질문글에 등록된 해시태그 정보
			List<HashMap<String, Object>> tag = qnaService.getHashtags(qstNo);
			logger.debug("조회한 정보 : {}", view);
			for(HashMap<String, Object> tagName : tag) {
				logger.debug("해시태그 이름 : {}", tagName.get("TAG_NAME"));
			}
			//질문글에 등록된 첨부파일 정보
			List<HashMap<String, Object>> files = qnaService.getFiles(qstNo);
			for(HashMap<String, Object> file : files) {
				logger.info("파일첨부 정보 : {}", file.get("QST_ORIGIN"));
			}
			//답변글 게시글 수 구하기
			int totalCount = qnaService.getCntAnswers(qstNo);
			paging.setTotalCount(totalCount);
			
			//페이징 생성하기
			Paging ansPaging = qnaService.getPaging(paging);
			Map<String, Object> ansParam = new HashMap<String, Object>();
			ansParam.put("qstNo", qstNo);
			ansParam.put("userNo", (Integer) session.getAttribute("uno"));
			ansParam.put("ansPaging", ansPaging);
			logger.debug("파라미터 hashmap : {}", ansParam);
			
			
			//답변글 정보(페이징 반영)
			List<HashMap<String, Object>> list = qnaService.getAnswers(ansParam);
			logger.debug("답변글 리스트 : {}", list);
			
			//질문글 정보 전달
			map.addAllAttributes(view);
			//등록된 해시태그 리스트 전달
			model.addAttribute("tagList", tag);
			//등록된 첨부파일 리스트 전달
			model.addAttribute("files", files);
			
			//답변글 정보 전달
			model.addAttribute("answers", list);
			//답변글 갯수 전달
			model.addAttribute("ansTotal", totalCount);
	}
	
	
}
