package com.example.Demo_JPA.Repository;

import com.example.Demo_JPA.Model.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    @Query(value = "select * from doctor", nativeQuery = true)
    public List<Doctor> getMinimumDoctorOnTheBasisOfVC(UUID vcid);

    @Modifying
    @Transactional
    @Query(value = "update doctor set patient_count=:patientCount where id=:docId", nativeQuery = true)
    public void updatePatientCountByOne(UUID docId, int patientCount);

//    @Modifying
//    @Transactional
//    @Query(value = "insert into doctor_patient_list (doctor_id, patient_list_id) values (:doctorId, :patientId)", nativeQuery = true)
//    public void insertIntoDoctorVsPatientsTable(UUID doctorId, UUID patientId);

}
