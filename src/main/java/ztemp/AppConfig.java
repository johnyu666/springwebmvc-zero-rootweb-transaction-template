package ztemp;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dao.CustomerDao;
import pojo.Customer;

@Configuration
@ComponentScan(basePackages= {"aspect","dao"})
@EnableAspectJAutoProxy(proxyTargetClass=false)
@EnableTransactionManagement
public class AppConfig 
//implements TransactionManagementConfigurer
{
	
	@Bean
	public DataSource createDataSource() {
		System.out.println("ds constructing....");
		String url="jdbc:mysql://localhost:3306/test?useSSL=false"
				,username="root"
				,password="123"
				,driverClassName="com.mysql.cj.jdbc.Driver";
		
//		DriverManagerDataSource ds=new DriverManagerDataSource(url, username, password);
		BasicDataSource ds=new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setDriverClassName(driverClassName);
		ds.setDefaultAutoCommit(false);
		return ds;
	}
	
	@Bean
	public DataSourceTransactionManager createTransactionManager() {
		DataSourceTransactionManager tx=new DataSourceTransactionManager(createDataSource());
		return tx;	
	}
	@Bean
	public JdbcTemplate createTemplate() {
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(createDataSource());
		return template;
	}
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return createTransactionManager();
//	}
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		CustomerDao cd=(CustomerDao)context.getBean("custoemrDaoImpl2");
		System.out.println(cd.getClass().getName());
		Customer c=new Customer();
		c.setCname("john");
		int rs=cd.addCustomer(c);
		System.out.println(rs);
	}
}
