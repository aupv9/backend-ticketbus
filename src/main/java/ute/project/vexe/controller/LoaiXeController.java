package ute.project.vexe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ute.project.vexe.model.LoaiXe;
import ute.project.vexe.service.loaixe.LoaiXeServiceServiceImpl;
import java.util.List;

@RestController
public class LoaiXeController {

    private LoaiXeServiceServiceImpl loaixeService;
    @Autowired
    public LoaiXeController(LoaiXeServiceServiceImpl loaixeService) {
        this.loaixeService = loaixeService;
    }

    @GetMapping(value = "/type-cars",produces = "application/json")
    public ResponseEntity<List<LoaiXe>> getAllTypeCars(){
        List<LoaiXe> loaiXeList=this.loaixeService.findAll();
        return loaiXeList != null ? new ResponseEntity<>(loaiXeList, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/type-cars/{id}",produces = "application/json")
    public ResponseEntity<LoaiXe> getTypeCarById(@PathVariable("id") int id){
        LoaiXe loaiXe=this.loaixeService.findById(id);
        return loaiXe != null ? new ResponseEntity<>(loaiXe, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/type-cars",produces = "application/json")
    public ResponseEntity<Boolean> insertTypeCar(@RequestBody LoaiXe loaiXe){
        return this.loaixeService.insert(loaiXe) ? new ResponseEntity<>(true, HttpStatus.CREATED) :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/type-cars",produces = "application/json")
    public ResponseEntity<Boolean> updateTypeCar(@RequestBody LoaiXe loaiXe){
        return this.loaixeService.update(loaiXe) ? new ResponseEntity<>(true, HttpStatus.OK) :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/type-cars/{id}",produces = "application/json")
    public ResponseEntity<Boolean> updateTypeCar(@PathVariable("id") int id){
        return this.loaixeService.delete(id) ? new ResponseEntity<>(true, HttpStatus.OK) :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

}
