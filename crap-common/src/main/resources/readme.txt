目录说明：
1.bean目录下为 公共bean
2.constants目录下为各种常量（包括公共错误码）
3.exception为自定义exception其中
       a.ByException为业务异常
       b.ByCodeException为编码预警(例：要求所有http返回报文都有是否成功的表示，所以任何ByJsonResult的调用处都必须传递是否成功参数，如果有人没有传递，则报错)
4.framework 包括公共的框架处理（包括异常处理等）
5.util 包括主要的工具类


依赖说明：
    当有人需要依赖该包进行开发时请注意:
1.请新建Controller继承com.biyao.common.framework.controller.BaseController
2.如有自定义错误码
        请： a.查询document.biyao.com确保定义的状态码，确保错误码不会重复
             b.在你自己的工程中定义好该错误码（例com.biyao.common.constants.enums.ByErrorCode）
             c.实现一个接口(com.biyao.common.framework.service.IErrorCodeInstall)的installErrorCodeMap方法；
               该方法需要收集所有你在工程中自己定义的errorCode：msg。
