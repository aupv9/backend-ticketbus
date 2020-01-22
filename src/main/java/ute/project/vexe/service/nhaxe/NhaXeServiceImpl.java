package ute.project.vexe.service.nhaxe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.dao.nhaxe.NhaXeDAOImpl;
import ute.project.vexe.model.NhaXe;

import java.util.List;

@Service
public class NhaXeServiceImpl implements NhaXeServiceImplement{

    private NhaXeDAOImpl nhaXeDAO;

    @Autowired
    public NhaXeServiceImpl(NhaXeDAOImpl nhaXeDAO) {
        this.nhaXeDAO = nhaXeDAO;
    }

    @Override
    public List<NhaXe> findAll() {
        return this.nhaXeDAO.findAll();
    }

    @Override
    public NhaXe findById(int id) {
        return this.nhaXeDAO.findById(id);
    }

    @Override
    public boolean insert(NhaXe loaiXe) {
        return this.nhaXeDAO.insert(loaiXe);
    }

    @Override
    public boolean update(NhaXe loaiXe) {
        return this.nhaXeDAO.update(loaiXe);
    }

    @Override
    public boolean delete(int id) {
        return this.nhaXeDAO.delete(id);
    }
}
