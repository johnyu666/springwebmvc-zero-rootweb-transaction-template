package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CustomerDao;
import pojo.Customer;

@Controller
public class CommonController {
	@Autowired
	@Qualifier(value="custoemrDaoImpl2")
	private CustomerDao cd;
	public CommonController() {
		System.out.println("common controller....");
	}
	@RequestMapping(value="login")
	public String login(Customer c) {
		cd.addCustomer(c);
		return "suc";
	}
}
