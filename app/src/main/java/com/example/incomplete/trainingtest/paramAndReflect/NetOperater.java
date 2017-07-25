package com.example.incomplete.trainingtest.paramAndReflect;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by incomplete on 17/7/21.
 */

public class NetOperater<E extends BaseParam, T extends BaseResult> {

    public E mParam;

    public Class<?> mResultClass;

    /**
     * 设置参数
     *
     * @param param
     * @return
     */
    public NetOperater<E, T> param(E param) {
        setParam(param);
        return this;
    }

    public void setParam(E param) {
        mParam = param;
    }

    private E getPatam() {
        return mParam;
    }

    public Map<String, Object> toParam() {
        Map<String, Object> resultMap = new HashMap<>();
        E paramBean = getPatam();
        if (null != paramBean) {
            Class<?> classzz = paramBean.getClass();
            Map<String, Object> mapp = new HashMap<>();
            try {
                resultMap = parseParam(classzz, mapp, paramBean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

        Log.e("param", resultMap.toString());
        return resultMap;

    }

    /**
     * @param c   参数类包括其父类
     * @param map 参数map
     * @param obj 参数类实例
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Map<String, Object> parseParam(Class<?> c, Map<String, Object> map, Object obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Param.class) != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                // 获取实际参数
                String key = field.getName();
                Object value = null;
//                if (mCheckParam) {
//                    long s = System.currentTimeMillis();
//                    value = checkParamField(field, obj);
//                    Log.e(TAG, "校验参数" + (System.currentTimeMillis() - s) + " ms");
//                } else {
//                    value = field.get(obj);
//                }
                value = field.get(obj);
                map.put(key, value);
            }
        }

        // 获取父类的参数
        while ((c = c.getSuperclass()) != null && !c.equals(Object.class)) {
            map = parseParam(c, map, obj);
        }
        return map;
    }


    interface SimpleCallback<T extends BaseResult> {
        void onResponse(T result);

        void onError();
    }

    public void setResultClass(Class<?> classzz) {
        this.mResultClass = classzz;

    }

}
