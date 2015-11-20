package test;

import java.text.ParseException;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < 6 ; i ++){
            sb.append((int)(Math.random()*9 + 1));
        }
        System.out.println("找回");

    }

}
