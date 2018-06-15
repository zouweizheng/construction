package com.company.project.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZouWeizheng on 2017-12-06.
 */
public class MapToObjectUtil<T> {


  public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
      if (map == null)
          return null;
      Object obj = beanClass.newInstance();
      BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
          Method setter = property.getWriteMethod();
          if (setter != null) {
              setter.invoke(obj, map.get(property.getName()));
          }
      }
      return obj;
  }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }




    /**
     * 反射Mapper  数据对象
     * @param map
     * @param t
     * @return
     * @throws Exception
     */
    public static  <T>T  mapperObj(Map map,Class<T> t) throws Exception{
        if(map==null||map.size()==0){
            return t.newInstance();
        }
        Object tobj=t.newInstance();
        for(Object key:map.keySet()){
            Field field =t.getDeclaredField((String) key);
            field.setAccessible(true);
            field.set(tobj, map.get(key));
        }
        return (T) tobj;
    }
}
