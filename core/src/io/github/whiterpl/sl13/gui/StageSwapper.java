package io.github.whiterpl.sl13.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import io.github.whiterpl.sl13.Game;
import io.github.whiterpl.sl13.controls.CharacterCreationController;
import io.github.whiterpl.sl13.controls.GameController;
import io.github.whiterpl.sl13.controls.MainMenuController;
import io.github.whiterpl.sl13.player.PlayerController;

public class StageSwapper {
    MainMenuStage mainMenuStage;
    CharacterCreationStage characterCreationStage;
    GameStage gameStage;
    PlayerController playerController;

    public enum State {
        MAIN_MENU,
        CHARACTER_CREATION,
        GAME
    }

    State state;

    public StageSwapper(BitmapFont guiFont, BitmapFont gameFont, NinePatchDrawable border) {
        this.mainMenuStage = new MainMenuStage(guiFont, border);
        this.characterCreationStage = new CharacterCreationStage(guiFont, border);
        this.gameStage = new GameStage(guiFont, gameFont, border);
        changeStage(State.MAIN_MENU);
        playerController = new PlayerController();
    }

    public void changeStage(State state) {
        this.state = state;

        switch (state) {
            case MAIN_MENU:
                Gdx.input.setInputProcessor(new MainMenuController(mainMenuStage, this));
                break;
            case CHARACTER_CREATION:
                characterCreationStage.resetAll();
                Gdx.input.setInputProcessor(new CharacterCreationController(playerController, characterCreationStage, this));
                break;
            case GAME:
                Game.getInstance().startNewGame();
                gameStage.getInfoPane().clearMessages();
                gameStage.getInfoPane().updateStats(playerController);
                gameStage.getGamePane().updateGameScreen(playerController.getActiveRegion(), playerController.getPlayer().getX(), playerController.getPlayer().getY());
                Gdx.input.setInputProcessor(new GameController(gameStage, this));
                break;
        }
    }

    public void draw() {
        switch (state) {
            case MAIN_MENU:
                mainMenuStage.draw();
                break;
            case CHARACTER_CREATION:
                characterCreationStage.draw();
                break;
            case GAME:
                gameStage.draw();
                break;
        }
    }

    public void dispose() {
        gameStage.dispose();
        characterCreationStage.dispose();
    }

    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
        characterCreationStage.getViewport().update(width, height);
    }

    public CharacterCreationStage getCharacterCreationStage() {
        return characterCreationStage;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public State getState() {
        return state;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }
}
