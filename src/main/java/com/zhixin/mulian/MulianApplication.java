package com.zhixin.mulian;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan("com.zhixin.mulian.mapper")
@SpringBootApplication
public class MulianApplication {

	 public static void main(String[] args) throws UnknownHostException {
	        ConfigurableApplicationContext application = SpringApplication.run(MulianApplication.class, args);
	        Environment env = application.getEnvironment();
	        String ip = InetAddress.getLocalHost().getHostAddress();
	        String port = env.getProperty("server.port");
	        String path = env.getProperty("server.servlet.context-path");
	        log.info("\n----------------------------------------------------------\n\t" +
	                "Application Happycycle-Boot is running! Access URLs:\n\t" +
	                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
	                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
	                "----------------------------------------------------------");

	    }

}
