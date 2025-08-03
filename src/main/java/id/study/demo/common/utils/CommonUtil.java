package id.study.demo.common.utils;

import java.util.UUID;

public class CommonUtil {
    public static String generateUUID(String prefix) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return prefix + uuid.substring(prefix.length());
    }

    private CommonUtil(){;}
}
