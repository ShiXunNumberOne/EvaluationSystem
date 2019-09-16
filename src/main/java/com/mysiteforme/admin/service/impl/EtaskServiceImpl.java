package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.dao.EtaskDao;
import com.mysiteforme.admin.service.EtaskService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtaskServiceImpl implements EtaskService {
    @Autowired
    EtaskDao etaskDao;
    @Override
    public void etask() {
        etaskDao.EtaskAll();
    }
}
