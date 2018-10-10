package com.nfjd;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableSwagger2
@MapperScan("com.nfjd.mappers")
public class AtivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtivitiApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(final RepositoryService repositoryService,
								  final RuntimeService runtimeService,
								  final TaskService taskService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				System.out.println("Number of process definitions : "
						+ repositoryService.createProcessDefinitionQuery().count());
				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
				runtimeService.startProcessInstanceByKey("oneTaskProcess");
				System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
			}
		};

	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*");
			}
		};
	}


	/**
	 * 注册DruidServlet
	 *
	 * @return
	 */
	/*@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid*//*");
		return servletRegistrationBean;
	}*/

	/**
	 * 注册DruidFilter拦截
	 *
	 * @return
	 */
	/*@Bean
	public FilterRegistrationBean duridFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<String, String>();
		//设置忽略请求
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid*//*");
		filterRegistrationBean.setInitParameters(initParams);
		filterRegistrationBean.addUrlPatterns("*//*");
		return filterRegistrationBean;
	}*/

	/**
	 * 配置DataSource
	 * @return
	 * @throws SQLException
	 */
	/*@Bean
	public DruidDataSource druidDataSource() throws SQLException {
		DruidDataSource druidDataSource = new DruidDataSource();
		//druidDataSource.setUsername("zhouweizheng");
		//druidDataSource.setPassword("zwz1234");
		//druidDataSource.setUrl("jdbc:mysql://mysql.nj-itc.com.cn:3306/activiti");
		druidDataSource.setMaxActive(100);
		druidDataSource.setFilters("stat,wall");
		druidDataSource.setInitialSize(10);

		return druidDataSource;
	}*/
	/**
	 *此处还可以这么配置
	 */
	//	@Bean
//	public DataSource  druidDataSource(
//			@Value("${spring.datasource.driverClassName}") String driverClass,
//            @Value("${spring.datasource.url}") String url,
//            @Value("${spring.datasource.username}") String username,
//            @Value("${spring.datasource.password}") String password){
//			DruidDataSource ds=new DruidDataSource();
//			ds.setDriverClassName(driverClass);
//	        ds.setUrl(url);
//	        ds.setUsername(username);
//	        ds.setPassword(password);
//	        try {
//				ds.setFilters("stat,wall");
//			} catch (SQLException e) {
//				e.printStackTrace();
//				  log.error("druid err:{}", e);
//			}
//		return ds;
//	}
}
