package ute.project.vexe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ute.project.vexe.model.TuyenDuong;
import ute.project.vexe.service.tuyenduong.TuyenDuongServiceImpl;

import java.util.List;

@RestController
public class TuyenDuongController {

    private TuyenDuongServiceImpl tuyenDuongService;

    @Autowired
    public TuyenDuongController(TuyenDuongServiceImpl tuyenDuongService) {
        this.tuyenDuongService = tuyenDuongService;
    }

    // lấy danh sách thông tin các route
    @GetMapping(value = "/routes", produces= "application/json")
    public ResponseEntity<List<TuyenDuong>> getAll(){
        List<TuyenDuong> listRouteDetail=this.tuyenDuongService.findAll();
        return listRouteDetail != null ? new ResponseEntity<>(listRouteDetail, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    // lấy 1 route theo id
    @GetMapping(value = "/route/{id}", produces= "application/json")
    public ResponseEntity<TuyenDuong> getRouteDetail(@PathVariable("id") int id){
        TuyenDuong tuyenDuongDetail=this.tuyenDuongService.findById(id);
        return tuyenDuongDetail != null ? new ResponseEntity<>(tuyenDuongDetail, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    // thêm mới 1 route
    @PostMapping(value = "/route",produces = "application/json")
    public ResponseEntity<Boolean> insertRouteDetail(@RequestBody TuyenDuong tuyenDuong){
        return this.tuyenDuongService.insert(tuyenDuong) ? new ResponseEntity<>(true,HttpStatus.CREATED):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
    //edit 1 route-detail
    @PutMapping(value = "/route",produces = "application/json")
    public ResponseEntity<Boolean> updateRouteDetail(@RequestBody TuyenDuong tuyenDuong){
        return this.tuyenDuongService.update(tuyenDuong) ? new ResponseEntity<>(true,HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
    // đánh dấu 1 route không còn hoạt động
    @DeleteMapping(value = "/route/{id}",produces = "application/json")
    public ResponseEntity<Boolean> deleteRouteDetail(@PathVariable("id") int id){
        return this.tuyenDuongService.delete(id) ? new ResponseEntity<>(true,HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
}
