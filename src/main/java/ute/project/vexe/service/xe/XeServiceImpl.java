package ute.project.vexe.service.xe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.dao.xe.XeDAOImpl;
import ute.project.vexe.model.Xe;
import ute.project.vexe.model.XeCanFind;

import java.sql.SQLException;
import java.util.List;

@Service
public class XeServiceImpl implements XeServiceImplement {


    private XeDAOImpl xeDAO;

    @Autowired
    public XeServiceImpl(XeDAOImpl xeDAO) {
        this.xeDAO = xeDAO;
    }

    @Override
    public List<Xe> findAll() {
        return this.xeDAO.findAll();
    }

    @Override
    public Xe findById(int id) {
        return this.xeDAO.findById(id);
    }

    @Override
    public List<XeCanFind> findByNoiDiAndNoiDenAndDate(String noidi, String noiden, String date)  {
        return this.xeDAO.findByNoiDiAndNoiDenAndDate(noidi,noiden,date);
    }

    @Override
    public boolean insert(Xe xe) {
        return this.xeDAO.insert(xe);
    }

    @Override
    public boolean update(Xe xe) {
        return this.xeDAO.update(xe);
    }

    @Override
    public boolean delete(int id) {
        return this.xeDAO.delete(id);
    }
}
