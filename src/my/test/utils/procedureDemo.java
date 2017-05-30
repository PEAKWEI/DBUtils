package my.test.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

import my.dbutils.jdbc.DbUtils;

/**
 * @author DGW
 * @date 2017 2017年5月29日 下午8:31:11
 * @filename procedureDemo.java  @TODO
 */
public class procedureDemo {
		public static void main(String[] args) {
			/*
			 * 存储过程的调用方法
			 */
			Connection con = DbUtils.getConnection();
			try {
				CallableStatement call = con.prepareCall("call selectPRO");
				String str = "";
				call.registerOutParameter(str, Type.INTERNAL);
				call.execute();
				String string = call.getString(str);
				System.out.println(string);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*try {
				DatabaseMetaData data = con.getMetaData();
				ResultSet resultSet = data.getProcedures(null, null, "%");
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

}
