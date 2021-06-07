package jeju.dao.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jeju.dto.qna.Answer;
import jeju.dto.qna.FileTB;
import jeju.dto.qna.Hashtag;
import jeju.dto.qna.Question;
import jeju.util.Paging;

public interface QnADao {
	/**
	 * 질문글 테이블 전체 조회하기
	 * 이후 추가 구현할 예정
	 * @param paging 보여질 페이지 정보(한 페이지 당 5개 글 구현)
	 * 
	 */
	public List<Question> selectQuestions(Paging paging);
	/**
	 * 질문글 전체 갯수 조회하기
	 * 
	 * 
	 * @return 조회된 질문글의 게시글 갯수
	 */
	public int selectCntQustion();
	/**
	 * 요청받은 번호에 해당하는 질문글 상세정보 조회하기
	 * 
	 * @param qstNo 요청받은 질문번호
	 * @return 조회한 질문글 테이블 정보
	 */
	public HashMap<String, Object> selectQuestionByqstNo(int qstNo);
	/**
	 * 요청받은 번호에 해당하는 해시태그 정보 조회하기
	 * 
	 * @param qstNo 클릭한 질문글 조회번호
	 * @return 해당 질문글에 등록된 해시태그 번호, 해시태그 이름 정보 Map
	 */
	public List<HashMap<String, Object>> selectHashTagByqstNo(int qstNo);
	/**
	 * 질문번호를 통해 조회한 질문글에 등록된 답변글의 총 갯수
	 * 
	 * @param qstNo 클릭한 질문글 번호
	 * @return 현재 등록된 답변글의 갯수
	 */
	public int selectCntAnswer(int qstNo);
	/**
	 * 요청받은 질문글 번호에 해당하는 답변글 리스트 조회하기
	 * 
	 * @param ansParam 클릭한 질문글 조회번호
	 * @return 해당 질문들에 등록된 답변글 리스트
	 */
	public List<HashMap<String, Object>> selectAnswers(Map<String, Object> ansParam);
	/**
	 * 검색한 문자열과 일치하는 태그명 갯수 조회
	 * 
	 * @param tagName 입력받은 문자열
	 * @return 조회된 태그의 갯수
	 */
	public int cntTags(String tagName);
	/**
	 * DB에 없는 태그명 새로 저장하기
	 * 
	 * @param tagName 입력받은 태그명
	 */
	public void insertTag(String tagName);
	/**
	 * DB에 저장된 태그정보 조회하기
	 * 
	 * @param tagName 입력받은 태그명
	 * @return DTO에 저장된 태그정보
	 */
	public Hashtag selectTag(String tagName);
	/**
	 * 질문글 DB에 저장하기
	 * 
	 * 
	 * @param question 요청받은 질문글 정보(제목, 작성자, 내용)
	 */
	public void insertQuestion(Question question);
	/**
	 * 저장한 파일 DB에 저장하기
	 * 등록한 질문글 번호를 받아 질문글과 연결하기
	 * 
	 * @param file 저장한 파일의 원본이름과 저장될 이름
	 */
	public void insertFiles(FileTB file);
	/**
	 * 질문글에 태그정보 추가하기
	 * 입력받은 태그명의 태그 번호를 받아와 DB 저장
	 * 
	 * @param map 질문글 번호, 태그번호가 담긴 map 객체
	 */
	public void insertTagIntoQustion(Map<String, Object> map);
	/**
	 * 질문글에 등록된 파일 정보 가져오기
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 * @return 질문글 번호로 저장된 파일 정보
	 */
	public List<HashMap<String, Object>> selectFiles(int qstNo);
	/**
	 * 질문글 관련 정보 삭제
	 * 
	 * @param qstNo 삭제할 질문글 번호
	 */
	public void deleteQuestion(int qstNo);

	
}
