package com.example.battle_arena.service;

import com.example.battle_arena.model.Player;
import com.example.battle_arena.model.Position;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private int MIN_X = 0;
    private int MIN_Y = 0;
    private int MAX_X = 7;
    private int MAX_Y = 7;
    private final List<Player> players = new ArrayList<>();
    private long nextId = 1; // Gestion des IDs manuels

    @PostConstruct
    public void init() {
        // Ajouter quelques joueurs au démarrage
        players.add(new Player(nextId++, "Joueur1", 10, 100, new Position(0, 1)));
        players.add(new Player(nextId++, "Joueur2", 20, 90, new Position(2, 2)));
        players.add(new Player(nextId++, "Joueur3", 30, 80,   new Position(3, 3)));
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public Optional<Player> getPlayerById(Long id) {
        return players.stream().filter(player -> player.getId().equals(id)).findFirst();
    }

    public Player createPlayer(Player player) {
        player.setId(nextId++);
        players.add(player);
        return player;
    }

    public boolean deletePlayer(Long id) {
        return players.removeIf(player -> player.getId().equals(id));
    }

    public Position movePlayer(Player player, Direction direction) {
        Position oldPosition = player.getPosition();
        int playerX = oldPosition.getX();
        int playerY = oldPosition.getY();

        if (direction == Direction.LEFT && playerX > MIN_X) {
            return new Position(playerX - 1, playerY);
        }

        if (direction == Direction.RIGHT && playerX < MAX_X) {
            return new Position(playerX + 1 , playerY);
        }

        if (direction == Direction.UP && playerY > MIN_Y) {
            return new Position(playerX, playerY - 1);
        }

        if (direction == Direction.DOWN && playerY < MAX_Y) {
            return new Position(playerX, playerY + 1);
        }

        return null;
    }
}

