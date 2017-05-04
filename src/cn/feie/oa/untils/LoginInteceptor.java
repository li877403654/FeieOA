package cn.feie.oa.untils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.feie.oa.domain.User;

public class LoginInteceptor  implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
			String URL = request.getRequestURI();
			HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(URL.contains("user_login")||u!=null){
			return true;
		}else{
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open ('"+request.getContextPath()+"/index.jsp','_top')");
			out.println("</script>");
			out.println("</html>");
			return false;
		}
	}
}
