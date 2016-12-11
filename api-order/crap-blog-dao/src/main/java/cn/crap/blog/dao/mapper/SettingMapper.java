package cn.crap.blog.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.crap.blog.domain.dao.Setting;

public interface SettingMapper {
	List<Setting> selectByIds(@Param(value="ids") List<Long> ids);
}
