package com.example.battle_arena.service;

import com.example.battle_arena.model.Player;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final List<Player> players = new ArrayList<>();
    private long nextId = 1; // Gestion des IDs manuels

    @PostConstruct
    public void init() {
        // Ajouter quelques joueurs au démarrage
        players.add(new Player(nextId++, "Joueur1", 10, 100, 0, 0));
        players.add(new Player(nextId++, "Joueur2", 20, 90, 1, 1));
        players.add(new Player(nextId++, "Joueur3", 30, 80, 2, 2));
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
}

