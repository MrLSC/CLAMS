package com.winconlab.clams.utils;

import com.li.jasypt.JasyptEncryptUtil;
import org.jasypt.encryption.StringEncryptor;

public class JasyptEncrypt implements StringEncryptor {

    //加密
    @Override
    public String encrypt(String s) {
        return JasyptEncryptUtil.encrypt("", s);
    }

    //解密
    @Override
    public String decrypt(String s) {
        return JasyptEncryptUtil.decrypt(s);
    }
}
