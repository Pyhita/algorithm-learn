package com.yangtao.BinaryTree.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @Author: pyhita
 * @Date: 2022/3/5
 * @Descrption: com.yangtao.BinaryTree.file
 * @Version: 1.0
 */
public class Files {

    public static void writeToFile(String filePath, Object data) {
        writeToFile(filePath, data, false);
    }

    public static void writeToFile(String filePath, Object data, boolean append) {
        if (filePath == null || data == null) return;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file, append);
                 BufferedWriter out = new BufferedWriter(writer) ) {
                out.write(data.toString());
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
