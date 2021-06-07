package jeju.service.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import jeju.dto.qna.FileTB;
import jeju.dto.qna.Hashtag;
import jeju.dto.qna.Question;
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
	public List<Question> getQuestionList(Paging paging);
	/**
	 * 질문글 상세조회하기
	 * 질문글 번호를 요청받아 해당하는 질문글 테이블 정보 반환받기
	 * 
	 * @param qstNo 질문글 번호
	 * @return 조회된 질문글 상세정보
	 */
	public HashMap<String, Object> getQuestion(int qstNo);
	/**
	 * 질문글에 등록된 태그정보 가져오기
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 * @return 질문글 번호에 등록된 해시태그 번호, 해시태그 이름 Map의 리스트
	 */
	public List<HashMap<String, Object>> getHashtags(int qstNo);
	/**
	 * 한 개의 질문들에 대한 답변글 갯수 조회하기
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 * @return 해당 질문글에 대한 답변글의 전체 갯수
	 */
	public int getCntAnswers(int qstNo);
	/**
	 * 질문글에 등록된 답변글 정보 가져오기
	 * 
	 * @param ansParam 질문글 번호
	 * @return 조회한 답변글 리스트
	 */
	public List<HashMap<String, Object>> getAnswers(Map<String, Object> ansParam);
	/**
	 * 입력받은 문자열이 DB에 등록 되어있는 해시태그명과 일치하는 지 검사
	 * 
	 * @param tagName 요청받은 문자열
	 * @return 해시태그가 없을 경우 false, 해시태그가 있을 경우 true 반환
	 */
	public boolean isExist(String tagName);
	/**
	 * 태그명이 등록되어 있지 않은 경우 DB에 새로 저장하기
	 * 
	 * @param tagName 요청받은 태그명
	 */
	public void addTagName(String tagName);
	/**
	 * DB에 등록된 해시태그 정보 조회하기
	 * 
	 * @param tagName 입력받은 태그명
	 * @return 해시태그 DTO 정보
	 */
	public Hashtag getTagInfo(String tagName);
	/**
	 * 요청받은 파일 처리하기
	 * 파일을 저장할 폴더 생성 후 원본과 저장할 이름 구분하여 생성
	 * 파일 dto에 원본과 저장된 이름 저장 후 반환받기
	 * 
	 * @param file 폼으로 요청받은 파일 정보 
	 * @return dto에 저장된 파일 
	 */
	public List<FileTB> createFile(List<MultipartFile> file);
	/**
	 * 질문글 생성하기
	 * 질문글 생성 폼에서 질문글 값을 요청받기
	 * 로그인을 한 유저에게서 번호 가져오기( 로그인 상태에서만 글 작성 가능)
	 * 
	 * @param question 요청받아 dto 저장한 질문글 정보(제목, 작성자, 내용)
	 * @param filetable 작성한 글 번호에 첨부할 파일정보
	 * @param tagNo 입력한 태그명에 해당하는 DB 태그번호
	 */
	public void createQuestion(Question question, List<FileTB> filetable, int[] tagNo);
	/**
	 * 변경전 저장되어있는 파일 관리하기
	 * 첨부파일 정보가 변경될 경우(삭제할 경우) 경로에 저장된 파일까지 모두 삭제
	 * 
	 * @param qstNo 작성된 게시글 번호
	 */
	public void removeFiles(int qstNo);
	/**
	 * 질문글 삭제하기
	 * 등록된 첨부파일, 태그번호, 답변글, 질문글 모두 삭제
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 */
	public void deleteQuestion(int qstNo);
	

}
