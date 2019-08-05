package online.cangjie.comumit.utils;

import com.alibaba.fastjson.JSONObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONUtil {
    public static <T> T jsonToObject(T t, JSONObject json) {
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[i].getName(), clazz);
                Method method = propertyDescriptor.getWriteMethod();
                switch (fields[i].getType().toString()) {
                    case "class java.lang.String":
                        method.invoke(t, json.getString(fields[i].getName()));
                        break;
                    case "class java.lang.Long":
                        method.invoke(t, json.getLong(fields[i].getName()));
                        break;
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return t;
    }
}
