package ute.project.vexe.service.nhaxe;

import ute.project.vexe.model.NhaXe;

import java.util.List;

public interface NhaXeServiceImplement {
    List<NhaXe> findAll();
    NhaXe findById(int id);
    boolean insert(NhaXe loaiXe);
    boolean update(NhaXe loaiXe);
    boolean delete(int id);
}
