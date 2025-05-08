package com.example.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class FileAtchController {

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) 
            throws IOException {
        File file = new File("c:/board/"+fileName);
        
        String contentType = Files.probeContentType(file.toPath());
        if(contentType == null || !contentType.startsWith("image/")) {
            contentType = "image/jpeg";
        }

        InputStreamResource resource = 
            new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                            .contentLength(file.length())
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(resource);
    }
    
}
