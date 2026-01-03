package com.devops.profileapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Profile name cannot be null")
    private String profileName;

    @NotBlank(message = "Player name is required")
    @Size(max = 30, message = "Player name can't be longer than 30 characters")
    private String playerName;

    @NotNull(message = "Player ID is required")
    @Positive(message = "Player ID must be a positive integer")
    private Long playerId;

    @NotNull(message = "Level is required")
    @PositiveOrZero(message = "Level cannot be negative")
    private Integer level;
    @NotNull(message = "XP is required")
    @PositiveOrZero(message = "XP cannot be negative")
    private Integer xp ;

    @Pattern(regexp = "^[A-Z][0-9]$", message = "Last checkpoint must match format: Letter + digit (e.g. A1, B5)")
    private String lastCheckpoint ;
}
