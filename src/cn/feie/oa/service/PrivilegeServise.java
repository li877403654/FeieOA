package cn.feie.oa.service;

import java.util.List;

import cn.feie.oa.domain.Privilege;

public interface PrivilegeServise {
	List<Privilege> findPriviegeAll();

	void updatePrivilege(Long[] privilegeIds, Integer pid);

	void deletePrivilege(Integer pid);

	List<Privilege> getPrivilegeIdsByPid(Integer pid);

	List<Privilege> findTopPrivielge();

	List<Privilege> getPriIdByPidString(String[] pidString);


	
	
	
}
