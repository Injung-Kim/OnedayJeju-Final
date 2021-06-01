package jeju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.QnADao;
import jeju.dto.QnAQuestion;
import jeju.service.face.QnAService;
import jeju.util.Paging;
@Service
public class QnAServiceImpl implements QnAService{
	@Autowired QnADao qnaDao;
	//질문글 게시글 수 구하기
	@Override
	public int getCntQuestions() {
		return qnaDao.selectCntQustion();
	}
	//페이징 생성하기
	@Override
	public Paging getPaging(Paging paging) {
		paging.setListCount(5);
		Paging listPaging = new Paging(paging.getTotalCount(), paging.getCurPage(), paging.getListCount());
		return listPaging;
	}
	//전체 질문글 리스트 불러오기
	@Override
	public List<QnAQuestion> getQuestionList(Paging paging) {
		
		return qnaDao.selectQuestions();
	}
}
