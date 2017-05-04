package cn.feie.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.feie.oa.dao.DeptDao;
import cn.feie.oa.dao.PostDao;
import cn.feie.oa.dao.UserDao;
import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private DeptDao deptDao;
	@Resource
	private PostDao postDao;
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public int delete(int uid) {
		// TODO Auto-generated method stub
		int value = 0;
		value = deptDao.delDeptuid(uid);
		value += deptDao.delPostuid(uid);
		value += userDao.delete(uid);
		return value;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public User findByid(int uid) {
		User user = userDao.getById(uid);
		List<Post> post = postDao.getPostByUid(uid);
		user.setPosts(post);
		return user;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public List<User> findByUname(String uname) {

		return userDao.findByUname(uname);
	}

	@Override
	public String login(String username) {
		String p = null;
		try {
			p = userDao.login(username);
		} catch (Exception e) {
			return null;
		}

		return p;
	}

	@Override
	public User finduid(String loginname, String password) {
		return userDao.finduid(loginname, password);
	}


	@Override
	public List<User> findUser(int pagestart, int pagesizi) {
		List<User> user = userDao.findUser(pagestart, pagesizi);
		for (User user2 : user) {
			List<Post> po =postDao.getPostByUid(user2.getUid());
			user2.setPosts(po);
		}
		return user;
	}

	@Override
	public void initializePassword(int uid) {
		userDao.initializePassword(uid);
	}
	public void userUpdata(User user,String poststrings){
		
		String[] pid = null;
		if (poststrings!=null) {
			pid = poststrings.split(",");
			postDao.delByUid(user.getUid());
			for (String string : pid) {
				postDao.addPost_user(user.getUid(),Integer.valueOf(string));
			}
		}
		boolean i = userDao.ifLogname(user.getLoginname());
		if(!i){
			user.setLoginname(userDao.getById(user.getUid()).getLoginname());
		}
		
		userDao.userUpdata(user);
		
		
	}

	@Override
	public void userAdd(User user,String[] post) {
		userDao.userAdd(user);
		user = userDao.getByLoginmame(user.getLoginname());
		for (String string : post) {
			postDao.addPost_user(user.getUid(),Integer.valueOf(string));
		}
	}

	@Override
	public int ifName(String uname) {
		int  i=0;
		boolean ifLogname = userDao.ifLogname(uname);
		if (ifLogname) {
			i = userDao.newuid();
		}
		return i;
	}
	@Override
	public int addImagerName(String u_name,int uid) {
		return userDao.addImagerName(u_name,uid);
	}
	@Override
	public int updatePassword(User user) {
		return userDao.updatePassword(user);
	}
	@Override
	public int ifPassword(User user) {
		return userDao.ifPassword(user);
	}
	@Override
	public int getOnLineUser() {
		return userDao.getOnLineUser();
	}
	
	@Override
	public void updateOnLine0(int uid) {
		userDao.updateOnLine0(uid);
	}
	@Override
	public void updateOnLine1(int uid) {
		userDao.updateOnLine1(uid);
	}

	@Override
	public List<User> onLineUserList() {
		List<User> user = userDao.onLineUserList();
		for (User user2 : user) {
			List<Post> po =postDao.getPostByUid(user2.getUid());
			user2.setPosts(po);
		}
		return user;
	}
}
