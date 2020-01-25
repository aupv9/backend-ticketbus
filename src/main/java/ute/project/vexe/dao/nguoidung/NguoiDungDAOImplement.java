package ute.project.vexe.dao.nguoidung;

import ute.project.vexe.model.NguoiDung;

import java.util.List;

public interface NguoiDungDAOImplement {
    List<NguoiDung> findAll();
    NguoiDung findById(int id);
    boolean insert(NguoiDung nguoiDung);
    boolean update(NguoiDung nguoiDung);
    boolean delete(int id);
}
