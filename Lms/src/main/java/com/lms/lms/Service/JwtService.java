package com.lms.lms.Service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(SecretKey secretKey) {
        this.secretKey = secretKey;
        try{
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
