package com.mercureit.DebtCollectorBFF;
import com.mercureit.DebtCollectorBFF.SystemReclamations.dtos.ReclamationDTO;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.EmailService;
import com.mercureit.DebtCollectorBFF.SystemReclamations.services.Reclamation.ReclamationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class ProjectDebtCollectorBFFApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjectDebtCollectorBFFApplication.class, args);
	}
/*
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*
	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		String subject = "Test d'envoi d'e-mail";
		String message = "Ceci est un test d'envoi d'e-mail depuis votre application.";
		String toEmail="Assouliimad07@gmail.com";

		// Send the email using EmailService
		emailService.sendEmail(toEmail, subject, message);
	}
	 */
	/*
	@EventListener(ApplicationReadyEvent.class)
	public void testDeleteReclamation() {

		ReclamationDTO reclamationDTO=new ReclamationDTO();
		reclamationDTO.setTitle("test");
		reclamationDTO.setDescription("test");
		ReclamationDTO reclamationDTO1 = reclamationService.saveReclamation(reclamationDTO);
		Long reclamationId=reclamationDTO1.getId();
		if(reclamationId!=null) {
			System.out.println("reclamationId="+reclamationId);
		}else {
			System.out.println("reclamationId is null");
		}

		// Delete the Reclamation by ID
		//reclamationService.deleteReclamation(1L);
	}

	 */

	}






 

