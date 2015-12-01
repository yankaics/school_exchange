package test;


import java.text.ParseException;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException {

        String picName = "aa.jpg";
        System.out.println(picName.substring(picName.lastIndexOf('.')));
    }

}
