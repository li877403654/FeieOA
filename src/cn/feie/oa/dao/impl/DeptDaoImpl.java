package cn.feie.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.feie.oa.dao.DeptDao;
import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.User;

@Repository
public class DeptDaoImpl extends JdbcDaoSupport implements DeptDao{

	private static final Class RowMapper = null;

	@Resource(name = "dataSource")
	public void gerJdbcDaoSupport(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	



	@Override
	public Dept getDeptById(int did) {
		Dept d = this.getJdbcTemplate().queryForObject("select * from dept where did=?",new Object[]{did},new RowMapper<Dept>() {

			@Override
			public Dept mapRow(ResultSet rs, int arg1) throws SQLException {
				Dept d = new Dept();
				d.setDid(rs.getInt("did"));
				d.setDname(rs.getString("dname"));
				d.setDeptuid(rs.getInt("deptuid"));
				return d;
			}
		});
		return d;
	}


	
	
	


	

	/* ******************************************************* */
	@Override
	public boolean delDept1(int did) {

		int i =0;
		List<Map<String, Object>> u = this.getJdbcTemplate().queryForList("select uid from user u where did=?",did);
		for (Map<String, Object> map : u) {
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Entry) it.next();
				int uid = (int) e.getValue();
				System.out.println(uid);
				System.out.println("2设置该部门管理的部门 ，主管为空");
				i+=this.getJdbcTemplate().update("DELETE FROM post_user WHERE uid = ?",uid);
				System.out.println("3删除该部门员工所有职务");
				i+=this.getJdbcTemplate().update("UPDATE dept SET deptuid=NULL WHERE deptuid = ?",uid);
				System.out.println("4删除该部门员工");
				int h=1/0;
				i+=this.getJdbcTemplate().update("DELETE FROM user WHERE uid = ?",uid);
			}
		}
		System.out.println("5删除部门");
		i += this.getJdbcTemplate().update("DELETE FROM dept WHERE did = ?",did);
		System.out.println(i);
		
		return i>0;
	}
	
	public List<Map<String, Object>> getUserByDid(int did){
		//System.out.println("1查询该部门员工");
		return this.getJdbcTemplate().queryForList("select * from user u where did=?",did);
	}
	public int delDeptuid(int uid){
		//System.out.println("2设置该部门管理的部门 ，主管为空");
		return this.getJdbcTemplate().update("DELETE FROM post_user WHERE uid = ?",uid);
	}
	public int delPostuid(int uid){
		//System.out.println("3删除该部门员工所有职务");
		return this.getJdbcTemplate().update("UPDATE dept SET deptuid=NULL WHERE deptuid = ?",uid);
	}
	public int delDeptUser(int uid){
		//System.out.println("4删除该部门员工");
		return this.getJdbcTemplate().update("UPDATE user set did=null WHERE uid = ?",uid);
	}
	public int delDept(int did){
		//System.out.println("5删除部门");
		return this.getJdbcTemplate().update("DELETE FROM dept WHERE did = ?",did);
	}
	
	
	
	@Override
	public boolean updateDept(Dept dept) {
		String deptuid;
		if (dept.getDeptuid()==0) {
			deptuid = null;
		}else{
			deptuid = String.valueOf(dept.getDeptuid());
		}		
		
		int i = this.getJdbcTemplate().update("UPDATE dept SET dname = ?,deptuid =? WHERE did =?",dept.getDname(),deptuid,dept.getDid());
		return i>0;
	}
	
	
	@Override
	public boolean addDept(Dept dept) {
		String deptuid;
		if (dept.getDeptuid()==0) {
			deptuid = null;
		}else{
			deptuid = String.valueOf(dept.getDeptuid());
		}
		
		//System.out.println(deptuid);
		List list = this.getJdbcTemplate().queryForList("SELECT * FROM dept WHERE dname=?",dept.getDname());
		System.out.println("判断="+list);
		if(list.size()!=0){
			return false;
		}
			int i = this.getJdbcTemplate().update("INSERT INTO dept(did,dname,deptuid,dcomment,dpid) VALUES(null,?,?,?,?)",dept.getDname(),deptuid,dept.getdcomment(),dept.getDpid());
			return i>0;
	}
	
	@Override
	public List<Map<String, Object>> findAllDept(){
		return this.getJdbcTemplate().queryForList("SELECT d.did,.d.dname,d.deptuid,u.uname FROM dept d LEFT JOIN user u ON  d.deptuid=u.uid WHERE  dpid IS NULL and d.did LIMIT 0,100");
	}
	@Override
	public List<Dept> findDept() {
		List<Dept> dept = this.getJdbcTemplate().query("SELECT d.*,u.uname FROM dept d LEFT JOIN user u ON  d.deptuid=u.uid WHERE  dpid IS NULL and d.did LIMIT 0,100",new RowMapper<Dept>() {
			@Override
			public Dept mapRow(ResultSet rs, int arg1) throws SQLException {
				Dept d = new Dept();
				User u = new User();
				d.setDid(rs.getInt("did"));
				d.setDpid(rs.getInt("dpid"));
				d.setDname(rs.getString("dname"));
				d.setDeptuid(rs.getInt("deptuid"));
				d.setdcomment(rs.getString("dcomment"));
				u.setUname(rs.getString("uname"));
				d.setUser(u);
				return d;
			}
		});
		return dept;
	}
	@Override
	public List<Map<String, Object>> findChildList(int did) {
		return this.getJdbcTemplate().queryForList("SELECT d.*,u.uname FROM dept d LEFT JOIN user u ON  d.deptuid=u.uid WHERE dpid = ? AND d.did LIMIT 0,100",did);
	}
	public List<Dept> findAllChild(int dpid){
		List<Dept> depts = this.getJdbcTemplate().query("SELECT d.*,u.uname FROM dept d LEFT JOIN user u ON  d.deptuid=u.uid WHERE dpid = ? AND d.did LIMIT 0,100",new RowMapper<Dept>() {

			@Override
			public Dept mapRow(ResultSet rs, int arg1) throws SQLException {
				Dept d = new Dept();
				d.setDid(rs.getInt("did"));
				d.setDpid(rs.getInt("dpid"));
				d.setDname(rs.getString("dname"));
				d.setDeptuid(rs.getInt("deptuid"));
				d.setdcomment(rs.getString("dcomment"));
				return d;
			}
		},dpid);
		return depts;
	}

		public List<User> findAlls(int pagestart,int pagesizi) {
			// TODO Auto-generated method stub
			@SuppressWarnings("unchecked")
			List<User> list = this.getJdbcTemplate().query("SELECT u.*,d.dname FROM user u LEFT JOIN dept d ON u.did=d.did ",
					new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					User u = new User();
					u.setUid(rs.getInt("uid"));
					u.setUname(rs.getString("uname"));
					u.setUsex(rs.getString("usex"));
					u.setUphone(rs.getString("uphone"));
					u.setLoginname(rs.getString("loginname"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setDescription(rs.getString("description"));
					Dept d = new Dept();
					d.setDname(rs.getString("dname"));
					u.setDept(d);
					return u;
				}

			});
			return list;
		}
	

	@Override
	public List<User> findUserByDId(int did,int pagestart,int pagesizi) {
		// TODO Auto-generated method stub
					@SuppressWarnings("unchecked")
					List<User> list = this.getJdbcTemplate().query("SELECT u.*,d.dname FROM user u LEFT JOIN dept d ON u.did=d.did where d.did=?",
							new Object[]{did},
							new RowMapper<User>() {

						@Override
	
						public User mapRow(ResultSet rs, int arg1) throws SQLException {
							// TODO Auto-generated method stub
							User u = new User();
							u.setUid(rs.getInt("uid"));
							u.setUname(rs.getString("uname"));
							u.setUsex(rs.getString("usex"));
							u.setUphone(rs.getString("uphone"));
							u.setLoginname(rs.getString("loginname"));
							u.setEmail(rs.getString("email"));
							u.setPassword(rs.getString("password"));
							u.setDescription(rs.getString("description"));
							Dept d = new Dept();
							d.setDname(rs.getString("dname"));
							u.setDept(d);
							return u;
						}

					});
					return list;
	}

	public boolean updateUserDid(int uid ,int did){
		String d;
		if (did==0) {
			d = null;
		}else{
			d = String.valueOf(did);
		}		
		return (this.getJdbcTemplate().update("UPDATE user SET did =? WHERE uid =?",d,uid))>0;
	}

	
	

}
