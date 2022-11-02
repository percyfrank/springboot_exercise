package com.springboot.api.parser;

import com.springboot.api.domain.Hospital;
import com.springboot.api.repository.HospitalDao;
import com.springboot.api.service.HospitalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {


    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\"";

    /**
     * @Autowired
     * ApplicationContext applicationContext;
     * @BeforeEach
     * void setUp() {
     *      this.hospitalReadLineContext = applicationContext.getBean("hospitalReadLineContext",ReadLineContext.class);
     * }
     */

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    HospitalService hospitalService;

    @Test
    @DisplayName("Hospital이 insert가 잘되는지")
    void addAndGet() {
        hospitalDao.deleteAll();
        Assertions.assertThat(hospitalDao.getCount()).isEqualTo(0);
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        Assertions.assertThat(hospitalDao.getCount()).isEqualTo(1);

        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
        Assertions.assertThat(selectedHospital.getId()).isEqualTo(hospital.getId());
        Assertions.assertThat(selectedHospital.getOpenServiceName()).isEqualTo(hospital.getOpenServiceName());
        Assertions.assertThat(selectedHospital.getOpenLocalGovernmentCode()).isEqualTo(hospital.getOpenLocalGovernmentCode());
        Assertions.assertThat(selectedHospital.getManagementNumber()).isEqualTo(hospital.getManagementNumber());
        Assertions.assertThat(selectedHospital.getBusinessStatus()).isEqualTo(hospital.getBusinessStatus());
        Assertions.assertThat(selectedHospital.getBusinessStatusCode()).isEqualTo(hospital.getBusinessStatusCode());
        Assertions.assertThat(selectedHospital.getLicenseDate()).isEqualTo(hospital.getLicenseDate());
        Assertions.assertThat(selectedHospital.getPhone()).isEqualTo(hospital.getPhone());
        Assertions.assertThat(selectedHospital.getFullAddress()).isEqualTo(hospital.getFullAddress());
        Assertions.assertThat(selectedHospital.getRoadNameAddress()).isEqualTo(hospital.getRoadNameAddress());
        Assertions.assertThat(selectedHospital.getHospitalName()).isEqualTo(hospital.getHospitalName());
        Assertions.assertThat(selectedHospital.getBusinessTypeName()).isEqualTo(hospital.getBusinessTypeName());
        Assertions.assertThat(selectedHospital.getHealthcareProviderCount()).isEqualTo(hospital.getHealthcareProviderCount());
        Assertions.assertThat(selectedHospital.getPatientRoomCount()).isEqualTo(hospital.getPatientRoomCount());
        Assertions.assertThat(selectedHospital.getTotalNumberOfBeds()).isEqualTo(hospital.getTotalNumberOfBeds());

        // 날짜, float
        Assertions.assertThat(selectedHospital.getTotalAreaSize()).isEqualTo(hospital.getTotalAreaSize());
    }

    @Test
    @DisplayName("10만건 이상 데이터가 파싱 되는지")
    void oneHundredThousandsRows() throws IOException {
        // 서버환경에서 build할 때 문제가 생길 수 있다.
        // 어디에서든지 실행할 수 있게 짜야함
        hospitalDao.deleteAll();
        String filename = "C:\\Users\\82104\\Desktop\\fulldata_01_01_02_P_의원.csv";
        int cnt = this.hospitalService.insertLargeVolumeHospitalData(filename);
        assertTrue(cnt > 1000);
        assertTrue(cnt > 10000);
        System.out.printf("파싱된 데이터 개수 : %d", cnt);
    }
    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 Test")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName());//col:1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // col: 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus()); //col:7
        assertEquals(13, hospital.getBusinessStatusCode());//col:9
        assertEquals("062-515-2875", hospital.getPhone());//col:15
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
        assertEquals("효치과의원", hospital.getHospitalName());//col:21
        assertEquals("치과의원", hospital.getBusinessTypeName());//col:25
        assertEquals(1, hospital.getHealthcareProviderCount()); //col:29
        assertEquals(0, hospital.getPatientRoomCount()); //col:30
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:31
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:32
    }

}