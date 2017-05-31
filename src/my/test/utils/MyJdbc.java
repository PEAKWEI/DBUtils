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
		  // - 载入数据库驱动 到使用的数据库官方下载jar包驱动
		  try {
			Class.forName("com.mysql.jdbc.Driver");
		  // - 建立数据库连接，获取Connection对象
			Connection con = DriverManager.getConnection(url, user, password);
		  // - 获取statement 对象或者preparedstatement(编译sql)
		  Statement statement = con.createStatement();
		  CallableStatement call = con.prepareCall("{call user procedurename(?,?,?)}");
		  call.setString(1, "proce1");
		  call.setString(2, "proce2");
		  //有参数输出的执行过程
		  call.registerOutParameter(3, java.sql.Types.INTEGER);
		  call.execute();
		  //拿到输出结果
		  int int1 = call.getInt(3);
		  
	/*	  PreparedStatement ps = con.prepareStatement("insert into user(username,password) value(?,?)");
	 	  //记住下标是从1开始
		  ps.setString(1, "user1");
		  ps.setString(2, "pass");
		  ps.addBatch();
		  ps.executeBatch();*/
		  // - 根据SQL语句拿到拿到rusultset 进行操作
		ResultSet query = statement.executeQuery("select * from user");
			while (query.next()) {
				System.out.println("删除成功"+ query.getString("username"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		 
	}
}
