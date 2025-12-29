package com.devops.profileapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {
    @Id
    private Long id;
    private String profileName;

    private String playerName;
    private Long playerId;
    private Integer level;
    private Integer xp ;
    private String lastCheckpoint ;
}
