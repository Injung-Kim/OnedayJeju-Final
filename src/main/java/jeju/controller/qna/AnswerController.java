package jeju.controller.qna;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jeju.dto.qna.Answer;
import jeju.service.face.QnAService;

@Controller
@RequestMapping(value="/qna")
public class AnswerController {
	/*
	 * @Autowired QnAService qnaService; Logger logger =
	 * LoggerFactory.getLogger(AnswerController.class);
	 * 
	 * @RequestMapping(value="/view") public String getAnswers(int qstNo) {
	 * List<Answer> list = qnaService.getAnswers(qstNo); 
	 * logger.info("답변글 리스트 : {}",
	 * list); return "/qna/view"; }
	 */
}
