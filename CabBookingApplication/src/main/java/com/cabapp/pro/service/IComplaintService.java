package com.cabapp.pro.service;

import com.cabapp.pro.entity.Complaint;

import java.util.List;

public interface IComplaintService {
    String createComplaint(Complaint complaint) ;
    String updateComplaint(Complaint complaint) ;
    Complaint getByCompalintId(Integer complaintId) ;
    List<Complaint> getAllComplaints() ;
    List<Complaint> complaintByCustomer(int id) ;
    List<Complaint> complaintOnDriver(int id) ;
    List<Complaint> complaintByStatus(String status) ;
}
