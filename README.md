# biubiubiu-barrage

------

> 欢迎关注我的博客：[MashiroC的奇思妙想](https://blog.mashiroc.cn/)
>
> 写点杂七杂八、奇思妙想。
>
> 偶尔分享一下最近学到的新技术。

一个用微信端发送弹幕，大屏幕播放的弹幕系统。

#### 示例:

![弹幕示例](http://p92wwofg0.bkt.clouddn.com/pic.png)

### 技术栈

- spring boot
- mysql
- mybatis
- netty
- websocket
- 微信接入

### 下载安装

Maven:

```xml
<!--spring boot的html模板-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!--spring boot的web模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        <!--mysql的驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--lombok库 自动生成getter setter方法-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--spring boot的单元测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--spring boot打包部署tomcat使用的servlet-api-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!--dbcp2数据库连接池-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.6.2</version>
            <scope>compile</scope>
        </dependency>
        <!--netty-->
        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
            <version>4.1.24.Final</version>
        </dependency>
```

### 使用方法

1.git clone

2.修改application.properties里的数据库用户名 密码等

3.修改AccessController里的聊天自动回复消息

4.部署 修改微信公众平台开发者中心相应参数

### 注意事项

- netty和spring必须运行在不同端口
- 本地测试时使用testOpenid来进行本地测试

### TODO

- 增加防刷机制
- 增加更多炫酷的弹幕
- 增加后台管理界面
- 增加一个自己留的洞(x)