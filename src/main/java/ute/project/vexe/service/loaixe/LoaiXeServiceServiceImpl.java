package ute.project.vexe.service.loaixe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ute.project.vexe.model.LoaiXe;

import java.util.List;

@Service("loaixeService")
public class LoaiXeServiceServiceImpl implements LoaiXeServiceImplement {

    private ute.project.vexe.dao.loaixe.LoaiXeDAOImpl loaiXe;

    @Autowired
    public LoaiXeServiceServiceImpl(ute.project.vexe.dao.loaixe.LoaiXeDAOImpl loaiXe) {
        this.loaiXe = loaiXe;
    }

    @Override
    public List<LoaiXe> findAll() {
        return this.loaiXe.findAll();
    }

    @Override
    public LoaiXe findById(int id) {
        return this.loaiXe.findById(id);
    }

    @Override
    public boolean insert(LoaiXe loaiXe) {
        return this.loaiXe.insert(loaiXe);
    }

    @Override
    public boolean update(LoaiXe loaiXe) {
        return this.loaiXe.update(loaiXe);
    }

    @Override
    public boolean delete(int id) {
        return this.loaiXe.delete(id);
    }
}
