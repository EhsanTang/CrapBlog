package cn.crap.blog.domain.dao;
public class Setting{
	private String mkey;
	private String value;
	private String remark;
	private String createtime;
	private byte status;
	private String type;
	private byte candelete;
	private int sequence;
	private long id;

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

	public void setStatus(byte status){
		this.status=status;
	}
	public byte getStatus(){
		return status;
	}

	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}

	public void setCandelete(byte candelete){
		this.candelete=candelete;
	}
	public byte getCandelete(){
		return candelete;
	}

	public void setSequence(int sequence){
		this.sequence=sequence;
	}
	public int getSequence(){
		return sequence;
	}

	public void setId(long id){
		this.id=id;
	}
	public long getId(){
		return id;
	}


}
