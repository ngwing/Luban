package top.zibin.luban;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Checker {
    private static List<String> format = new ArrayList<>();
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    private static final String PNG = "png";
    private static final String WEBP = "webp";
    private static final String GIF = "gif";

    static {
        format.add(JPG);
        format.add(JPEG);
        format.add(PNG);
        format.add(WEBP);
        format.add(GIF);
    }

    static boolean isImage(String path) {
        String suffix = getSuffix(path);
        return format.contains(suffix.toLowerCase());
    }

    static String getSuffix(String path) {
        String suffix = ".jpg";
        if (path == null)
            return suffix;
        int lastIndexOfDot = path.lastIndexOf(".");
        if (lastIndexOfDot != -1)
            suffix = path.substring(lastIndexOfDot + 1, path.length()).toLowerCase();
        return suffix;
    }

    static boolean isJPG(String path) {
        if (TextUtils.isEmpty(path))
            return false;

        String suffix = getSuffix(path);
        return suffix.contains(JPG) || suffix.contains(JPEG);
    }

    static String checkSuffix(String path) {

        return getSuffix(path);
    }

    static boolean needCompress(int leastCompressSize, String path) {
        if (leastCompressSize > 0) {
            File source = new File(path);
            if (!source.exists()) {
                return false;
            }

            if (source.length() <= (leastCompressSize << 10)) {
                return false;
            }
        }
        return true;
    }

}
