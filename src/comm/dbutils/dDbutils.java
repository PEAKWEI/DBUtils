package comm.dbutils;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import my.dbutils.jdbc.DbUtils;

public class dDbutils {
	public static void main(String[] args) throws SQLException {
		QueryRunner runner = DbUtils.getQueryRunner();
		ResultSetHandler<user> handler = new BeanHandler<user>(user.class);
		user user = runner.query("select * from user", handler);
		System.out.println(user.toString());
	}

}
