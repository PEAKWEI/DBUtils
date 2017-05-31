package my.test.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.junit.Test;

import com.sun.rowset.CachedRowSetImpl;

import my.dbutils.jdbc.DbUtils;

public class DbutilsDemo {
	
	@Test
	public void rowCollction() throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	CachedRowSet crt=new CachedRowSetImpl();
	crt.setUrl("jdbc:mysql://localhost:3306/wsy_student");
	crt.setUsername("root");
	crt.setPassword("123456");
	//通过command来执行命令
	crt.setCommand("select * from user");
	crt.execute();
	//遍历行集
	while (crt.next()) {
		System.out.println(crt.getString("username"));
	}
		
	}
	
	@Test
	public void exinsert() {
		Connection con = DbUtils.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("select * from user",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet executeQuery = ps.executeQuery();
			//移动游标的插入行
			executeQuery.moveToInsertRow();
			//使用updatexxx
			executeQuery.updateString(2, "use");
			//进行持久化操作
			executeQuery.insertRow();
		    //手动进行提交
			con.commit();
		} catch (SQLException e) {
			new Exception("出现了异常");
			e.printStackTrace();
		}
	
	}

}
