package com.example.battle_arena.service;


import com.example.battle_arena.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {
    private GameState gameState = new GameState(1L, 1, false);

    public GameState getGameState() {
        return gameState;
    }

    public void nextTurn() {
        gameState.setTurnNumber(gameState.getTurnNumber() + 1);
    }
}
