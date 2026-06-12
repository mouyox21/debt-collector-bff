package com.mercureit.DebtCollectorBFF.SystemReclamations.enumerations;

public enum ClaimCategory {
    TECHNICAL_ISSUE,        // Problèmes techniques tels que des bugs ou des erreurs dans le système
    COMPONENT_FAILURE,      // Problèmes liés à des composants spécifiques (ex : gestion des dossiers, gestion des clients)
    USER_INTERFACE,         // Problèmes d'affichage, de navigation ou d'interaction utilisateur
    PERFORMANCE_ISSUE,      // Problèmes de lenteur ou de performance dégradée
    DATA_INCONSISTENCY,     // Problèmes de données incorrectes ou incohérentes
    SECURITY_ISSUE,         // Problèmes de sécurité (ex : accès non autorisé, failles de sécurité)
    FUNCTIONALITY_REQUEST,  // Demandes de nouvelles fonctionnalités ou améliorations
    DOCUMENTATION_ERROR,    // Problèmes liés à la documentation ou aux guides utilisateur
    OTHER                   // Autres types de problèmes non spécifiés
}
