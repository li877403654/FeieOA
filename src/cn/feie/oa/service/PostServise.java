package cn.feie.oa.service;

import java.util.List;
import java.util.Map;

import cn.feie.oa.domain.Post;

public interface PostServise {
	List<Post> allPost();
	String getPidByUid(int uid);
	Post getPostBypname(String pname);
	void addPost(Post post);
	void deletePost(Integer pid);
	void updatePost(Post post);
	Post getPostBypid(Integer pid);
}
