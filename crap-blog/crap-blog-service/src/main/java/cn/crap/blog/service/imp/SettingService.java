package cn.crap.blog.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.crap.blog.dao.imp.SettingDao;
import cn.crap.blog.domain.dao.Setting;
import cn.crap.blog.service.ISettingService;
@Service
public class SettingService implements ISettingService{

	@Autowired
	private SettingDao dao;
	@Override
	public List<Setting> selectByIds(List<Long> ids) {
		if(ids == null || ids.size() == 0){
			return new ArrayList<Setting>();
		}
		return dao.selectByIds(ids);
	}
}
