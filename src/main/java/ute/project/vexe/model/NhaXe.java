package ute.project.vexe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhaXe {

    private int id;

    private String ten;

    private boolean deleted;
}
