package com.example.battle_arena.controller;

import com.example.battle_arena.model.Player;
import com.example.battle_arena.model.Position;
import com.example.battle_arena.service.Direction;
import com.example.battle_arena.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        if (playerService.deletePlayer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/move/{id}")
    public ResponseEntity<String> updateDirection(@PathVariable("id") Long id, @RequestBody Direction direction) {
        Optional<Player> player = playerService.getPlayerById(id);

        if (player.isEmpty()) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }

        Player value = player.get();
        Position newPosition = playerService.movePlayer(value, direction);

        if (newPosition == null) {
            return new ResponseEntity<>("Move failed", HttpStatus.BAD_REQUEST);
        }

        value.setPosition(newPosition);

        String responseMessage = "Player ID " + id + " moved to X: " + newPosition.getX() + " Y: " + newPosition.getY();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
