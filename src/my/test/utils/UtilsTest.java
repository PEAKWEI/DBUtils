package my.test.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.CachedResultSetMetaData;
import com.sun.rowset.CachedRowSetImpl;
import my.dbutils.jdbc.DbUtils;

@SuppressWarnings("unused")
public class UtilsTest {
	public static void main(String[] args) throws SQLException {
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
