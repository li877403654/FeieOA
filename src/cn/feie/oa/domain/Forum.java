package cn.feie.oa.domain;

import java.util.ArrayList;
import java.util.List;

public class Forum {
	private Long id;
	private String name;
	private String description;
	private Integer topicCount;
	private Integer replyCount;
	private int rank;
	private List<Topic> topics = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	
	
}
