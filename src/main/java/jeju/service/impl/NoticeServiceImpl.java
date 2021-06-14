package jeju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jeju.dao.face.NoticeDao;
import jeju.dto.Notice;
import jeju.dto.NoticeComment;
import jeju.dto.NoticeFile;
import jeju.dto.NtcDisLike;
import jeju.dto.NtcLike;
import jeju.dto.NtcReport;
import jeju.service.face.NoticeService;
import jeju.util.Paging;

@Service
public class NoticeServiceImpl implements NoticeService {

	//logger 객체
	private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);
	
	@Autowired ServletContext context;
	
	@Autowired NoticeDao noticeDao;
	
	
	@Override
	public Paging getPaging(Paging inData) {
		
		//총 게시글 수 조회
		int totalCount = noticeDao.selectCntAll();
		
		//페이징 계산
		Paging paging = new Paging( totalCount, inData.getCurPage() );
		
		return paging;
	}

	@Override
	public List<Notice> getNoticeList(Paging paging) {
		return noticeDao.noticePageSelect(paging);
	}

	@Override
	public Notice viewNotice(Notice nt) {
		
		noticeDao.updateHit(nt);
		
		return noticeDao.selectNoticeByNotice(nt);
	}

	@Override
	@Transactional
	public void writeNotice(Notice nt, MultipartFile[] file) {
		
		//공지글 객체 입력
		noticeDao.insertNotice( nt );
		
		for( int i=0; i<file.length; i++ ) {
			
			//첨부파일이 전달되지 않았다면 리턴
			if( file[i].getSize() <= 0 ) {
				return;
			}
			
		}
		
		//파일이 저장될 경로(real path)
		String storedPath = context.getRealPath("noticeUpLoad");
		
		//폴더가 존재하지 않으면 생성하기
		File stored = new File(storedPath);
		if( !stored.exists() ) {
			stored.mkdir();
		}
		
		//원본 저장될 이름 객체 생성
		String originName[] = new String[file.length];
		String storedName[] = new String[file.length];
		
		//원본 저장될 이름 객체 저장
		for( int i=0; i<file.length; i++ ) {
			originName[i] = file[i].getOriginalFilename();
			//원본파일이름에 UUID추가하기 (파일명이 중복되지않도록 설정)
			storedName[i] = originName[i] + UUID.randomUUID().toString().split("-")[3] + UUID.randomUUID().toString().split("-")[4];
			logger.debug("originName : {}", originName[i] );
			logger.debug("storedName : {}", storedName[i] );
		}
		
		
		for( int i=0; i<file.length; i++ ) {
			
			//저장될 파일 정보 객체
			File dest = new File( stored, storedName[i] );
			
			//업로드된 파일을 저장하기
			try {
				file[i].transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} // for( int i=0; i<file.length; i++ ) end
		
		for( int i=0; i<file.length; i++ ) {
			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setNtNo( nt.getNtNo() );
			noticeFile.setNtfOgname( originName[i] );
			noticeFile.setNtfStname( storedName[i] );
			
			noticeDao.insertNoticeFile(noticeFile);
		}
	}

	@Override
	public List<NoticeFile> viewAttachFile(Notice notice) {
		
		return noticeDao.selectNtfByNtNo(notice);
	}

	@Override
	public NoticeFile AttachNoticeFile(NoticeFile ntf) {
		return noticeDao.selectNtfByNtfNo(ntf);
	}

	@Override
	public boolean deleteNoticeFile(NoticeFile ntf) {
		ntf = noticeDao.selectNtfByNtfNo(ntf);
		noticeDao.deleteNtfFile(ntf);

		logger.info("ntf : {} ", ntf);

		File delFile = new File( context.getRealPath("noticeUpLoad") + "/" + ntf.getNtfStname());
		delFile.delete();
		
		
		if( noticeDao.countNtf(ntf) > 0 ) {
			return false;
		}
		
		return true;
	}

	@Override
	@Transactional
	public void modifyNotice(Notice nt, MultipartFile[] file) {
		
		if( "".equals(nt.getNtTitle()) ) {
			nt.setNtTitle("(제목없음 없음)");
		}
		if( "".equals(nt.getNtContent()) || "&nbsp;".equals(nt.getNtContent())  || nt.getNtContent() == null 
					|| "<p>&nbsp;</p>".equals(nt.getNtContent()) || "<p><br></p>".equals(nt.getNtContent()) ) {
			nt.setNtContent("(내용 없음)");
		}
		
		//공지글 객체 입력
		noticeDao.updateNotice( nt );
		
		for( int i=0; i<file.length; i++ ) {
			
			//첨부파일이 전달되지 않았다면 리턴
			if( file[i].getSize() <= 0 ) {
				return;
			}
			
		}
		
		//파일이 저장될 경로(real path)
		String storedPath = context.getRealPath("noticeUpLoad");
		
		//폴더가 존재하지 않으면 생성하기
		File stored = new File(storedPath);
		if( !stored.exists() ) {
			stored.mkdir();
		}
		
		
		//원본 저장될 이름 객체 생성
		String originName[] = new String[file.length];
		String storedName[] = new String[file.length];
		
		//원본 저장될 이름 객체 저장
		for( int i=0; i<file.length; i++ ) {
			originName[i] = file[i].getOriginalFilename();
			//원본파일이름에 UUID추가하기 (파일명이 중복되지않도록 설정)
			storedName[i] = originName[i] + UUID.randomUUID().toString().split("-")[3] + UUID.randomUUID().toString().split("-")[4];
			logger.debug("originName : {}", originName[i] );
			logger.debug("storedName : {}", storedName[i] );
		}
		
		
		for( int i=0; i<file.length; i++ ) {
			
			//저장될 파일 정보 객체
			File dest = new File( stored, storedName[i] );
			
			//업로드된 파일을 저장하기
			try {
				file[i].transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} // for( int i=0; i<file.length; i++ ) end
		
		for( int i=0; i<file.length; i++ ) {
			NoticeFile noticeFile = new NoticeFile();
			noticeFile.setNtNo( nt.getNtNo() );
			noticeFile.setNtfOgname( originName[i] );
			noticeFile.setNtfStname( storedName[i] );
			
			noticeDao.insertNoticeFile(noticeFile);
		}
		
	}

	@Override
	public void deleteNotice(Notice notice) {
		noticeDao.deletNotice(notice);
	}

	@Override
	public void writeParentComment(NoticeComment ntc) {
		noticeDao.insertParentComment(ntc);
	}

	@Override
	public List<NoticeComment> viewNoticeComment(Paging paging) {
		return noticeDao.selectAllNtc(paging);
	}

	@Override
	public NoticeComment modifyNtcDelete(NoticeComment ntc) {
		
		
		ntc = noticeDao.selectNtc(ntc);
		
		if( ntc.getNtcDelete().equals("yes") ) {

			noticeDao.updateNtcDeleteN(ntc);
			ntc = noticeDao.selectNtc(ntc);
			
			return ntc;
		}
		
		noticeDao.updateNtcDeleteY(ntc);
		ntc = noticeDao.selectNtc(ntc);
		
		return ntc;
	}

	@Override
	public NoticeComment modifyNtc(NoticeComment ntc) {
		
		noticeDao.updateNtc(ntc);
		
		return noticeDao.selectNtc(ntc);
	}

	@Override
	public boolean Like(NtcLike nl) {
		
		int cnt = noticeDao.selectCntNtcLike(nl);
		
		if( cnt > 0 ) {
			noticeDao.deleteNtcLike(nl);
			return false;
		} else {
			noticeDao.insertNtcLike(nl);
			return true;
		}
		
		
	}

	@Override
	public boolean disLike(NtcDisLike nd) {
		int cnt = noticeDao.selectCntNtcDisLike(nd);
		
		if( cnt > 0 ) {
			noticeDao.deleteNtcDisLike(nd);
			return false;
		} else {
			noticeDao.insertNtcDisLike(nd);
			return true;
		}
	}

	@Override
	public List<Boolean> isLike(List<NtcLike> ntcLikeList) {
		
		List<Boolean> isLike = new ArrayList<Boolean>();
		
		for(NtcLike l : ntcLikeList ) {
			int cnt = noticeDao.selectCntNtcLike(l);
			
			if( cnt > 0 ) { //좋아요
				isLike.add(true);
				
			} else { //좋아요 안함
				isLike.add(false);
				
			}
			
		}
		
		return isLike;
	}

	@Override
	public List<Boolean> isDisLike(List<NtcDisLike> ntcDisLikeList) {
		List<Boolean> isDisLike = new ArrayList<Boolean>();
		
		for(NtcDisLike d : ntcDisLikeList ) {
			int cnt = noticeDao.selectCntNtcDisLike(d);
			
			if( cnt > 0 ) { //좋아요
				isDisLike.add(true);
				
			} else { //좋아요 안함
				isDisLike.add(false);
				
			}
			
		}
		
		return isDisLike;
	}

	@Override
	public int getTotalCntLike(NtcLike nl) {
		return noticeDao.selectTotalLike(nl);
	}

	@Override
	public int getTotalCntDisLike(NtcDisLike nd) {
		return noticeDao.selectTotalDisLike(nd);
	}

	@Override
	public boolean ntcReport(NtcReport nr) {
		
		noticeDao.insertNtcReport(nr);
		
		if( noticeDao.NtcReportCnt(nr) > 0 ) {
			
			return true;
		} 
		
		return false;
	}

	@Override
	public List<Boolean> isReport(List<NtcReport> ntr) {
		List<Boolean> isReport = new ArrayList<Boolean>();
		
		for( NtcReport r : ntr ) {
			logger.info("r : {}", r);
			
			int cnt = noticeDao.NtcReportCnt( r );
			logger.info("cnt : {}", cnt);
			
			if( cnt > 0 ) { //신고 함
				isReport.add(false);
			} else { //신고 안함
				isReport.add(true);
			}
			logger.info("isReport : {}", isReport);
			
		}
		return isReport;
	}

	@Override
	public Paging getCmtPaging(Paging inData) {

		//총 게시글 수 조회
		int totalCount = noticeDao.selectNtcCntAll(inData);
		
		//페이징 계산
		Paging paging = new Paging( totalCount, inData.getCurPage() );
		
		return paging;

	}

	@Override
	public List<NoticeComment> viewAllNoticeComment() {
		return noticeDao.selectAllComment();
	}

	@Override
	public int cmtTotalCnt() {
		return noticeDao.selectCntAllComment();
	}

	@Override
	public boolean NtcDelete(NoticeComment ntc) {
		
		noticeDao.deleteNtc(ntc);
		
			if( noticeDao.selectCntByNtcNo(ntc) > 0) { //삭제 안됨
				
				return false;
			}
		
		return true; //삭제됨
	}

	@Override
	public List<NtcReport> viewAllNtcReport() {
		return noticeDao.selectAllNtcReport();
	}

	@Override
	public int cmtrTotalCnt() {
		return noticeDao.selectCntNtcReport();
	}
	

}
