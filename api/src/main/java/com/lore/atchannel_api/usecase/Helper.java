package com.lore.atchannel_api.usecase;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, String> NewHttpResponse(String key, String value) {
        Map<String, String> res = new HashMap<>();
        res.put(key, value);
        return res;
    }
    
}
