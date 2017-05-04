package cn.feie.oa.domain;

import java.util.Date;

public class Topic {
	private Long id;
	private String title;
	private Integer replyCount;		//回复数
	private Date creationtime;	  //创建时间
	private Date updatetime;     //修改时间
	private Integer type;	//精华帖
	private Forum forum;  
	private User user = new User();
	private Reply reply = new Reply();
	
	public Topic() {
		super();
	}
	public Long getId() {
		return id;
	}
	public Reply getReply() {
		return reply;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Date getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
