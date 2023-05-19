package br.com.renata.sheout.application.service;

import br.com.renata.sheout.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("${sheout.files.logo}")
    private String logoDir;

    @Value("${sheout.files.category}")
    private String categoriesDir;

    @Value("${sheout.files.clothe}")
    private String clothesDir;


    public void uploadLogo(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, logoDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public void uploadClothe(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, categoriesDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public byte[] getBytes(String type, String imgName) {

        try {
            String dir;

            if ("clothe".equals(type)) {
                dir = clothesDir;

            } else if ("logo".equals(type)) {
                dir = logoDir;

            } else if ("category".equals(type)) {
                dir = categoriesDir;

            } else {
                throw new Exception(type + "that is not a valid image ");
            }

            return IOUtils.getBytes(Paths.get(dir, imgName));

        } catch (Exception e) {
            throw new ApplicationServiceException(e);
        }
    }
}
