package com.company.project.common.util;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

import static org.springframework.boot.autoconfigure.web.HttpEncodingProperties.DEFAULT_CHARSET;

/**
 * Created by Administrator on 2017/7/4.
 */
public class EncryptAndDecryptUtil {

    private static Logger logger = Logger.getLogger(EncryptAndDecryptUtil.class);
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public static String encryptDes(String data, String key, String iv){
        try {
            logger.info("data:"+data+"key:"+key+"iv:"+iv);
            DESKeySpec dks = new DESKeySpec(key.getBytes(DEFAULT_CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key secretKey = keyFactory.generateSecret(dks);
            logger.info("secretKey:"+secretKey);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv.getBytes(DEFAULT_CHARSET)));
            byte[] encryptedData = cipher.doFinal(data.getBytes(DEFAULT_CHARSET));
            logger.info("encryptedData:"+encryptedData);
            String encryptDesData = new BASE64Encoder().encode(encryptedData);
            logger.info("encryptDes:" + encryptDesData);
            return encryptDesData;
        } catch (Exception e) {
            logger.error("data:"+data+"key:"+key);
            return "";
        }
    }

   /* public static String decryptDes(String data, String key, String iv){
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes(DEFAULT_CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv.getBytes(DEFAULT_CHARSET)));
            byte decryptedData[] = cipher.doFinal(Encodes.decodeBase64(data));
            return new String(decryptedData, DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("decryptDes  data:{}  key:{}  msg:{}", data, key,e.getMessage());
            return "";
        }
    }*/

    public static String decryptDes(String data, String key, String iv){
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes(DEFAULT_CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv.getBytes(DEFAULT_CHARSET)));
            byte[] encryptDesData = new BASE64Decoder().decodeBuffer(data);
            byte decryptedData[] = cipher.doFinal(encryptDesData);
            return new String(decryptedData, DEFAULT_CHARSET);
        } catch (Exception e) {
            return "";
        }
    }







}
