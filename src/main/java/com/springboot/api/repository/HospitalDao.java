package com.springboot.api.repository;

import com.springboot.api.domain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.util.Scanner;

@Repository
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;

    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // List<Hospital> -- 11만건의 Hospital
    public void add(Hospital hospital) {
        String sql = "INSERT INTO `like_lion`.`nation_wide_hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_number`, `patient_room_count`, `total_number_of_beds`, `total_area_size`)" +
                " VALUES (?,?,?" +
                "?,?,?," +
                "?,?,?," +
                "?,?,?" +
                "?,?,?" +
                "?);";
        this.jdbcTemplate.update(sql,hospital.getId(),hospital.getOpenServiceName(),hospital.getOpenLocalGovernmentCode()
                hospital.getManagementNumber(),hospital.getLicenseDate(),hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),hospital.getPhone(),hospital.getFullAddress(),
                hospital.getRoadNameAddress(),hospital.getHospitalName(),hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(),hospital.getPatientRoomCount(),hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize());
    }
}
