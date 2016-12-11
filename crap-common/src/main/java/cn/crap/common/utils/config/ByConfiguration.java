package cn.crap.common.utils.config;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * 配置信息读取,默认使用properties/application.properties文件
 * 使用方式: ByConfiguration.getInstance().get***();
 * @author liuhui@idstaff.com
 *
 */
public class ByConfiguration {

    private static final Log logger1 = LogFactory.getLog(ByConfiguration.class);

    private static ByConfiguration appConfig;

    public synchronized static ByConfiguration getInstance() {
        if (appConfig == null) {
            appConfig = new ByConfiguration();
        }
        return appConfig;
    }

    private ByConfiguration() {

    }

    public static XMLConfiguration config = new XMLConfiguration();
    private static long delayReflashTime = 1000 * 60 * 10;        //每隔10分钟检查一次文件是否变化。

    static {
        try {
            config = new XMLConfiguration("conf/applicaton.xml");
            FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
            fileChangedReloadingStrategy.setRefreshDelay(delayReflashTime);
            config.setReloadingStrategy(fileChangedReloadingStrategy);
        } catch (ConfigurationException e) {
            logger1.error("config异常", e);
        }
    }

	/*
	static {
		try {
			 config.addConfiguration(new PropertiesConfiguration("properties/application.properties"));
			 config.addConfiguration(new XMLConfiguration("conf/applicaton.xml"));
		} catch (ConfigurationException e) {
		}
	}
	 */

    /**
     * 读取配置的红包拦截条件
     */
    public List<Object> getCouponLimitPaths() {
        return config.getList("limitImplPath.path");
    }

    /**
     * 读取API地址
     *
     * @return
     */
    public String getCanMannalPay() {
        return config.getString("CanMannalPay");
    }

    /**
     * 读取API地址
     *
     * @return
     */
    public String getApiUrl() {
        return config.getString("urlConfig.ApiUrl");
    }

    /**
     * 读取PtAPI地址
     *
     * @return
     */
    public String getPtApiUrl() {
        return config.getString("urlConfig.PtApiUrl");
    }


    /**
     * 读取API地址
     *
     * @return
     */
    public String getMiLifeIndexUrl() {
        return config.getString("urlConfig.MilifeIndex");
    }

    /**
     * 读取图片服务地址
     *
     * @return
     */
    public String getImageServer() {
        return config.getString("urlConfig.ImageServer");
    }

    /**
     * @return
     */
    public String getMainSite() {
        return config.getString("urlConfig.MainSite");
    }

    /**
     * 读取购物站点地址
     *
     * @return 购物站点地址
     */
    public String getDZShopSite() {
        return config.getString("urlConfig.DZShopSite");
    }

    /**
     * 读取商家后台站点地址
     *
     * @return 商家后台站点地址
     */
    public String getSjAdminSite() {
        return config.getString("urlConfig.SjAdmin");
    }

    public String getCommentApi() {
        return config.getString("urlConfig.CommentApi");
    }
    /****************appConfig start*********************/
    /**
     * 读取visitCookie
     *
     * @return
     */
    public String getVisitCode() {
        return config.getString("appConfig.VisitCookie");
    }

    /**
     * 读取cookie对应的写入domain
     *
     * @return
     */
    public String getDomain() {
        return config.getString("appConfig.Domain");
    }

    /**
     * 读取signkey
     *
     * @return
     */
    public String getSignkey() {
        return config.getString("appConfig.signkey");
    }

    /**
     * 读取LoginCookie
     *
     * @return
     */
    public String getLoginCookie() {
        return config.getString("appConfig.LoginCookie");
    }

    /**
     * 读取应用名称
     *
     * @return
     */
    public String getAppName() {
        return config.getString("appConfig.app_name");
    }

    /**
     * 读取页面公共title
     *
     * @return
     */
    public String getPageTitle() {
        return config.getString("appConfig.PageTitle");
    }

    /****************appConfig over*********************/

    /**
     * 读取fee_type
     *
     * @return
     */
    public String getPaymentFee_type() {
        return config.getString("payment.caifutong.fee_type");
    }

    /**
     * 读取文件共享路径
     *
     * @return
     */
    public String getSharePath() {
        return config.getString("fileConfig.SharePath");
    }

    /**
     * 读取linux文件共享路径
     *
     * @return
     */
    public String getSharePathLinux() {
        return config.getString("fileConfig.SharePathLinux");
    }


    /**
     * 获取渲染服务器配置项
     *
     * @return
     */
    public Dictionary<String, Integer> getRenderServers() {

        Dictionary<String, Integer> dict = new Hashtable<String, Integer>();

        List<HierarchicalConfiguration> servers = config.configurationsAt("renderConfig.servers.server");

        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            String ip = sub.getString("ip");
            Integer port = Integer.valueOf(sub.getString("port"));
            dict.put(ip, port);
        }

        return dict;
    }


    /**
     * 获取支持多材质渲染服务器配置项
     *
     * @return
     */
    public Dictionary<String, Integer> getMultiMaterialRenderServers() {

        Dictionary<String, Integer> dict = new Hashtable<String, Integer>();

        List<HierarchicalConfiguration> servers = config.configurationsAt("multiMaterial_renderConfig.servers.server");

        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            String ip = sub.getString("ip");
            Integer port = Integer.valueOf(sub.getString("port"));
            dict.put(ip, port);
        }

        return dict;
    }

    /**
     * 获取使用多材质引擎渲染的的二级品类ID。
     *
     * @return
     */
    public ArrayList<Integer> getUseMMDEngineCate2() {
        ArrayList<Integer> cate2 = new ArrayList<Integer>();
        List<Object> cate2_Objects = new ArrayList<Object>();
        List<HierarchicalConfiguration> useMMDEngineCate2 = config.configurationsAt("useMMDEngineCate2");
        for (Iterator<HierarchicalConfiguration> it = useMMDEngineCate2.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            cate2_Objects = sub.getList("category2");
            break;
        }
        for (int i = 0; i < cate2_Objects.size(); i++) {
            cate2.add(Integer.valueOf(cate2_Objects.get(i).toString()));
        }
        return cate2;
    }

    /**
     * 获取sessionIdKey
     *
     * @return
     */
    public String getSessionIDKey() {
        return config.getString("appConfig.SessionIDKey");
    }

    public Dictionary<String, HierarchicalConfiguration> GetCaiFuTongParteners() {
        Dictionary<String, HierarchicalConfiguration> dic = new Hashtable<String, HierarchicalConfiguration>();
        List<HierarchicalConfiguration> servers = config.configurationsAt("payment.caifutong.partners.partner");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            dic.put(String.valueOf(sub.getProperty("[@id]")), sub);
        }
        return dic;
    }

    public HierarchicalConfiguration GetCaiFuTongPartenerById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("payment.caifutong.partners.partner");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }

    public HierarchicalConfiguration GetCaiFuTongPartenerOtherPayById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("otherspayment.caifutong.partners.partner");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }

    public HierarchicalConfiguration GetAlipayPartenerById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("payment.alipay.partners.partner");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }

    public HierarchicalConfiguration GetOthersPayAlipayPartenerById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("otherspayment.alipay.partners.partner");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }


    /**
     * 获取tokenServer的ip地址
     *
     * @return
     */
    public String getTokenServerIp() {
        return config.getString("tokenConfig.server.ip");
    }

    /**
     * 获取tokenServer的port
     *
     * @return
     */
    public int getTokeServerPort() {
        return config.getInt("tokenConfig.server.port");
    }

    public String getSupplierId4ShoeList() {
        return config.getString("shoelist.supplierid");
    }

    public String getPayTest() {
        return config.getString("appConfig.paytest");
    }

    /*
     * 是否使用公测页面
     */
    public boolean getuseTestPage() {
        String usrTestPage = config.getString("appConfig.useTestPage");
        if (usrTestPage == null || !usrTestPage.equals("true")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 读取Redis IP
     *
     * @return
     */
    public String getRedisIp() {
        return config.getString("redisConfig.redisIp");
    }

    /**
     * 读取Redis Port
     *
     * @return
     */
    public int getRedisPort() {
        return config.getInt("redisConfig.redisPort");
    }

    /**
     * 读取运动鞋商家账号手机
     *
     * @return
     */
    public String getSupplierMobile4SportShoe() {
        return config.getString("sportsshoe.suppliermoblie");
    }

    /**
     * 读取运动鞋商家推送url
     *
     * @return
     */
    public String getPushUrl4SportShoe() {
        return config.getString("sportsshoe.pushurl");
    }

    /**
     * 读取运动鞋商家推送url
     *
     * @return
     */
    public String getBbsUrl() {
        return config.getString("urlConfig.bbsUrl");
    }

    /**
     * 读取Android的安装文件路径
     *
     * @return
     */
    public String getApkPath() {
        return config.getString("urlConfig.apkUrl");
    }

    public String getMiprivateKey() {
        return config.getString("xiaoMiKey.privateKey");
    }

    public String getMipublicKey() {
        return config.getString("xiaoMiKey.publicKey");
    }

    public String getMiSecretKey() {
        return config.getString("xiaoMiKey.AppSecret");
    }

    public String getMiOrderSyncUrl() {
        return config.getString("xiaoMiKey.OrderSyncUrl");
    }

    public String getMiCouponSyncUrl() {
        return config.getString("xiaoMiKey.CouponSyncUrl");
    }

    public String getMiCouponUseUrl() {
        return config.getString("xiaoMiKey.CouponUseUrl");
    }

    public String getMiCouponCreateUrl() {
        return config.getString("xiaoMiKey.CouponCreateUrl");
    }

    public String getMiAppID() {
        return config.getString("xiaoMiKey.AppID");
    }

    public String getMiVersion() {
        return config.getString("xiaoMiKey.version");
    }

    /**
     * 获取freeServer的ip地址
     *
     * @return
     */
    public String getFreeServerIp() {
        return config.getString("freeConfig.server.ip");
    }

    /**
     * 获取freeServer的port
     *
     * @return
     */
    public int getFreeServerPort() {
        return config.getInt("freeConfig.server.port");
    }

    public HierarchicalConfiguration getExpressConfigById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("expressConfig.express");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }

    /**
     * 获取是否支持F码
     *
     * @return
     */
    public boolean supportFCode() {
        return config.getBoolean("fCode");
    }

    /**
     * 获取是否支持限量购
     *
     * @return
     */
    public boolean supportLimitSale() {
        return config.getBoolean("limitSale");
    }

    /**
     * 获取是否支持材料库存check
     *
     * @return
     */
    public boolean supportMaterialCheck() {
        return config.getBoolean("materialCheck");
    }

    public boolean supportSyscMi() {
        return config.getBoolean("syscMi");
    }

    /**
     * 是否有汽车销售时的特殊用户
     *
     * @return
     */
    public boolean getQicheExcept() {
        return config.getBoolean("qicheExcept");
    }

    /**
     * 是否有特价车的限量购买
     *
     * @return
     */
    public boolean getQicheLimit() {
        return config.getBoolean("qicheLimit");
    }

    public List<Object> getOrderSperators() {
        return config.getList("orderSperators");
    }

    public List<Object> getOrderSperators2() {
        return config.getList("orderSperators2");
    }

    /**
     * 发送短信配置
     */
    public int getSmsIpRestrictTime() {
        return config.getInt("sms.ipRestrictTime");
    }

    public int getSmsIpRestrictNum() {
        return config.getInt("sms.ipRestrictNum");
    }

    public int getSmsPhoneRestrictTime() {
        return config.getInt("sms.phoneRestrictTime");
    }

    public int getSmsPhoneRestrictNum() {
        return config.getInt("sms.phoneRestrictNum");
    }

    public String getSmsDisplayNum() {
        return config.getString("sms.displayNum");
    }

    /**
     * 发送次数redis过期时间
     */
    public String getSmsRedisExpiredTime() {
        return config.getString("sms.redisExpiredTime");
    }

    /**
     * 短信重复发送次数
     */
    public String getSmsResendUpperLimit() {
        return config.getString("sms.resendUpperLimit");
    }

    /**
     * 短信重复发送休眠时间
     */
    public String getSmsResendSleepTime() {
        return config.getString("sms.resendSleepTime");
    }

    /**
     * ip最大发送短信数量
     */
    public String getSmsTimeNumForIp() {
        return config.getString("sms.smsTimeNumForIp");
    }

    /**
     * uuid最大发送数量
     */
    public String getSmsTimeNumForUuid() {
        return config.getString("sms.smsTimeNumForUuid");
    }

    /**
     * 非短信功能网络请求黑名单过滤功能是否开放
     */
    public String getBlackListIsOpen() {
        return config.getString("blackListIsOpen");
    }

    /**
     * 黑名单提示文案
     **/
    public String getBlackListTip() {
        return config.getString("blackListTip");
    }

    /**
     * API 私有密钥
     */
    public String getApiKey() {
        return config.getString("apiKey");
    }

    public boolean getkickBackEnableUnConnection() {
        return config.getBoolean("kickBack.enable-unconnecttion");
    }

    /**
     * 获取微信公众号
     */
    public HierarchicalConfiguration getWeiXinById(String id) {
        HierarchicalConfiguration hierconfig = null;
        List<HierarchicalConfiguration> servers = config.configurationsAt("weixins.weixin");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            if (sub.getProperty("[@id]").equals(id)) {
                hierconfig = sub;
            }
        }
        return hierconfig;
    }

    /**
     * 获取分布式redis连接池ip，port
     */
    public static List<String> GetRedisPoolIpAndPort() {
        List<String> redis = new ArrayList<String>();
        List<HierarchicalConfiguration> servers = config.configurationsAt("redisPoolConfig.redis");
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            redis.add(sub.getString("redisIp") + "_" + sub.getString("redisPort"));
        }
        return redis;
    }

    /**
     * 获取分布式redis负载映射
     */
    public static int[] GetRedisPoolLoad() {

        List<HierarchicalConfiguration> servers = config.configurationsAt("redisPoolLoad.redis");
        int[] loadMap = new int[servers.size()];
        int i = 0;
        for (Iterator<HierarchicalConfiguration> it = servers.iterator(); it.hasNext(); ) {
            HierarchicalConfiguration sub = it.next();
            loadMap[i] = Integer.parseInt(sub.getProperty("[@index]").toString());
            i++;
        }
        return loadMap;
    }

}
