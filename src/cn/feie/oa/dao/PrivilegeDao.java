package cn.feie.oa.dao;

import java.util.List;

import cn.feie.oa.domain.Privilege;

public interface PrivilegeDao {
	
	List<Privilege> getPrivilegeByPid(Integer pid);
	List<Privilege> findPriviegeAll();
	void UpdatePrivilege(Privilege privilege);
	void updatePrivilege(Long privilegeIds, Integer pid);
	void deletePrivilege(Integer pid);
	List<Privilege> findTopPrivielge();
	List<Privilege> getPrivilegeByParentId(Long id);
	List<Privilege> getPriIdByPidString(String[] pidString);
}
