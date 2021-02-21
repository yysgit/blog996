package com.yys.szcp.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class ToolUtils {
    public static <T> Predicate<T> distinctByField(Function<? super T,Object> field){
        Map<Object,Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(field.apply(t),Boolean.TRUE)==null;
    }
}
