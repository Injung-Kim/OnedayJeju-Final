package jeju.service.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import jeju.dto.qna.Answer;
import jeju.dto.qna.FileTB;
import jeju.dto.qna.Hashtag;
import jeju.dto.qna.Question;
import jeju.dto.qna.Question_original;
import jeju.util.Paging;

public interface QnAService {
	/**
	 * 질문글 전체 갯수 조회하기
	 * 
	 * @param word 요청받은 해시태그 검색어
	 * @return 조회된 질문글의 게시글 갯수
	 */
	public int getCntQuestions(String word);
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
	 * @param word 검색한 태그명
	 * @return DB에 저장된 전체 질문글 리스트
	 */
	public List<HashMap<String, Object>> getQuestionList(Paging paging, String word);
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
	public void createQuestion(Question_original question, List<FileTB> filetable, int[] tagNo);
	/**
	 * 변경전 저장되어있는 파일 관리하기
	 * 첨부파일 정보가 변경될 경우(삭제할 경우) 경로에 저장된 파일까지 모두 삭제
	 * 
	 * @param file 작성된 게시글 번호
	 */
	public void removeFiles(FileTB file);
	/**
	 * 질문글 삭제하기
	 * 등록된 첨부파일, 태그번호, 답변글, 질문글 모두 삭제
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 */
	public void deleteQuestion(int qstNo);
	/**
	 * 질문글에 첨부된 파일정보 리스트
	 * 
	 * @param qstNo 요청받은 질문글 번호
	 * @return 조회한 파일 정보 리스트
	 */
	public List<HashMap<String, Object>> getFiles(int qstNo);
	/**
	 * 질문글 업데이트
	 * 
	 * @param question 수정한 질문글 정보가 담긴 dto객체
	 * @param filetable 새로 첨부한 파일 정보
	 * @param tagNo 변경한 태그번호 
	 */
	public void updateQuestion(Question question, List<FileTB> filetable, int[] tagNo);
	/**
	 * 답변글 추가하기
	 * 질문글 상세보기에서 답변글 생성 폼을 통해 답변글 값 요청받기
	 * 로그인을 한 유저에게서 작성자(유저번호) 전달받기 
	 * 
	 * @param list 업로드한 파일리스트
	 * @param session 로그인 정보
	 * @param answer 요청받아 dto 저장한 답변글 정보
	 */
	public void createAnswer(List<FileTB> list, HttpSession session, Answer answer);
	/**
	 * 게시글 삭제하기
	 * 답변글 번호를 요청받아 해당 답변글 삭제
	 * 
	 * @param ansNo 요청받아 dto 저장한 답변글 번호
	 */
	public void deleteAnswer(FileTB ansNo);
	/**
	 * 답변글 수정하기
	 * 
	 * @param answer 요청받은 수정된 답변글 정보
	 * @param filetable  새로 추가된 첨부파일 정보
	 */
	public void updateAnswer(Answer answer, List<FileTB> filetable);
	/**
	 * 답변글 좋아요 수 전체조회
	 * 
	 * @param answer 조회할 답변글 정보(질문글, 답변글 번호)
	 * @return 조회한 좋아요 전체 갯수
	 */
	public int getCntAnsLikes(Answer answer);
	/**
	 * 답변글별 좋아요 변경하기
	 * 
	 * @param answer 좋아요 변경 요청한 답변글 정보(질문글, 답변글, 유저정보)
	 * @return 좋아요 변경여부(좋아요 등록한 경우 true, 좋아요 취소한 경우 false 반환)
	 */
	public boolean modifyLike(Answer answer);
	/**
	 * 로그인한 유저가 작성한 질문글 갯수 조회하기
	 * 
	 * @param question 로그인한 유저 번호가 담긴 dto 객체
	 * @return 조회한 전체 질문글 갯수
	 */
	public int selectCntQustionByuno(Question question);
	/**
	 * 마이페이지 qna 조회
	 * 로그인한 유저가 작성한 질문글 리스트 조회하기
	 * 
	 * @param listPaging 현재 페이지 번호가 담긴 Paging 객체
	 * @param question 유저번호가 담긴 dto 객체
	 * @return 조회한 질문글 리스트
	 */
	public List<HashMap<String, Object>> getQstListByUserno(Paging listPaging, Question question);
	/**
	 *  로그인한 유저가 작성한 답변글 갯수 조회하기
	 * 
	 * @param answer 로그인한 유저 번호가 담긴 dto 객체
	 * @return 조회한 전체 답변글 갯수
	 */
	public int selectCntAnswerByuno(Answer answer);
	/**
	 * 마이페이지 qna 조회
	 * 로그인한 유저가 작성한 질문글 리스트 조회하기
	 * 
	 * @param listPaging 현재 페이지 번호가 담긴 Paging 객체
	 * @param answer 유저번호가 담긴 dto 객체
	 * @return 조회한 질문글 리스트
	 */
	public List<HashMap<String, Object>> getansListByUserno(Paging listPaging, Answer answer);
	/**
	 * 유저가 좋아요를 누른 답변글 갯수 조회
	 * 
	 * @param answer 유저번호가 담긴 dto 객체
	 * @return 조회한 좋아요를 누른 답변글 갯수
	 */
	public int getCntAnsLikedByuno(Answer answer);
	/**
	 * 유저가 좋아요를 누른 답변글 리스트 조회
	 * 
	 * @param listPaging 현재 페이지 번호가 담긴 Paging 객체
	 * @param answer 유저번호가 담긴 dto 객체
	 * @return 조회한 좋아요를 누른 답변글 리스트
	 */
	public List<HashMap<String, Object>> getansLikedListByUno(Paging listPaging, Answer answer);
	
}
