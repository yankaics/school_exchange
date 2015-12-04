package test;


import java.text.ParseException;
import java.util.Date;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException, InterruptedException {

        boolean flag = true;
        System.out.println(Boolean.toString(flag));
    }

    /**
     * 最新日期与原始日期差值是否超过1分钟
     *
     * @param oldDate 原始日期
     * @param newDate 最新日期
     * @return 返回true表示超过，否则不超过
     */
    public boolean dataDifference(Date oldDate, Date newDate) {
        boolean flag = false;
        long diff = newDate.getTime() - oldDate.getTime();
        if (diff > 60000) {
            flag = true;
        }
        return flag;
    }

}
