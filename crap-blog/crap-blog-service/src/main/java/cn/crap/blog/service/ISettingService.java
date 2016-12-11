package cn.crap.blog.service;

import java.util.List;

import cn.crap.blog.domain.dao.Setting;

public interface ISettingService {
	public int save(Setting t);
	public int update(Setting t);
	public int delete(long id);
	public Setting get(long id);
	List<Setting> selectByIds(List<Long> ids);
}
