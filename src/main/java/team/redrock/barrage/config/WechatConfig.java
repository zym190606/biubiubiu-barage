package team.redrock.barrage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {
    private String appid;
    private String appsecret;
    private String token;
}
