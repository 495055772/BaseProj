package com.projectzero.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * json工具类<br>
 * http://wangym.iteye.com/blog/738933<br>
 * http://www.cnblogs.com/windlaughing/p/3241776.html<br>
 * <br>
 * fastjson有bug 属性名首字母小写但第二个字母大写的情况会无法解析javabean属性丢失 https://github.com/alibaba/fastjson/pull/106 <br>
 * <br>
 * 性能考虑，使用jackon
 *
 * 
 */
public class JsonUtil {

    private static ObjectMapper mapper; // create once, reuse

    static {
        mapper = new ObjectMapper();
        // http://wiki.fasterxml.com/JacksonHowToIgnoreUnknown?highlight=%28FAIL%5C_ON%5C_UNKNOWN%5C_PROPERTIES%29
        // 忽略json中多余的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T parseObject(String jsonStr, Class<T> entityClass) {
        T ret = null;

        try {
            ret = mapper.readValue(jsonStr, entityClass);
        } catch (Exception e) {
            DevUtil.e("will", "something Exception with:" + e.toString());
            e.printStackTrace();
        }

        return ret;
    }

    public static Object parseTemplateObject(String jsonStr, Class<?> outerClass, Class<?> templateClass) {
        Object obj = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(outerClass, templateClass);
            obj = mapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> ArrayList<T> parseList(String jsonStr, Class<T> entityClass) {
        ArrayList<T> ret = null;

        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, entityClass);
            ret = mapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static <K, V> HashMap<K, V> parseMap(String jsonStr, Class<K> keyClass, Class<V> valueClass) {
        HashMap<K, V> ret = null;

        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
            ret = mapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String toJSONString(Object ojb) {
        String ret = null;

        try {
            ret = mapper.writeValueAsString(ojb);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
