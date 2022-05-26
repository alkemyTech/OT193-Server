package com.alkemy.somosmas.utils;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class ImageHelper {
    private Base64MultipartFile base64MultipartFile;
    public Base64MultipartFile base64ToImage(String encodedBase64, String fileName) throws  IOException {
		
        try {
             
            if(encodedBase64.equals(null)||fileName.equals(null)){
                throw new IllegalStateException("No se ingresaron los datos completos");
            }else{String encodeImg = encodedBase64.substring(encodedBase64.indexOf(",") + 1);
            byte[] decodedBytes = Base64.decodeBase64(encodeImg);
             base64MultipartFile = new Base64MultipartFile(decodedBytes, fileName);
            base64MultipartFile.transferTo(base64MultipartFile.getFile());}
            
        } catch (IllegalStateException e) {
            throw new IllegalStateException("IllegalStateException : " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64MultipartFile;

    }
}
