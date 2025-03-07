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
    private Position position;

    public void setPositionX(int x){
        this.position.setX(x);
    }

    public void setPositionY(int y){
        this.position.setY(y);
    }
}

