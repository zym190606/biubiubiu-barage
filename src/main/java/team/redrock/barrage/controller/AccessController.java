package team.redrock.barrage.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import team.redrock.barrage.been.WechatMessage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

@RestController
public class AccessController {

    @Value("${wechat.token}")
    private String token;

    @PostMapping(value = "/access", produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public WechatMessage reply(@RequestBody WechatMessage message) {
        WechatMessage responseMessage = new WechatMessage();
        responseMessage.setFromUserName(message.getToUserName());
        responseMessage.setToUserName(message.getFromUserName());
        responseMessage.setContent("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd5fb5e11338cc615&redirect_uri=http://62jqhy.natappfree.cc/barrageIndex&response_type=code&scope=snsapi_userinfo#wechat_redirect ");
        responseMessage.setMsgType("text");
        responseMessage.setCreateTime(String.valueOf(new Date().getTime()));
        return responseMessage;
    }

    @GetMapping("/access")
    public String access(@Param("signature") String signature, @Param("timestamp") String timestamp,
                         @Param("nonce") String nonce, @Param("echostr") String echostr) {
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }


    private String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }
}
