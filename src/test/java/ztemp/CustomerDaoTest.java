package ztemp;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import pojo.Customer;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@Transactional(isolation=Isolation.READ_COMMITTED)
public class CustomerDaoTest {
	@Autowired
	@Qualifier(value="custoemrDaoImpl2")
	private CustomerDao cd;	
	private int addCustomer() {
		Customer c=new Customer();
		c.setCname("john");
		int rs=cd.addCustomer(c);
		
		return rs;
	}
	@Rollback
	@Test
	public void testAdd() throws Exception {
		int rs=addCustomer();
		assertTrue(rs==1);
		
	}
	
	@Rollback
	@Test
	public void testDelete() throws Exception {
		int rs=addCustomer();
		int rs1=cd.deleteCustomer(rs);
		System.out.println(rs1);
		assertTrue(rs1>=1);
	}
}
