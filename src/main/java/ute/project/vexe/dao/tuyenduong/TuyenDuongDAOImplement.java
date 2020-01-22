package ute.project.vexe.dao.tuyenduong;

import ute.project.vexe.model.TuyenDuong;

import java.util.List;

public interface TuyenDuongDAOImplement {
    List<TuyenDuong> findAll();
    TuyenDuong findById(int id);
    boolean insert(TuyenDuong tuyenDuong);
    boolean update(TuyenDuong tuyenDuong);
    boolean delete(int id);
}
