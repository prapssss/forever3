package com.forever3.util;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;

public class ImageUtil {

    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String imageName = null;

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        if (imageName == null || imageName.isEmpty()) {
            imageName = "download.png";
        }

        return imageName;
    }

    public String uploadImage(Part part, String rootPath, String saveFolder) {
        String physicalPath = getPhysicalPath(rootPath, saveFolder);
        File fileSaveDir = new File(physicalPath);

        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdirs()) {
                return null;
            }
        }

        try {
            String imageName = getImageNameFromPart(part);
            String filePath = physicalPath + File.separator + imageName;
            part.write(filePath);
            return imageName; // ✅ Only store file name in DB
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getPhysicalPath(String rootPath, String saveFolder) {
        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        return rootPath + "resources" + File.separator + "images" + File.separator + saveFolder;
    }
}
