package com.mercureit.DebtCollectorBFF.SystemReclamations.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String title;
    private String message;
    private String recipient;
    private boolean read;
    private LocalDateTime timestamp;
    public NotificationDTO(String title, String message, String recipient, boolean read, LocalDateTime timestamp) {
        this.title = title;
        this.message = message;
        this.recipient = recipient;
        this.read = read;
        this.timestamp = timestamp;
    }
}
