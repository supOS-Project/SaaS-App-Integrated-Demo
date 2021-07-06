package com.bluetron.eco.sdk.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author caonuoqi@supos.com
 */
public class SuposConfig {
    private static String bossAppId;
    private static String bossSecretKey;
    private static String rsaPublicKey;
    private static  String bossBaseUrl;
    public static String getBossAppId() {
        return bossAppId;
    }

    public static String getBossSecretKey() {
        return bossSecretKey;
    }

    public static String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public static String getBossBaseUrl() {
        return bossBaseUrl;
    }

    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = SuposConfig.class.getClassLoader().getResourceAsStream("bluetron-saas-sdk.properties");
            properties.load(inputStream);
            rsaPublicKey = properties.getProperty("boss.rsa.public.key");
            bossAppId = properties.getProperty("boss.app.id");
            bossSecretKey = properties.getProperty("boss.secret.key");
            bossBaseUrl=properties.getProperty("boss.base.url");
            inputStream.close();
        } catch (Exception e) {
           throw new RuntimeException("请配置bluetron-saas-sdk.properties");
        }
    }
}
