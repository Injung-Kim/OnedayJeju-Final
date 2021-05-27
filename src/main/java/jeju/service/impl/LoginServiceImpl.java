package jeju.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jeju.dao.face.LoginDao;
import jeju.dto.JejuUser;
import jeju.service.face.LoginService;

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
	public String getSearchId(String name, String phone) {
		
		JejuUser userData = new JejuUser();
		userData.setUserName(name);
		userData.setUserPhone(phone);
		
		return loginDao.selectSearchId(userData);
	}


//	@Override
//	public String getSearchId(String name, String phone) {
//		
//		JejuUser jejuUser = new JejuUser();
//		jejuUser.setUserName(name);
//		jejuUser.setUserPhone(phone);
//		return loginDao.selectSearchId(jejuUser);
//	}


}
