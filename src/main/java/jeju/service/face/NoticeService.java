package jeju.service.face;

import java.util.List;

import jeju.dto.Notice;
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
	public Notice viewNotice(Notice nt);



}
