package cn.feie.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.feie.oa.action.form.Superjoin;
import cn.feie.oa.domain.Forum;
import cn.feie.oa.domain.Reply;
import cn.feie.oa.domain.Topic;
import cn.feie.oa.service.ForumService;

@Controller
@Scope("prototype")
public class ForumAction  {

	@Resource
	private ForumService forumService;
	
	@RequestMapping("forum_list")
	public String fourm_list(Model model){
		List<Forum> findForum = forumService.findForum();
		model.addAttribute("forumList", findForum);
		return "BBS_Forum/forumList";
	}
	
	@RequestMapping("forumManage_list")
	public String forumManage_list(Model model){
		List<Forum> forums = forumService.getForumAl();
		model.addAttribute("forums",forums);
		return "BBS_Forum/forumManage";
	}
	@RequestMapping("forum_forumShow")
	public String forum_forumShow(HttpServletRequest request,Model model,Superjoin superjoin){
		if (superjoin.getType()!=null) {
			forumService.updateTopicType(superjoin);
		}
		Superjoin findTopicPage = forumService.findTopicPage(superjoin); 
		model.addAttribute("superpage",findTopicPage);
		return "BBS_Forum/forumShow";
	}
	@RequestMapping("forum_topicShow")
	public String forum_getReply(Superjoin superjoin,Model model){
		if(superjoin.getSql()!=null){
			String reply_updateReply = reply_updateReply(superjoin.getReply());
			return reply_updateReply;
		}else{
		if (superjoin.getReply().getTitle()!=null&&superjoin.getReply().getTitle()!=""){
			forumService.addReply(superjoin.getReply());
		}
			superjoin = forumService.findReply(superjoin);
			model.addAttribute("superpage",superjoin);
			return "BBS_Topic/topicShow";
			}
		}
	@RequestMapping("forum_replyUI")
	public String forum_replyUI(Reply reply,Model model,HttpServletRequest request){
		reply = forumService.getReplyById(reply.getId());
		reply.setTitle("回复:"+reply.getTitle());
		reply.setContent(null);
		reply.setFaceIcon(null);
		model.addAttribute("reply",reply);
		return "BBS_Reply/saveUI";
	}
	@RequestMapping("reply_deleteReply")
	public String reply_deleteReply(Reply reply){
		int i = forumService.deleteReplyById(reply.getId());
		return "redirect:/forum_topicShow.action?id="+reply.getTopic().getId();
	}
	@RequestMapping("reply_updateReplyUI")
	public String reply_updateReplyUI(Reply reply,Model model){
		reply = forumService.getReplyById(reply.getId());
		model.addAttribute("reply",reply);
		return "BBS_Reply/saveUI";
	}
	@RequestMapping("reply_updateReply")
	public String reply_updateReply(Reply reply){
		int i = forumService.updateReply(reply);
		return "redirect:/forum_topicShow.action?id="+reply.getTopic().getId();
	}
	@RequestMapping("topic_addTopicUI")
	public String topic_addTopic(Topic topic,Model model){
		Forum forum = forumService.getForumByFId(topic.getForum().getId());
		model.addAttribute("forum",forum);
		return "BBS_Topic/saveUI";
	}
	@RequestMapping("topic_addTopic")
	public String topic_addTopic(Topic topic){
		int i = forumService.addTopic(topic);
		return "redirect:/forum_forumShow.action?id="+topic.getForum().getId()+"&forum.id="+topic.getForum().getId();
	}
	@RequestMapping("moveUp")
	public String moveUp(int id){
		int i = forumService.updateMoveUp(id);
		return "redirect:/forumManage_list.action";
	}
	@RequestMapping("moveDown")
	public String moveDown(int id){
		int i = forumService.updateMoveDown(id);
		return "redirect:/forumManage_list.action";
	}
	@RequestMapping("addForumUI")
	public String addForumUI(){
		return "BBS_ForumManage/saveUI";
	}
	@RequestMapping("addForum")
	public String addForum(Forum forum){
		if (forum.getId()!=null) {
			String updateForum = updateForum(forum);
			return updateForum;
		}
		int i = forumService.addForum(forum);
		return "redirect:/forumManage_list.action";
	}
	@RequestMapping("deleteForum")
	public String deleteForum(Long id){
		int i = forumService.deleteForum(id);
		return "redirect:/forumManage_list.action";
	}
	@RequestMapping("updateForumUI")
	public String updateForumUI(Long id,Model model){
		Forum forum = forumService.getForumByFId(id);
		model.addAttribute("forum",forum);
		return "BBS_ForumManage/saveUI";
	}
	@RequestMapping("updateForum")
	public String updateForum(Forum forum){
		int i = forumService.updateForum(forum);
		return "redirect:/forumManage_list.action";
	}
	@RequestMapping("forum_ifForumName")
	public @ResponseBody boolean forum_ifForumName(String name){
		boolean f = forumService.forum_ifForumName(name);
		return f;
	}
	
}
