package com.winconlab.clams.utils;

/**
 * 字符通用工具类
 */

public class CharUtil {

    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     *
     * @param c

     * @return

     */

    public static boolean isChineseCharacter(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {

            return true;

        }

        return false;

    }


    /**
     * 完整的判断中文汉字和符号
     *
     * @param strName

     * @return

     */

    public static boolean isContainsChinese(String strName) {

        char[] ch = strName.toCharArray();

        for (int i = 0; i < ch.length; i++) {

            char c = ch[i];

            if (isChineseCharacter(c)) {

                return true;

            }

        }

        return false;

    }

}
