package ute.project.vexe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XeCanFind implements Serializable {

    private int idXe;
    private Set<Ghe> danhsachghe;
    private int idNhaXe;
    private String tenNhaXe;
    private int idLoaiXe;
    private int kieuXe;
    private String tenLoaiXe;
    private String noidi;
    private String noiden;
    private String date;
    private List<LichTrinh> lichTrinh;
    private int idTuyenDuongDetail;

}
