package ute.project.vexe.dao.tuyenduongdetail;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ute.project.vexe.model.LichTrinh;
import ute.project.vexe.model.TuyenDuongDetail;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class TuyenDuongDetailDAOImp extends JdbcDaoSupport implements TuyenDuongDetailDAOImplement {

    /*
    Tiêm datasource qua contructor
    * */
    @Autowired
    public TuyenDuongDetailDAOImp(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    @Override
    public List<TuyenDuongDetail> findAll(){
        try {
            return this.getJdbcTemplate().query("SELECT * FROM tuyenduong_detail",(rs, i) -> {
                TuyenDuongDetail tuyenDuongDetail=new TuyenDuongDetail();
                tuyenDuongDetail.setXe_id(rs.getInt("xe_id"));
                tuyenDuongDetail.setTuyenduong_id(rs.getInt("tuyenduong_id"));
                tuyenDuongDetail.setDeleted(rs.getBoolean("deleted"));
                String lichtrinh= rs.getString("lichtrinh");
                /*
                 * Chuyển đổi từ chuỗi sang mảng JSON rồi sao đó thêm vào danh sách
                 * */
                JSONArray jsonObjectarr=new JSONArray(lichtrinh);
//                System.out.println(jsonObjectarr);
                List<LichTrinh> lichTrinhs=new ArrayList<>();
                jsonObjectarr.forEach(o -> {
                    System.out.println();
                    LichTrinh lichTrinh=new LichTrinh(((JSONObject) o).getString("giodi"),
                            ((JSONObject) o).getString("diadiem"),((JSONObject) o).getString("diachi"),
                            ((JSONObject) o).getString("tinh"));
                    lichTrinhs.add(lichTrinh);
                });
                tuyenDuongDetail.setLichtrinh(lichTrinhs);
                return tuyenDuongDetail;
            });
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public TuyenDuongDetail findById(int id) {
        Object[] args = new Object[] {id};
        int[] argTypes = new int[] {Types.INTEGER};
        try {
            return this.getJdbcTemplate().queryForObject("SELECT * FROM tuyenduong_detail WHERE tuyenduong_id = ?",args,argTypes,(rs, index) -> {
                TuyenDuongDetail tuyenDuongDetail=new TuyenDuongDetail();
                tuyenDuongDetail.setXe_id(rs.getInt("xe_id"));
                tuyenDuongDetail.setTuyenduong_id(rs.getInt("tuyenduong_id"));
                tuyenDuongDetail.setDeleted(rs.getBoolean("deleted"));
                String lichtrinh= rs.getString("lichtrinh");
                /*
                 * Chuyển đổi từ chuỗi sang mảng JSON rồi sao đó thêm vào danh sách
                 * */
                JSONArray jsonObjectarr=new JSONArray(lichtrinh);
                List<LichTrinh> lichTrinhs=new ArrayList<>();
                jsonObjectarr.forEach(o -> {
                    LichTrinh lichTrinh=new LichTrinh(((JSONObject) o).getString("giodi").toString(),
                            ((JSONObject) o).get("diadiem").toString(),((JSONObject) o).get("diachi").toString(),
                            ((JSONObject) o).get("tinh").toString());
                    lichTrinhs.add(lichTrinh);
                });
                tuyenDuongDetail.setLichtrinh(lichTrinhs);
                return tuyenDuongDetail;
            });
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(TuyenDuongDetail tuyenDuongDetail) {
        //tạo array chứa tham số từ clients
        Object[] args=new Object[]{tuyenDuongDetail.getTuyenduong_id(),tuyenDuongDetail.getXe_id(),tuyenDuongDetail.getLichtrinh().toString()};
        try {
            return this.getJdbcTemplate().update("INSERT INTO tuyenduong_detail values(?,?,default,?)",args) != 0?true:false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TuyenDuongDetail tuyenDuongDetail) {
    Object[] args=new Object[]{tuyenDuongDetail.getXe_id(),tuyenDuongDetail.getTuyenduong_id(),
            tuyenDuongDetail.getLichtrinh().toString(),tuyenDuongDetail.getId()};
        try {
            return this.getJdbcTemplate().update("UPDATE tuyenduong_detail SET xe_id = ?, tuyenduong_id = ?, lichtrinh = ? WHERE id = ?",args) !=0? true: false;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return this.getJdbcTemplate().update("UPDATE tuyenduong_detail SET deleted = ? WHERE id = ?",true,id) != 0?true:false;
        }catch (Exception ex){
            return false;
        }

    }
}
