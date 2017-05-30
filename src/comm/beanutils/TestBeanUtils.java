package comm.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;

import comm.dbutils.user;

/**
 * @author DGW
 * @date 2017 2017��5��30�� ����3:24:16
 * @filename TestBeanUtils.java @TODO
 */
public class TestBeanUtils {
	@Test
	public void demo() {
		user user = new user();
		try {
			BeanUtils.setProperty(user, "username", "����");
			//ת��Ϊmap
			Map<String, String> map = BeanUtils.describe(user);
			System.out.println(map.get("username"));
			//ִ�з���
			MethodUtils.invokeMethod(user, "speak", null);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
