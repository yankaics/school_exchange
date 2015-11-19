package test;

import com.schoolexchange.www.service.UserService;
import com.schoolexchange.www.service.impl.UserServiceImpl;
import com.schoolexchange.www.tools.BCrypt;

import java.text.ParseException;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        //加密测试
        String passwordHash = BCrypt.hashpw("123456", BCrypt.gensalt());
       /* boolean correct = BCrypt.checkpw("Hello", passwordHash);
        boolean wrong = BCrypt.checkpw("World", passwordHash);
        System.out.println(correct);*/
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.judge_password(passwordHash,"123456"));

    }

}
