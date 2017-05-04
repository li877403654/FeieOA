package cn.feie.oa.action;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.Privilege;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.DeptService;
import cn.feie.oa.service.PostServise;
import cn.feie.oa.service.PrivilegeServise;
import cn.feie.oa.service.UserService;

@Controller
@Scope("prototype")
public class UserAction {
	@Resource
	public UserService userService;
	@Resource
	public DeptService deptService;
	@Resource
	private PostServise postServise;
	@RequestMapping("/user_main")
	public String user_main(Model model){
		return "System_User/loginUI";
	}
	/*
	@RequestMapping("/user_list2")
	public String user_list1(Model model) {
		return "";
	}
	*/
	@RequestMapping("/user_findUser")
	public String user_list(Model model) {
		List<User> list = userService.findUser(0, 100);
		model.addAttribute("list",list);
		return "System_User/list";
	}
	@RequestMapping("/user_initializePassword")
	public String user_initialize(int uid){
		userService.initializePassword(uid);
		return "redirect:/user_findUser.action";
	}
	@RequestMapping("user_userdelete")
	public String user_delete(int uid){
		int i = userService.delete(uid);
		System.out.println(i);
		if (i>=3) {
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		return "redirect:/user_findUser.action";
	}
	@RequestMapping("user_updateUser")
	public String user_updateUser(int uid,Model model){
		List<Dept> dept = deptService.findAllDept();
		User user = userService.findByid(uid);
		List<Post> allPost = postServise.allPost();
		model.addAttribute("postlist",allPost);
		model.addAttribute("olduser", user);
		model.addAttribute("deptlist",dept);
		model.addAttribute("poststrings",postServise.getPidByUid(uid));
		return "System_User/updateUI";
	}
	@RequestMapping("user_userUpdate")
	public String updateuser(User user,String poststrings){
		userService.userUpdata(user, poststrings);
		return "redirect:/user_findUser.action";
	}
	@RequestMapping("user_addUser")
	public String user_addUser(User user,Model model){
		List<Dept> dept = deptService.findAllDept();
		List<Post> allPost = postServise.allPost();
		model.addAttribute("postlist",allPost);
		model.addAttribute("deptlist",dept);
		return "System_User/saveUI";
	}
	@RequestMapping("user_userAdd")
	public String user_userAdd(User user,String poststrings){
		String[] post = poststrings.split(",");
		userService.userAdd(user,post);
		
		return "redirect:/user_findUser.action";
	}
	@RequestMapping(value="user_ifName",method=RequestMethod.POST)
	public @ResponseBody int user_ifName(String uname){
		int uid = userService.ifName(uname);
		return uid;
	}
	@RequestMapping("user_user")
	public String user_user(HttpSession session,Model models){
		int uid = ((cn.feie.oa.domain.User) session.getAttribute("user")).getUid();
		User user = userService.findByid(uid);
		models.addAttribute("user",user);
		return "System_User/editUserInfoUI";
	}
	@RequestMapping("user_imager")
	public String user_addimager(Integer uid,HttpServletResponse response,HttpServletRequest request,Model model,User user){
		//判断提交过来的是否是文件上传表单
		File image = user.getImager();
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart) {
					//构造一个文件上传处理对象	
					DiskFileItemFactory factory = new DiskFileItemFactory();
					 factory.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
					   factory.setRepository(new File(request.getRealPath("/")
					     + "style/image"));// 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录
					ServletFileUpload upload = new ServletFileUpload(factory);
					// 从request得到 所有 上传域的列表
					//定义一个迭代器
					try {
						PrintWriter out = response.getWriter();
						Iterator items;
						//解析出表单中提交的文件内容
						items = upload.parseRequest(request).iterator();
						while (items.hasNext()) {//判断当前是否存在
							FileItem item = (FileItem) items.next();//返回当前元素
							//判断参数域是普通的表单输入域还是文件上传域,如果是表单输入域返回true
							if (!item.isFormField()) {
								String imgname = item.getName();//文件名
								// 得到去除路径的文件名
							    String t_name = imgname.substring(imgname.lastIndexOf("\\") + 1);
							    // 得到文件的扩展名(无扩展名时将得到全名)
							    String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
								long now = System.currentTimeMillis();
							    String.valueOf(now);
							    // 保存的最终文件完整路径,保存在web根目录下的ImagesUploaded目录下
							    String u = request.getContextPath()+"/style/image/"+uid+"."+t_ext;;
							    String u_name = request.getRealPath("/")+"style/image/"+uid+"."+t_ext;
							     // 保存文件
							 // u_name = "E:\\eclipse\\FeieOA\\WebContent\\style\\image/"+uid+"."+t_ext;
							    userService.addImagerName(u,uid);
							    	item.write(new File(u_name));
							   }
						}
					} catch (FileUploadException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return "redirect:/user_user.action?uid="+uid;
	}
	@RequestMapping("user_ifPassword")
	public @ResponseBody boolean ifpassword(User user){
		int i = userService.ifPassword(user);
		return i>0?true:false;
	}
	@RequestMapping("user_updatePassword")
	public String updatePassword(User user,Integer uid,String newpasswprd2){
		user.setPassword(newpasswprd2);
		int i = userService.updatePassword(user);
		return "redirect:/user_findUser.action";
	}
	@RequestMapping("user_userUpsw")
	public String user_Upsw(User user){
		return "System_User/editPasswordUI";
	}
	
}
