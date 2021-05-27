package jeju.dao.face;

import jeju.dto.JejuUser;

public interface LoginDao {
	

	public int selectCntUser(JejuUser login);
	
	public String selectUserNick(JejuUser login);
	
	public String selectUserGrade(JejuUser login);
	
}
