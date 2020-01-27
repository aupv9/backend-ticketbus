package ute.project.vexe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ute.project.vexe.model.Xe;
import ute.project.vexe.model.XeCanFind;
import ute.project.vexe.service.xe.XeServiceImpl;
import java.util.List;

@RestController
public class XeController {

    private XeServiceImpl xeService;

    @Autowired
    public XeController(XeServiceImpl xeService) {
        this.xeService = xeService;
    }

    @GetMapping(value = "/cars",produces = "application/json")
    public ResponseEntity<List<Xe>> getXes(){
        List<Xe> xeList=this.xeService.findAll();
        return xeList != null ? new ResponseEntity<>(xeList, HttpStatus.OK)
                : new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/car/{id}",produces = "application/json")
    public ResponseEntity<Xe> getCarById(@PathVariable("id") int id){
        Xe xe=this.xeService.findById(id);
        return xe != null ? new ResponseEntity<>(xe, HttpStatus.OK)
                :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/car-list/by-value",produces = "application/json")
    public ResponseEntity<List<XeCanFind>> getXeByParams(@RequestBody XeCanFind xeCanFind) {
        List<XeCanFind> xeList=this.xeService
                .findByNoiDiAndNoiDenAndDate(xeCanFind.getNoidi(),xeCanFind.getNoiden(),
                        xeCanFind.getDate());
        return xeList != null ? new ResponseEntity<>(xeList, HttpStatus.OK)
                : new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/car",produces = "application/json",
            consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<Boolean> insertCar(@RequestBody Xe xe){
        return this.xeService.insert(xe) ? new ResponseEntity<>(true, HttpStatus.CREATED)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/car",produces = "application/json")
    public ResponseEntity<Boolean> updateHouseCar(@RequestBody Xe xe){
        return this.xeService.update(xe) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/car/{id}",produces = "application/json")
    public ResponseEntity<Boolean> updateHouseCar(@PathVariable("id") int id){
        return this.xeService.delete(id) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/update-car",produces = "application/json")
    public ResponseEntity<Boolean> updateSeats(@org.jetbrains.annotations.NotNull @RequestBody Xe xe){
        return this.xeService.updateGhe(xe.getId(),xe.getIdGhe()) ? new ResponseEntity<>(true, HttpStatus.OK)
                :  new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }

}
