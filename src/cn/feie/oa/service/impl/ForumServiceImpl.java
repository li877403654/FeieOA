package cn.feie.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.feie.oa.action.form.Superjoin;
import cn.feie.oa.dao.ForumDao;
import cn.feie.oa.domain.Forum;
import cn.feie.oa.domain.Reply;
import cn.feie.oa.domain.Topic;
import cn.feie.oa.service.ForumService;
@Service
public class ForumServiceImpl implements ForumService {

	@Resource
	private ForumDao forumDao;
	
	@Override
	public List<Forum> findForum() {
		List<Forum> findForum = forumDao.findForum();
		for (Forum forum : findForum) {
			int topicCount =  forumDao.getTopicCountByForumId(forum.getId());
			int replyCount = forumDao.getReplyCountByForumId(forum.getId());
			forum.setTopicCount(topicCount);
			forum.setReplyCount(replyCount);
		}
		return findForum;
	}

	@Override
	public List<Topic> findTopicById(Long id, Integer pageNo, Integer pageNum) {
		List<Topic> findTopicById = forumDao.findTopicById(id, pageNo, pageNum);
		
		return findTopicById;
	}

	@Override
	public List<Reply> findReplyById(Long id, Integer pageNo, Integer pageNum) {
		return forumDao.findReplyById(id, pageNo, pageNum);
	}
	@Override
	public Forum getForumByFId(Long id) {
		return forumDao.getForumByFId(id);
	}

	@Override
	public Superjoin findTopicPage(Superjoin superjoin) {
		Superjoin su = forumDao.findTopicPage(superjoin);
		List<Topic> topics = su.getTopics();
		for (Topic topic : topics) {
			topic.setReplyCount(forumDao.getReplyCountByTopicId(topic.getId()));
		}
		
		su.setTopics(topics);
		return su;
	}

	@Override
	public Superjoin findReply(Superjoin superjoin) {
		return forumDao.findReply(superjoin);
	}
	@Override
	public void addReply(Reply reply) {
		forumDao.addReply(reply);
	}
	@Override
	public void updateTopicType(Superjoin superjoin) {
		forumDao.updateTopicType(superjoin);
	}
	
	@Override
	public int deleteReplyById(Long id) {
		return forumDao.deleteReplyById(id);
	}
	@Override
	public Reply getReplyById(Long id) {
		return forumDao.getReplyById(id);
	}
	@Override
	public int updateReply(Reply reply) {
		return forumDao.updateReply(reply);
	}

	@Override
	public int addTopic(Topic topic) {
		return forumDao.addTopic(topic);
	}
	@Override
	public List<Forum> getForumAl() {
		return forumDao.getForumAl();
	}
	@Override
	public int updateMoveDown(int id) {
		return forumDao.updateMoveDown(id);
	}
	@Override
	public int updateMoveUp(int id) {
		return forumDao.updateMoveUp(id);
	}
	@Override
	public int addForum(Forum forum) {
		return forumDao.addForum(forum);
	}
	@Override
	public int deleteForum(Long id) {
		return forumDao.deleteForum(id);
	}
	@Override
	public int updateForum(Forum forum) {
		return forumDao.updateForum(forum);
	}
	@Override
	public boolean forum_ifForumName(String name) {
		return forumDao.forum_ifForumName(name);
	}
}
