package jeju.controller.qna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.service.face.QnAService;

@Controller
@RequestMapping(value="/qna")
public class AnswerController {
	private Logger logger = LoggerFactory.getLogger(AnswerController.class);
	@Autowired QnAService qanService;
	@RequestMapping(value="/write/answer", method = RequestMethod.POST)
	public void writeAnswer() {
		logger.info("요청성공");
	}
}
