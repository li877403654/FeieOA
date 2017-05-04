package cn.feie.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.feie.oa.domain.Privilege;
import cn.feie.oa.service.impl.PrivilegeServiseImpl;

public class OAInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	//1.获取spring容器
//	WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
//	//2.从spring容器中获取pricilegeService
//	PrivilegeServiseImpl servise = (PrivilegeServiseImpl) applicationContext.getBean("PrivilegeServiseImpl");
//	//3.使用哦
//	List<Privilege> topPriviege = servise.findTopPrivielge();
//	//4.将权限数据放入application作用域
//	sce.getServletContext().setAttribute("privilegeTopList", topPriviege);
//	System.out.println("权限数据放入 applicationContext");
	}

}
