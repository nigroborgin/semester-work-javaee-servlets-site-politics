package ru.kpfu.itis.shkalin.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUpDownUtil {

//    public void uploadFile(Part filePart, String uploadPath) {
//
//    }

    public void uploadFile(Part filePart, String uploadPath, String fileName) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        uploadPath += File.separator + fileName;
        InputStream content = filePart.getInputStream();
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        FileOutputStream outputStream = new FileOutputStream(uploadPath);
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
    }
}
