package ute.project.vexe.service.tuyenduong;

import ute.project.vexe.model.TuyenDuong;

import java.util.List;

public interface TuyenDuongServiceImplment {
    List<TuyenDuong> findAll();
    TuyenDuong findById(int id);
    boolean insert(TuyenDuong tuyenDuongDetail);
    boolean update(TuyenDuong tuyenDuongDetail);
    boolean delete(int id);
}
