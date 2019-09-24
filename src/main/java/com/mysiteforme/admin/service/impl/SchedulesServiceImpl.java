package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.SchedulesDao;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.VO.Schedules;
import com.mysiteforme.admin.entity.VO.SchedulesVO;
import com.mysiteforme.admin.service.SchedulesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchedulesServiceImpl extends ServiceImpl<SchedulesDao, Schedules> implements SchedulesService {
    @Override
    public Page<SchedulesVO> queryTeachOutMapByPage(Page<SchedulesVO> page) {
        return page.setRecords(this.baseMapper.selectTeachOutMap(page));
    }

    @Override
    public Schedules saveSchedules(Schedules schedules) {
        baseMapper.insert(schedules);
        return  findSchedulesById(schedules.getId());
    }

    @Override
    public Schedules findSchedulesById(int id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("id", id);
        Schedules s = baseMapper.selectSchedulesByMap(map);
        return s;
    }

    @Override
    public List<Schedules> selectAll() {
        EntityWrapper<Schedules> wrapper = new EntityWrapper<>();
        List<Schedules> schedulesList = baseMapper.selectList(wrapper);
        return schedulesList;
    }

    @Override
    public int updataSchedulesById(Schedules schedules) {
        int s = baseMapper.updataSchedulesById(schedules);
        return s;
    }

    @Override
    public int deleteSchedulesById(int id) {
        int c = baseMapper.deleteSchedulesById(id);
        return c;
    }
}
