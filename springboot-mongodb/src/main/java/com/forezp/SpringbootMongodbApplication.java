package com.forezp;


import com.forezp.dao.ConfigInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@ComponentScan(basePackages={"com.forezp"})
public class SpringbootMongodbApplication  {


	@Autowired
	private ConfigInfoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}


}
