package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.VO.Schedules;
import com.mysiteforme.admin.entity.VO.SchedulesVO;

import java.util.HashMap;
import java.util.List;

public interface SchedulesService {
    Page<SchedulesVO> queryTeachOutMapByPage(Page<SchedulesVO> page);
    Schedules saveSchedules(Schedules schedules);
    Schedules findSchedulesById(int id);
    List<Schedules> selectAll();
    int updataSchedulesById(Schedules schedules);
    int deleteSchedulesById(int id);
}
