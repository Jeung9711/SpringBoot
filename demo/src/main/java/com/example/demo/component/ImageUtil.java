package com.example.demo.component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component("ImageUtil2")
public class ImageUtil {
    public ImageUtil save(String path) throws IOException{
        URL url = null;
        url = new URL(path);

        String fileName = path.substring(path.lastIndexOf("/")+1);
        String fileExt = path.substring(path.lastIndexOf(".")+1);

        BufferedImage img = ImageIO.read(url);
        ImageIO.write(img,fileExt, new File(fileName));
        return null;
    }
}
