package cn.feie.oa.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.eclipse.jdt.internal.compiler.problem.ShouldNotImplement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.feie.oa.dao.DeptDao;
import cn.feie.oa.dao.PostDao;
import cn.feie.oa.dao.UserDao;
import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.DeptService;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
	
	@Resource
	private DeptDao deptdao;
	
	@Resource
	private PostDao postDao;
	@Resource
	private UserDao userdao;
	
	
	public boolean addDept(Dept dept){
		return deptdao.addDept(dept);
	}
	
	public boolean updateDept(Dept dept){
		return deptdao.updateDept(dept);
	}
	
	
	public List<Dept> findTopDept(){
		List<Dept> AllDept = deptdao.findDept();
		return AllDept;	
	}
	
	public List<Dept> findAllDept(){
		String str = "┠";
		List<Dept> AllDept = deptdao.findDept();
		List<Dept> d = new ArrayList<>();
		showChildList(AllDept,d, str);
		return d;				
	}
	
	public void showChildList(Collection<Dept> tolist,List<Dept> depts,String str) {
		for (Dept dept : tolist) {
			dept.setDname(str+dept.getDname());
			//顶级菜单
			depts.add(dept);
			
			List<Dept> findAllChild = deptdao.findAllChild(dept.getDid());
			if (findAllChild!=null&&findAllChild.size()>0) {
				showChildList(findAllChild,depts,"&nbsp;&nbsp;&nbsp;"+str);				
			}
		}
	}
//	public List<Dept> showChildList(Dept dept){
//		//查询出子集菜单
//		List<Dept> depts = deptdao.findAllChild(dept.getDpid());
//		for (Dept dept2 : depts) {
//			List<Dept> showChildList = showChildList(dept2);
//			dept.setDepts(showChildList);
//		}
//		return depts;
//	}
	
	public boolean delDept(int did) {
		int i =0;
		//System.out.println("1查询该部门员工");
		List<Map<String, Object>> u = deptdao.getUserByDid(did);
		for (Map<String, Object> map : u) {
			
			
			for (String key: map.keySet()) {
				//Map.Entry e = (Entry) it.next();
				//int uid = (int) e.getValue();
				int uid = (int) map.get("uid");
				//System.out.println("2设置该部门管理的部门 ，主管为空");
//				i+=deptdao.delDeptuid(uid);
				//System.out.println("3删除该部门员工所有职务");
				i+=deptdao.delPostuid(uid);
				//System.out.println("4删除该部门员工");
				i+=deptdao.delDeptUser(uid);
			}
			
//			Iterator it = map.entrySet().iterator();
//			System.out.println("it="+it);
//			System.out.println("map="+map);
//			while (it.hasNext()) {
//				//Map.Entry e = (Entry) it.next();
//				//int uid = (int) e.getValue();
//				int uid = (int) map.get("uid");
//				System.out.println(uid);
//				//System.out.println("2设置该部门管理的部门 ，主管为空");
//				i+=deptdao.delDeptuid(uid);
//				//System.out.println("3删除该部门员工所有职务");
//				i+=deptdao.delPostuid(uid);
//				//System.out.println("4删除该部门员工");
//				i+=deptdao.delDeptUser(uid);
//			}
		}
		//System.out.println("5删除部门");
		i +=deptdao.delDept(did);
		//System.out.println(i);
		return true;
	}
	
	@Override
	public List<User> findUserByDId(int did,int pagestart,int pagesizi) {
		Set<Post> set =null;
		List<User> user = deptdao.findUserByDId(did, pagestart, pagesizi);
		for (User user2 : user) {
			List<Post> po =postDao.getPostByUid(user2.getUid());
			user2.setPosts(po);
		}
		return user;
	}
	
	@Override
	public List<User> findAlls(int pagestart, int pagesizi) {
		Set<Post> set =null;
		List<User> user = deptdao.findAlls(pagestart, pagesizi);
		for (User user2 : user) {
			List<Post> po =postDao.getPostByUid(user2.getUid());
			user2.setPosts(po);
		}
		return user;
	}

	@Override
	public Dept getDeptById(int did) {
		// TODO Auto-generated method stub
		return deptdao.getDeptById(did);
	}

	@Override
	public boolean updateUserDid(int uid, int did) {
		// TODO Auto-generated method stub
		return deptdao.updateUserDid(uid, did);
	}

	@Override
	public List<Map<String, Object>> findChildList(int did) {
		return deptdao.findChildList(did);
	}
	

}
