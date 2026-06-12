package com.mercureit.DebtCollectorBFF.repositories.personne;

import com.mercureit.DebtCollectorBFF.dto.PersonneDTO;
import com.mercureit.DebtCollectorBFF.dto.PersonneDtoFDCreation;
import com.mercureit.DebtCollectorBFF.entities.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.PersonneDTO(p.id, " +
            "p.type, " +
            "CASE WHEN TYPE(p) = PPhysique THEN pp.nom ELSE '' END, " +
            "CASE WHEN TYPE(p) = PPhysique THEN pp.prenom ELSE '' END, " +
            "CASE WHEN TYPE(p) = PMorale THEN pm.nomLegal ELSE '' END, " +
            "p.numIdentif, " +
            "t.numeroTelephone, " +
            "a.ville) " +
            "FROM Personne p " +
            "LEFT JOIN p.telephone t " +
            "LEFT JOIN p.adresses a " +
            "LEFT JOIN PPhysique pp ON p.id = pp.id " +
            "LEFT JOIN PMorale pm ON p.id = pm.id")
    Page<PersonneDTO> findAllPersonnesWithDetails(Pageable pageable);

    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.PersonneDTO(p.id, " +
            "p.type , " +
            "CASE WHEN TYPE(p) = PPhysique THEN pp.nom ELSE '' END, " +
            "CASE WHEN TYPE(p) = PPhysique THEN pp.prenom ELSE '' END, " +
            "CASE WHEN TYPE(p) = PMorale THEN pm.nomLegal ELSE '' END, " +
            "p.numIdentif, " +
            "t.numeroTelephone, " +
            "a.ville) " +
            "FROM Personne p " +
            "LEFT JOIN p.telephone t " +
            "LEFT JOIN p.adresses a " +
            "LEFT JOIN PPhysique pp ON p.id = pp.id " +
            "LEFT JOIN PMorale pm ON p.id = pm.id " +
            "WHERE (:refClient IS NULL OR p.id = :refClient) " +
            "AND (:type IS NULL OR CAST(p.type AS string) = :type) " +
            "AND (:nomRS IS NULL OR (CASE WHEN TYPE(p) = PPhysique THEN pp.nom ELSE pm.nomLegal END LIKE %:nomRS%)) " +
            "AND (:cinICE IS NULL OR p.numIdentif LIKE %:cinICE%) " +
            "AND (:telephone IS NULL OR t.numeroTelephone LIKE %:telephone%) " +
            "AND (:ville IS NULL OR a.ville LIKE %:ville%)")
    Page<PersonneDTO> filterPersonnes(Pageable pageable,
                                      @Param("refClient") Long refClient,
                                      @Param("type")String type,
                                      @Param("nomRS")String nomRS,
                                      @Param("cinICE")String cinICE,
                                      @Param("telephone")String telephone,
                                      @Param("ville")String ville);


    @Query("SELECT new com.mercureit.DebtCollectorBFF.dto.PersonneDtoFDCreation(" +
            "p.id, " +
            "p.type, " +
            "CASE WHEN p.type = 'PHYSIQUE' THEN pp.nom ELSE '' END, " +
            "CASE WHEN p.type = 'PHYSIQUE' THEN pp.prenom ELSE '' END, " +
            "CASE WHEN p.type = 'MORALE' THEN pm.nomLegal ELSE '' END, " +
            "p.numIdentif, " +
            "p.activite, " +
            "p.email, " +
            "CASE WHEN TYPE(p) = com.mercureit.DebtCollectorBFF.entities.PMorale THEN pm.paysConstitution ELSE '' END, " +
            "CASE WHEN TYPE(p) = com.mercureit.DebtCollectorBFF.entities.PMorale THEN pm.dateDeCreation ELSE null END, " +
            "CASE WHEN p.type = 'PHYSIQUE' THEN pp.paysNaissance ELSE '' END, " +
            "CASE WHEN p.type = 'PHYSIQUE' THEN pp.dateDeNaissance ELSE null END, " +
            "a, " +
            "t, " +
            "p.dateCreation, " +
            "d" +
            ") " +
            "FROM Personne p " +
            "LEFT JOIN PPhysique pp ON p.id = pp.id " +
            "LEFT JOIN PMorale pm ON p.id = pm.id " +
            "LEFT JOIN p.adresses a " +  // Corrected to 'adresse'
            "LEFT JOIN p.telephone t " +
            "LEFT JOIN Dirigeant d ON pm.id = d.idDirigeant " +
            "WHERE p.numIdentif = :numIdentif")
    List<PersonneDtoFDCreation> findByNum_Identif(@Param("numIdentif") String numIdentif);

}






