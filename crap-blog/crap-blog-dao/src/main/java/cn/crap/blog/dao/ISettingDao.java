package cn.crap.blog.dao;


import java.util.List;

import cn.crap.blog.domain.dao.Setting;

public interface ISettingDao {
	List<Setting> selectByIds(List<Long> ids);
}
