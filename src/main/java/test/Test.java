package test;

import com.schoolexchange.www.tools.BCrypt;

import java.text.ParseException;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        //加密测试
        String passwordHash = BCrypt.hashpw("Hello", BCrypt.gensalt());
        boolean correct = BCrypt.checkpw("Hello", passwordHash);
        boolean wrong = BCrypt.checkpw("World", passwordHash);
        System.out.println(correct);
    }

}
