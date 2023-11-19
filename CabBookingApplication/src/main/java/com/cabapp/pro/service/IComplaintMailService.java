package com.cabapp.pro.service;

import com.cabapp.pro.dto.EmailDetails;

public interface IComplaintMailService {
    public void sendSimpleMail(EmailDetails details) ;
    }