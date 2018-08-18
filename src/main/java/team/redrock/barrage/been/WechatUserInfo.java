package team.redrock.barrage.been;

import lombok.Data;

@Data
public class WechatUserInfo {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String[] privilege;
    private String unionid;

    private String errcode;
    private String errmsg;

}