package com.hzyc.hzycpos.domain;

public class User {
	//当前页
	private int nowPage;
	//默认显示6条
	private int pageSize = 6;
    public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private Integer id;

    private String uname;

    private String pwd;

    private String trueName;

    private String phone;

    private String idCard;

    private String email;

    private String address;

    public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	private Integer enabled;
    private String rname;
    private String storeName;
    
    //用来参数绑定 角色id
    private int rid;
    
    public void setRid(String rid){
    	this.rid = Integer.parseInt(rid);
    }
    public int getRid(){
    	return this.rid;
    }
    
    public Integer getStoreInfoId() {
		return storeInfoId;
	}

	public void setStoreInfoId(Integer storeInfoId) {
		this.storeInfoId = storeInfoId;
	}

	public byte[] getCardImgFace() {
		return cardImgFace;
	}

	public void setCardImgFace(byte[] cardImgFace) {
		this.cardImgFace = cardImgFace;
	}

	public byte[] getCardImgReverse() {
		return cardImgReverse;
	}

	public void setCardImgReverse(byte[] cardImgReverse) {
		this.cardImgReverse = cardImgReverse;
	}

	private Integer storeInfoId;
    private byte[] cardImgFace;
    private byte[] cardImgReverse;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}