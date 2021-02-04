/**
 * Copyright (C), 2020-2020,贵州铭明网络科技有限公司
 * FileName: AesUtil
 * Author:   杨朝湖
 * Date:     2020/12/18 11:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangya.v3.v2;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Gym
 * @create 2020/12/18
 * @since 1.0.0
 */
public class AesUtil {
    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;
    private final byte[] aesKey;

    /**
     * @param key APIv3 密钥
     */
    public AesUtil(byte[] key) {
        if (key.length != KEY_LENGTH_BYTE) {
            throw new IllegalArgumentException("无效的ApiV3Key，长度必须为32个字节");
        }
        this.aesKey = key;
    }

    /**
     * 证书和回调报文解密
     *
     * @param associatedData associated_data
     * @param nonce          nonce
     * @param cipherText     ciphertext
     * @return {String} 平台证书明文
     * @throws GeneralSecurityException 异常
     */
    public String decryptToString(byte[] associatedData, byte[] nonce, String cipherText) throws GeneralSecurityException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);

            return new String(cipher.doFinal(Base64.decode(cipherText)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
