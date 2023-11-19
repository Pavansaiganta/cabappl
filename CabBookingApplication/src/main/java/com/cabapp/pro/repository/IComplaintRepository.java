package com.cabapp.pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cabapp.pro.entity.Complaint;

@Repository
public interface IComplaintRepository  extends JpaRepository<Complaint,Integer> {
	

    public List<Complaint> findByDriverId(int id);

    public List<Complaint> findByStatus(String status);
    
    @Query(value="select * from complaint where complaint_id = :complaintId",nativeQuery=true)
    public Complaint findByComplaintId(Integer complaintId);
	@Query(value="select * from complaint where customer_id = :id",nativeQuery=true)
    public List<Complaint> findByCustomerId(int id);
}
