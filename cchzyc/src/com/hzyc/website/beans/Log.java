package com.hzyc.website.beans;

public class Log {
	//执行起止时间（用来查询日志绑定）
	private String startTime;
	private String endTime;
	
	//记录抛出的异常
	private String exception;
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
    private Integer id;

    private String loginaccount;

    private String loginip;

    private String actionurl;

    private String module;

    private String method;

    private long actiontime;

    private String description;

    private String gmtcreate;

    public String getGmtcreate() {
		return gmtcreate;
	}

	private short state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginaccount() {
        return loginaccount;
    }

    public void setLoginaccount(String loginaccount) {
        this.loginaccount = loginaccount == null ? null : loginaccount.trim();
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public String getActionurl() {
        return actionurl;
    }

    public void setActionurl(String actionurl) {
        this.actionurl = actionurl == null ? null : actionurl.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public long getActiontime() {
        return actiontime;
    }

    public void setActiontime(long actiontime) {
        this.actiontime = actiontime ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String mylog() {
        return gmtcreate;
    }

    public void setGmtcreate(String gmtcreate) {
        this.gmtcreate = gmtcreate == null ? null : gmtcreate.trim();
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

	/*SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = sdf.format(gmtCreate);
	}
*/
	@Override
    public String toString(){
		return "Log: 操作人ID:'"+loginaccount+"', 操作人IP:'"+loginip+"', 请求路径：'"+actionurl+"', 执行模块：'"+module+"', 执行方法：'"+method+"', 响应时间："+actiontime+", 描述：'"+description+"', 操作时间："+gmtcreate+", 状态："+state+"}";
    }
}