package cn.feie.oa.action;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.DeptService;
import cn.feie.oa.service.UserService;

@Controller
@Scope("prototype")
public class DeptAction {
	@Resource
	private DeptService deptService;
	@Resource
	private UserService userService;
	@RequestMapping("/dept_add")
	public String dept_addDept(Model model,Dept dept){
		//System.out.println(dept.toString());
		boolean b = deptService.addDept(dept);
		if(b){
			System.out.println("添加成功");
		}else{
			System.out.println("添加失败");
		}
		return "redirect:/dept_allDept.action";
	}
	/**
	 * 删除部门
	 * @param model
	 * @param did 删除部门id
	 * @return	重定向到遍历部门
	 * 
	 */
	@RequestMapping("/dept_del")
	public String dept_delDept(Model model,int did){
		boolean b = deptService.delDept(did);
		if(b){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		return "redirect:/dept_allDept.action";
	}
	
	@RequestMapping("/dept_update")
	public String dept_update(Model model,Dept dept){
		//System.out.println(dept.toString());
		boolean b = deptService.updateDept(dept);
		if(b){
			System.out.println("修改成功");
		}else{
			System.out.println("修改失败");
		}
		return "redirect:/dept_allDept.action";
	}
	@RequestMapping("/dept_allDept")
	public String dept_findDept(Model model){//全部部门遍历 did dname deptuid uname
		List<Dept> dept = deptService.findTopDept();
		model.addAttribute("dept",dept);
		return "System_Department/list";
	}
	
	
	@RequestMapping("/dept_getUname")
	public String getUnameByUid(Model model,Integer did,int go,Integer dpid){
		List<User> list = userService.findAll();
		
		List<Dept> findAllDept = deptService.findAllDept();
		model.addAttribute("deptList",findAllDept);
		model.addAttribute("uname",list);
		switch (go) {
		case 1:
			Dept d = deptService.getDeptById(did);
			model.addAttribute("dept", d);			
			return "System_Department/updateUI";
		case 2:
			if (dpid==null) {
				model.addAttribute("dpid",0);
				return "System_Department/saveUI";
			}else{
				model.addAttribute("dpid",dpid);
				return "System_Department/saveUI";
			}
		default:
			break;
		}
		return null;
		
	}
	/**
	 * 查询全部员工
	 * @param model
	 * @return
	 */
	@RequestMapping("/dept_getAllUser")
	public String dept_list(Model model) {
		List<User> list = deptService.findAlls(0,100);
		List<Dept> dept = deptService.findAllDept();
		model.addAttribute("alldept", dept);
		model.addAttribute("alluser",list);
		return "System_Department/deptlist";
	}
	@RequestMapping("/dept_getUserByDid")
	public String dept_getUserByDid(Model model,int did){
		if(did==0){
			return "redirect:/dept_getAllUser.action";
		}
		List<User> list = deptService.findUserByDId(did, 0, 100);
		List<Dept> dept = deptService.findAllDept();
		model.addAttribute("alldept", dept);
		model.addAttribute("alluser",list);
		model.addAttribute("mydid",did);
		return "System_Department/deptlist";
	}
	@RequestMapping("/dept_addUserUI")
	public String dept_addUserUI(Model model,int did){
		
		model.addAttribute("dept", deptService.findAllDept());
		model.addAttribute("alluser", deptService.findAlls(0, 100));
		return "System_Department/addUserUI";
	}
	@RequestMapping("/dept_addUser")
	public String dept_addUser(Model model,String userstrings,int did){
		if(StringUtils.isNoneBlank(userstrings)){
		String[] uid = userstrings.split(",");
			System.out.println();
			for (String string : uid) {
				boolean i = deptService.updateUserDid(Integer.valueOf(string), did);
				if(!i){
					System.out.println("添加失败");
				}
			}	
		}
		return "redirect:/dept_getAllUser.action";
	}
	@RequestMapping("dept_findChildList")
	public String dept_findChildList(int did,Model model,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		List<Map<String, Object>> dept = deptService.findChildList(did);
		model.addAttribute("dept", dept);
		model.addAttribute("dpid", did);
		Dept deptById = deptService.getDeptById(did);
		model.addAttribute("deptname",deptById.getDname());
		return "System_Department/list1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//遍历部门  部门名称 部门主管姓名																多表	根据部门id 查找 did dname deptuid uname
	//部门更新  查找部门 》																				
				//部门名称  部门主管（根据id查找姓名）》多表 id dname deptuid uname
			 // 写入部门 
				//部门名称   部门主管(遍历员工)（根据姓名查找id）》	部门编号	部门名称 主管id
	//添加部门  写入部门	先遍历员工(员工编号 员工名称)
				// 部门名称 部门主管id
	
}
