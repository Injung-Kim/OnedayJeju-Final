package jeju.service.face;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jeju.dto.Notice;
import jeju.dto.NoticeComment;
import jeju.dto.NoticeFile;
import jeju.dto.NtcDisLike;
import jeju.dto.NtcLike;
import jeju.dto.NtcReport;
import jeju.util.Paging;

public interface NoticeService {

	/**
	 * 공지사항 목록을 위한 페이징 객체를 생성한다
	 * 
	 * 파라미터 객체의 curPage(현재 페이지)와
	 * DB에서 조회한 totalCount(총 게시글 수)를 이용한다
	 * 
	 * @param inData - curPage(현재 페이지)를 저장하고 있는 Paging객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(Paging inData);

	/**
	 * 페이징이 적용된 공지사항 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 게시글 목록
	 */
	public List<Notice> getNoticeList(Paging paging);

	/**
	 * 공지글번호를 이용하여 상세 정보 보기
	 *  조회수를 1 증가 시킨다
	 *  
	 * @param nt - 공지글 번호를 가진 객체
	 * @return 조회된 공지글 상세정보 객체
	 */
	public Notice viewNotice(Notice notice);

	/**
	 * 공지글 객체와 파일을 처리
	 * 
	 * @param nt - 공지글 객체
	 * @param file - 첨부 파일 객체
	 */
	public void writeNotice(Notice nt, MultipartFile[] file);

	/**
	 * 공지 객체를 이용해 첨부파일 정보 조회
	 * 
	 * @param notice - 공지 객체
	 * @return 파일첨부 객체
	 */
	public List<NoticeFile> viewAttachFile(Notice notice);

	public NoticeFile AttachNoticeFile(NoticeFile ntf);

	/**
	 * 첨부파일 번호가 담긴 객체를 이용해서 삭제
	 * 
	 * @param ntf - 첨부파일번호가 담긴 객체
	 * @return 삭제 유무
	 */
	public boolean deleteNoticeFile(NoticeFile ntf);

	/**
	 * 공지글 객체와 파일 수정
	 * 
	 * @param nt 공지글 객체
	 * @param file 파일 정보
	 */
	public void modifyNotice(Notice nt, MultipartFile[] file);

	
	/**
	 * 공지사항을 삭제 한다
	 * 
	 * @param notice - 공지글 객체
	 */
	public void deleteNotice(Notice notice);
	
	/**
	 * 공지사항 댓글 입력
	 * 
	 * @param ntc - 댓글 정보가 담긴 객체
	 */
	public void writeParentComment(NoticeComment ntc);

	/**
	 * 공지사항 댓글 전체 조회 (페이징)
	 * 
	 * @param paging - 공지사항 객체
	 * @return 공지사항 리스트
	 */
	public List<NoticeComment> viewNoticeComment(Paging paging);

	/**
	 * 공지사항 댓글 삭제 여부 yes or no
	 * 
	 * @param ntc - 공지사항 댓글번호가 담긴 객체
	 * @return 댓글 삭제여부를 반환
	 */
	public NoticeComment modifyNtcDelete(NoticeComment ntc);

	/**
	 * 공지사항 댓글 수정
	 * 
	 * @param ntc - 댓글 내용이 담긴 객체
	 */
	public NoticeComment modifyNtc(NoticeComment ntc);

	/**
	 * 공지사항 댓글 좋아요를 확인하고 좋아요를 토글(넣고빼기) 작업
	 * 
	 * @param nl - 좋아요 대상 정보
	 * @return true - 좋아요, false - 좋아요 취소
	 */
	public boolean Like(NtcLike nl);

	/**
	 * 공지사항 댓글 좋아요를 확인하고 좋아요를 토글(넣고빼기) 작업
	 * 
	 * @param nl - 좋아요 대상 정보
	 * @return true - 좋아요, false - 좋아요 취소
	 */
	public boolean disLike(NtcDisLike nd);

	/**
	 * 공지사항 댓글 좋아요 상태 확인
	 * 
	 * @param ntcLikeList - 좋아요 상태를 확인할 게시글과 정보
	 * @return true - 좋아요한 상태, false - 좋아요 안한상태
	 */
	public List<Boolean> isLike(List<NtcLike> ntcLikeList);

	/**
	 * 공지사항 댓글 좋아요 상태 확인
	 * 
	 * @param ntcLikeList - 싫어요 상태를 확인할 게시글과 정보
	 * @return true - 싫어요한 상태, false - 싫어요 안한상태
	 */
	public List<Boolean> isDisLike(List<NtcDisLike> ntcDisLikeList);

	/**
	 * 총 좋아요 수를 구한다
	 * 
	 * @param nl - 좋아요수를 파악할 댓글 객체
	 * @return 총 좋아요 수
	 */
	public int getTotalCntLike(NtcLike nl);

	/**
	 * 총 싫어요 수를 구한다
	 * 
	 * @param nl - 싫어요수를 파악할 댓글 객체
	 * @return 총 싫어요 수
	 */
	public int getTotalCntDisLike(NtcDisLike nd);

	/**
	 * 공지사항 댓글 신고
	 * 
	 * @param nr - 신고 정보가 담긴 객체
	 * @return 신고가 정상적으로 되었는지 확인
	 */
	public boolean ntcReport(NtcReport nr);

	/**
	 * 공지사항 신고 상태 확인
	 * 
	 * @param ntr - 신고 상태를 확인할 정보
	 * @return true - 신고 상태 , false - 신고 안한상태
	 */
	public List<Boolean> isReport(List<NtcReport> ntr);

	/**
	 * 공지사항 댓글 목록을 위한 페이징 객체를 생성한다
	 * 
	 * 파라미터 객체의 curPage(현재 페이지)와
	 * DB에서 조회한 totalCount(총 게시글 수)를 이용한다
	 * 
	 * @param inData - curPage(현재 페이지)를 저장하고 있는 Paging객체
	 * @return 페이징 계산이 완료된 Paging객체
	 */
	public Paging getCmtPaging(Paging inData);

	/**
	 * 댓글 전체 조회
	 * 
	 * @return - 댓글 전체 조회 리스트
	 */
	public List<NoticeComment> viewAllNoticeComment();

	/**
	 * 댓글 전체 수
	 * 
	 * @return - 댓글 전체 수 
	 */
	public int cmtTotalCnt();

	/**
	 * 댓글 삭제
	 * 
	 * @param ntc - 공지사항 댓글 객체
	 * @return 삭제되면 true, 안되면 false
	 */
	public boolean NtcDelete(NoticeComment ntc);

	/**
	 * 신고 조회 리스트
	 * 
	 * @return - 신고 조회 반환
	 */
	public List<NtcReport> viewAllNtcReport();

	/**
	 * 신고 수
	 * 
	 * @return 신고 수 조회
	 */
	public int cmtrTotalCnt();



}
