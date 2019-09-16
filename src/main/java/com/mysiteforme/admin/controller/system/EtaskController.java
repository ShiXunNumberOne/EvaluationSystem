package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.service.impl.EtaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EtaskController {
    @Autowired
    EtaskServiceImpl etaskService;
    @RequestMapping("/select")
    public void etask(){
        etaskService.etask();
    }
}
