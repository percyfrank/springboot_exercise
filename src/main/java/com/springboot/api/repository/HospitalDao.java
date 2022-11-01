package com.springboot.api.repository;

import com.mysql.cj.result.Row;
import com.springboot.api.domain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;


    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // List<Hospital> -- 11만건의 Hospital
    public void add(Hospital hospital) {
        String sql = "INSERT INTO `like_lion`.`nation_wide_hospital` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_number`, `patient_room_count`, `total_number_of_beds`, `total_area_size`)" +
                " VALUES (?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?);";
        this.jdbcTemplate.update(sql,hospital.getId(),hospital.getOpenServiceName(),hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(),hospital.getLicenseDate(),hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(),hospital.getPhone(),hospital.getFullAddress(),
                hospital.getRoadNameAddress(),hospital.getHospitalName(),hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(),hospital.getPatientRoomCount(),hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize());
    }

    RowMapper<Hospital> rowMapper = (rs,rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());

        return hospital;
    };
    public Hospital findById(int id) {
        return this.jdbcTemplate.queryForObject("select * from nation_wide_hospital where id = ?", rowMapper,id);
    }

    public int getCount() {
        String sql = "select count(id) from nation_wide_hospital";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);

    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from nation_wide_hospital");
    }

}
