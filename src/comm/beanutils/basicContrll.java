package comm.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import comm.dbutils.user;

/**
 * @author DGW
 * @date 2017 2017年5月30日 下午2:44:45
 * @filename basicContrll.java @TODO
 */
public class basicContrll {
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Class cls = Class.forName("comm.dbutils.user");
		user instance = (user) cls.newInstance();
		// 自带时间转换器
		// ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// 自定义时间控制器
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class type, Object value) {
				// 当value参数等于空时返回空
				if (value == null) {
					return null;
				}
				// 自定义时间的格式为yyyy-MM-dd
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				// 创建日期类对象
				Date dt = null;
				try {
					// 使用自定义日期的格式转化value参数为yyyy-MM-dd格式
					dt = sdf.parse((String) value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 返回dt日期对象
				return dt;
			}
		}, Date.class);
		BeanUtils.setProperty(instance, "date", "1997-22-22");
		String string = BeanUtils.getProperty(instance, "date");
		System.out.println(string);
	}

}
