package jeju.dao.face;

import java.util.List;

import jeju.dto.QnAQuestion;

public interface QnADao {
	/**
	 * 질문글 테이블 전체 조회하기
	 * 이후 추가 구현할 예정
	 * 
	 */
	public List<QnAQuestion> selectQuestions();
	/**
	 * 질문글 전체 갯수 조회하기
	 * 
	 * 
	 * @return 조회된 질문글의 게시글 갯수
	 */
	public int selectCntQustion();

	
}
