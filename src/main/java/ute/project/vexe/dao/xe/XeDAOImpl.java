package ute.project.vexe.dao.xe;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class XeDAOImpl extends JdbcDaoSupport implements XeDAOImplement {

    @Autowired
    public XeDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Xe> findAll() {
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().query("SELECT * FROM xe",
                    (resultSet, index) -> {
                        JSONArray arrGhe=new JSONArray(resultSet.getString("danhsachghe"));
                        List<Ghe> gheList=new ArrayList<>();
                        arrGhe.forEach(o -> {
                            Ghe ghe=new Ghe(((JSONObject) o).getInt("stt"),
                                    ((JSONObject) o).getInt("gia"),
                                    ((JSONObject) o).getBoolean("trangthai"));
                            gheList.add(ghe);
                        });
                        return new Xe(resultSet.getInt("id"),
                                    resultSet.getBoolean("deleted"),
                                    gheList,resultSet.getInt("loaixe_id"),
                                    resultSet.getInt("nhaxe_id"));
                    }
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Xe findById(int id) {
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject("SELECT * FROM xe WHERE id = ?",
                    new Object[]{id},
                    (resultSet, index) -> {
                        JSONArray arrGhe=new JSONArray(resultSet.getString("danhsachghe"));
                        List<Ghe> gheList=new ArrayList<>();
                        arrGhe.forEach(o -> {
                            Ghe ghe=new Ghe(((JSONObject) o).getInt("stt"),
                                    ((JSONObject) o).getInt("gia"),
                                    ((JSONObject) o).getBoolean("trangthai"));
                            gheList.add(ghe);
                        });
                        return new Xe(resultSet.getInt("id"),
                                resultSet.getBoolean("deleted"),
                                gheList,resultSet.getInt("loaixe_id"),
                                resultSet.getInt("nhaxe_id"));
                    }
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<XeCanFind> findByNoiDiAndNoiDenAndDate(String noidi, String noiden, String date)  {
        try {
            return this.getJdbcTemplate()
                    .query("SELECT * FROM findbynoidiandnoidenandngay(?,?,?)",
                    new Object[]{noidi,noiden,date},((resultSet, i) -> {
                        XeCanFind xeCanFind=new XeCanFind();
                        xeCanFind.setIdXe(resultSet.getInt("idXe"));
                        xeCanFind.setIdLoaiXe(resultSet.getInt("idNhaXe"));
                        xeCanFind.setTenNhaXe(resultSet.getString("tenNhaXe"));
                        xeCanFind.setIdLoaiXe(resultSet.getInt("idLoaiXe"));
                        xeCanFind.setKieuXe(resultSet.getInt("kieuxe"));
                        xeCanFind.setTenLoaiXe(resultSet.getString("tenLoaiXe"));
                        xeCanFind.setNoidi(resultSet.getString("noidi"));
                        xeCanFind.setNoiden(resultSet.getString("noiden"));
                        xeCanFind.setDate(resultSet.getString("ngaydi"));
                        xeCanFind.setIdTuyenDuongDetail(resultSet.getInt("idTDDT"));
                        JSONArray danhsachghe=new JSONArray(resultSet.getString("danhsachghe"));
                        List<Ghe> gheList=new ArrayList<>();
                        danhsachghe.forEach(o -> {
                            Ghe ghe=new Ghe(((JSONObject) o).getInt("stt"),
                                    ((JSONObject) o).getInt("gia"),
                                    ((JSONObject) o).getBoolean("trangthai"));
                            gheList.add(ghe);
                        });
                        JSONArray jsonObjectarr=new JSONArray(resultSet.getString("lchtrinh"));
                        List<LichTrinh> lichTrinhs=new ArrayList<>();
                        jsonObjectarr.forEach(o -> {
                            LichTrinh lichTrinh=new LichTrinh(((JSONObject) o).getString("giodi"),
                                    ((JSONObject) o).getString("diadiem"),((JSONObject) o).getString("diachi"),
                                    ((JSONObject) o).get("tinh").toString());
                                    lichTrinhs.add(lichTrinh);
                            });
                        xeCanFind.setDanhsachghe(gheList);
                        xeCanFind.setLichTrinh(lichTrinhs);
                        return xeCanFind;
                    })) ;

        }catch (Exception ex){
            ex.fillInStackTrace();
        }
        return null;
    }
        @Override
    public boolean insert(Xe xe) {
        return false;
    }

    @Override
    public boolean update(Xe xe) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
