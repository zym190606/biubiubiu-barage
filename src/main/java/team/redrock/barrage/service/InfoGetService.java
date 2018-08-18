package team.redrock.barrage.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team.redrock.barrage.been.WechatCodeResponse;
import team.redrock.barrage.been.WechatUserInfo;
import team.redrock.barrage.util.SendUtil;

@Component
@Service
public class InfoGetService {
    private Gson gson = new Gson();

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String secret;

    public WechatUserInfo getUserInfo(String code) {
        WechatUserInfo userInfo = null;
        //第一步 用code换token
        StringBuilder url1 = new StringBuilder();
        url1.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=").append(appid).append("&secret=").append(secret).append("&code=").append(
                code).append("&grant_type=authorization_code");
        String result1 = SendUtil.sendGet(url1.toString());
        WechatCodeResponse wechatCodeResponse = gson.fromJson(result1, WechatCodeResponse.class);
        //第二步 用token换信息
        if (wechatCodeResponse.getErrcode() == null) {
            StringBuilder url2 = new StringBuilder();
            url2.append("https://api.weixin.qq.com/sns/userinfo?access_token=").append(wechatCodeResponse.getAccessToken()).append("&openid=").append(wechatCodeResponse.getOpenid()).append("&lang=zh_CN");
            String result2 = SendUtil.sendGet(url2.toString());
            userInfo = gson.fromJson(result2, WechatUserInfo.class);
            if (userInfo.getErrcode() != null) {
                throw new RuntimeException("获取信息发生错误！\n错误码为" + userInfo.getErrcode() + "\n错误信息为：" + userInfo.getErrmsg());
            }
        } else {
            throw new RuntimeException("获取信息发生错误！\n错误码为:" + wechatCodeResponse.getErrcode() + "\n错误描述为：" + wechatCodeResponse.getErrmsg());
        }
        return userInfo;
    }
}
