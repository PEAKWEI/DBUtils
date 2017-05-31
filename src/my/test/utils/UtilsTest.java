package my.test.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.junit.Test;

import com.sun.rowset.CachedRowSetImpl;

import my.dbutils.jdbc.DbUtils;

public class UtilsTest {
	
	@Test
	public void testRowSet() throws SQLException {
		CachedRowSet rowset = DbUtils.getCacheRowset();
		rowset.setCommand("select * from user");
		rowset.execute();
		while (rowset.next()) {
			System.out.println(rowset.getString("username"));
		}
	}
	
	
	public static void m(String[] args) throws SQLException {
		// 利用行集进行查询
		/*
		 * CachedRowSet cachedata=new CachedRowSetImpl();
		 * cachedata.setUsername(name); cachedata.setPassword(password);
		 * cachedata.setCommand(cmd); cachedata.execute(); cachedata
		 */
		// 普通查询
		Connection connection = DbUtils.getConnection();
		String sql = "SELECT name,phoneNum,adress from student";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet query = statement.executeQuery();
		// 创建缓存
		CachedRowSet rowset = new CachedRowSetImpl();
		rowset.populate(query);
		DbUtils.close(statement, connection);
		// 再一次遍历测试
		while (rowset.next()) {
			System.out.print(rowset.getString("name") + ' ');
			System.out.print(rowset.getInt("phoneNum") + ' ');
			System.out.println(rowset.getString("adress"));
		}

	}

	private static void insert() throws SQLException {
		Connection connection = DbUtils.getConnection();
		String sql = "INSERT INTO student(name,phoneNum,adress) VALUE(?,?,?) ;";
		PreparedStatement statement = connection.prepareStatement(sql);
		int i = 1;
		while (i <= 1000) {
			statement.setString(1, "test" + i);
			statement.setInt(2, i);
			statement.setString(3, "daihao" + i);
			statement.addBatch();
			statement.executeBatch();
			++i;
		}

		DbUtils.close(statement, connection);
	}

}
