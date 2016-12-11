package cn.crap.blog.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.crap.blog.domain.dao.Setting;

public interface SettingMapper {
	public int save(Setting t);
	public int update(Setting t);
	public int delete(@Param("id") long id);
	public Setting get(@Param("id") long id);
	List<Setting> selectByIds(List<Long> ids);
}
