package cn.feie.oa.dao;

import java.util.List;



import cn.feie.oa.domain.User;

public interface UserDao{
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(User entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 * @return 
	 */
	int delete(int id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(User entity);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	User getById(int id);



	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<User> findAll();

	List<User> findByUname(String uname);
	/**
	 * 查询密码
	 * @param username
	 * @return
	 */
	String login(String username);	
	User finduid(String loginname, String password);
	List<User> findUser(int pagestart,int pagesizi);
	void initializePassword(int uid);
	void userUpdata(User user);
	void userAdd(User user);
	boolean ifLogname(String username);
	int newuid();

	int addImagerName(String u_name,int uid);

	int updatePassword(User user);

	int ifPassword(User user);

	User getByLoginmame(String loginname);

	int getOnLineUser();
	
	void updateOnLine1(int uid);
	
	void updateOnLine0(int uid);

	List<User> onLineUserList();
	
}
