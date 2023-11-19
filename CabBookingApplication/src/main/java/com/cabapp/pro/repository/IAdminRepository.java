package com.cabapp.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabapp.pro.entity.Admin;


@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);

}
