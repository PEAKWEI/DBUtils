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
	//ͨ��command��ִ������
	crt.setCommand("select * from user");
	crt.execute();
	//�����м�
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
			//�ƶ��α�Ĳ�����
			executeQuery.moveToInsertRow();
			//ʹ��updatexxx
			executeQuery.updateString(2, "use");
			//���г־û�����
			executeQuery.insertRow();
		    //�ֶ������ύ
			con.commit();
		} catch (SQLException e) {
			new Exception("�������쳣");
			e.printStackTrace();
		}
	
	}

}
