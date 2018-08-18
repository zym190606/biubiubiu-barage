package team.redrock.barrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WechatBarrageApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WechatBarrageApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WechatBarrageApplication.class);
    }

}
