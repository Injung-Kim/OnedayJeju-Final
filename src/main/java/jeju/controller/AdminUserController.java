package jeju.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jeju.dto.JejuUser;
import jeju.service.face.AdminUserService;
import jeju.util.Paging;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired private AdminUserService adminUserService;
	
	@RequestMapping(value="/list")
	public void getList(Paging inData, Model model) {
		logger.info("/admin/user/list [GET]");
		
		//페이징 계산
		Paging paging = adminUserService.getPaging(inData);
		paging.setSearch(inData.getSearch()); //검색어 적용
		paging.setCondition(inData.getCondition()); //검색조건 적용
//		logger.info("페이징: {}", paging);
		
		List<JejuUser> userList = adminUserService.getList(paging); //목록 조회
		for(int i=0; i < userList.size(); i++) {
//			logger.info(userList.get(i).toString());
		}
		model.addAttribute("list", userList); //목록 전달
		model.addAttribute("paging", paging); //페이징 적용
	}
	
	@RequestMapping(value = "/info")
	public String getInfo(JejuUser userInfo, Model model) {
//		logger.info("/admin/user/info [GET]");
//		logger.info("userNo : {}", userInfo.toString());
		
		userInfo = adminUserService.getInfo(userInfo); //정보 조회
//		logger.info("사용자정보 : {}", userInfo.toString());
		model.addAttribute("info", userInfo); //정보 전달
		
		return "admin/user/info";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(JejuUser user, Model model) {
//		logger.info("/admin/user/modify [GET]");
//		logger.info("userNo : {}", user.toString());
		
		user = adminUserService.getInfo(user); //정보 조회
//		logger.info("사용자정보 : {}", user.toString());
		model.addAttribute("user", user); //정보 전달
		
		return "admin/user/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyProcess(JejuUser user) {
//		logger.info("/admin/user/modify [POST]");
//		logger.info("사용자정보 수정 : {}", user);
		
		adminUserService.modify(user); //정보 수정
		
		return "redirect:/admin/user/info?userNo="+user.getUserNo(); //해당 사용자의 정보조회 페이지로 이동
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(JejuUser user) {
//		logger.info("/admin/user/delete [GET]");
		
		adminUserService.delete(user); //계정 삭제
		
		return "redirect:/admin/user/list"; //목록페이지로 이동
	}
	
}
