package cn.crap.blog.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.crap.blog.service.ISettingService;
import cn.crap.common.bean.result.ByJsonResult;
import cn.crap.common.constants.enums.ByResultCode;
import cn.crap.common.framework.controller.BaseController;


@Controller
@RequestMapping("/setting")
public class SettingController extends BaseController {
	
	@Autowired
	private ISettingService service;
	@ResponseBody
	@RequestMapping(value = "/get.do")
	public ByJsonResult get() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		return new ByJsonResult(ByResultCode.SUCCESS,  service.selectByIds(ids) );
	}
}
