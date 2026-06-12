package com.mercureit.DebtCollectorBFF.dto;

import java.time.LocalDate;

public interface PieceJointeProjection {
    Long getIdFile();
    String getNomFile();
    String getTypeFile();
    Long getTailleFile();
    LocalDate getDateCreation();
    String getPath();
    String getPermissionAccess();
    String getStatusFile();
    LocalDate getDateExpiration();
    String getNumIdentite();
    String getTypepiecejoindre();
}
