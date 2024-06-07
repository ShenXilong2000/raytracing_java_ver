package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author sxl
 * @Date 2024/5/30 16:17
 **/
public class FileUtils {

    public static void saveFileToPPM(String fileContent) {
        String fileName = "image" + DateUtils.getDate();
        saveFileToPPM(fileName, fileContent);
    }

    public static void saveFileToPPM(String filename, String fileContent) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".ppm"));
            writer.write(fileContent);
            writer.close();
            System.out.println("String has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void saveFileToJPG(BufferedImage image) {
        String fileName = "image" + DateUtils.getDate();
        saveFileToJPG(fileName, image);
    }


    public static void saveFileToJPG(String fileName, BufferedImage image) {
        File file = new File("images/" + fileName + ".bmp");
        // 将图像写入文件
        try {
            ImageIO.write(image, "BMP", file);
            System.out.println("Image has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the image.");
            e.printStackTrace();
        }
    }

}
