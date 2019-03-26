package com.fairytrip.data;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashPass {

    public String generateSaltKey(){
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        String saltKey = new String(array, Charset.forName("UTF-8"));
        return saltKey;
    }

    public String hashPassword(String password, String saltKey)throws NoSuchAlgorithmException, IOException {
        String encodedPassword = null;
        byte[] salt = base64ToByte(saltKey);
        int count = 5;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);

        byte[] btPass = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < count; i++) {
            digest.reset();
            btPass = digest.digest(btPass);
        }

        encodedPassword = byteToBase64(btPass);
        return encodedPassword;
    }

    public boolean checkPassword(String password, String saltKey, String hash1) throws NoSuchAlgorithmException, IOException{
        String hash2 = hashPassword(password, saltKey);

        if(hash1.equalsIgnoreCase(hash2)){
            return true;
        }
        else{
            return false;
        }
    }

    private byte[] base64ToByte(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] returnbyteArray = decoder.decodeBuffer(str);
        return returnbyteArray;
    }

    private String byteToBase64(byte[] bt) {
        BASE64Encoder encoder = new BASE64Encoder();
        String returnString = encoder.encode(bt);
        return returnString;
    }
}

