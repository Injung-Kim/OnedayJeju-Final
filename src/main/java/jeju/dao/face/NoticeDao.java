package jeju.dao.face;

import java.util.List;

import jeju.dto.Notice;
import jeju.dto.NoticeComment;
import jeju.dto.NoticeFile;
import jeju.dto.NtcDisLike;
import jeju.dto.NtcLike;
import jeju.dto.NtcReport;
import jeju.util.Paging;

public interface NoticeDao {

	/**
	 * 전체 게시글 수를 조회한다
	 * 
	 * @return 총 게시글 수
	 */
	public int selectCntAll();

	/**
	 * 페이징을 적용하여 공지사항 목록 조회
	 * 	Paging.startNo, Paging.endNo를 활용한다
	 * 	board_no가 아닌 rownum을 이용하여 조회한다
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 게시글 목록
	 */
	public List<Notice> noticePageSelect(Paging paging);

	/**
	 * 조회하려는 공지글의 조회수를 1 증가
	 * 
	 * @param nt - 공지글번호를 가진 객체
	 */
	public void updateHit(Notice nt);

	/**
	 * 공지글번호를 이용하여 게시글을 조회
	 * 
	 * @param nt - 공지글번호를 가진 객체
	 * @return 조회된 공지글
	 * 
	 */
	public Notice selectNoticeByNotice(Notice nt);

	/**
	 * 입력받은 공지사항 삽입
	 * 
	 * @param nt - 공지글 객체
	 */
	public void insertNotice(Notice nt);

	
	/**
	 * 입력받은 첨부파일 삽입
	 * 
	 * @param noticeFile - 첨부파일 객체
	 */
	public void insertNoticeFile(NoticeFile noticeFile);

	
	/**
	 * 공지글 번호가 담긴 객체를 이용해 첨부파일 객체 리스트 상세 조회
	 * 
	 * @param notice - 공지글 번호가 담긴 객체
	 * @return 첨부파일 리스트 상세 보기
	 */
	public List<NoticeFile> selectNtfByNtNo(Notice notice);

	/**
	 * 파일번호가 담긴 객체를 이용해 첨부파일 객체 조회
	 * 
	 * @param ntf - 파일번호가 담긴 객체
	 * @return 첨부파일 객체
	 */
	public NoticeFile selectNtfByNtfNo(NoticeFile ntf);

	/**
	 * 첨부파일 번호가 담긴 객체를 이용해 삭제
	 * 
	 * @param ntf - 첨부파일 번호가 담긴 객체
	 */
	public void deleteNtfFile(NoticeFile ntf);
	
	/**
	 * 첨부파일 번호를 이용해서 파일확인
	 * 
	 * @param ntf - 첨부파일 번호가 담긴 객체
	 * @return 1 or 0 을 반환 후 삭제 유무 판단
	 */
	public int countNtf(NoticeFile ntf);

	
	/**
	 * 공지글 수정한다
	 * 
	 * @param nt 수정된 공지 정보가 담긴 객체
	 */
	public void updateNotice(Notice nt);

	/**
	 * 공지글 삭제
	 * 
	 * @param notice - 공지사항 객체
	 */
	public void deletNotice(Notice notice);

	/**
	 * 공지사항 댓글 입력
	 * 
	 * @param ntc - 공지사항 댓글 정보가 담긴 객체
	 */
	public void insertParentComment(NoticeComment ntc);
	
	/**
	 * 공지사항 댓글 전체 조회 (페이징)
	 * 
	 * @param paging - 공지사항 객체
	 * @return 공지사항 댓글 리스트
	 */
	public List<NoticeComment> selectAllNtc(Paging paging);

	/**
	 * 공지사항 삭제여부를 수정
	 * 
	 * @param ntc - 공지사항 댓글번호가 담긴 객체
	 */
	public void updateNtcDeleteY(NoticeComment ntc);

	/**
	 * 공지사항 삭제여부를 수정
	 * 
	 * @param ntc - 공지사항 댓글 객체
	 */
	public void updateNtcDeleteN(NoticeComment ntc);

	/**
	 * 공지사항 조회
	 * 
	 * @param ntc - 공지사항 댓글번호가 담긴 객체
	 * @return 공지사항 댓글 객체 반환
	 */
	public NoticeComment selectNtc(NoticeComment ntc);

	/**
	 * 공지사항 댓글 수정
	 * 
	 * @param ntc - 댓글 내용이담긴 객체
	 * @return 수정된 댓글 객체
	 */
	public void updateNtc(NoticeComment ntc);

	/**
	 * 해당 댓글에 좋아요를 했는지 확인
	 * 
	 * @param l - 해당 댓글의 정보
	 * @return 좋아요 개수가 0이면 true, 0보다 크면 false
	 */
	public int selectCntNtcLike(NtcLike l);

	/**
	 * 해당 댓글에 싫어요를 했는지 확인
	 * 
	 * @param l - 해당 댓글의 정보
	 * @return 싫어요 개수가 0이면 true, 0보다 크면 false
	 */
	public int selectCntNtcDisLike(NtcDisLike d);

	/**
	 * 좋아요상태 지우기
	 * 
	 * @param nl - 좋아요 정보 객체
	 */
	public void deleteNtcLike(NtcLike nl);

	/**
	 * 좋아요상태 넣기
	 * 
	 * @param nl - 좋아요 정보 객체
	 */
	public void insertNtcLike(NtcLike nl);

	/**
	 * 싫어요상태 지우기
	 * 
	 * @param nl - 싫어요 정보 객체
	 */
	public void deleteNtcDisLike(NtcDisLike nd);
	
	/**
	 * 싫어요상태 넣기
	 * 
	 * @param nl - 싫어요 정보 객체
	 */
	public void insertNtcDisLike(NtcDisLike nd);

	/**
	 * 총 좋아요 수를 조회
	 * 
	 * @param nl - 좋아요 정보가 담긴 객체
	 * @return 총 좋아요 수
	 */
	public int selectTotalLike(NtcLike nl);

	/**
	 * 총 싫어요 수를 조회
	 * 
	 * @param nl - 싫어요 정보가 담긴 객체
	 * @return 총 싫어요 수
	 */
	public int selectTotalDisLike(NtcDisLike nd);

	/**
	 * 신고한 내용을 입력
	 * 
	 * @param nr - 신고정보가 담긴 객체
	 */
	public void insertNtcReport(NtcReport nr);
	
	/**
	 * 신고가 제대로 되었는지 확인
	 * 
	 * @param nr - 신고 정보가 담긴 객체
	 * @return 0이라면 신고가 안되었고 1이라면 신고가 됨
	 */
	public int NtcReportCnt(NtcReport nr);

	/**
	 * 전체 게시글 댓글 수를 조회한다
	 * @param inData 
	 * 
	 * @return 총 게시글 수
	 */
	public int selectNtcCntAll(Paging inData);

	/**
	 * 전체 댓글 조회
	 * 
	 * @return 전체 댓글 조회 리스트
	 */
	public List<NoticeComment> selectAllComment();

	/**
	 * 전체 댓글 수
	 * 
	 * @return - 전체 댓글 수
	 */
	public int selectCntAllComment();

	/**
	 * 삭제가 되었는지 확인
	 * 
	 * @param ntc - 댓글번호가 담긴 객체
	 * @return 0이면 삭제 1이면 삭제가 안됨
	 */
	public int selectCntByNtcNo(NoticeComment ntc);

	/**
	 * 댓글 삭제
	 * 
	 * @param ntc - 삭제할 댓글 번호
	 */
	public void deleteNtc(NoticeComment ntc);

	/**
	 * 신고 현황 조회 리스트
	 * 
	 * @return 신고 현황 조회
	 */
	public List<NtcReport> selectAllNtcReport();

	/**
	 * 신고 수
	 * 
	 * @return 신고 수
	 */
	public int selectCntNtcReport();


}
