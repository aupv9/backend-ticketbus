package ute.project.vexe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ute.project.vexe.model.NhaXe;
import ute.project.vexe.service.nhaxe.NhaXeServiceImpl;

import java.util.List;

@RestController
public class NhaXeController {

    private NhaXeServiceImpl nhaXeService;
    @Autowired
    public NhaXeController(NhaXeServiceImpl nhaXeService) {
        this.nhaXeService = nhaXeService;
    }

    @GetMapping(value = "/house-cars",produces = "application/json")
    public ResponseEntity<List<NhaXe>> getNhaXes(){
        List<NhaXe> nhaXeList=this.nhaXeService.findAll();
        return nhaXeList != null ? new ResponseEntity<>(nhaXeList, HttpStatus.OK)
                                : new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/house-car/{id}",produces = "application/json")
    public ResponseEntity<NhaXe> getHouseCarById(@PathVariable("id") int id){
        NhaXe nhaXe=this.nhaXeService.findById(id);
        return nhaXe != null ? new ResponseEntity<>(nhaXe, HttpStatus.OK)
                :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/house-car",produces = "application/json")
    public ResponseEntity<Boolean> insertHouseCar(@RequestBody NhaXe nhaXe){
        return this.nhaXeService.insert(nhaXe) ? new ResponseEntity<>(true, HttpStatus.CREATED)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/house-car",produces = "application/json")
    public ResponseEntity<Boolean> updateHouseCar(@RequestBody NhaXe nhaXe){
        return this.nhaXeService.update(nhaXe) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/house-car/{id}",produces = "application/json")
    public ResponseEntity<Boolean> updateHouseCar(@PathVariable("id") int id){
        return this.nhaXeService.delete(id) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
}
