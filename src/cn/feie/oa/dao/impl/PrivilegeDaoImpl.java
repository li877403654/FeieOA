package cn.feie.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.feie.oa.dao.PrivilegeDao;
import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.Privilege;

@Repository
public class PrivilegeDaoImpl extends JdbcDaoSupport implements PrivilegeDao {
	@Resource(name="dataSource")
	public void getjdbcsupport(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	
	@Override
	public List<Privilege> getPrivilegeByPid(Integer pid) {
		List<Privilege> list = this.getJdbcTemplate().query("select * from itcast_post_privilege where postid = ?",new RowMapper<Privilege>(){

			@Override
			public Privilege mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Privilege pri = new Privilege();
				pri.setId(rs.getLong("privilegeId"));
				return pri;
			}},pid);
		return list;
	}@Override
	public void UpdatePrivilege(Privilege privilege) {
	}
	public List<Privilege> findPriviegeAll(){
		List<Privilege> p =  this.getJdbcTemplate().query("select * from privilege",new RowMapper<Privilege>() {

			@Override
			public Privilege mapRow(ResultSet rs, int arg1) throws SQLException {
				Privilege pri = new Privilege();
				pri.setId(rs.getLong("id"));
				pri.setName(rs.getString("name"));
				pri.setUrl(rs.getString("url"));
				return pri;
			}
		});
		return p;
	}

	@Override
	public void updatePrivilege(Long privilegeIds, Integer pid) {
			this.getJdbcTemplate().update("insert into itcast_post_privilege set privilegeId = ?,postid = ?",privilegeIds,pid);
	}

	@Override
	public void deletePrivilege(Integer pid) {
		this.getJdbcTemplate().update("DELETE FROM itcast_post_privilege WHERE postId = ?",pid);
	}
	@Override
	public List<Privilege> findTopPrivielge() {
		List<Privilege> p =  this.getJdbcTemplate().query("select * from privilege where parentId IS NULL ",new RowMapper<Privilege>() {

			@Override
			public Privilege mapRow(ResultSet rs, int arg1) throws SQLException {
				Privilege pri = new Privilege();
				pri.setId(rs.getLong("id"));
				pri.setName(rs.getString("name"));
				pri.setUrl(rs.getString("url"));
				Privilege pri2 = new Privilege();
				pri2.setId(rs.getLong("parentId"));
				pri.setPrivilege(pri2);
				return pri;
			}
		});
		return p;
	}
	@Override
	public List<Privilege> getPrivilegeByParentId(Long parentId) {
		List<Privilege> p =  this.getJdbcTemplate().query("select * from privilege where parentId = ? ",new RowMapper<Privilege>() {

			@Override
			public Privilege mapRow(ResultSet rs, int arg1) throws SQLException {
				Privilege pri = new Privilege();
				pri.setId(rs.getLong("id"));
				pri.setName(rs.getString("name"));
				pri.setUrl(rs.getString("url"));
				Privilege pri2 = new Privilege();
				pri2.setId(rs.getLong("parentId"));
				pri.setPrivilege(pri2);
				return pri;
			}
		},parentId);
		return p;
	}
	public List<Privilege> getPriIdByPidString(String[] pidString) {
		String sql = "SELECT DISTINCT ip.privilegeId,p.* FROM itcast_post_privilege ip LEFT JOIN privilege p ON ip.privilegeId = p.id WHERE ";
		for (int i = 0; i < pidString.length; i++) {
			System.out.println(pidString.length);
			if (i>0) {
				sql += " or ip.postid ="+pidString[i];
			}else{
			sql += " ip.postid ="+pidString[i];			
			}
		}
		List<Privilege> p =  this.getJdbcTemplate().query(sql,new RowMapper<Privilege>() {
			@Override
			public Privilege mapRow(ResultSet rs, int arg1) throws SQLException {
				Privilege p = new Privilege();
				p.setId(rs.getLong("privilegeId"));
				p.setName(rs.getString("name"));
				p.setUrl(rs.getString("url"));
				return p; 
			}
		});
		return p;
	}
}
