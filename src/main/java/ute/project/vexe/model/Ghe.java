package ute.project.vexe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ghe {

    private int stt;

    private int gia;

    private boolean trangthai;

    @Override
    public String toString() {
        return "{" +
                "stt:" + stt +
                ", gia:" + gia +
                ", trangthai:" + trangthai +
                '}';
    }
}
