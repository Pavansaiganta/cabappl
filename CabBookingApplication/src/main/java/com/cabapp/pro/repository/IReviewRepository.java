package com.cabapp.pro.repository;
import com.cabapp.pro.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review,Integer>{

	

}
