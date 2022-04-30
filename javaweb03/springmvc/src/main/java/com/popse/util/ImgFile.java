package com.popse.util;

import java.io.File;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/26 21:23
 * @Version : 1.0
 **/
public class ImgFile {
    public static final String IMG_PATH = "D:\\Desktop\\Project\\javaweb\\javaweb03\\upload\\";

    public static void deleteImg(File file) {
        if(!file.exists()) return;
        if(file.isFile() || file.list() == null)
            file.delete();
        else {
            File []files = file.listFiles();
            for(File f : files) {
                deleteImg(f);
            }
            file.delete();
        }
    }
}
