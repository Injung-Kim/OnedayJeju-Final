package jeju.util;

import org.apache.commons.mail.HtmlEmail;

import jeju.dto.JejuUser;

public class MailUtil {

	public void sendMail(JejuUser user) {
		
		//Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "imlim1440@gmail.com";
		String hostSMTPpwd = "imlim1995..";
		
		//보내는 사람
		String fromEmail = "imlim1440@gmail.com";
		String fromName = "어느 날, 제주";
		
		String subject = "";
		String msg = "";
		
		subject = "[어느 날, 제주] 임시 비밀번호 발급 안내";
		msg += "<div><h3>" + user.getUserId() + "님의 임시 비밀번호 입니다. <br>로그인 후 비밀번호를 변경해주세요.</h3>";
		msg += "<p>임시 비밀번호: ";
		msg += user.getUserPw() + "</p></div>";
		
		// 받는 사람 E-Mail 주소
		String mail = user.getUserEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
