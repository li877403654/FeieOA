package cn.feie.oa.service;

import java.util.List;
import java.util.Map;

import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.User;

public interface DeptService {

	Dept getDeptById(int did);
	boolean addDept(Dept dept);
	boolean delDept(int did);
	boolean updateDept(Dept dept);
	/**
	 * 遍历所有部门
	 * @return
	 */
	List<Dept> findAllDept();
	/**
	 * 根据did遍历所有员工
	 * @param did
	 * @param pagestart
	 * @param pagesizi
	 * @return
	 */
	List<User> findUserByDId(int did,int pagestart,int pagesizi);
	/**
	 * 遍历所有员工
	 * @param pagestart
	 * @param pagesizi
	 * @return
	 */
	List<User> findAlls(int pagestart,int pagesizi);
	boolean updateUserDid(int uid ,int did);
	List<Map<String, Object>> findChildList(int did);
	List<Dept> findTopDept();
}
