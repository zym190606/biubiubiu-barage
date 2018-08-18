package team.redrock.wechatbarrage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatBarrageApplicationTests {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(System.currentTimeMillis()/1000);
    }

    @Test
    public void contextLoads() {
    }

}
