package com.learn.springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.MulticastChannel;

@Service
public interface FileService {

    String uploadImage(String path, MultipartFile file)throws IOException;

    InputStream getResource(String path,String fileName)throws IOException;

}
