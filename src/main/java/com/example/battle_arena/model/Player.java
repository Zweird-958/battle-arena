package com.example.battle_arena.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;
    private String username;
    private int xp;
    private int health;
    private int positionX;
    private int positionY;
}

