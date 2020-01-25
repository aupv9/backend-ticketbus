package ute.project.vexe.dao.nguoidung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ute.project.vexe.model.LoaiXe;
import ute.project.vexe.model.NguoiDung;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class NguoiDungDAOImpl extends JdbcDaoSupport implements NguoiDungDAOImplement {

    @Autowired
    public NguoiDungDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<NguoiDung> findAll() {
        try {
            return this.getJdbcTemplate().query("SELECT * FROM fnc_findall_nguoidung()",
                    (resultSet, i) -> {
                        NguoiDung user=new NguoiDung();
                        user.setId(resultSet.getInt(1));
                        user.setTaikhoan(resultSet.getString(2));
                        user.setMatkhau(resultSet.getString(3));
                        user.setHo(resultSet.getString(4));
                        user.setTen(resultSet.getString(5));
                        user.setDeleted(resultSet.getBoolean(6));
                        List<String> roles= Arrays.asList(resultSet.getString(7).split(","));
                        user.setRoles(roles);
                        return  user;
                    });
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public NguoiDung findById(int id) {
        return null;
    }

    @Override
    public boolean insert(NguoiDung nguoiDung) {
        return false;
    }

    @Override
    public boolean update(NguoiDung nguoiDung) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
