package jeju.controller.qna;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jeju.dto.QnAQuestion;
import jeju.service.face.QnAService;
import jeju.util.Paging;

@Controller
@RequestMapping(value="/qna")
public class QuestionController {
	@Autowired QnAService qnaService;
	private Logger logger = LoggerFactory.getLogger(QuestionController.class);
	@RequestMapping(value="/list")
	public void listAll(Paging paging) {
		//질문 게시글 갯수 구하기
		paging.setTotalCount(qnaService.getCntQuestions());
		//페이징 생성하기
		Paging qnaPaging = qnaService.getPaging(paging);
		//질문글 리스트 불러오기
		List<QnAQuestion> list = qnaService.getQuestionList(qnaPaging);
		logger.info("list : {}", list);
		
	}
}
