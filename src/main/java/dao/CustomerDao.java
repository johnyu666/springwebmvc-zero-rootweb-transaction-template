package dao;

import pojo.Customer;

public interface CustomerDao {
	public int addCustomer(Customer c);
	public int deleteCustomer(int id);
}
