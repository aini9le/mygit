package com.lii.cloud.common.base.tools;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import net.minidev.json.JSONObject;

/**
 * 描述：token 测试
 *
 * @Author: xiaoyong
 * @Date: 2018-12-07
 */
public class JavaWebToken {

    /**
     * 1.创建一个32-byte的密匙
     */

    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();


    //生成一个token
    public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {

        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);

        //签名
        jwsObject.sign(jwsSigner);

        //生成token
        return jwsObject.serialize();
    }


    /**
     * 解密 token
     * @param token
     * @return
     */
    public static JSONObject EnToken(String token) {
        try {
            //        解析token
            JWSObject jwsObject = JWSObject.parse(token);
            //获取到载荷
            Payload payload = jwsObject.getPayload();
            //建立一个解锁密匙
            JWSVerifier jwsVerifier = new MACVerifier(secret);
            //判断token
            if (jwsObject.verify(jwsVerifier)) {
                System.out.println("载荷数据=" + payload.toString());
                return payload.toJSONObject();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("access","105997");
            String token = creatToken(map);
            System.out.println("token=" + token);
            EnToken(token);
//            JWTClaimsSet.
        } catch (JOSEException e) {
            e.printStackTrace();
        }

    }
}
