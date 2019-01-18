package cn.xiaoyu;

import java.util.Collections;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.github.pagehelper.PageHelper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan(basePackages = "cn.xiaoyu.dao") // 引入文件
@SpringBootApplication
@EnableSwagger2 // 加入 Swagger
public class Startup extends SpringBootServletInitializer {

 
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	
	//配置ConversionService  String日期格式转化
	   @Bean
	    public ConversionService conversionService() {
	        FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
	        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
	        registrar.setUseIsoFormat(true);
	        factory.setFormatterRegistrars(Collections.singleton(registrar));
	        factory.afterPropertiesSet();
	        return factory.getObject();
	    }
	
	
	//分页插件
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql");  
		pageHelper.setProperties(properties);
		return pageHelper;
	}
 
}
