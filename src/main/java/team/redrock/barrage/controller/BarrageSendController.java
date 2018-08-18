package team.redrock.barrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.redrock.barrage.been.BarrageMessage;
import team.redrock.barrage.been.ResponseEntity;
import team.redrock.barrage.mapper.BarrageMapper;
import team.redrock.barrage.service.BarrageCoreService;
import team.redrock.barrage.service.InfoGetService;
import team.redrock.barrage.been.WechatUserInfo;

import javax.servlet.http.HttpSession;

@Controller
public class BarrageSendController {

    @Autowired
    InfoGetService infoGetService;
    @Autowired
    BarrageCoreService coreService;
    @Autowired
    BarrageMapper barrageMapper;
    @Value("${wechat.appid}")
    String appid;

    private static final ResponseEntity OK = new ResponseEntity<>(200, "ok", null);

    private static final ResponseEntity BAN = new ResponseEntity<>(403, "you are to", null);

    private static final ResponseEntity PLEASE_USE_WECHAT = new ResponseEntity<>(403, "please use wechat", null);

    @GetMapping("/sendBarrage")
    @ResponseBody
    public ResponseEntity sendBarrage(BarrageMessage barrageMsg, HttpSession session) {
        String openid = (String) session.getAttribute("openid");
        if (openid != null) {
            if (coreService.isNotBan(openid)) {
                if (barrageMsg.getText().length() < 15) {
                    barrageMapper.insert(barrageMsg.getText(),barrageMsg.getColor(),openid,String.valueOf(System.currentTimeMillis()));
                    //TODO:放redis
                    coreService.send(barrageMsg);
                    return OK;
                }
            }
            return BAN;
        }
        return PLEASE_USE_WECHAT;
    }

    @GetMapping("/barrageIndex")
    public String barrageSendIndex(String code, HttpSession session) {
        WechatUserInfo userInfo = null;
        if ("testOpenid".equals(code)) {
            session.setAttribute("openid", "testOpenid");
        } else {
            userInfo = infoGetService.getUserInfo(code);
        }
        if (session.getAttribute("openid") == null) {
            session.setAttribute("openid", userInfo.getOpenid());
        }
        System.out.println(session.getAttribute("openid"));
        return "send";
    }
}