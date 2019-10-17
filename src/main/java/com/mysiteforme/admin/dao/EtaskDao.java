package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Etask;

import java.util.Map;

public interface EtaskDao extends BaseMapper<Etask> {
    Etask selectEtaskByMap(Map<String, Object> map);

    Etask findEtaskById(int id);
    int updataEtaskById(Etask etask);
    int deleteEtaskById(int id);
    int openEtaskById(int id);
    int closeEtaskById(int id);
    int suspendEtaskById(int id);
    int endEtaskById(int id);
    int onoroffEtaskById(int id);
    int SelectOpen();  //     查询处在开启状态的批次数量
}
