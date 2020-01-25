package ute.project.vexe.dao.xe;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.*;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Transactional
public class XeDAOImpl extends JdbcDaoSupport implements XeDAOImplement {

    @Autowired
    public XeDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    @Override
    public Set<Ghe> convertStringtoSet(String input) {
        JSONArray arrGhe=new JSONArray(input);
        Set<Ghe> gheList=new HashSet<>();
        arrGhe.forEach(o -> {
            Ghe ghe=new Ghe(((JSONObject) o).getInt("stt"),
                    ((JSONObject) o).getInt("gia"),
                    ((JSONObject) o).getBoolean("trangthai"));
            gheList.add(ghe);
        });
        return gheList;
    }
    @Override
    public List<Xe> findAll() {
        try {
            return this.getJdbcTemplate().query("SELECT * FROM fnc_get_xes()",
                    (resultSet, index) -> {
                        return new Xe(resultSet.getInt("id"),
                                    resultSet.getBoolean("deleted"),
                                    this.convertStringtoSet(resultSet.getString("danhsachghe")),
                                    resultSet.getInt("loaixe_id"),
                                    resultSet.getInt("nhaxe_id"),null);
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
                        return new Xe(resultSet.getInt("id"),
                                resultSet.getBoolean("deleted"),
                                this.convertStringtoSet(resultSet.getString("danhsachghe"))
                                ,resultSet.getInt("loaixe_id"),
                                resultSet.getInt("nhaxe_id"),null);
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
                    .query("SELECT * FROM fnc_findbynoidiandnoidenandngay(?,?,?)",
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

                        Set<Ghe> gheList=convertStringtoSet(resultSet.getString("danhsachghe"));

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
        try {
            return this.getJdbcTemplate().update("CALL insert_xe(?,?,?)",
                    new Object[]{xe.getLoaixe_id(),xe.getNhaxe_id(),xe.getDanhsachghe().toString()}) >=0;

        }catch (Exception ex){
            ex.printStackTrace();
        }
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

    @Override
    public boolean updateGhe(int idXe, Set<Integer> idGhes) {
        try {
            Set<Ghe> xes=this.findById(idXe).getDanhsachghe();
            xes.forEach(ghe -> {
                if(idGhes.contains(ghe.getStt())){
                    boolean state=ghe.isTrangthai();
                    ghe.setTrangthai(!state);
                }
            });
            return this.getJdbcTemplate().update("CALL update_ghe(?,?)",
                    idXe,xes.toString()) >= 0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }


}
