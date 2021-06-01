package jeju.dao.face;

import java.util.List;

import jeju.dto.Notice;
import jeju.util.NoticePaging;

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
	public List<Notice> noticePageSelect(NoticePaging paging);

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

}
