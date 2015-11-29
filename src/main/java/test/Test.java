package test;

import com.schoolexchange.www.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by shadow on 2015/11/19.
 * Test类
 */
public class Test {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        sf.parse("1993-04-18");
    }

}
