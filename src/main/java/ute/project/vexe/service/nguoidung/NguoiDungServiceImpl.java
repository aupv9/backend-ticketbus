package ute.project.vexe.service.nguoidung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.dao.nguoidung.NguoiDungDAOImpl;
import ute.project.vexe.model.NguoiDung;

import java.util.List;

@Service
public class NguoiDungServiceImpl implements  NguoiDungServiceImplement {

    private NguoiDungDAOImpl nguoiDungDAO;

    @Autowired
    public NguoiDungServiceImpl(NguoiDungDAOImpl nguoiDungDAO) {
        this.nguoiDungDAO = nguoiDungDAO;
    }

    @Override
    public List<NguoiDung> findAll() {
        return this.nguoiDungDAO.findAll();
    }

    @Override
    public NguoiDung findById(int id) {
        return this.nguoiDungDAO.findById(id);
    }

    @Override
    public boolean insert(NguoiDung nguoiDung) {
        return this.nguoiDungDAO.insert(nguoiDung);
    }

    @Override
    public boolean update(NguoiDung nguoiDung) {
        return this.nguoiDungDAO.update(nguoiDung);
    }

    @Override
    public boolean delete(int id) {
        return this.nguoiDungDAO.delete(id);
    }
}
