package cn.feie.oa.domain;

import java.util.Date;

public class Email {

	private int eid;
	private String etitle;
	private String etext;
	private int goid;
	private int comeid;
	private int draft;
	private int waste;
	private Date godate;
	private int place;
	private User user;
	private User user1;
	
	public User getUser1() {
		return user1;
	}


	public void setUser1(User user1) {
		this.user1 = user1;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}





	public int getEid() {
		return eid;
	}


	public void setEid(int eid) {
		this.eid = eid;
	}


	public String getEtitle() {
		return etitle;
	}


	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}


	public String getEtext() {
		return etext;
	}


	public void setEtext(String etext) {
		this.etext = etext;
	}


	public int getGoid() {
		return goid;
	}


	public void setGoid(int goid) {
		this.goid = goid;
	}


	public int getComeid() {
		return comeid;
	}


	public void setComeid(int comeid) {
		this.comeid = comeid;
	}


	public int getDraft() {
		return draft;
	}


	public void setDraft(int draft) {
		this.draft = draft;
	}


	public int getWaste() {
		return waste;
	}


	public void setWaste(int waste) {
		this.waste = waste;
	}


	public Date getGodate() {
		return godate;
	}


	public void setGodate(Date godate) {
		this.godate = godate;
	}


	public int getPlace() {
		return place;
	}


	public void setPlace(int place) {
		this.place = place;
	}


	public Email() {
	}
	
}
