package jeju.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	
	public static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info(" + + + 로그인 페이지 인터셉터 시작 + + + ");
		
		HttpSession session = request.getSession();
		
		//로그인 상태일 때 로그인 페이지&아이디, 비밀번호 찾기 페이지 이동 불가
		if (session.getAttribute("login") != null) { 
			logger.info(" >> 접속 불가: 로그인 상태");
			
			response.sendRedirect("/"); //메인페이지로
			
			return false;
		}
		
		logger.info(" >> 접속 가능: 비로그인 상태");
		
		return true;
	}

	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info(" + + + 로그인 페이지 인터셉터 끝 + + + ");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
