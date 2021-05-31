package jeju.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.LoginDao;
import jeju.dto.JejuUser;
import jeju.service.face.LoginService;
import jeju.util.MailUtil;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired private LoginDao loginDao;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


	@Override
	public boolean login(JejuUser login) {
		
		if (loginDao.selectCntUser(login) > 0) {
			
			return true;
		}
		return false;
	}


	@Override
	public String getNickData(JejuUser login) {
		return loginDao.selectUserNick(login);
	}


	@Override
	public String getGradeData(JejuUser login) {
		return loginDao.selectUserGrade(login);
	}
	
	
	@Override
	public int getUsernoData(JejuUser login) {
		return loginDao.selectUserNo(login);
	}


	@Override
	public String getSearchId(String name, String phone) {
		
		JejuUser userData = new JejuUser();
		userData.setUserName(name);
		userData.setUserPhone(phone);
		
		return loginDao.selectSearchId(userData);
	}


	@Override
	public String getSearchPw(String id, String email) {
		
		String result = null;
		
		JejuUser userData = new JejuUser();
		userData.setUserId(id);
		userData.setUserEmail(email);
		
		boolean isExist = loginDao.selectCntMem(userData);
		
		if(isExist) {
			
			//임시 비밀번호 생성
			int index = 0;
			char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
					'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' };
			
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < 8; i++) {
				index = (int) (charSet.length * Math.random());
				sb.append(charSet[index]);
			}
			
			String tempPw = sb.toString();
//			logger.info("임시 비밀번호 확인: " + tempPw);
			
			
			//회원 객체에 임시 비밀번호 담기
			userData.setUserPw(tempPw);
			
			//메일 전송
			MailUtil mail = new MailUtil();
			mail.sendMail(userData);
			
			//비밀번호 변경
			loginDao.updatePw(userData);
			
			result = "Success";
		
		} else {
			
			result = "Fail";
		}
		
		return result;
	}






}
