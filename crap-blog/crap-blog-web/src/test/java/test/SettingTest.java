package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.crap.blog.service.ISettingService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SettingTest {
	@Autowired
	ISettingService service;
	
	
	@Test 
	public void selectSetting() throws ParseException{
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		service.selectByIds(ids);
		
	}
}
