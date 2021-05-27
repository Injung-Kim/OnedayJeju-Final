package jeju.service.face;

import jeju.dto.JejuUser;

public interface LoginService {

	
	public boolean login(JejuUser login);
	
	public String getNickData(JejuUser login);
	
	public String getGradeData(JejuUser login);
	
}
