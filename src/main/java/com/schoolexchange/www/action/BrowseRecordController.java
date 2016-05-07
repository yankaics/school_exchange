package com.schoolexchange.www.action;

import com.schoolexchange.www.entity.BrowseRecords;
import com.schoolexchange.www.service.BrowseRecordService;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by shadow on 2016/5/5.
 */
@Controller
public class BrowseRecordController {

    @Autowired
    private BrowseRecordService browseRecordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/to_redirect_myBrowseRecord")
    public String toRedirectMyBrowseRecord(HttpServletRequest request, Model model) {
        String user_name = (String) request.getSession().getAttribute("sx_user_name");
        Integer userId = userService.getUserIdByUserName(user_name);
        List<BrowseRecords> list = browseRecordService.queryAllMyBrowseRecord(userId);
        model.addAttribute("list", list);
        return "myBrowseRecord";
    }
}
