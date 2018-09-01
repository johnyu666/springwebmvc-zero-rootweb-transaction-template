package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pojo.Customer;

@Repository
public class CustoemrDaoImpl2 implements CustomerDao{
	@Autowired
	private JdbcTemplate template;
	@Transactional
	@Override
	public int addCustomer(Customer c) {
		String sql = "insert into customers(cname) values(?)";
		int rs=template.update(sql,new Object[] {c.getCname()});
		return rs;
	}
	@Override
	public int deleteCustomer(int id) {
		String sql="delete from customers where id=?";
		int rs=template.update(sql,new Object[] {id});
		return rs;
	}
	
}
