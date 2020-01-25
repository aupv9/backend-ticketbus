package ute.project.vexe.dao.loaixe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.LoaiXe;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class LoaiXeDAOImpl extends JdbcDaoSupport implements LoaiXeDAOImplement {

    @Autowired
    public LoaiXeDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<LoaiXe> findAll() {
        try {
            return this.getJdbcTemplate().query("SELECT * FROM fnc_findall_loaixe()",
                    (resultSet, i) -> new LoaiXe(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getBoolean(4)));
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public LoaiXe findById(int id) {
        try {
            return this.getJdbcTemplate().queryForObject("SELECT * FROM fnc_findbyidloaixe(?)",
                    new Object[]{id},
                    (resultSet,i) -> new LoaiXe(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getBoolean(4)));
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(LoaiXe loaiXe) {
        try {
            this.getJdbcTemplate().update("CALL sp_insert_loaixe( ?,? )",
                    new Object[]{loaiXe.getKieu(),loaiXe.getTen()});
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LoaiXe loaiXe) {
        try {
            this.getJdbcTemplate().update("CALL sp_update_loaixe(?,?,?,?)",
                    loaiXe.getKieu(),loaiXe.getTen(),loaiXe.isDeleted(),loaiXe.getId());
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            this.getJdbcTemplate().update("CALL sp_delete_loaixe(?)",
                                        new Object[]{id});
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
