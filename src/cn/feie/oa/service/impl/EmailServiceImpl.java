package cn.feie.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.feie.oa.dao.EmailDao;
import cn.feie.oa.domain.Email;
import cn.feie.oa.service.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	private int num = 30;
	@Resource
	private EmailDao emailDao;

	@Override
	public boolean addEmail(Email email) {
		// TODO Auto-generated method stub
		if(email.getGoid()==email.getComeid()){
			return false;
		}
		return emailDao.addEmail(email);
	}
	@Override
	public void addDrafyEmail(Email email) {

	
	
	}

	public int sumEmail(){
		return emailDao.sumEmail();
	}



	public List<Email> box(int goid,int comeid, int startnum, int endnum,String whatbox) {
		//outbox inbox draftbox waste
		switch (whatbox) {
		case "outbox":
			List<Email> e = emailDao.outemail(goid,comeid, startnum, endnum);
			return e;
		case "inbox":
			return emailDao.comeEmail(goid,comeid, startnum, endnum);
		case "draftbox":
			return emailDao.draftbox(goid,comeid, startnum, endnum);
		case "waste":
			return emailDao.waste(goid,comeid, startnum, endnum);	
		default:
			break;
		}
		return null;
	}
	public int sumnum(int sizi){
		int i =0;
		if(sizi!=i){
			i = (sizi/num)+1;
		}
		return i;
	}

	@Override
	public Email getEmailByEid(int eid) {
		// TODO Auto-generated method stub
		return emailDao.getEmailByEid(eid);
	}

	@Override
	public void wasteEmail(int eid) {
		emailDao.wasteEmail(eid);
		
	}

	@Override
	public void delEmail(int eid) {
		emailDao.delEmail(eid);
	}

	@Override
	public void draftEmail(int eid) {
		emailDao.draftEmail(eid);
	}

	@Override
	public void restoreEmail(int eid) {
		// TODO Auto-generated method stub
		emailDao.restoreEmail(eid);
	}
	@Override
	public void newsno(int eid){
		emailDao.newsno(eid);
	}

	@Override
	public int newsnum(int uid) {
		// TODO Auto-generated method stub
		return emailDao.newsnum(uid);
	}
}
