package cn.feie.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.feie.oa.domain.User;
import cn.feie.oa.service.UserService;
@Controller
@Scope("prototype")
public class HomeAction {
	@Resource
	private UserService userService;
	
	@RequestMapping("home_index")
	public String index(){
	
		return "index";
	}
	@RequestMapping("home_top")
	public String top(){
	
		
		
		return "top";
	}
	@RequestMapping("home_left")
	public String left(){
	
		
		
		return "left";
	}
	@RequestMapping("home_right")
	public String right(){
	
		
		
		return "right";
	}
	@RequestMapping("home_bottom")
	public String bottom(){
	
		
		
		return "bottom";
	}
	@RequestMapping("home_logout")
	public String logout(HttpServletRequest request){
		
		return "System_User/logout";
	}
	@RequestMapping("getOnLineUser")
	public @ResponseBody int getOnLineUser(){
		int i = userService.getOnLineUser();
		return i;
	}
	@RequestMapping("closeAll")
	public @ResponseBody void closeAll(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		userService.updateOnLine0(user.getUid());
	}
	@RequestMapping("onLineUserList")
	public String onLineUserList(Model model){
		List<User> list = userService.onLineUserList();
		model.addAttribute("list",list);
		return "System_User/onLinueUser";
	}
}
