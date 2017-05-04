package cn.feie.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.feie.oa.dao.PrivilegeDao;
import cn.feie.oa.domain.Privilege;
import cn.feie.oa.service.PrivilegeServise;

@Service
public class PrivilegeServiseImpl implements PrivilegeServise {

		@Resource
		private PrivilegeDao privilegeDao;

		@Override
		public List<Privilege> findPriviegeAll() {
			return privilegeDao.findPriviegeAll();
		}

		@Override
		public void updatePrivilege(Long[] privilegeIds, Integer pid) {
			for (Long long1 : privilegeIds) {
				privilegeDao.updatePrivilege(long1,pid);				
			}
		}

		@Override
		public void deletePrivilege(Integer pid) {
			privilegeDao.deletePrivilege(pid);
		}
		@Override
		public List<Privilege> getPrivilegeIdsByPid(Integer pid) {
		return privilegeDao.getPrivilegeByPid(pid);
		}

		@Override
		public List<Privilege> findTopPrivielge() {
			List<Privilege> list = privilegeDao.findTopPrivielge();
			chi(list);
			return list;
		}
		public List<Privilege> chi(List<Privilege> list){
			List<Privilege> list1 = new ArrayList<>();
			for (Privilege p1 : list) {
				list1 = privilegeDao.getPrivilegeByParentId(p1.getId());
				p1.setParent(list1);
				chi(list1);
			}
			return list;
		}
		@Override
		public List<Privilege> getPriIdByPidString(String[] pidString) {
		return privilegeDao.getPriIdByPidString(pidString);
		}
}
