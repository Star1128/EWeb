package com.ethan.eweb.prop;

import com.ethan.eweb.EWebApplication;

/**
 * @author Ethan 2023/1/7
 */
public class BaseProp {

    public static int COUNT_IMGS = 10;
    public static int MAX_LIST_ITEMS_VIEW = 10000;
    public static int MAX_LIST_ITEMS_COMMENT = 100;

    public static String UPLOAD_PATH = "upload/";

    public static String getPath() {
        String path = EWebApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        if (path.startsWith("/")) {
            path = path.replaceFirst("/", "");
        }
        System.out.println("path -- > " + path);
        String resultPath = path.replace("file:/", "").replace("EWeb.jar!/BOOT-INF/classes!/", "");
        System.out.println("resultPath -- > " + resultPath);
        return resultPath;
    }

}
