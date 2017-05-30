package my.dbutils.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author DGW
 * @date 2017 2017年5月30日 下午1:42:52
 * @filename DbUtils.java  @TODO
 */
public class DbUtils {
	private static Connection con;
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static DataSource dataSource;

	static {
		try {
			readInfo();// 读配置
			Class.forName(driver);// 读驱动
			dataSource=new ComboPooledDataSource();//构造C3p0数据源
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("数据库连接失败！");
		}

	}

	/*
	 * 取得连接
	 * 
	 */
	public static Connection getConnection() {
		return con;
	}

	/*
	 * 拿到commos DButils重要类 
	 */
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
	/*
	 * 释放资源
	 * 
	 * @parm PreparedStatement
	 * 
	 * @parm Connection
	 */
	public static void close(PreparedStatement pSta, Connection con) {
		try {
			if (pSta != null) {
				pSta.close();
				pSta = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("连接没有关闭");
		} finally {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rsSet, PreparedStatement pSta, Connection con) {
		try {
			if (rsSet != null) {
				rsSet.close();
				rsSet = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pSta != null) {
					pSta.close();
					pSta = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
						con = null;
					}

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	private static void readInfo() throws IOException {
		InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("dbutils.properties");
		Properties pro = new Properties();
		pro.load(in);
		url = pro.getProperty("url");
		driver = pro.getProperty("driver");
		username = pro.getProperty("username");
		password = pro.getProperty("password");
		in.close();

	}

}
