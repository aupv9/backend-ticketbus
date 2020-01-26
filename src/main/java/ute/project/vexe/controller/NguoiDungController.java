package ute.project.vexe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ute.project.vexe.model.NguoiDung;
import ute.project.vexe.service.jwt.JwtService;
import ute.project.vexe.service.nguoidung.NguoiDungServiceImpl;

import java.util.List;

@RestController
public class NguoiDungController {


    private NguoiDungServiceImpl nguoiDungService;
    private JwtService jwtService;
    @Autowired
    public NguoiDungController(NguoiDungServiceImpl nguoiDungService,JwtService jwtService) {
        this.nguoiDungService = nguoiDungService;
        this.jwtService=jwtService;
    }

    @GetMapping(value = "/users",produces = "application/json")
    public ResponseEntity<List<NguoiDung>> getAllTypeCars(){
        List<NguoiDung> loaiXeList=this.nguoiDungService.findAll();
        return loaiXeList != null ? new ResponseEntity<>(loaiXeList, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    /*
    Method login
    @param User user truyền từ client vào
    return Response
    * */
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody NguoiDung user) {

        HttpStatus httpStatus = null;
        String result="";
        try {
            if (nguoiDungService.checkLogin(user)) {

                result=jwtService.generateTokenLogin(user.getTaikhoan());
                httpStatus = HttpStatus.OK;
            } else {
                result="Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result="Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }
}
