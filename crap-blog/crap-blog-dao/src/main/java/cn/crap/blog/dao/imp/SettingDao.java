package cn.crap.blog.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.crap.blog.dao.ISettingDao;
import cn.crap.blog.dao.mapper.SettingMapper;
import cn.crap.blog.domain.dao.Setting;

@Service
public class SettingDao implements ISettingDao{
	
	@Autowired
	private SettingMapper mapper;
	
	@Override
	public List<Setting> selectByIds(List<Long> ids) {
		return mapper.selectByIds(ids);
	}

	@Override
	public int save(Setting t) {
		return mapper.save(t);
	}

	@Override
	public int update(Setting t) {
		return mapper.update(t);
	}

	@Override
	public int delete(long id) {
		return mapper.delete(id);
	}

	@Override
	public Setting get(long id) {
		return mapper.get(id);
	}
}
