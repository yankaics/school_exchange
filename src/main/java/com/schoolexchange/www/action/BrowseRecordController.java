package com.schoolexchange.www.action;

import com.schoolexchange.www.service.BrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shadow on 2016/5/5.
 *
 */
@Controller
public class BrowseRecordController {

    @Autowired
    private BrowseRecordService browseRecordService;

    @RequestMapping(value = "/to_redirect_myBrowseRecord")
    public String toRedirectMyBrowseRecord(){

        return "myBrowseRecord";
    }
}
