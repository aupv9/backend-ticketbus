package ute.project.vexe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaChi {

    private int id;

    private String duong;

    private String phuong;

    private String quan;

    private String tinh;

    private int nhaxe_id;
}
