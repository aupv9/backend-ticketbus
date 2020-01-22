package ute.project.vexe.service.loaixe;

import ute.project.vexe.model.LoaiXe;

import java.util.List;

public interface LoaiXeServiceImplement {
    List<LoaiXe> findAll();
    LoaiXe findById(int id);
    boolean insert(LoaiXe loaiXe);
    boolean update(LoaiXe loaiXe);
    boolean delete(int id);
}
