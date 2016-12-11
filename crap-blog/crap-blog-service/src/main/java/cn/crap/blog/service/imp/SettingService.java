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
		return dao.selectByIds(ids);
	}

	@Override
	public int save(Setting t) {
		return dao.save(t);
	}

	@Override
	public int update(Setting t) {
		return dao.update(t);
	}

	@Override
	public int delete(long id) {
		return dao.delete(id);
	}

	@Override
	public Setting get(long id) {
		return dao.get(id);
	}
}
