package cn.bdqn.datacockpit.entity;

import java.util.Date;

public class Analysistasks {
    private Integer id;

    private String starttime;
    
    private String endtime;

    private Integer cid;

    private Integer did;

    private Integer taskstatus;

    private String rule;

    private String feedback;

    private Integer arithmeticid;
    
    private String name;
    
    private int state;
    public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getTaskstatus() {
    	if(taskstatus==1){
    		return "执行中";
    	}else if(taskstatus==2){
    		return "执行完成";
    	}else if(taskstatus==0){
    		return "已添加";
    	}else{
    		return "任务失败";
    	}
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getArithmeticid() {
        return arithmeticid;
    }

    public void setArithmeticid(Integer arithmeticid) {
        this.arithmeticid = arithmeticid;
    }
}