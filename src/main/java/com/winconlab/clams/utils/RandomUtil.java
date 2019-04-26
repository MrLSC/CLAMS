package com.winconlab.clams.utils;

import java.util.Random;

public class RandomUtil {
    public static String getRandomString(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 随机生产 factor 位的数字，最大不超过 19位，因为long的最大值为19位
     * @param factor
     * @return
     */
    public static Long randomNum(int factor){
        return new Double((Math.random() + 1) * Math.pow(10, factor - 1)).longValue();
    }



}
