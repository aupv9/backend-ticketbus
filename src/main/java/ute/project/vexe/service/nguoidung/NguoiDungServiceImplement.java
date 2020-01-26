package ute.project.vexe.service.nguoidung;

import ute.project.vexe.model.NguoiDung;

import java.util.List;

public interface NguoiDungServiceImplement {
    List<NguoiDung> findAll();
    NguoiDung findById(int id);
    boolean insert(NguoiDung nguoiDung);
    boolean update(NguoiDung nguoiDung);
    boolean delete(int id);
    NguoiDung loadUserByUsername(String username);
    boolean checkLogin(NguoiDung user);
}
