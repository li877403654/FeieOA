package cn.feie.oa.dao;

import java.util.List;
import java.util.Map;

import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.User;

public interface DeptDao {

	
	
	/**
	 * 根据部门id查找名字
	 * @return
	 */
	Dept getDeptById(int did);
	
	/**
	 * 删除部门
	 * @param did
	 * @return
	 */
	boolean delDept1(int did);
	/**
	 * 更新部门
	 * @param dept
	 * @return
	 */
	boolean updateDept(Dept dept);
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	boolean addDept(Dept dept);
	/**
	 * 多表查询全部部门查询 did dname deptuid uname
	 * @return
	 */
	List<Map<String, Object>> findAllDept();
	
	
	/**
	 * 根据id查询全部员工
	 * @param deptid
	 * @return
	 */
	List<User> findUserByDId(int did,int pagestart,int pagesizi);
	/**
	 * 多表查找全部user
	 * @return
	 */
	List<User> findAlls(int pagestart,int pagesizi);
	public List<Map<String, Object>> getUserByDid(int did);
	public int delDeptuid(int uid);
	public int delPostuid(int uid);
	public int delDeptUser(int uid);
	public int delDept(int did);
	boolean updateUserDid(int uid ,int did);

	List<Map<String, Object>> findChildList(int did);

	List<Dept> findDept();
	List<Dept> findAllChild(int dpid);
}
