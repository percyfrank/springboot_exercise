package com.springboot.api.controller;

import com.springboot.api.domain.Hospital;
import com.springboot.api.dto.HospitalRequestDto;
import com.springboot.api.repository.HospitalDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @GetMapping(value = "/hospitals/{id}")
    public ResponseEntity<Hospital> get(@PathVariable int id) {
        return ResponseEntity
                .ok()
                .body(hospitalDao.findById(id));
    }

    @GetMapping(value = "/hospitals")
    public List<Hospital> getAll() {
        return hospitalDao.getAll();
    }

    @PostMapping(value = "/hospitals/{id}")
    public Hospital add(@RequestBody HospitalRequestDto hospitalRequestDto) {
        return new Hospital(hospitalRequestDto.getId(), hospitalRequestDto.getOpenServiceName(),
                hospitalRequestDto.getOpenLocalGovernmentCode(), hospitalRequestDto.getManagementNumber(),
                hospitalRequestDto.getLicenseDate(), hospitalRequestDto.getBusinessStatus(),
                hospitalRequestDto.getBusinessStatusCode(), hospitalRequestDto.getPhone(),
                hospitalRequestDto.getFullAddress(), hospitalRequestDto.getRoadNameAddress(),
                hospitalRequestDto.getHospitalName(), hospitalRequestDto.getBusinessTypeName(),
                hospitalRequestDto.getHealthcareProviderCount(), hospitalRequestDto.getPatientRoomCount(),
                hospitalRequestDto.getTotalNumberOfBeds(), hospitalRequestDto.getTotalAreaSize());
    }

    @DeleteMapping(value = "/hospitals/{id}")
    public void deleteById(@PathVariable int id) {
        hospitalDao.deleteById(id);
    }

    @DeleteMapping(value = "/hospitals")
    public void deleteAll() {
        hospitalDao.deleteAll();
    }

}
