package com.example.battle_arena.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Player {
    public int id;
    public String name;
    public int x;
    public int y;
    public int health;
    public int xp = 0;

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void addXP(int xp) {
        this.xp += xp;
    }
}
