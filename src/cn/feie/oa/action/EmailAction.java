package cn.feie.oa.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.feie.oa.domain.Email;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.EmailService;
import cn.feie.oa.service.UserService;

@Controller
@Scope("prototype")
public class EmailAction {
	private static final String User = null;
	@Resource
	private EmailService emailService;
	@Resource
	private UserService userService;
	
	private int num = 30;
	
	
	@RequestMapping("/email_box")
	public String email_box(Model model,int startnum,String box,HttpSession session){
		//集成
			int uid = ((cn.feie.oa.domain.User) session.getAttribute("user")).getUid();
			List<Email> f = emailService.box(uid,uid,((startnum-1)*30),num,box);
			int sumemail = emailService.box(uid,uid,0,100,box).size();
			model.addAttribute("num", startnum);
			model.addAttribute("sumnum", emailService.sumnum(sumemail));
			model.addAttribute("email",f);
			model.addAttribute("sumemail",sumemail);
		return "Person_Message/"+box;
	}
	
	
	@RequestMapping("/email_out")
	public String emailOut(Model model){
		//编辑邮件
		List<User> u = userService.findAll();
		model.addAttribute("ulist", u);
		
		return "Person_Message/saveUI";
	}
	@RequestMapping("/email_selectReceiverUI")
	public String email_selectReceiverUI(Model model){
		//选择用户
		return "Person_Message/selectReceiverUI";
	}
	@RequestMapping("email_save")
	public String save(Model model,String userstrings,Email email,HttpServletRequest request) throws UnsupportedEncodingException{
		//发送邮件
		request.setCharacterEncoding("utf-8");
		email.setGodate(new Date());
		email.setWaste(0);
		email.setDraft(0);
		if(email.getEid()==0){
		String[] ustr = userstrings.split(",");
		for (String string : ustr) {
			email.setComeid(Integer.valueOf(string));
			emailService.addEmail(email);
		}
		}else{
			String[] ustr = userstrings.split(",");
			for (String string : ustr) {
				email.setComeid(Integer.valueOf(string));
				
			}	
		}
		return "redirect:/email_box.action?uid="+email.getGoid()+"&startnum=1&box=outbox";
	}
	
	@RequestMapping("email_show")
	public String show(Model model,int eid,HttpSession session){
		//邮件内容
		Email email = emailService.getEmailByEid(eid);
		User u = (User) session.getAttribute("user");
		int newsnum = emailService.newsnum(u.getUid());
		session.setAttribute("newsnum",newsnum);
		if(u.getUid()==email.getComeid()){
			emailService.newsno(eid);
		}
		model.addAttribute("email", email);
		return "Person_Message/show";
	}
	@RequestMapping("email_newout")
	public String newout(Model model,int eid){
		//重新发送
		Email email = emailService.getEmailByEid(eid);
		emailService.addEmail(email);
		return "redirect:/email_box.action?uid="+email.getGoid()+"&startnum=1&box=outbox";
	}
	@RequestMapping("email_del")
	public String del(Model model,int eid,HttpServletRequest request,String box){
		User u = (User) request.getSession().getAttribute("user");
		emailService.wasteEmail(eid);
		return "redirect:/email_box.action?uid="+u.getUid()+"&startnum=1&box="+box;
	}
	@RequestMapping("email_delete")
	public String delete(Model model,int eid,HttpServletRequest request,String box){
		User u = (User) request.getSession().getAttribute("user");
		emailService.delEmail(eid);
		return "redirect:/email_box.action?uid="+u.getUid()+"&startnum=1&box="+box;
	}
	
	@RequestMapping("email_out1")
	public String email_out(Model model,int eid){
		emailService.draftEmail(eid);
		List<User> u = userService.findAll();
		Email email = emailService.getEmailByEid(eid);
		model.addAttribute("ulist", u);
		model.addAttribute("email", email);
		return "Person_Message/saveUI";
	}
	
	@RequestMapping("email_reply")
	public String email_reply(Model model,int goid){
		List<User> u = userService.findAll();
		model.addAttribute("ulist", u);
		Email e = new Email();
		e.setGoid(goid);
		model.addAttribute("email",e);
		return "Person_Message/saveUI";
	}
	@RequestMapping("email_restore")
	public String email_restore(Model model,int eid,HttpSession session){
		User u = (User) session.getAttribute("user");
		emailService.restoreEmail(eid);
		return "redirect:/email_box.action?uid="+u.getUid()+"&startnum=1&box=waste";
	} 
	@RequestMapping("/email_drafybox")
	public String email_drafybox(Model model,String userstrings,Email email,HttpServletRequest request) throws UnsupportedEncodingException{
				request.setCharacterEncoding("utf-8");
				email.setGodate(new Date());
				email.setWaste(0);
				email.setDraft(1);
				if(email.getEid()==0){
				String[] ustr = userstrings.split(",");
				for (String string : ustr) {
					email.setComeid(Integer.valueOf(string));
					emailService.addDrafyEmail(email);
				}
				}else{
					String[] ustr = userstrings.split(",");
					for (String string : ustr) {
						email.setComeid(Integer.valueOf(string));
						
					}	
				}
				return "redirect:/email_box.action?uid="+email.getGoid()+"&startnum=1&box=outbox";
	}
	
}





//@RequestMapping("/email_go")
//public String go(Model model,int uid,int startnum){
//	//发送邮件
//	System.out.println(uid);
//	List<Email> f = emailService.box(uid,((startnum-1)*30),num,"outbox");
//	int sumemail = emailService.box(uid,0,1000,"outbox").size();
//	model.addAttribute("num",startnum);
//	model.addAttribute("sumnum", emailService.sumnum(sumemail));
//	model.addAttribute("email",f);
//	model.addAttribute("sumemail",sumemail);
//	return "Person_Message/outbox";
//}
//@RequestMapping("/email_come")
//public String comeEmail(Model model,int uid, int startnum){
//	//收邮件
//	System.out.println("页数"+startnum+"uid="+uid);
//	List<Email> f = emailService.box(uid,((startnum-1)*30),num,"inbox");
//	int sumemail = emailService.box(uid,0,1000,"inbox").size();
//	model.addAttribute("num",startnum);
//	model.addAttribute("sumnum", emailService.sumnum(sumemail));
//	model.addAttribute("email",f);
//	model.addAttribute("sumemail",sumemail);
//	return "Person_Message/inbox";
//} 
//@RequestMapping("email_draft")
//public String draft(Model model,int uid,int startnum){
//	//草稿箱
//	List<Email> f = emailService.box(uid,((startnum-1)*30),num,"draftbox");
//	int sumemail = emailService.box(uid,0,1000,"draftbox").size();
//	model.addAttribute("num", startnum);
//	model.addAttribute("sumnum", emailService.sumnum(sumemail));
//	model.addAttribute("email",f);
//	model.addAttribute("sumemail",sumemail);
//	return "Person_Message/draftbox";
//}
//@RequestMapping("/email_waste")
//public String email_waste(Model model,int uid,int startnum){
//	//垃圾箱
//		List<Email> f = emailService.box(uid,((startnum-1)*30),num,"waste");
//		int sumemail = emailService.box(uid,0,1000,"waste").size();
//		model.addAttribute("num", startnum);
//		model.addAttribute("sumnum", emailService.sumnum(sumemail));
//		model.addAttribute("email",f);
//		model.addAttribute("sumemail",sumemail);
//	return "Person_Message/waste";
//}