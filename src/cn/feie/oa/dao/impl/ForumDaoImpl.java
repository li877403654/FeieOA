package cn.feie.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import cn.feie.oa.action.form.Superjoin;
import cn.feie.oa.dao.ForumDao;
import cn.feie.oa.domain.Forum;
import cn.feie.oa.domain.Reply;
import cn.feie.oa.domain.Topic;
import cn.feie.oa.domain.User;
@Repository
public class ForumDaoImpl extends JdbcDaoSupport implements ForumDao {

	@Resource(name = "dataSource")
	public void gerJdbcDaoSupport(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public List<Forum> findForum() {
		List<Forum> forum = this.getJdbcTemplate().query("SELECT * FROM (SELECT  f.*,t.id tid,t.creationtime,t.forumId,t.title,t.uid,u.uname FROM forum f  LEFT JOIN topic t ON f.id = t.forumId  LEFT JOIN user u ON t.uid = u.uid ORDER BY t.creationtime DESC ) a GROUP BY a.id ORDER BY a.rank ",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				List<Topic> topics = new ArrayList<>();
				Topic topic = new Topic();
				User u = new User();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setReplyCount(rs.getInt("replyCount"));
				f.setTopicCount(rs.getInt("topicCount"));
				topic.setId(rs.getLong("tid"));
				topic.setTitle(rs.getString("title"));
				topic.setCreationtime(rs.getTimestamp("creationtime"));
				u.setUname(rs.getString("uname"));
				topic.setUser(u);
				topics.add(topic);
				f.setTopics(topics);
				return f;
			}});
		return forum;
	}

	@Override
	public List<Topic> findTopicById(Long id, Integer pageNo, Integer pageNum) {
		List<Topic> topic = this.getJdbcTemplate().query("SELECT * FROM topic t LEFT JOIN user u ON t.uid = u.uid WHERE t.forumId = ? LIMIT ?,?",new RowMapper<Topic>(){

			@Override
			public Topic mapRow(ResultSet rs, int arg1) throws SQLException {
				Topic topic = new Topic();
				topic.setTitle("title");
				topic.setType(rs.getInt("type"));
				topic.setCreationtime(rs.getTimestamp("recommend"));
				topic.setUpdatetime(rs.getTimestamp("updatetime"));
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				topic.setUser(u);
				return topic;
			}},id,pageNo,pageNum);
		return topic;
	}

	@Override
	public List<Reply> findReplyById(Long id, Integer pageNo, Integer pageNum) {
		List<Reply> reply = this.getJdbcTemplate().query("SELECT * FROM topic t LEFT JOIN USER u ON t.uid = u.uid WHERE t.forumId = ? LIMIT ?,?",new RowMapper<Reply>(){

			@Override
			public Reply mapRow(ResultSet rs, int arg1) throws SQLException {
				Reply reply = new Reply();
				reply.setId(rs.getLong("id"));
				reply.setRecommend(rs.getInt("recommend"));
				reply.setTitle(rs.getString("title"));
				reply.setRecoverytime(rs.getTimestamp("recoverytime"));
				reply.setContent(rs.getString("content"));
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				reply.setUser(u);
				return reply;
			}},id,pageNo,pageNum);
		return reply;
	}
	@Override
	public Forum getForumByFId(Long id) {
		Forum forum = this.getJdbcTemplate().queryForObject("SELECT * FROM forum where id = ?",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				return f;
			}},id);
		return forum;
	}
	@Override
	public Superjoin findTopicPage(Superjoin s){
//		select * from topic t left join user u on t.uid = u.uid where t.id = 1 AND t.type =2 ORDER BY updatetime  desc
		int pageNum = s.getPageNum();
		int pageNo = s.getPageNo()!=null?s.getPageNo():1;
		int recommend = s.getRecommend()!=null?s.getRecommend():0;
		String sql = "SELECT * FROM topic t LEFT JOIN (SELECT u.`uid` ruid,u.`uname` runame,r.`recoverytime`,r.`topicId`,r.`id`,r.`title` FROM reply r  LEFT JOIN user u  ON r.uid = u.uid  WHERE r.reply IS NULL  ORDER BY r.recoverytime DESC) r  ON t.`id` = r.`topicId`  LEFT JOIN user u  ON t.`uid` = u.`uid`  WHERE t.`forumId` = ? ";
		if (recommend !=0) {
			sql +="AND t.type =2 ";
		}
		String sqlPage = "SELECT COUNT(t.id) FROM topic t LEFT JOIN (SELECT r.`topicId` FROM reply r  WHERE r.reply IS NULL  ORDER BY r.recoverytime DESC  LIMIT 0,1) r  ON t.`id` = r.`topicId`  LEFT JOIN user u  ON t.`uid` = u.`uid`  WHERE t.`forumId` = ? ";
//		String stu = sql.substring(sql.indexOf("?")+1,sql.indexOf(";"));
		String[] split = sql.split("\\?");
		sqlPage += split[1];
		
		int pageSum = this.getJdbcTemplate().queryForInt(sqlPage,s.getId());
//总记录数
		s.setPageSum(pageSum);
		
		    sql += " GROUP BY t.id ";
			switch (s.getRankcount()) {
			case 0:
				sql +="ORDER BY t.type<>2,t.creationtime ";
				break;
			case 1:
				sql +="ORDER BY updatetime ";
				break;
			case 2:
				sql +="ORDER BY t.creationtime ";
				break;
			case 3:
				sql +="ORDER BY t.replyCount ";
				break;
			default:
				break;
			}
		
		
		if(s.getElevator()!=0){
			sql +="desc ";
		}
		sql += "LIMIT "+(pageNo-1)*pageNum+","+pageNum+";";
		
		
		Forum forum = this.getJdbcTemplate().queryForObject("select * from forum where id = ?",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setReplyCount(rs.getInt("replyCount"));
				f.setTopicCount(rs.getInt("topicCount"));
				return f;
			}},s.getForum().getId());
		s.setForum(forum);
		
		List<Topic> topics = this.getJdbcTemplate().query(sql,new RowMapper<Topic>(){

			@Override
			public Topic mapRow(ResultSet rs, int arg1) throws SQLException {
				Topic topic = new Topic();
				topic.setId(rs.getLong("id"));
				topic.setTitle(rs.getString("title"));
				topic.setType(rs.getInt("type"));
				topic.setCreationtime(rs.getTimestamp("creationtime"));
				topic.setReplyCount(rs.getInt("replyCount"));
				topic.setUpdatetime(rs.getTimestamp("updatetime"));
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				topic.setUser(u);
				Reply reply = new Reply();
				reply.setRecoverytime(rs.getTimestamp("recoverytime"));
				User ru = new User();
				ru.setUname(rs.getString("runame"));
				reply.setUser(ru);
				topic.setReply(reply);
				return topic;
			}},s.getForum().getId());
		//主题
		s.setTopics(topics);
		//总页数
		s.setPageCount(pageSum/pageNum+1);
		s.setPageNo(pageNo);
		
		
		return s;
	}
	@Override
	public Superjoin findReply(Superjoin s) {
		int pageNo = s.getPageNo()!=null?s.getPageNo():1;
		s.setPageNum(10);
		Topic t = this.getJdbcTemplate().queryForObject("SELECT t.*,f.id fid FROM topic t LEFT JOIN forum f ON t.forumid = f.id WHERE t.id = ?",new RowMapper<Topic>(){

			@Override
			public Topic mapRow(ResultSet rs, int arg1) throws SQLException {
				Topic topic = new Topic();
				topic.setId(rs.getLong("id"));
				topic.setTitle(rs.getString("title"));
				Forum f = new Forum();
				f.setId(rs.getLong("fid"));
				topic.setForum(f);
				return topic;
			}},s.getId());
		
		List<Reply> reply = this.getJdbcTemplate().query("SELECT * FROM reply r LEFT JOIN user u ON r.uid = u.uid WHERE  r.reply IS NULL and r.topicId = ? LIMIT ?,?",new RowMapper<Reply>(){

			@Override
			public Reply mapRow(ResultSet rs, int arg1) throws SQLException {
				Reply reply = new Reply();
				reply.setId(rs.getLong("id"));
				reply.setTitle(rs.getString("title"));
				reply.setRecoverytime(rs.getTimestamp("recoverytime"));
				reply.setContent(rs.getString("content"));
				reply.setFaceIcon(rs.getInt("faceIcon"));
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setImgname(rs.getString("imgname"));
				reply.setUser(u);
				return reply;
			}},s.getId(),(pageNo-1)*s.getPageNum(),s.getPageNum());
		int pageSum = this.getJdbcTemplate().queryForInt("SELECT count(*) FROM reply r WHERE reply IS NULL AND r.topicid = ?",s.getId());
		s.setPageSum(pageSum);
		s.setPageCount(pageSum/s.getPageNum()+1);
		s.setTopic(t);
		s.setReplies(reply);
		s.setPageNo(pageNo);
		return s;
	}
	@Override
	public void addReply(Reply reply) {
		this.getJdbcTemplate().update("INSERT INTO reply(title,content,topicId,uid,recoverytime,faceIcon) VALUES(?,?,?,?,now(),?)",
					reply.getTitle(),
					reply.getContent(),
					reply.getTopic().getId(),
					reply.getUser().getUid(),
					reply.getFaceIcon()
				);
	}
	
	@Override
	public void updateTopicType(Superjoin superjoin) {
		this.getJdbcTemplate().update("UPDATE topic SET TYPE = ? WHERE id = ?",
				superjoin.getType(),
				superjoin.getId()
		);
	}
	@Override
	public int getReplyCountByForumId(Long id) {
		int i = this.getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM topic t,forum f,reply r WHERE t.forumid = f.id AND t.id = r.topicid AND f.id = ?",id);
		return i;
	}
	@Override
	public int getTopicCountByForumId(Long id) {
		int i = this.getJdbcTemplate().queryForInt("select count(*) from topic where forumid = ?",id);
		return i;
	}
	@Override
	public int getReplyCountByTopicId(Long id) {
		int i = this.getJdbcTemplate().queryForInt("select count(*) from reply where topicid = ?",id);
		return i;
	}
	@Override
	public int deleteReplyById(Long id) {
		int i = this.getJdbcTemplate().update("delete from reply where id = ?",id);
		return i;
	}
	@Override
	public Reply getReplyById(Long id) {
		Reply r = this.getJdbcTemplate().queryForObject("select r.*,t.title ttitle,t.id tid,f.id fid from reply r left join topic t on r.topicid = t.id left join forum f on t.forumid = f.id where r.id=?",new RowMapper<Reply>(){

			@Override
			public Reply mapRow(ResultSet rs, int arg1) throws SQLException {
				Reply reply = new Reply();
				reply.setId(rs.getLong("id"));
				reply.setTitle(rs.getString("title"));
				reply.setRecoverytime(rs.getTimestamp("recoverytime"));
				reply.setContent(rs.getString("content"));
				reply.setFaceIcon(rs.getInt("faceIcon"));
				Topic topic = new Topic();
				topic.setId(rs.getLong("tid"));
				topic.setTitle(rs.getString("ttitle"));
				reply.setTopic(topic);
				Forum forum = new Forum();
				forum.setId(rs.getLong("fid"));
				topic.setForum(forum);
				return reply;
			}},id);
		return r;
	}
	@Override
	public int updateReply(Reply r) {
		int i = this.getJdbcTemplate().update("UPDATE reply SET title=?,content=?,faceIcon=? WHERE id=?",
				r.getTitle(),
				r.getContent(),
				r.getFaceIcon(),
				r.getId());
		return i;
	}
	@Override
	public int addTopic(Topic topic) {
		int i = this.getJdbcTemplate().update("INSERT INTO topic(title,uid,creationtime,updatetime,forumId,type) VALUES(?,?,now(),now(),?,0)",
				topic.getTitle(),
				topic.getUser().getUid(),
				topic.getForum().getId()
			);
		return i;
	}
	@Override
	public List<Forum> getForumAl() {
		List<Forum> forum = this.getJdbcTemplate().query("SELECT * FROM forum ORDER BY rank",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setReplyCount(rs.getInt("replyCount"));
				f.setTopicCount(rs.getInt("topicCount"));
				f.setRank(rs.getInt("rank"));
				return f;
			}});
		return forum;
	}
	public Object getByid(Long id){
		Forum forum = this.getJdbcTemplate().queryForObject("SELECT * FROM forum f WHERE f.id = ?",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setRank(rs.getInt("rank"));
				return f;
			}},id);
		return forum;
	}
	@Override
	public int updateMoveDown(int id) {
		Forum forum1 = (Forum) getByid(Long.valueOf(id));
		try {
			Forum forum2 = this.getJdbcTemplate().queryForObject("SELECT * FROM forum f WHERE f.rank>? ORDER BY f.rank LIMIT 0,1",new RowMapper<Forum>(){
				
				@Override
				public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
					Forum f = new Forum();
					f.setId(rs.getLong("id"));
					f.setName(rs.getString("name"));
					f.setDescription(rs.getString("description"));
					f.setReplyCount(rs.getInt("replyCount"));
					f.setTopicCount(rs.getInt("topicCount"));
					f.setRank(rs.getInt("rank"));
					return f;
				}},forum1.getRank());
			int i = this.getJdbcTemplate().update("update forum set rank = ? where id = ?",forum1.getRank(),forum2.getId());
			i += this.getJdbcTemplate().update("update forum set rank = ? where id = ?",forum2.getRank(),forum1.getId());
			return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public int updateMoveUp(int id) {
		Forum forum1 = (Forum) getByid(Long.valueOf(id));
		try {
		Forum forum2 = this.getJdbcTemplate().queryForObject("SELECT * FROM forum f WHERE f.rank<? ORDER BY f.rank DESC LIMIT 0,1",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setReplyCount(rs.getInt("replyCount"));
				f.setTopicCount(rs.getInt("topicCount"));
				f.setRank(rs.getInt("rank"));
				return f;
			}},forum1.getRank());
		int i = this.getJdbcTemplate().update("update forum set rank = ? where id = ?",forum1.getRank(),forum2.getId());
		i += this.getJdbcTemplate().update("update forum set rank = ? where id = ?",forum2.getRank(),forum1.getId());
		return i;
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public int addForum(Forum forum) {
		int i = this.getJdbcTemplate().update("INSERT INTO forum(NAME,description,rank) VALUES(?,?,0);",
				forum.getName(),
				forum.getDescription());
		Forum forum2 = this.getJdbcTemplate().queryForObject("SELECT * FROM forum WHERE NAME=?",new RowMapper<Forum>(){

			@Override
			public Forum mapRow(ResultSet rs, int arg1) throws SQLException {
				Forum f = new Forum();
				f.setId(rs.getLong("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setRank(rs.getInt("rank"));
				return f;
			}},forum.getName());
		i += this.getJdbcTemplate().update("UPDATE forum SET rank = ? WHERE id = ?;",
				forum2.getId(),
				forum2.getId());
		return i;
	}
	@Override
	public int deleteForum(Long id) {
		int i =  this.getJdbcTemplate().update("DELETE FROM forum WHERE id = ?",id);
		return i;
	}
	@Override
	public int updateForum(Forum forum) {
		int i =  this.getJdbcTemplate().update("UPDATE forum SET name = ?,description = ? WHERE id = ?;",
				forum.getName(),
				forum.getDescription(),
				forum.getId());
		return i;
	}
	@Override
	public boolean forum_ifForumName(String name) {
		int i = this.getJdbcTemplate().queryForInt("select count(*) from forum f where name = ?",name);
		return i==0?true:false;
	}
}
