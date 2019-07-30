package com.zb.util;

public class RedisName {
    public static String t1 = "t{getAll}";

    public static String B(int id){
        String name ="b{type_id:"+id+"}";
        return name;
    }

    public static String b8 = "b{getAll}";


}
