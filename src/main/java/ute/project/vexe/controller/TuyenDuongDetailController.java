package ute.project.vexe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ute.project.vexe.model.TuyenDuongDetail;
import ute.project.vexe.service.tuyenduongdetail.TuyenDuongDetailServiceImpl;

import java.util.List;

@RestController
public class TuyenDuongDetailController {

    private TuyenDuongDetailServiceImpl tuyenDuongDetailService;

    @Autowired
    public TuyenDuongDetailController(TuyenDuongDetailServiceImpl tuyenDuongDetail) {
        this.tuyenDuongDetailService = tuyenDuongDetail;
    }

    // lấy danh sách thông tin các route-detail
    @GetMapping(value = "/route-details", produces= "application/json")
    public ResponseEntity<List<TuyenDuongDetail>> getAll(){
        List<TuyenDuongDetail> listRouteDetail=this.tuyenDuongDetailService.findAll();
        return listRouteDetail != null ? new ResponseEntity<>(listRouteDetail, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    // lấy 1 route-detail theo id
    @GetMapping(value = "/route-detail/{id}", produces= "application/json")
    public ResponseEntity<TuyenDuongDetail> getRouteDetail(@PathVariable("id") int id){
        TuyenDuongDetail tuyenDuongDetail=this.tuyenDuongDetailService.findById(id);
        return tuyenDuongDetail != null ? new ResponseEntity<>(tuyenDuongDetail, HttpStatus.OK) :  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    // thêm mới 1 route-detail
    @PostMapping(value = "/route-detail",produces = "application/json")
    public ResponseEntity<Boolean> insertRouteDetail(@RequestBody TuyenDuongDetail tuyenDuongDetail){
        return this.tuyenDuongDetailService.insert(tuyenDuongDetail) ? new ResponseEntity<>(true,HttpStatus.CREATED):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
    //edit 1 route-detail
    @PutMapping(value = "/route-detail",produces = "application/json")
    public ResponseEntity<Boolean> updateRouteDetail(@RequestBody TuyenDuongDetail tuyenDuongDetail){
        return this.tuyenDuongDetailService.update(tuyenDuongDetail) ? new ResponseEntity<>(true,HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
    // đánh dấu 1 route-detail không còn hoạt động
    @DeleteMapping(value = "/route-detail/{id}",produces = "application/json")
    public ResponseEntity<Boolean> deleteRouteDetail(@PathVariable("id") int id){
        return this.tuyenDuongDetailService.delete(id) ? new ResponseEntity<>(true,HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }
}
