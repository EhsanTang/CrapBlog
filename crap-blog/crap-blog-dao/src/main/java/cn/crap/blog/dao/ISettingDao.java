package cn.crap.blog.dao;


import java.util.List;

import cn.crap.blog.domain.dao.Setting;

public interface ISettingDao {
	public int save(Setting t);
	public int update(Setting t);
	public int delete(long id);
	public Setting get(long id);
	
	List<Setting> selectByIds(List<Long> ids);
}
