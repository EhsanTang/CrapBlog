package cn.crap.blog.service;

import java.util.List;

import cn.crap.blog.domain.dao.Setting;

public interface ISettingService {
List<Setting> selectByIds(List<Long> ids);
}
