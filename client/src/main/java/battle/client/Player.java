package battle.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private int x;
    private int y;
    private int health;
    private int xp = 0;

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void addXP(int xp) {
        this.xp += xp;
    }
}
