package jeju.service.impl;


import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeju.dao.face.JoinDao;
import jeju.dto.JejuUser;
import jeju.service.face.JoinService;
import jeju.util.AuthMailHandler;


@Service
public class JoinServiceImpl implements JoinService {
	
	@Autowired private JoinDao joinDao;
	@Autowired private JavaMailSenderImpl mailSender;
	
	private int size;
	
	//인증키 생성
	private String getKey(int size) {
		this.size = size;
		return init();
	}
	
	//인증코드 난수 발생
	private String init() {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
				
		while(sb.length() < size) {
			num = random.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}
	
	@Override
	public int checkId(String userId) {
		//아이디 중복 조회
		return joinDao.selectCountId(userId);
	}
	
	@Override
	public int checkNick(String userNick) {
		//닉네임 중복 조회
		return joinDao.selectCountNick(userNick);
	}	
	
	@Override
	@Transactional
	public void join(JejuUser user) {
				
		//임의의 인증키 생성
		String authkey = getKey(6); //6자리 난수 인증코드		
		user.setUserMailAuthkey(authkey); //생성된 인증키 DTO객체에 저장
		
		System.out.println("authkey : " + authkey);
		
		//회원정보 삽입
		joinDao.insertUser(user);
	
		//인증메일 발송
		try {
			AuthMailHandler sendAuthMail = new AuthMailHandler(mailSender);
				
			sendAuthMail.setSubject("[어느 날, 제주] 회원가입 인증 이메일");
			sendAuthMail.setText(new StringBuffer().append("<h1>회원가입 이메일 인증</h1>")
								.append("<p>아래의 인증 링크를 클릭하셔서 회원가입을 완료해주세요.</p>")
								.append("<a href=http://localhost:8088/user/confirmMail?authkey=")
								.append(authkey)
								.append(" target='_blank'>이메일 인증 확인</a>")
								.toString());
			sendAuthMail.setFrom("onedayjeju@gmail.com", "관리자");
			sendAuthMail.setTo(user.getUserEmail());
			sendAuthMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void checkMailAuth(String authkey) {
		//이메일인증 상태 업데이트
		joinDao.updateMailAuth(authkey);
	}
	
}
