package cn.feie.oa.dao;

import java.util.List;

import cn.feie.oa.action.form.Superjoin;
import cn.feie.oa.domain.Forum;
import cn.feie.oa.domain.Reply;
import cn.feie.oa.domain.Topic;

public interface ForumDao {
	List<Forum> findForum();
	List<Topic> findTopicById(Long id,Integer pageNo,Integer pageNum);
	List<Reply> findReplyById(Long id,Integer pageNo,Integer pageNum);
	Forum getForumByFId(Long id);
	Superjoin findTopicPage(Superjoin s);
	Superjoin findReply(Superjoin superjoin);
	void addReply(Reply reply);
	void updateTopicType(Superjoin superjoin);
	int getTopicCountByForumId(Long id);
	int getReplyCountByForumId(Long id);
	int getReplyCountByTopicId(Long id);
	int deleteReplyById(Long id);
	Reply getReplyById(Long id);
	int updateReply(Reply reply);
	int addTopic(Topic topic);
	List<Forum> getForumAl();
	int updateMoveUp(int id);
	int updateMoveDown(int id);
	int addForum(Forum forum);
	int deleteForum(Long id);
	int updateForum(Forum forum);
	boolean forum_ifForumName(String name);
	
}