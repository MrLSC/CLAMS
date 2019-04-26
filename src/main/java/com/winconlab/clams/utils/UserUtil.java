package com.winconlab.clams.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.text.DecimalFormat;
import java.util.Random;

public class UserUtil {

    /**
     * 生成用户密码加密时的随机盐
     *
     * @return
     */
    public static String generatePasswordSalt() {
        return RandomUtil.getRandomString(new Random().nextInt(4) + 4);
    }

    /**
     * 返回6位数的用户数字账号
     *
     * @param user_count
     * @return
     */
    public static String generateUserCode(int user_count) {
        DecimalFormat df = new DecimalFormat("000000");
        return df.format(user_count + 1);
    }

    /**
     * 生成用户密码
     *
     * @param userPassword
     * @param salt
     * @return
     */
    public static String generatePassword(String userPassword, String salt) {
//        return new Md5Hash(userPassword, ByteSource.Util.bytes(salt), 2).toString();
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource s = ByteSource.Util.bytes(salt);
        int hashIterations = 2;//加密2次
        return new SimpleHash(hashAlgorithmName, userPassword, s, hashIterations).toString();
    }

    /**
     * 校验用户密码
     *
     * @param userPassword
     * @param salt
     * @param dbPassword
     * @return
     */
    public static boolean verifyUserPassword(String userPassword, String salt, String dbPassword) {
        return generatePassword(userPassword, salt).equals(dbPassword);
    }
}
