package ute.project.vexe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoaiXe {

    private int id;

    private int kieu;

    private String ten;

    private boolean deleted;

}
