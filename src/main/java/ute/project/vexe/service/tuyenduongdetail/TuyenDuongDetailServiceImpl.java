package ute.project.vexe.service.tuyenduongdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.dao.tuyenduongdetail.TuyenDuongDetailDAOImp;
import ute.project.vexe.model.TuyenDuongDetail;

import java.util.List;

@Service
public class TuyenDuongDetailServiceImpl implements TuyenDuongDetailServiceImplement {

    private TuyenDuongDetailDAOImp tuyenDuongDetailImp;

    @Autowired
    public TuyenDuongDetailServiceImpl(TuyenDuongDetailDAOImp tuyenDuongDetailImp) {
        this.tuyenDuongDetailImp = tuyenDuongDetailImp;
    }

    @Override
    public List<TuyenDuongDetail> findAll() {
        return this.tuyenDuongDetailImp.findAll();
    }

    @Override
    public TuyenDuongDetail findById(int id) {
        return this.tuyenDuongDetailImp.findById(id);
    }

    @Override
    public boolean insert(TuyenDuongDetail tuyenDuongDetail) {
        return this.tuyenDuongDetailImp.insert(tuyenDuongDetail);
    }

    @Override
    public boolean update(TuyenDuongDetail tuyenDuongDetail) {
        return this.tuyenDuongDetailImp.update(tuyenDuongDetail);
    }

    @Override
    public boolean delete(int id) {
        return this.tuyenDuongDetailImp.delete(id);
    }
}
