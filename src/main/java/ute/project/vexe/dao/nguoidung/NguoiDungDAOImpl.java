package ute.project.vexe.dao.nguoidung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        try {
            return  this.getJdbcTemplate().queryForObject("SELECT * FROM fnc_findnguoidungbyid(?)",
                    new Object[]{id},(resultSet, i) -> new NguoiDung(resultSet.getInt(1),
                            resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getBoolean(6)
                            ,Arrays.asList(resultSet.getString(7).split(",")))
                            );

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(NguoiDung nguoiDung) {
        try {
            return this.getJdbcTemplate().update("CALL sp_insert_nguoidung(?,?,?,?,?)",
                    nguoiDung.getTaikhoan(),nguoiDung.getMatkhau(),nguoiDung.getHo(),
                    nguoiDung.getTen(),nguoiDung.getRoles()) >= 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(NguoiDung nguoiDung) {
        try {
            return this.getJdbcTemplate().update("CALL sp_update_user(?,?,?,?,?)",
                    nguoiDung.getMatkhau(),nguoiDung.getHo(),nguoiDung.getTen(),
                    nguoiDung.getRoles(),nguoiDung.isDeleted(),nguoiDung.getId()) >= 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            return this.getJdbcTemplate().update("CALL sp_delete_user(?)",
                    id) >= 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public NguoiDung loadUserByUsername(String user) {
        try {
            return this.getJdbcTemplate().queryForObject("SELECT * FROM fnc_finduserbyusername(?)",
                    new Object[]{user},(resultSet, i) -> new NguoiDung(resultSet.getInt(1),
                            resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getBoolean(6)
                            ,Arrays.asList(resultSet.getString(7).split(","))));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NguoiDung checkLogin(NguoiDung user) {
        try {
            return this.getJdbcTemplate()
                    .queryForObject("SELECT * FROM  fnc_findnguoidungbyaccount(?,?)",
                    new Object[]{user.getTaikhoan(),user.getMatkhau()},
                            (resultSet, i) -> new NguoiDung(resultSet.getInt(1),
                            resultSet.getString(2),resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),resultSet.getBoolean(6)
                            ,Arrays.asList(resultSet.getString(7).split(","))));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
