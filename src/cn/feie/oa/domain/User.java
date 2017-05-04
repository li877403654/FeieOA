package cn.feie.oa.domain;
import java.io.File;
import java.util.*;

public class User {

    public User() {
    }
    
    private int uid;

   
    private String uname;

    private Dept dept;


    private String usex;

    private String uphone;

    private String loginname;
    
    private File imager;

    private String email;

    private List<Post> posts;
    
    private String password;
    
    private String description;
    
    private String imgname;
	
    public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}


	public File getImager() {
		return imager;
	}

	public void setImager(File imager) {
		this.imager = imager;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", dept=" + dept
				+ ", usex=" + usex + ", uphone=" + uphone + ", loginname="
				+ loginname + ", email=" + email + ", posts=" + posts
				+ ", password=" + password + ", description=" + description
				+ "]";
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User(int uid, String uname, Dept dept, String usex, String uphone,
			String loginname, String email, List<Post> posts, String password,
			String description) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.dept = dept;
		this.usex = usex;
		this.uphone = uphone;
		this.loginname = loginname;
		this.email = email;
		this.posts = posts;
		this.password = password;
		this.description = description;
	}


	

}