package queryworkday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-03-02 14:48
 */
public class SimpleDateFormatUtils {
    private static SimpleDateFormat formatCommon = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatCommonDay = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatCommonSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat formatCommonDateStr = new SimpleDateFormat("yyyyMMddHHmmss");

    public static SimpleDateFormat getFormatCommon() {
        return formatCommon;
    }

    public static SimpleDateFormat getFormatCommonDay() {
        return formatCommonDay;
    }

    public static SimpleDateFormat getFormatCommonSSS() {
        return formatCommonSSS;
    }

    public static SimpleDateFormat getFormatCommonDateStr() {
        return formatCommonDateStr;
    }

    /**
     * 将一种日期格式化转换成另外一种
     *
     * @param resultFormat
     * @param sourceFormat
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static String transferDateFormat(SimpleDateFormat resultFormat,
                                            SimpleDateFormat sourceFormat, String dateStr) throws ParseException {
        if (dateStr == null || "".equals(dateStr))
            return "";
        return resultFormat.format(sourceFormat.parse(dateStr));

    }

    /**
     * 将毫秒数直接格式化输出
     *
     * @param time
     * @param sdf
     * @return
     */
    public static String formatLong(Long time, SimpleDateFormat sdf) {
        try {
            Date date = new Date(time);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
