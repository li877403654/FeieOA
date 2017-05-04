package cn.feie.oa.action;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.Privilege;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.PostServise;
import cn.feie.oa.service.PrivilegeServise;
import cn.feie.oa.service.UserService;

@Controller
@Scope("prototype")
public class PostAction {
	@Resource
	private PostServise postServise;
	@Resource
	private PrivilegeServise privilegeServise;
	@Resource
	private UserService userService;
	
	@RequestMapping("/post_list")
	public String post_list(Model model) {
		List<Post> post = postServise.allPost();
		System.out.println(post);
		model.addAttribute("list", post);
		return "System_Post/list";
	}
	
	@RequestMapping("post_ifPostName")
	public @ResponseBody boolean post_ifPostName(String pname){
		Post p = postServise.getPostBypname(pname);
		return p==null?true:false;
		}
	
	@RequestMapping("post_addPostUI")
	public String post_addPostUI(Model model){
		return "System_Post/addPost";
	}
	
	@RequestMapping("post_addPost")
	public String post_addPost(Model model,Post post){
		if (post.getPid()!=0) {
			postServise.updatePost(post);
		}else{
			postServise.addPost(post);
		}
			return "redirect:/post_list.action";
		
	}
	@RequestMapping("post_deletePost")
	public String post_deletePost(Integer pid){
		postServise.deletePost(pid);
		return "redirect:/post_list.action";
	}
	@RequestMapping("post_setPrivilegeUI")
	public String post_setPrivilegeUI(Integer pid,Model model){
		//查询
		Post post = postServise.getPostBypid(pid);
		model.addAttribute("post",post);
		//查询全部权限
		//List<Privilege> privileges = privilegeServise.findPriviegeAll();
		//查询顶级权限
		List<Privilege> privileges = privilegeServise.findTopPrivielge();
		model.addAttribute("privileges",privileges);
		//查询角色权限
		List<Privilege> pri = privilegeServise.getPrivilegeIdsByPid(pid);
		post.setPrivileges(pri);
		return "System_Post/privilegeList";
	}
	@RequestMapping("post_setPrivilege")
	public String post_setPrivilege(Long[] privilegeIds,Integer pid){
		if (privilegeIds!=null&&privilegeIds.length>0) {
			privilegeServise.deletePrivilege(pid);
			privilegeServise.updatePrivilege(privilegeIds,pid);
		}else{
			privilegeServise.deletePrivilege(pid);
		}
		return "redirect:/post_list.action";
	}
	
	@RequestMapping("post_updatePostUI")
	public String post_updatePostUI(Model model,Integer pid){
		Post post = postServise.getPostBypid(pid);
		model.addAttribute("post",post);
		return "System_Post/addPost";
	}
	@RequestMapping("post_ifPrivilege")
	public @ResponseBody boolean post_ifPrivilege(HttpSession session,Long priId){
		User u = (User) session.getAttribute("user");
		String pidIndex = postServise.getPidByUid(u.getUid());
		String[] pidString = pidIndex.split(",");
		List<Privilege> priIdByPidString = privilegeServise.getPriIdByPidString(pidString);
		int frequency = Collections.frequency(priIdByPidString,priId);
		return frequency>0?false:true;
	}
	
	
}
