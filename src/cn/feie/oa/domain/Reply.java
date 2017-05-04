package cn.feie.oa.domain;

import java.util.Date;

public class Reply {
	private Long id;
	private String title;
	private String content;
	private Date recoverytime;	  //回复时间
	private Integer recommend;   //顶帖
	private Integer faceIcon;    //表情
	private User user = new User();
	private Topic topic;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public Integer getFaceIcon() {
		return faceIcon;
	}
	public void setFaceIcon(Integer faceIcon) {
		this.faceIcon = faceIcon;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRecoverytime() {
		return recoverytime;
	}
	public void setRecoverytime(Date recoverytime) {
		this.recoverytime = recoverytime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	
	
}
