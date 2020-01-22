package ute.project.vexe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuyenDuong {

    private int id;

    private String noidi;

    private String noiden;

    private String date;

    private boolean deleted;
}

