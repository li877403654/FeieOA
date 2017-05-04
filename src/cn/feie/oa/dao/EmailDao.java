package cn.feie.oa.dao;

import java.util.List;

import cn.feie.oa.domain.Email;

public interface EmailDao {
	
	boolean addEmail(Email email);
	List<Email> comeEmail(int goid,int comeid,int startnum,int endnum);
	int sumEmail();
	List<Email> waste(int goid,int comeid,int startnum,int endnum);
	List<Email> draftbox(int goid,int comeid,int startnum,int endnum);
	List<Email> outemail(int goid,int comeid,int startnum,int endnum);
	Email getEmailByEid(int eid);
	void wasteEmail(int eid);
	void delEmail(int uid);
	void draftEmail(int eid);
	void restoreEmail(int eid);
	void newsno(int uid);
	int newsnum(int uid);
}
