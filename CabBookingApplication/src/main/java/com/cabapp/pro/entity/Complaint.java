package com.cabapp.pro.entity;

    import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.*;

    @Entity
   
   
    @ApiModel(description = "Details about Complaint Bean")
    public class Complaint {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer complaintId;
        private Instant dateOnRegister;
        private String comment;
        private String status;
        private String email;
       
        private int customerId;
       
        private int driverId;
        
        public Complaint() {
			// TODO Auto-generated constructor stub
		}

		public Complaint(Integer complaintId, Instant dateOnRegister, String comment, String status, String email,
				int customerId, int driverId) {
			super();
			this.complaintId = complaintId;
			this.dateOnRegister = dateOnRegister;
			this.comment = comment;
			this.status = status;
			this.email = email;
			this.customerId = customerId;
			this.driverId = driverId;
		}

		

		public Integer getComplaintId() {
			return complaintId;
		}

		public void setComplaintId(Integer complaintId) {
			this.complaintId = complaintId;
		}

		public Instant getDateOnRegister() {
			return dateOnRegister;
		}

		public void setDateOnRegister(Instant dateOnRegister) {
			this.dateOnRegister = dateOnRegister;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public int getDriverId() {
			return driverId;
		}

		public void setDriverId(int driverId) {
			this.driverId = driverId;
		}

		@Override
		public String toString() {
			return "Complaint [complaintId=" + complaintId + ", dateOnRegister=" + dateOnRegister + ", comment="
					+ comment + ", status=" + status + ", email=" + email + ", customerId=" + customerId + ", driverId="
					+ driverId + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(comment, complaintId, customerId, dateOnRegister, driverId, email, status);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Complaint other = (Complaint) obj;
			return Objects.equals(comment, other.comment) && Objects.equals(complaintId, other.complaintId)
					&& customerId == other.customerId && Objects.equals(dateOnRegister, other.dateOnRegister)
					&& driverId == other.driverId && Objects.equals(email, other.email)
					&& Objects.equals(status, other.status);
		}

		
		
		
		
    }
