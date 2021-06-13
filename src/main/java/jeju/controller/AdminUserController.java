package jeju.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//		logger.info("/admin/user/list 접속");
//		logger.info("파라미터 : {}", inData);
		
		//페이징 계산
		Paging paging = adminUserService.getPaging(inData);
		paging.setSearch(inData.getSearch());
//	logger.info("페이징: {}", paging);
		
		//회원 목록 조회
		List<JejuUser> userList = adminUserService.getList(paging);
		for(int i=0; i<userList.size(); i++) {
//			logger.info(userList.get(i).toString());
		}
		
		//모델값 전달
		model.addAttribute("userList", userList);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value = "/info")
	public String getInfo(@RequestParam int userNo, Model model) {
//		logger.info("/admin/user/info userNo : {}", userNo);
		
		//사용자번호로 사용자정보 조회 
		JejuUser userInfo = adminUserService.getInfo(userNo);
//		logger.info("사용자정보 : {}", userInfo.toString());
		
		//모델값 전달
		model.addAttribute("info", userInfo);
		
		return "admin/user/info";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam int userNo) {
		logger.info("/admin/user/delete");
		
		//사용자계정 삭제
		adminUserService.delete(userNo);
		return "redirect:/admin/user/list";
	}
	
}
