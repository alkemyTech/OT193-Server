package com.alkemy.somosmas.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.alkemy.somosmas.services.AmazonClient;




@RestController
@ControllerAdvice
@RequestMapping("/storage/")
public class BucketController {
    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

   @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {

     
            try {
                return this.amazonClient.uploadFile(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "ocurrio un error en el upload del archivo"+ e.getMessage();
            }catch(Exception e){
                e.printStackTrace();
                return "ocurrio un error general: "+e.getMessage();
            }          
   
       

    }
    
  
   @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }


    @ExceptionHandler
    public String  handleFileUploadSize(RuntimeException e){
        System.out.println(e);
        return "se presento un error al cargar el archivo: "+e.getMessage();
    }

}
