package ute.project.vexe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuyenDuongDetail {

    private int id;

    private int xe_id;

    private int tuyenduong_id;

    private boolean deleted;

    private List<LichTrinh> lichtrinh;

}
