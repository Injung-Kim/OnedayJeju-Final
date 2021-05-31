package jeju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.NoticeDao;
import jeju.dto.Notice;
import jeju.service.face.NoticeService;
import jeju.util.NoticePaging;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired NoticeDao noticeDao;
	
	@Override
	public NoticePaging getPaging(NoticePaging inData) {
		
		//총 게시글 수 조회
		int totalCount = noticeDao.selectCntAll();
		
		//페이징 계산
		NoticePaging paging = new NoticePaging( totalCount, inData.getCurPage() );
		
		return paging;
	}

	@Override
	public List<Notice> getNoticeList(NoticePaging paging) {
		return noticeDao.noticePageSelect(paging);
	}

	@Override
	public Notice viewNotice(Notice nt) {
		
		noticeDao.updateHit(nt);
		
		return noticeDao.selectNoticeByNotice(nt);
	}

}
