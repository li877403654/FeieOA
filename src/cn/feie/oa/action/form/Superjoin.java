package cn.feie.oa.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.feie.oa.domain.Forum;
import cn.feie.oa.domain.Reply;
import cn.feie.oa.domain.Topic;

public class Superjoin {
	private Long id; 
	private Integer pageNo; 
	private String name;
	private Integer pageSum;	//总记录数
	private Integer PageNum = 5;//每页多少条记录
	private Integer pageCount ; //总页数
	private Integer recommend; //精华帖
	private Integer rankcount = 0; //综合排序
	private int elevator;  //升降
	private String sql;        //sql语句
	private Integer type;      //类型
	private Topic topic = new Topic();
	private List<Topic> topics = new ArrayList<>();
	private Reply reply = new Reply();
	
	public Superjoin() {
		super();
	}
	private Forum forum = new Forum();
	private List<Reply> replies = new ArrayList<>(); 
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSql() {
		return sql;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Integer getPageNum() {
		return PageNum;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public void setPageNum(Integer pageNum) {
		PageNum = pageNum;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public Integer getPageSum() {
		return pageSum;
	}
	public void setPageSum(Integer pageSum) {
		this.pageSum = pageSum;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getRankcount() {
		return rankcount;
	}
	public void setRankcount(Integer rankcount) {
		this.rankcount = rankcount;
	}
	public Integer getElevator() {
		return elevator;
	}
	public void setElevator(Integer elevator) {
		this.elevator = elevator;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public void setElevator(int elevator) {
		this.elevator = elevator;
	}
	
	
}
