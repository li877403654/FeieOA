package cn.feie.oa.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.feie.oa.dao.PostDao;
import cn.feie.oa.domain.Post;
@Repository
public class PostDaoImpl extends JdbcDaoSupport implements PostDao {
	@Resource(name="dataSource")
	public void getjdbcsupport(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	
	public List<Post> allPost(){
		List<Post> list = this.getJdbcTemplate().query("select * from post",new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int arg1) throws SQLException {
				Post p = new Post();
				p.setPid(rs.getInt("pid"));
				p.setPname(rs.getString("pname"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		});
		return list;
	}
	
	public List<Map<String, Object>> getUidByPid(int pid){
		return this.getJdbcTemplate().queryForList("select * from post_user where pid=?",pid);
	}
	public List<Map<String, Object>> getPidByUid(int uid){
		return this.getJdbcTemplate().queryForList("select * from post_user where uid=?",uid);
	}
	public List<Post> getPostByUid(int uid){
		List<Post> post = this.getJdbcTemplate().query("SELECT  p.* FROM post p LEFT JOIN post_user pu ON p.`pid` = pu.`pid` LEFT JOIN user u ON pu.`uid` = u.`uid` WHERE u.`uid` = ?",new RowMapper<Post>() {

			@Override
			public Post mapRow(ResultSet rs, int arg1) throws SQLException {
				Post p = new Post();
				p.setPid(rs.getInt("pid"));
				p.setPname(rs.getString("pname"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		},uid);
		return post;
	}
	public void delByUid(int uid){
		this.getJdbcTemplate().update("delete from post_user where uid=?",uid);
	}
	public void addPost_user(int uid ,int pid){
		this.getJdbcTemplate().update("INSERT INTO post_user(uid,pid) VALUES(?,?);",uid,pid);
	}
	
	public Post getPostBypname(String pname) {
		Post p = new Post();
		try {
			p = this.getJdbcTemplate().queryForObject("select * from post where pname = ?",new RowMapper<Post>(){
				
				@Override
				public Post mapRow(ResultSet rs, int arg1) throws SQLException {
					Post p = new Post();
					p.setPid(rs.getInt("pid"));
					p.setPname(rs.getString("pname"));
					p.setDescription(rs.getString("description"));
					return p;
				}
				
			},pname);
		} catch (Exception e) {
			return null;
		}
		return p;
	}
	@Override
	public void addPost(Post post) {
		this.getJdbcTemplate().update("INSERT INTO post(pname,description) VALUES(?,?)",post.getPname(),post.getDescription());
	}
	@Override
	public void deletePost(Integer pid) {
		this.getJdbcTemplate().update("delete from post where pid = ?",pid);
	}
	@Override
	public void updatePost(Post post) {
		this.getJdbcTemplate().update("update post set pname = ?,description = ? where pid = ?",post.getPname(),post.getDescription(),post.getPid());
	}
	@Override
	public Post getPostBypid(Integer pid) {
		Post p = this.getJdbcTemplate().queryForObject("select * from post where pid = ?",new RowMapper<Post>(){

			@Override
			public Post mapRow(ResultSet rs, int arg1) throws SQLException {
				Post p = new Post();
				p.setPid(rs.getInt("pid"));
				p.setPname(rs.getString("pname"));
				p.setDescription(rs.getString("description"));
				return p;
			}
			
		},pid);
		return p;
	}
}
