package com.mercureit.DebtCollectorBFF.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseModelDto {

	private String username;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private LocalDate dateNaissance;
	private String description;
}
