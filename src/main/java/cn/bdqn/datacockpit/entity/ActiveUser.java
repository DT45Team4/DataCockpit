package cn.bdqn.datacockpit.entity;

import java.io.Serializable;
import java.util.List;


public class ActiveUser implements Serializable{
	private String phone;
	private String password;
	private int rid;
	private List<String> listp;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public List<String> getListp() {
		return listp;
	}
	public void setListp(List<String> listp) {
		this.listp = listp;
	}
	
}
