package cn.feie.oa.domain;

import java.util.List;
import java.util.Set;



public class Dept {

    public Dept() {
    }

    
    private int did;
    private String dname;
    private int deptuid;
    private String dcomment;
    private Integer dpid;
    private User deptuser;
    private Set<User> users;
    private Dept dChildList;
    private List<Dept> depts;
    private User user;
    
    
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public String getDcomment() {
		return dcomment;
	}

	public void setDcomment(String dcomment) {
		this.dcomment = dcomment;
	}

	public Dept getdChildList() {
		return dChildList;
	}

	public void setdChildList(Dept dChildList) {
		this.dChildList = dChildList;
	}

	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}

	public String getdcomment() {
		return dcomment;
	}

	public void setdcomment(String dcomment) {
		this.dcomment = dcomment;
	}

	public Integer getDpid() {
		return dpid;
	}

	public void setDpid(int dpid) {
		this.dpid = dpid;
	}

	public int getDeptuid() {
		return deptuid;
	}

	public void setDeptuid(int deptuid) {
		this.deptuid = deptuid;
	}


	

	public int getDid() {
		return did;
	}

	public Dept(int did, String dname, User deptuser, Set<User> users) {
		super();
		this.did = did;
		this.dname = dname;
		this.deptuser = deptuser;
		this.users = users;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public User getDeptuser() {
		return deptuser;
	}

	public void setDeptuser(User deptuser) {
		this.deptuser = deptuser;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	


	@Override
	public String toString() {
		return "Dept [did=" + did + ", dname=" + dname + ", deptuid=" + deptuid
				+ ", deptuser=" + deptuser + ", users=" + users + "]";
	}

	public Dept(int did, String dname, User deptuser) {
		super();
		this.did = did;
		this.dname = dname;
		this.deptuser = deptuser;
	}

	


}