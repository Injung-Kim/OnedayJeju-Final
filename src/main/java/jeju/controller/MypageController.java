package jeju.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jeju.dto.JejuUser;
import jeju.service.face.MypageService;

@Controller
public class MypageController {

	@Autowired private MypageService mypageService;
	
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	
	//마이페이지 화면
	//유저 정보 가져오기
	@RequestMapping(value = "/member/mypage", method = RequestMethod.GET)
	public String mypage (HttpSession session, Model model) {
		logger.info("/member/mypage 마이페이지 로드 완료");
		
		//세션에서 회원번호 가져오기
		int userno = (int) session.getAttribute("uno");
		
		//유저 정보 가져와서 띄워주기
		JejuUser user = mypageService.getUserData(userno);
		logger.info("가져온 유저 정보: {}", user);
		
		//프로필 사진(썸네일) 조회
//		JejuUser thum = mypageService.viewProfile(userno);
		
		
		//모델값 전달
		model.addAttribute("userData", user);
//		model.addAttribute("profile", thum);
	
		return "/member/mypage";
	}
	
	
	
	//닉네임 변경 처리
	@RequestMapping(value = "/member/mypage", method = RequestMethod.POST)
	public String updateNickProc (
			HttpSession session, Model model
//			, @RequestParam ("newNick") String nick
			, JejuUser user	) {
			
		logger.info("/member/mypage [POST]");
//		logger.info(user.toString());
		
		
		
		//중복 닉네임 확인
		String nick = user.getUserNick();
		
		
		//특수문자를 제외한 모든 문자 허용
		String p = "(^[0-9a-zA-Z가-힣]*$)";
		boolean i = Pattern.matches(p, nick);
		
		
		// 1. 특수문자가 있을 경우
		if(i == false) { 
			model.addAttribute("msg", "영어 대소문자, 숫자, 한글만 설정 가능합니다");
			model.addAttribute("url", "/member/mypage");
			
			return "/member/resultnick";
		}
		
		
		// 2. 20자 초과 / 아무 값도 입력하지 않을 경우
		if( nick.length() > 20 || "".equals(nick) ) {
			model.addAttribute("msg", "닉네임은 최소 1자, 최대 20자로 설정할 수 있습니다");
			model.addAttribute("url", "/member/mypage");
			
			return "/member/resultnick";
		}
		
		
		// 3. DB에서 존재하는 닉네임인 지 확인
		int cntUser = mypageService.selectCntNick(nick);
		model.addAttribute("cntUser", cntUser);
		
		if (cntUser < 1) { //존재하지 않을 때
			
			//세션에서 회원번호 가져오기
			user.setUserNo((int) session.getAttribute("uno"));

			//닉네임 변경하기
			mypageService.updateNick(user);
			
			logger.info(">>> 닉네임 변경 성공");
			logger.info("변경된 닉네임 {}", nick);
			session.setAttribute("nick", nick);

			model.addAttribute("msg", "닉네임 변경 완료!");
			model.addAttribute("url", "/member/mypage");
		
		} else { //존재할 때
			
			logger.info(">>> 닉네임 변경 실패");
			model.addAttribute("msg", "중복된 닉네임입니다");
			model.addAttribute("url", "/member/mypage");
		}
		
		return "member/resultnick";
	}
	
	
	
	//회원정보수정 처리
	@RequestMapping(value = "/member/mypage/settings", method = RequestMethod.POST)
	public void settingsProc (
			@RequestParam("newPhone") String phone
			, @RequestParam("newEmail") String email
			, @RequestParam("newAddress") String address
			, @RequestParam("newAddressDetail") String addressDetail
			, HttpSession session
			, Writer out
			) {
		logger.info("/member/mypage/settings [POST]");

		
		//받아온 파라미터값 저장
		JejuUser user = new JejuUser();
		user.setUserNo((int) session.getAttribute("uno"));
		user.setUserEmail(email);
		user.setUserAddress(address);
		user.setUserAddressDetail(addressDetail);
		user.setUserPhone(phone);
		
		logger.info("받아온 parameter: {}", user);
		
		
		//회원정보 업데이트
		mypageService.updateUser(user);
		
		//jsonView
		try {
//			out.append("{\"redirectUrl\":\"/member/mypage\"}");
			out.write ("{\"redirectUrl\":\"/member/mypage\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//비밀번호 변경 처리
	@RequestMapping(value = "/member/mypage/updatepw", method = RequestMethod.POST)
	public String updatePwProc (
			@RequestParam("oldPw") String oldpw
			, @RequestParam("newPw") String newpw
			, @RequestParam("newPwRe") String newpwre
			, HttpSession session
			, Model model
			) {
		logger.info("/member/mypage/updatepw [POST]");
		

		//비밀번호 확인
		int userno = (int) session.getAttribute("uno");
		boolean checkPw = mypageService.checkPw(userno, oldpw);
		model.addAttribute("check", checkPw);
		
		
		//비밀번호 정규식
		//	8~15자리 영어 소문자, 숫자
		String pwReg = "(^[a-z0-9]{8,15}$)";
		boolean i = Pattern.matches(pwReg, newpw);
		
		
		model.addAttribute("redirect", false);
		
		if(checkPw) { //oldpw가 일치할 때
			
			if( !newpw.equals(newpwre)) {
				logger.info(">>> 비밀번호 변경 실패(확인값 불일치)");
				
				model.addAttribute("msg", "새 비밀번호와 새 비밀번호 확인 값이 일치하지 않습니다");
//				model.addAttribute("url", "/member/updatepw");

//				return "jsonView";
				
				
			} else if (i == false) {
				logger.info(">>> 비밀번호 변경 실패(정규식 불일치)");
				
				model.addAttribute("msg", "비밀번호는 8 ~ 15자리 영어 소문자, 숫자 조합으로 설정해야합니다");
//				model.addAttribute("url", "/member/updatepw");
				
//				return "jsonView";
			
				
			} else { 
				
				//받아온 파라미터값 저장
				JejuUser user = new JejuUser();
				user.setUserNo((int) session.getAttribute("uno"));
				user.setUserPw(newpw);
				
				mypageService.updatePw(user);
				
				logger.info(">>> 비밀번호 변경 성공");
				logger.info("변경된 내용: {}", user.getUserPw());
				
				model.addAttribute("msg", "비밀번호 변경 완료!");
				model.addAttribute("redirect", true);
				model.addAttribute("url", "/member/mypage");
				
//				return "member/updatepw";
			}

		
		} else { //oldpw가 일치하지 않을 때
			
			logger.info(">>> 비밀번호 변경 실패(현재 비밀번호 불일치)");
			model.addAttribute("msg", "현재 비밀번호가 일치하지 않습니다");
//			model.addAttribute("url", "/member/updatepw");
		}
		
		return "jsonView";
//		return "member/updatepw";
		
	}
	
}
