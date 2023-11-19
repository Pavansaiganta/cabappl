package com.cabapp.pro.service;

import com.cabapp.pro.dto.EmailDetails;
import com.cabapp.pro.entity.Complaint;
import com.cabapp.pro.entity.Customer;
import com.cabapp.pro.exception.ComplaintException;
import com.cabapp.pro.repository.IComplaintRepository;
import com.cabapp.pro.repository.ICustomerRepository;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class CompalintServiceImpl  implements IComplaintService{
  
	
	
	@Autowired
    private IComplaintMailService mailService;

    @Autowired
    private IComplaintRepository complaintRepository;

    @Autowired
    private ICustomerRepository customerRepository;
    


	@Override
	public String createComplaint(Complaint complaint)  {
		
		
		 Customer customer = customerRepository.getCustomerByCustomerId(complaint.getCustomerId());
		 Date date = new Date();
		   complaint.setStatus("Pending");
		   complaint.setDateOnRegister(date.toInstant());
			Complaint complaintResponse =  complaintRepository.save(complaint);
		if (complaintResponse.getComplaintId() != 0) {
			String body = "Dear " + customer.getUsername() + "," + " \r\n"
				
					+ "We hope this email finds you well. \r\n"
					+ "\r\n"
					+ "Thank you for bringing to our attention the concerns you experienced during your recent cab trip with us. We sincerely apologize for any inconvenience caused and want you to know that your feedback is taken seriously.\r\n"
					+ "\r\n"
					+ "Trip Details:\r\n"
					+ "- Date: [Date]\r\n"
					+ "- Time: [Time]\r\n"
					+ "- Pickup Location: [Pickup Location]\r\n"
					+ "- Drop-off Location: [Drop-off Location]\r\n"
					+ "\r\n"
					+ "We understand the importance of a seamless travel experience and are committed to resolving the issues you've raised. Our team is currently investigating the matter and taking appropriate steps to ensure such incidents are addressed promptly.\r\n"
					+ "\r\n"
					+ "If you have any additional details or specific points you'd like us to consider, please feel free to respond to this email or contact our customer support at [Customer Support mobile number : 180028374892].\r\n"
					+ "\r\n"
					+ "We appreciate your patience and the opportunity to improve our service based on your valuable feedback. Rest assured, we are taking this matter seriously, and we are committed to making the necessary improvements.\r\n"
					+ "\r\n"
					+ "Thank you for choosing Us, and we look forward to the opportunity to serve you better in the future.\r\n"
					+ "\r\n"
					+ "Best regards,\r\n"
					//+ " <br>"
					//+ "A new Complaint as been registered as per you mentioned, please find the complaint id"
					+ "Admin";
			EmailDetails email = new EmailDetails(complaint.getEmail(), body, "Received Your Complaint ");

			mailService.sendSimpleMail(email);

			return "Successfully Created Complaint with the id : " + complaintResponse.getComplaintId();
		}
		else {
			throw new ComplaintException(404,"complaint not saved");

		}
		

		}
	
	
	
	
	
	
	

	@Override
	public String updateComplaint(Complaint complaint) {
		
		

		 Customer customer = customerRepository.getCustomerByCustomerId(complaint.getComplaintId());
			Complaint complaintResponse = getByCompalintId(complaint.getComplaintId());
			
			complaintResponse.setComment(complaint.getComment());
			complaintResponse.setStatus(complaint.getStatus());
		if (complaintResponse.getComplaintId() != 0) {
			complaintRepository.save(complaintResponse);
			String body = "Dear " + customer.getUsername() + "," + "<br><br>"
					+ "There is an update on the complaint you have raised "
					+ complaint.getComplaintId();
			EmailDetails email = new EmailDetails(complaint.getEmail(), body, "Successfully updated your complaint ");

			mailService.sendSimpleMail(email);

			return "Successfully  Complaint updated for the id : " + complaintResponse.getComplaintId();
		}
		else {
			throw new ComplaintException(500,"complaint not updated");

		}
		}
		 
	
	
	
	
	

	@Override
	public Complaint getByCompalintId(Integer complaintId)  {
		
			Complaint complaint = complaintRepository.findByComplaintId(complaintId);
			if (ObjectUtils.isEmpty(complaint)) {
				throw new ComplaintException(404,"complaint id not found");
			}
			return complaint;
		
	}

	
	
	
	@Override
	public List<Complaint> getAllComplaints() {
		
			 List<Complaint> allCompalints =	complaintRepository.findAll();
			if (ObjectUtils.isEmpty(allCompalints)) {
				throw new ComplaintException(404,"complaints not found");
			}
			return allCompalints;
		
	}
	
	
	

	@Override
	public List<Complaint> complaintByCustomer(int id) {
			System.out.println("in complaint customer");
			List<Complaint> complaintsByUser = complaintRepository.findByCustomerId(id);
			if (ObjectUtils.isEmpty(complaintsByUser)) {
				throw new ComplaintException(404,"complaints not found for user");
			}
			return complaintsByUser;
		
	}

	
	
	@Override
	public List<Complaint> complaintByStatus(String status)  {
		
			List<Complaint> complaintsByStatus = complaintRepository.findByStatus(status);
			if (ObjectUtils.isEmpty(complaintsByStatus)) {
				throw new ComplaintException(404,"complaints not found for Status");
			}
			return complaintsByStatus;
		
	}




	@Override
	public List<Complaint> complaintOnDriver(int id) {
		List<Complaint> complaintOnDriver = complaintRepository.findByDriverId(id);
		if (ObjectUtils.isEmpty(complaintOnDriver)) {
			throw new ComplaintException(404,"complaints not found for user");
		}
		return complaintOnDriver;
	}


}


	

