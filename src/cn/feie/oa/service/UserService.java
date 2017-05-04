package cn.feie.oa.service;

import java.util.List;


import cn.feie.oa.domain.User;

public interface UserService {

	List<User> findAll();

	int delete(int uid);

	void add(User user);

	User findByid(int uid);

	void update(User user);

	List<User> findByUname(String uname);

	String login(String username);
	
	User finduid(String loginname, String password);

	List<User> findUser(int pagestart,int pagesizi); 
	
	void initializePassword(int uid); 
	
	void userUpdata(User user,String poststrings);
	
	void userAdd(User user,String[] post);
	
	int ifName(String uname);
//	Object login(String username);

	int addImagerName(String u_name,int uid);
	
	int updatePassword(User user);

	int ifPassword(User user);

	int getOnLineUser();
	
	void updateOnLine1(int uid);
	
	void updateOnLine0(int uid);

	List<User> onLineUserList();
}
