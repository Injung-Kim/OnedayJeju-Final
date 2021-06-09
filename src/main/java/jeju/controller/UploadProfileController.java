package jeju.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jeju.dto.JejuUser;
import jeju.service.face.LoginService;
import jeju.service.face.MypageService;

@Controller
public class UploadProfileController {
	@Autowired ServletContext context;
	@Autowired private MypageService mypageService;
	@Autowired private LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(UploadProfileController.class);
	
	

	//프로필 사진 업로드 처리
	@RequestMapping(value = "/member/mypage/profile", method = RequestMethod.POST)
	public String updateProfileProc(
			HttpSession session
			, JejuUser user
			, @RequestParam (value = "file", required = false) MultipartFile fileupload ) {
		
		logger.info("/member/mypage/profile [POST]");
		
		logger.info("fileupload: {}", fileupload);
		logger.info("fileupload: {}", fileupload.getOriginalFilename());
		
		user.setUserNo((int) session.getAttribute("uno")); 
//		logger.info(user.toString());
		
		//프로필 사진 변경
		mypageService.updateProfile(user, fileupload);
		
		user.setUserId( (String) session.getAttribute("id") );
		session.setAttribute("profile", loginService.getProfileData(user)); //변경된 사진 세션에 저장

		
		return "redirect:/member/mypage";
		
	}
	
	
	//프로필 사진 삭제 처리
	@RequestMapping(value = "/member/mypage/delprofile", method = RequestMethod.POST)
	public String deleteProfileProc(
			HttpSession session
			, JejuUser user
			) {
		
		logger.info("/member/mypage/delprofile [POST]");
		
		user.setUserNo((int) session.getAttribute("uno"));
		
		if(session.getAttribute("profile") != null) {
			session.setAttribute("profile", null);

			//프로필 사진 삭제
			mypageService.deleteProfile(user);

		} else {
            logger.info("profile 세션 not null");
         }
		

		return "redirect:/member/mypage";
	}
	
}
