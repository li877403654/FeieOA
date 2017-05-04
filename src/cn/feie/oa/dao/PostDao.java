package cn.feie.oa.dao;

import java.util.List;
import java.util.Map;

import cn.feie.oa.domain.Post;

public interface PostDao {
	/**
	 * 全部职位
	 * @return
	 */
	List<Post> allPost();
	/**
	 * 更具pid查询这个职位的员工
	 * @param pid
	 * @return
	 */
	List<Map<String, Object>> getUidByPid(int pid);
	/**
	 * 根据uid查找员工职务
	 * @param uid
	 * @return
	 */
	List<Map<String, Object>> getPidByUid(int uid);
	/**
	 * 根据用户id清空用户职务
	 * @param uid
	 */
	void delByUid(int uid);
	/**
	 * 根据uid添加职位
	 * @param uid
	 * @param pid
	 */
	void addPost_user(int uid ,int pid);
	
	List<Post> getPostByUid(int uid);
	
	Post getPostBypname(String pname);
	void addPost(Post post);
	void deletePost(Integer pid);
	void updatePost(Post post);
	Post getPostBypid(Integer pid);
}
