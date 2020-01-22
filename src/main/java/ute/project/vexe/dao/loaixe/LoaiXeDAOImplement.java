package ute.project.vexe.dao.loaixe;

import ute.project.vexe.model.LoaiXe;

import java.util.List;

public interface LoaiXeDAOImplement {
    List<LoaiXe> findAll();
    LoaiXe findById(int id);
    boolean insert(LoaiXe loaiXe);
    boolean update(LoaiXe loaiXe);
    boolean delete(int id);
}
