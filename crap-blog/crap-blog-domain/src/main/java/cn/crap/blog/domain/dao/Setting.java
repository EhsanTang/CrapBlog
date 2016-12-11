package cn.crap.blog.domain.dao;
public class Setting{
	private String mkey;
	private String value;
	private String remark;
	private String createtime;
	private Byte status;
	private String type;
	private Byte candelete;
	private Integer sequence;
	private Long id;

	public void setMkey(String mkey){
		this.mkey=mkey;
	}
	public String getMkey(){
		return mkey;
	}

	public void setValue(String value){
		this.value=value;
	}
	public String getValue(){
		return value;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setCreatetime(String createtime){
		this.createtime=createtime;
	}
	public String getCreatetime(){
		return createtime;
	}

	public void setStatus(Byte status){
		this.status=status;
	}
	public Byte getStatus(){
		return status;
	}

	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}

	public void setCandelete(Byte candelete){
		this.candelete=candelete;
	}
	public Byte getCandelete(){
		return candelete;
	}

	public void setSequence(Integer sequence){
		this.sequence=sequence;
	}
	public Integer getSequence(){
		return sequence;
	}

	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}


}
