package ute.project.vexe.dao.tuyenduongdetail;

import ute.project.vexe.model.TuyenDuongDetail;

import java.util.List;

public interface TuyenDuongDetailDAOImplement {
    List<TuyenDuongDetail> findAll();
    TuyenDuongDetail findById(int id);
    boolean insert(TuyenDuongDetail tuyenDuongDetail);
    boolean update(TuyenDuongDetail tuyenDuongDetail);
    boolean delete(int id);
}
