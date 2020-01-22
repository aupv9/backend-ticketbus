package ute.project.vexe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xe {

    private int id;
    private boolean deleted;
    private List<Ghe> danhsachghe;
    private int loaixe_id;
    private int nhaxe_id;
}
