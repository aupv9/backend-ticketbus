package ute.project.vexe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LichTrinh {

    private String giodi;
    private String diadiem;
    private String diachi;
    private String tinh;

    @Override
    public String toString() {
        return "{" +
                "giodi:'" + giodi + '\'' +
                ", diadiem:'" + diadiem + '\'' +
                ", diachi:'" + diachi + '\'' +
                ", tinh:'" + tinh + '\'' +
                '}';
    }
}
