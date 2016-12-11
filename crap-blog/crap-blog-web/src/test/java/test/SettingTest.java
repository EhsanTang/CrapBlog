package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.crap.blog.domain.dao.Setting;
import cn.crap.blog.service.ISettingService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SettingTest {
	@Autowired
	ISettingService service;
	
	
//	@Test 
//	public void selectSetting() throws ParseException{
//		List<Long> ids = new ArrayList<Long>();
//		ids.add(1L);
//		service.selectByIds(ids);
//	}
//	@Test 
//	public void save() throws ParseException{
//		Setting s = new Setting();
//		s.setCandelete(Byte.valueOf("1"));
//		s.setMkey("test--");
//		s.setRemark("dddd");
//		s.setSequence(10);
//		s.setStatus(Byte.valueOf("2"));
//		s.setType("IMAGE");
//		s.setValue("img.png");
//		service.save(s);
//	}
//	
//	@Test 
//	public void update() throws ParseException{
//		Setting s = new Setting();
//		s.setMkey("test-2822-");
//		s.setId(76L);
//		service.update(s);
//	}

	
	@Test 
	public void delete() throws ParseException{
		service.delete(76L);
	}
	
}
