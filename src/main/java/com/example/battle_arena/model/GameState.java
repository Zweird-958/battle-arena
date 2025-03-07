package com.example.battle_arena.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private Long id;
    private int turnNumber;
    private boolean gameOver;
}
