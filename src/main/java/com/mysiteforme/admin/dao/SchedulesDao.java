package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.VO.Schedules;
import com.mysiteforme.admin.entity.VO.SchedulesVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SchedulesDao extends BaseMapper<Schedules> {

    List<SchedulesVO> selectTeachOutMap(Pagination page);
    Schedules selectSchedulesByMap(Map<String, Object> map);
    Schedules findSchedulesById(int id);
    int updataSchedulesById(Schedules schedules);
    int deleteSchedulesById(int id);
}
