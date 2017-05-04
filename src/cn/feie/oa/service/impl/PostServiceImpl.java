package cn.feie.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.feie.oa.dao.PostDao;
import cn.feie.oa.domain.Post;
import cn.feie.oa.domain.User;
import cn.feie.oa.service.PostServise;
import cn.feie.oa.service.UserService;
@Service
public class PostServiceImpl implements PostServise {

	@Resource	
	private PostDao postDao;
	@Resource
	private UserService userService;
	@Override
	public List<Post> allPost() {
		// TODO Auto-generated method stub
		List<Post> p = postDao.allPost();
		for (Post post : p) {
			List<Map<String, Object>> map = postDao.getUidByPid(post.getPid());
			List<User> user = new ArrayList<>();
			for (Map<String, Object> map2 : map) {
				int uid = (int) map2.get("uid");
				User u = userService.findByid(uid);
				user.add(u);
			}
			post.setUsers(user);
		}
		return p;
	}
	@Override
	public String getPidByUid(int uid) {
		List<Map<String, Object>> pidByUid = postDao.getPidByUid(uid);
		String str = "";
		for (Map<String, Object> map : pidByUid) {
			str+=map.get("pid")+",";
		}
		return str;
	}
	
	 @Override
	public Post getPostBypname(String pname) {
		return postDao.getPostBypname(pname);
	}
	 @Override
	public void addPost(Post post) {
		 postDao.addPost(post);
	}
	 @Override
	public void deletePost(Integer pid) {
		 postDao.deletePost(pid);
	}
	 @Override
	public void updatePost(Post post) {
		 postDao.updatePost(post);
	}
	 @Override
	public Post getPostBypid(Integer pid) {
		 Post post = postDao.getPostBypid(pid);
		return post;
	}
}
