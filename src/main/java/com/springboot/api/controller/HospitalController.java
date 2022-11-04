package com.springboot.api.controller;

import com.springboot.api.domain.Hospital;
import com.springboot.api.dto.HospitalRequestDto;
import com.springboot.api.repository.HospitalDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @GetMapping(value = "/hospitals/{id}")
    public ResponseEntity<Hospital> get(@PathVariable int id) {
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital); // value 값이 null인 경우 Optional 타입 객체 생성

        if(!opt.isEmpty()) { // null이 아닐 시
            return ResponseEntity.ok().body(hospital); // 상태코드 200 반환
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital()); // 상태코드 400 반환
        }
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
        try {
            hospitalDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/hospitals")
    public void deleteAll() {
        try {
            hospitalDao.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
