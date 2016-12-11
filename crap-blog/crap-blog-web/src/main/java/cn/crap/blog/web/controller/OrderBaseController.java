package cn.crap.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.crap.common.framework.controller.BaseController;


@Controller
@RequestMapping("/orderBase")
public class OrderBaseController extends BaseController {
	/**
	 * 支付成功修改预计发货时间等，由api、主动查询服务调用
	 * @param orderIds
	 * @return
	 * @throws ParseException
	 */
//	@ResponseBody
//	@RequestMapping(value = "/paySuccess.do")
//	public ByJsonResult get(@RequestParam String orderIds) throws ParseException {
//		String[] orderIdStrings = orderIds.split(",");
//		List<Long> orderIdList = new ArrayList<Long>();
//		for (int i = 0; i < orderIdStrings.length; i++) {
//			Long orderId = Long.parseLong(orderIdStrings[i]);
//			orderIdList.add(orderId);
//		}
//		return new ByJsonResult(ByResultCode.SUCCESS,  orderBaseService.paySuccess(orderIdList) );
	}
