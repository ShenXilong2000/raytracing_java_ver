package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author sxl
 * @Date 2024/5/30 16:24
 **/
public class DateUtils {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }
}
