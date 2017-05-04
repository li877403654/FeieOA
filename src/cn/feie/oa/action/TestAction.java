package cn.feie.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.feie.oa.domain.Privilege;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.EmailService;
import cn.feie.oa.service.PostServise;
import cn.feie.oa.service.PrivilegeServise;
import cn.feie.oa.service.UserService;

@Controller
@Scope("prototype")
public class TestAction {
	@Resource
	private UserService userService;
	@Resource
	private EmailService emailService;
	@Resource
	private PrivilegeServise privilegeServise;
	@Resource
	private PostServise postServise;
	@RequestMapping("/user_list")
	public String user_list(Model model, String uname) {
		List<User> list = null;
		if (uname == null || uname.equals("all")) {
			list = userService.findAll();
		} else {
			list = userService.findByUname(uname);
			model.addAttribute("unamevalue", uname);
		}
		model.addAttribute("list", list);
		return "../../list";
	}

	@RequestMapping("/user_delete")
	public String user_delete(Model model, int uid) {
		int value = userService.delete(uid);
		return "redirect:/user_list.action";
	}

	@RequestMapping("/user_save")
	public String user_save(Model model, User user) {

		userService.add(user);
		return "redirect:/user_list.action";
	}

	@RequestMapping("/user_update")
	public String user_update(Model model, User user) {

		userService.update(user);
		return "";
	}

	@RequestMapping("/user_updateUI")
	public String user_updateUI(Model model, int uid) {

		User user = userService.findByid(uid);
		model.addAttribute("user", user);

		return "../../update";
	}

	@RequestMapping("/user_login01")
	public String user_aaa(Model model, User user,HttpServletRequest request) {
		String p = userService.login(user.getLoginname());	
		if (p!=null&&user.getPassword().equals(p)) {
			User u = userService.finduid(user.getLoginname(), user.getPassword());
			String user_index = user_index(model, u, request);
			return user_index;
		}else {
			return "redirect:/user_main.action";
		}
	}
	@RequestMapping("user_index")
	public String user_index(Model model,User u,HttpServletRequest request){
		int newsnum = emailService.newsnum(u.getUid());
		List<Privilege> privileges = privilegeServise.findTopPrivielge();
		String pidIndex = postServise.getPidByUid(u.getUid());
		String[] pidString = pidIndex.split(",");
		List<Privilege> priIdByPidString = privilegeServise.getPriIdByPidString(pidString);
		//上线
		userService.updateOnLine1(u.getUid());
		
		request.getSession().setAttribute("user",u);
		request.getSession().setAttribute("newsnum",newsnum);
		request.getSession().setAttribute("privileges",privileges);
		request.getSession().setAttribute("MyPri",priIdByPidString);
		return "index";
	}
	@RequestMapping("colseSession")
	public String cloceSession(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		userService.updateOnLine0(user.getUid());
		HttpSession s= request.getSession();
		s.invalidate();
		return "redirect:/user_login.action";
	}
	@RequestMapping("/user_login")
	public String user_login(HttpServletRequest request) {
		return "System_User/loginUI";
	}
}
