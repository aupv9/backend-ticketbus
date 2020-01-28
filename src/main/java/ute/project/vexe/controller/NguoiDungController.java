package ute.project.vexe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<NguoiDung>> getAllUsers(){
        List<NguoiDung> loaiXeList=this.nguoiDungService.findAll();
        return loaiXeList != null ? new ResponseEntity<>(loaiXeList, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/user",produces = "application/json")
    public ResponseEntity<Boolean> insertUser(@RequestBody NguoiDung nguoiDung){
        return this.nguoiDungService.insert(nguoiDung) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/user",produces = "application/json")
    public ResponseEntity<Boolean> updateUser(@RequestBody NguoiDung nguoiDung){
        return this.nguoiDungService.update(nguoiDung) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/user/{id}",produces = "application/json")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id){
        return this.nguoiDungService.delete(id) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
    /*
    Method login
    @param User user truyền từ client vào
    return Response
    * */
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody NguoiDung user) {

        HttpStatus httpStatus = null;
        HttpHeaders httpHeaders=new HttpHeaders();
        String result="";
        NguoiDung nguoiDung=nguoiDungService.checkLogin(user);
        try {
            if (nguoiDung!= null) {
                result=jwtService.generateTokenLogin(user.getTaikhoan());
                httpHeaders.set("x-user",nguoiDung.getRoles().toString());
                httpStatus = HttpStatus.OK;
            } else {
                result="Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result="Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result,httpHeaders, httpStatus);
    }
}
