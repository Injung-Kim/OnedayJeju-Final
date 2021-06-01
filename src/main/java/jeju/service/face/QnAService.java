package jeju.service.face;

import java.util.List;

import jeju.dto.QnAQuestion;
import jeju.util.Paging;

public interface QnAService {
	/**
	 * 질문글 전체 갯수 조회하기
	 * 
	 * 
	 * @return 조회된 질문글의 게시글 갯수
	 */
	public int getCntQuestions();
	/**
	 * 페이징 정보 생성하기
	 * 페이지 번호를 요청받아 페이지 정보 생성 후 전달하기
	 * 
	 * @param paging 현재 페이지 번호가 담긴 Paging 객체
	 * @return 생성된 페이지 정보
	 */
	public Paging getPaging(Paging paging);
	/**
	 * 질문글 전체 리스트 조회하기
	 * 이후 태그명으로 전체 리스트 불러올 예정
	 * @param paging 요청받은 현재 페이지 정보
	 * @return DB에 저장된 전체 질문글 리스트
	 */
	public List<QnAQuestion> getQuestionList(Paging paging);

}
