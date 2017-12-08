package cn.bdqn.datacockpit.entity;

import java.util.HashMap;
import java.util.List;

public class Tablerelation2 {
	private String name;
	private String tid1;
	private String tid2;
	private int col1;
	private int col2;
	private String cname1;
	private String cname2;
	private int state;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private HashMap<Integer, String> map;
	
	public HashMap<Integer, String> getMap() {
		return map;
	}
	public void setMap(HashMap<Integer, String> map) {
		this.map = map;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCname1() {
		return cname1;
	}
	public void setCname1(String cname1) {
		this.cname1 = cname1;
	}
	public String getCname2() {
		return cname2;
	}
	public void setCname2(String cname2) {
		this.cname2 = cname2;
	}
	public int getCol1() {
		return col1;
	}
	public void setCol1(int col1) {
		this.col1 = col1;
	}
	public int getCol2() {
		return col2;
	}
	public void setCol2(int col2) {
		this.col2 = col2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTid1() {
		return tid1;
	}
	public void setTid1(String tid1) {
		this.tid1 = tid1;
	}
	public String getTid2() {
		return tid2;
	}
	public void setTid2(String tid2) {
		this.tid2 = tid2;
	}
	
}
