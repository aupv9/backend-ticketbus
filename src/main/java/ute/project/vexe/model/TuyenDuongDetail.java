package ute.project.vexe.model;


import lombok.Data;

import java.util.List;


@Data
public class TuyenDuongDetail {

    private int id;

    private int xe_id;

    private int tuyenduong_id;

    private boolean deleted;

    private List<LichTrinh> lichtrinh;

}
