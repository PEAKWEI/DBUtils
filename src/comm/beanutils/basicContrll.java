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
 * @date 2017 2017��5��30�� ����2:44:45
 * @filename basicContrll.java @TODO
 */
public class basicContrll {
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Class cls = Class.forName("comm.dbutils.user");
		user instance = (user) cls.newInstance();
		// �Դ�ʱ��ת����
		// ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// �Զ���ʱ�������
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(Class type, Object value) {
				// ��value�������ڿ�ʱ���ؿ�
				if (value == null) {
					return null;
				}
				// �Զ���ʱ��ĸ�ʽΪyyyy-MM-dd
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				// �������������
				Date dt = null;
				try {
					// ʹ���Զ������ڵĸ�ʽת��value����Ϊyyyy-MM-dd��ʽ
					dt = sdf.parse((String) value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// ����dt���ڶ���
				return dt;
			}
		}, Date.class);
		BeanUtils.setProperty(instance, "date", "1997-22-22");
		String string = BeanUtils.getProperty(instance, "date");
		System.out.println(string);
	}

}
