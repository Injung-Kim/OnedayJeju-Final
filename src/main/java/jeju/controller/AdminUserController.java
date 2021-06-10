package jeju.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jeju.dto.JejuUser;
import jeju.service.face.AdminUserService;
import jeju.util.Paging;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired private AdminUserService adminUserService;
	
	@RequestMapping(value="/list")
	public void getList(Paging inData, Model model) {
		logger.info("/admin/list [GET]");
		
		//페이징 계산
		Paging paging = adminUserService.getPaging(inData);
		logger.debug(paging.toString());
		
		//회원 목록 조회
		List<JejuUser> userList = adminUserService.getList(paging);
		for(int i=0; i<userList.size(); i++) {
			logger.debug(userList.get(i).toString());
		}
		
		//모델값 전달
		model.addAttribute("userList", userList);
		model.addAttribute("paging", paging);
	}
	
	
}
