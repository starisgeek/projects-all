package com.yunfenghui.jf.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yunfenghui.jf.api.filter.FilterConfiguration;

@SpringBootApplication(scanBasePackages = { "com.yunfenghui.jf.api.controller" })
@ImportResource("classpath:service-consumer.xml")
@Import(value = { FilterConfiguration.class })
public class Bootstrap {
	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
}
