package ute.project.vexe.service.tuyenduong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.dao.tuyenduong.TuyenDuongDAOImpl;
import ute.project.vexe.model.TuyenDuong;

import java.util.List;

@Service
public class TuyenDuongServiceImpl implements TuyenDuongServiceImplment {

    private TuyenDuongDAOImpl tuyenDuongDAO;

    @Autowired
    public TuyenDuongServiceImpl(TuyenDuongDAOImpl tuyenDuongDAO) {
        this.tuyenDuongDAO = tuyenDuongDAO;
    }

    @Override
    public List<TuyenDuong> findAll() {
        return this.tuyenDuongDAO.findAll();
    }

    @Override
    public TuyenDuong findById(int id) {
        return this.tuyenDuongDAO.findById(id);
    }

    @Override
    public boolean insert(TuyenDuong tuyenDuong) {
        return this.tuyenDuongDAO.insert(tuyenDuong);
    }

    @Override
    public boolean update(TuyenDuong tuyenDuong) {
        return this.tuyenDuongDAO.update(tuyenDuong);
    }

    @Override
    public boolean delete(int id) {
        return this.tuyenDuongDAO.delete(id);
    }
}
