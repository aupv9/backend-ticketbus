package ute.project.vexe.service.xe;

import ute.project.vexe.model.Ghe;
import ute.project.vexe.model.Xe;
import ute.project.vexe.model.XeCanFind;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface XeServiceImplement {
    List<Xe> findAll();
    Xe findById(int id);
    List<XeCanFind> findByNoiDiAndNoiDenAndDate(String noidi, String noiden, String date) throws SQLException;
    boolean insert(Xe xe);
    boolean update(Xe xe);
    boolean delete(int id);
    boolean updateGhe(int idXe, Set<Integer> idGhes);

}
