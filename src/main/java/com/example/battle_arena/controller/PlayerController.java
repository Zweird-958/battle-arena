package com.example.battle_arena.controller;

import com.example.battle_arena.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private int GRID_SIZE = 8;
    private List<Player> players = List.of(
            new Player(1, "Alice", 0, 0, 100, 0),
            new Player(2, "Bob", 1, 2, 100, 0),
            new Player(3, "Charlie", 3, 4, 100, 0),
            new Player(4, "David", 5, 6, 100, 0)
    );

    private AtomicInteger currentPlayerIndex = new AtomicInteger(0);

    @GetMapping
    public List<Player> getPlayers() {
        return players;
    }

    @PostMapping("/move")
    public synchronized Player movePlayer(@RequestParam String direction) {
        if (players.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucun joueur en jeu !");
        }

        Player player = getCurrentPlayer(); // Récupère le joueur actuel
        int newX = player.getX();
        int newY = player.getY();

        // Calculer la nouvelle position
        switch (direction.toUpperCase()) {
            case "UP": newY = player.getY() - 1; break;
            case "DOWN": newY = player.getY() + 1; break;
            case "LEFT": newX = player.getX() - 1; break;
            case "RIGHT": newX = player.getX() + 1; break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Direction invalide !");
        }

        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Déplacement impossible, hors de la grille !");
        }

        int finalNewX = newX;
        int finalNewY = newY;
        Optional<Player> targetOpt = players.stream()
                .filter(p -> p.getX() == finalNewX && p.getY() == finalNewY && p != player)
                .findFirst();

        if (targetOpt.isPresent()) {
            Player target = targetOpt.get();
            target.takeDamage(10);
            player.addXP(10);

            if (target.getHealth() <= 0) {
                player.addXP(50);
                System.out.println(target.getName() + " a été éliminé !");
                players.removeIf(p -> p.getId() == target.getId());
            }
        } else {
            // Appliquer le déplacement
            player.setX(newX);
            player.setY(newY);
        }



        // Passer au joueur suivant uniquement si le déplacement est valide
        if (!players.isEmpty()) {
            int nextIndex = (currentPlayerIndex.incrementAndGet()) % players.size();
            currentPlayerIndex.set(nextIndex);
        }

        return player;
    }

    @GetMapping("/current")
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex.get());
    }
}
