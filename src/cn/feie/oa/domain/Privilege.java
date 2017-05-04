package cn.feie.oa.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Privilege {
	private Long id;
	private String name;
	private String url;
	private Privilege privilege;
	private List<Privilege> parent = new ArrayList<>();
	private List<Post> posts = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public List<Privilege> getParent() {
		return parent;
	}
	public void setParent(List<Privilege> parent) {
		this.parent = parent;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", url=" + url
				+ ", privilege=" + privilege + ", parent=" + parent
				+ ", posts=" + posts + "]";
	}
	
	
}
