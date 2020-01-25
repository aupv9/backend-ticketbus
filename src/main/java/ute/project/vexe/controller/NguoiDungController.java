package ute.project.vexe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ute.project.vexe.model.LoaiXe;
import ute.project.vexe.model.NguoiDung;
import ute.project.vexe.service.nguoidung.NguoiDungServiceImpl;

import java.util.List;

@RestController
public class NguoiDungController {


    private NguoiDungServiceImpl nguoiDungService;

    @Autowired
    public NguoiDungController(NguoiDungServiceImpl nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping(value = "/users",produces = "application/json")
    public ResponseEntity<List<NguoiDung>> getAllTypeCars(){
        List<NguoiDung> loaiXeList=this.nguoiDungService.findAll();
        return loaiXeList != null ? new ResponseEntity<>(loaiXeList, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
