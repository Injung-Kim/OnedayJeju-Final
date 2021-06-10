package jeju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jeju.dao.face.MypageDao;
import jeju.dto.JejuUser;
import jeju.service.face.MypageService;

@Service
public class MypageServiceImpl implements MypageService {
	
	//서블릿 컨텍스트 객체
	@Autowired ServletContext context;
	
	@Autowired private MypageDao mypageDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MypageServiceImpl.class);

	
	
	@Override
	public JejuUser getUserData(int userno) {
		return mypageDao.selectUserByUserno(userno);
	}

	@Override
	public int selectCntNick(String nick) {
		return mypageDao.selectCntNick(nick);
	}

	@Override
	public void updateNick(JejuUser user) {
		mypageDao.updateUserNick(user);
	}

	
	
	@Override
	public JejuUser viewProfile(int userno) {
		return mypageDao.selectByProfile(userno);
	}
	
	@Override
	public void updateProfile(JejuUser user, MultipartFile fileupload) {
		
		logger.info("------- 첨부파일 업로드 시작 -------");
		
		if (fileupload.getSize() <= 0) {
			return;
		}
		
		//파일이 저장될 경로 (real path)
		String storedPath = context.getRealPath("resources/upload");
		logger.info("real path: {}", storedPath);
		
		
		//폴더가 존재하지 않으면 생성하기
		File stored = new File(storedPath);
		if (!stored.exists()) {
			stored.mkdir();
		}
		
		//저장될 파일의 이름 생성
		String filename = fileupload.getOriginalFilename();
		
		//UUID 생성
		String uid = UUID.randomUUID().toString().split("-")[4];
		
		//원본파일이름에 UUID 추가
		filename += uid;
		logger.info(filename);
		
		
		//저장된 파일 정보 객체
		File dest = new File(stored, filename);
		
		try {
			//업로드된 파일 저장하기
			fileupload.transferTo(dest);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//DB에서 origin, stored에 널 값 넣어주기
		mypageDao.deleteProfile(user);
		
		
		//DB에 기록하기
		user.setUserOrigin(fileupload.getOriginalFilename());
		user.setUserStored(filename);
		
		
		mypageDao.updateProfile(user);
	}

	
	@Override
	public void deleteProfile(JejuUser user) {
		//DB에서 origin, stored에 널 값 넣어주기
		mypageDao.deletePhoto(user);
	}

	
	@Override
	public void updateUser(JejuUser user) {
		mypageDao.updateUser(user);
	}

	@Override
	public boolean checkPw(int userno, String oldpw) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userno", userno);
		map.put("oldpw", oldpw);
		
		int res = mypageDao.checkPw(map);
		
		if( res == 1) {
			return true;
		
		} else {
			return false;
		}
		
	}

	
	@Override
	public void updatePw(JejuUser user) {
		mypageDao.updatePw(user);
	}





	
	
	

}
