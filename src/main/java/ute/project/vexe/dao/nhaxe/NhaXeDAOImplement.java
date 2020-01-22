package ute.project.vexe.dao.nhaxe;

import ute.project.vexe.model.NhaXe;

import java.util.List;

public interface NhaXeDAOImplement {
    List<NhaXe> findAll();
    NhaXe findById(int id);
    boolean insert(NhaXe loaiXe);
    boolean update(NhaXe loaiXe);
    boolean delete(int id);
}
