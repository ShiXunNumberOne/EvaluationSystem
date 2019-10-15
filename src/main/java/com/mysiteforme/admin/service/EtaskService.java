package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Etask;

import java.util.List;

public interface EtaskService extends IService<Etask> {

    Etask saveEtask(Etask etask);
    Etask findEtaskById(int id);
    List<Etask> selectAll();
    int updataEtaskById(Etask etask);
    int onoroffEtaskById(int id);
    int deleteEtaskById(int id);
    int openEtaskById(int id);
    int suspendEtaskById(int id);
    int endEtaskById(int id);
    int closeEtaskById(int id);
    int SelectOpen();
}
