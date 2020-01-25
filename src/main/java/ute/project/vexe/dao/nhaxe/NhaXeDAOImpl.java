package ute.project.vexe.dao.nhaxe;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.NhaXe;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class NhaXeDAOImpl extends JdbcDaoSupport
        implements NhaXeDAOImplement {

    public NhaXeDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<NhaXe> findAll() {
        try {
            return this.getJdbcTemplate().query("SELECT * FROM fnc_findallnhaxe()",
                    (resultSet, i) -> new NhaXe(resultSet.getInt(1),resultSet.getString(2),resultSet.getBoolean(3))
                    );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public NhaXe findById(int id) {
        try {
            return this.getJdbcTemplate().queryForObject("SELECT * FROM nhaxe WHERE id = ?",
                    new Object[]{id},
                    (resultSet, i) -> new NhaXe(resultSet.getInt(1),resultSet.getString(2),resultSet.getBoolean(3)));
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(NhaXe loaiXe) {
        try {
              return this.getJdbcTemplate().update("CALL insert_nhaxe(?)",
                    new Object[]{loaiXe.getTen()}) >= 0 ;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(NhaXe nhaXe) {
        try {
            return this.getJdbcTemplate().update("UPDATE nhaxe SET ten = ?, deleted = ? WHERE id =?",
                    nhaXe.getTen(), nhaXe.isDeleted(), nhaXe.getId()) != 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return this.getJdbcTemplate().update("UPDATE nhaxe SET deleted= ? WHERE id = ?",
                    new Object[]{true,id}) != 0 ? true : false;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
