package com.uff.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.uff.CSVDaoException;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class ReflectionHelper {

    public static Class<?> createClass(String className) {
        try {
            return (Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new CSVDaoException("problème de creation Class ", e);
        }
    }

    public static Object createInstance(String className) {
        return createInstance(createClass(className));
    }

    public static Object createInstance(Class<?> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new CSVDaoException("problème d'une nouvelle instance ", e);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("problème d'une nouvelle instance ", e);
        }
    }


    public static Object getProperty(Object o, String property) {
        try {
            Method setter = findGetter(property, o);
            return setter.invoke(o);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("problème d'invoke getter " + property, e);
        } catch (InvocationTargetException e) {
            throw new CSVDaoException("problème d'invoke getter " + property, e);
        }

    }

    public static void setProperty(Object o, String property, Object value) {
        try {
            Method setter = findSetter(property, o);
            setter.invoke(o, value);
        } catch (IllegalAccessException e) {
            throw new CSVDaoException("problème d'invoke setter" + property, e);
        } catch (InvocationTargetException e) {
            throw new CSVDaoException("problème d'invoke setter " + property, e);
        }
    }


    public static Class getType(Class clazz, String propertyName) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(propertyName)) {
                    return descriptor.getPropertyType();
                }

            }
            throw new CSVDaoException("property  " + propertyName + " introuvable dans la classe " + clazz);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("problème 'get class' pour  'property' " + propertyName, e);
        }
    }


    private static Method findGetter(String name, Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(name)) {
                    return descriptor.getReadMethod();
                }

            }
            throw new CSVDaoException("property  " + name + " introuvable dans la classe " + object);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("problème de 'read method' pour la 'property' " + name, e);
        }
    }

    private static Method findSetter(String name, Object object) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getName().equals(name)) {
                    return descriptor.getWriteMethod();
                }

            }
            throw new CSVDaoException("property  " + name + " introuvable dans la classe " + object);

        } catch (IntrospectionException e) {
            throw new CSVDaoException("problème de 'read method' pour la 'property' " + name, e);
        }
    }


}
