package br.com.projeto.tutoria.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParamUtil {
    private ParamUtil() {
    }

    public static String objetoParaQueryParam(Object obj) {
        List<String> queryParam = new ArrayList<>();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (!field.isAccessible()) field.setAccessible(true);

                queryParam.add(field.getName() + "=" + field.get(obj));
            }
        } catch (Exception e) {
            return "";
        }

        return "?" + String.join("&", queryParam);
    }
}