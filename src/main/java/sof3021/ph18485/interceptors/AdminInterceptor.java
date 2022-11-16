package sof3021.ph18485.interceptors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import sof3021.ph18485.entities.AccountRole;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Autowired
	private ServletContext context;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		HttpSession session = req.getSession();
		if (session.getAttribute("username") != null && session.getAttribute("role") == AccountRole.ADMIN) {
			resp.sendRedirect(context.getContextPath() + "/home");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
