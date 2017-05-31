package my.test.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbc {
	private static final String password = "123456";
	private static final String user = "root";
	private static final String url = "jdbc:mysql://localhost:3306/wsy_student";

	public static void main(String[] args) {
		  // - �������ݿ����� ��ʹ�õ����ݿ�ٷ�����jar������
		  try {
			Class.forName("com.mysql.jdbc.Driver");
		  // - �������ݿ����ӣ���ȡConnection����
			Connection con = DriverManager.getConnection(url, user, password);
		  // - ��ȡstatement �������preparedstatement(����sql)
		  Statement statement = con.createStatement();
		  CallableStatement call = con.prepareCall("{call user procedurename(?,?,?)}");
		  call.setString(1, "proce1");
		  call.setString(2, "proce2");
		  //�в��������ִ�й���
		  call.registerOutParameter(3, java.sql.Types.INTEGER);
		  call.execute();
		  //�õ�������
		  int int1 = call.getInt(3);
		  
	/*	  PreparedStatement ps = con.prepareStatement("insert into user(username,password) value(?,?)");
	 	  //��ס�±��Ǵ�1��ʼ
		  ps.setString(1, "user1");
		  ps.setString(2, "pass");
		  ps.addBatch();
		  ps.executeBatch();*/
		  // - ����SQL����õ��õ�rusultset ���в���
		ResultSet query = statement.executeQuery("select * from user");
			while (query.next()) {
				System.out.println("ɾ���ɹ�"+ query.getString("username"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		 
	}
}
