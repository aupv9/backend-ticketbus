package ute.project.vexe.dao.tuyenduong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.NhaXe;
import ute.project.vexe.model.TuyenDuong;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TuyenDuongDAOImpl extends JdbcDaoSupport
        implements TuyenDuongDAOImplement {
    @Autowired
    public TuyenDuongDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<TuyenDuong> findAll() {
        try {
            return this.getJdbcTemplate().query("SELECT * FROM tuyenduong",
                    (resultSet, i) -> new TuyenDuong(resultSet.getInt(1),
                                            resultSet.getString(4),
                                            resultSet.getString(3),
                                            resultSet.getString(2),
                                            resultSet.getBoolean(5))
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public TuyenDuong findById(int id) {
        try {
            return this.getJdbcTemplate().queryForObject("SELECT * FROM tuyenduong WHERE id = ?",
                    new Object[]{id},
                    (resultSet, i) -> new  TuyenDuong(resultSet.getInt(1),
                                                    resultSet.getString(2),
                                                    resultSet.getString(3),
                                                    resultSet.getString(4),
                                                    resultSet.getBoolean(5))
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(TuyenDuong tuyenDuong) {
        try {
            return this.getJdbcTemplate().update("INSERT INTO tuyenduong VALUES (default ,?,?,?,default )",
                    tuyenDuong.getDate(),tuyenDuong.getNoiden(),tuyenDuong.getNoidi()) != 0 ;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TuyenDuong tuyenDuong) {
        try {
            return this.getJdbcTemplate()
                    .update("UPDATE tuyenduong SET date = ?, noiden = ?, noidi = ?,deleted = ? WHERE id =?",
                            tuyenDuong.getDate(),tuyenDuong.getNoiden(),
                            tuyenDuong.getNoidi(),tuyenDuong.isDeleted()
                            ,tuyenDuong.getId()) != 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return this.getJdbcTemplate()
                    .update("UPDATE tuyenduong SET deleted= ? WHERE id = ?",
                    true, id) != 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
