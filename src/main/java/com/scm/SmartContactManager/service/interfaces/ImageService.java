package com.scm.SmartContactManager.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile contactImage);
    String getUrlFromPublicId(String publicId);
}
