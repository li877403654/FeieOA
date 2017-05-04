package cn.feie.oa.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.feie.oa.dao.UserDao;
import cn.feie.oa.domain.Dept;
import cn.feie.oa.domain.User;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Resource(name = "dataSource")
	public void gerJdbcDaoSupport(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public int delete(int id) {
		int value = this.getJdbcTemplate().update(
				"delete from user where uid=?", id);
		return value;
	}

	@Override
	public void update(final User user) {
		// TODO Auto-generated method stub
		int value = this.getJdbcTemplate().update(
				"update user set uname=?,email=? where uid = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setString(1, user.getUname());
						ps.setString(2, user.getEmail());
						ps.setInt(3, user.getUid());
					}
				});
	}
	
	@Override
	public int updatePassword(User user){
		int i = this.getJdbcTemplate().update("update user set password=? where uid = ?",user.getPassword(),user.getUid());
		return i;
	}

	@Override
	public User getById(int id) {
		User user = (User) this.getJdbcTemplate().queryForObject(
				"SELECT u.*,d.* FROM user u LEFT JOIN dept d ON u.did=d.did where u.uid = ?", new Object[] { id },
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setUid(rs.getInt("uid"));
						u.setImgname(rs.getString("imgname"));
						u.setUname(rs.getString("uname"));
						u.setUsex(rs.getString("usex"));
						u.setUphone(rs.getString("uphone"));
						u.setLoginname(rs.getString("loginname"));
						u.setEmail(rs.getString("email"));
						u.setPassword(rs.getString("password"));
						u.setDescription(rs.getString("description"));
						Dept d = new Dept();
						d.setDid(rs.getInt("did"));
						d.setDname(rs.getString("dname"));
						u.setDept(d);
						return u;
					}

				});

		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		List<User> userList = this.getJdbcTemplate().query(sql,
				new Object[] {}, new BeanPropertyRowMapper<User>(User.class));

		return userList;
	}
	@Override
	public List<User> onLineUserList() {
		@SuppressWarnings("unchecked")
		List<User> user = this.getJdbcTemplate().query("SELECT u.*,d.dname FROM user u LEFT JOIN dept d ON u.did=d.did where u.online = 1",new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
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
		return user;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getJdbcTemplate().update(
				"insert into user(uname,email) values(?,?)",
				new Object[] { user.getUname(), user.getEmail() });
	}

	@Override
	public List<User> findByUname(String uname) {
		// TODO Auto-generated method stub
		String sql = "select * from user where uname=?";
		List<User> userList = this.getJdbcTemplate().query(sql,
				new Object[] { uname },
				new BeanPropertyRowMapper<User>(User.class));
		return userList;
	}

	 @Override
	 public boolean ifLogname(String username) {
	 try {
		 int i = this.getJdbcTemplate().queryForInt("select password from user where loginname=?",
				 username);
	} catch (Exception e) {
		return true;
	}
	 return false;
	 }

	@Override
	public String login(String username) {
		Map m = null;
		try {
			m = getJdbcTemplate().queryForMap(
					"select password from user where loginname=?", username);
		} catch (Exception e) {
			return null;
		}

		return (String) m.get("password");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User finduid(String loginname, String password) {
		// TODO Auto-generated method stub
		User user=	(User)getJdbcTemplate().queryForObject("SELECT * FROM user WHERE loginname=? AND PASSWORD=? ",new Object[]{loginname,password}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		return user;
	}

	@Override
	public List<User> findUser(int pagestart, int pagesizi) {
		@SuppressWarnings("unchecked")
		List<User> user = this.getJdbcTemplate().query("SELECT u.*,d.dname FROM user u LEFT JOIN dept d ON u.did=d.did LIMIT ?,?",new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
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
		},pagestart,pagesizi);
		return user;
	}

	@Override
	public void initializePassword(int uid) {
		this.getJdbcTemplate().update("update user set password = '1234' where uid = ?",uid);
	}

	@Override
	public void userUpdata(User user) {
		String did = null;
		if (user.getDept().getDid()!= 0) {
			did = String.valueOf(user.getDept().getDid());
		}
		this.getJdbcTemplate().update("update user set loginname = ?,did = ?,usex = ?,uphone = ?,email = ?,description = ? where uid = ?",user.getLoginname(),
				did,user.getUsex(),user.getUphone(),user.getEmail(),user.getDescription(),user.getUid());
	}

	@Override
	public void userAdd(User user) {
		
		String did = null;
		if (user.getDept()!=null&&user.getDept().getDid()!= 0) {
			did = String.valueOf(user.getDept().getDid());
		}
		
		this.getJdbcTemplate().update("INSERT INTO user(uid,uname,usex,uphone,loginname,email,PASSWORD,description,did,imgname,imgpath) VALUES(?,?,?,?,?,?,?,?,?,null,null)",
				user.getUid(),user.getUname(),user.getUsex(),user.getUphone(),user.getLoginname(),user.getEmail(),user.getPassword(),user.getDescription(),did);
	}

	@Override
	public int newuid() {
		int i = 0;
		try {
			 i = this.getJdbcTemplate().queryForInt("SELECT MAX(uid) from user");
		} catch (Exception e) {
			return 0;
		}
		System.out.println(i);
		 return i+1;
	}
	
	@Override
	public int addImagerName(String u_name,int uid) {
		int update = this.getJdbcTemplate().update("UPDATE user SET imgname =? WHERE uid =?;",u_name,uid);
		return update;
	}
	
	@Override
	public int ifPassword(User user) {
		List<Map<String, Object>> q = this.getJdbcTemplate().queryForList("select * from user where password=? and uid= ?",user.getPassword(),user.getUid());
		return q!=null&&q.size()>0?1:0;
	}

	public User getByLoginmame(String loginname) {
		User user = (User) this.getJdbcTemplate().queryForObject("SELECT * from user u where u.loginname=?",new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setUsex(rs.getString("usex"));
				u.setUphone(rs.getString("uphone"));
				u.setLoginname(rs.getString("loginname"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setDescription(rs.getString("description"));
				return u;
			}
		},loginname);
		return user;
	}
	@Override
	public int getOnLineUser() {
		int i = this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM user WHERE online = 1");
		return i;
	}
	public void updateOnLine1(int uid){
		int update = this.getJdbcTemplate().update("UPDATE user SET online = ? WHERE uid =? ;",1,uid);
	}
	public void updateOnLine0(int uid){
		int update = this.getJdbcTemplate().update("UPDATE user SET online = ? WHERE uid =? ;",0,uid);
	}
	

}
