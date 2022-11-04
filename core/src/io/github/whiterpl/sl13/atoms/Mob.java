package io.github.whiterpl.sl13.atoms;

import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Atom {

    protected short maxHp;
    protected short currentHp;

    protected short maxSp;
    protected short currentSp;

    protected short[] skills;

    protected List<Item> equipment;

    protected int credits;

    protected int x;
    protected int y;

    protected int actionDelay;
    protected int nextUpdateTurn;

    public Mob(String name, String description, char symbol, String colorString, Status... statuses) {
        super(name, description, symbol, colorString, statuses);
        this.equipment = new ArrayList<>();
        this.skills = new short[9];
    }

    public Mob(String name, String description, char symbol, String colorString) {
        super(name, description, symbol, colorString);
        this.equipment = new ArrayList<>();
        this.skills = new short[9];
    }

    // GETTERS & SETTERS

    public short getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(short maxHp) {
        this.maxHp = maxHp;
    }

    public short getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(short currentHp) {
        this.currentHp = currentHp;
    }

    public short getMaxSp() {
        return maxSp;
    }

    public void setMaxSp(short maxSp) {
        this.maxSp = maxSp;
    }

    public short getCurrentSp() {
        return currentSp;
    }

    public void setCurrentSp(short currentSp) {
        this.currentSp = currentSp;
    }

    public short[] getSkills() {
        return skills;
    }

    public void setSkills(short[] skills) {
        this.skills = skills;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getActionDelay() {
        return actionDelay;
    }

    public int getNextUpdateTurn() {
        return nextUpdateTurn;
    }

    public void setNextUpdateTurn(int nextUpdateTurn) {
        this.nextUpdateTurn = nextUpdateTurn;
    }

    // STATIC METHODS

    public static short calculateMaxHp(short toughness) {
        return (short) (50 + toughness * 10);
    }

    public static short calculateMaxSp(short willpower) {
        return (short) (50 + willpower * 10);
    }

    // METHODS

    public void placeMob(Region region, int x, int y) {
        Tile selectedTile = region.getTile(x, y);

        if (selectedTile.blocksPassage()) throw new IllegalArgumentException();
        else if (selectedTile.mob != null) throw new IllegalArgumentException();
        else {
            this.x = x;
            this.y = y;
            selectedTile.mob = this;
        }
    }

    public boolean move(Region region, Direction direction) {
        switch (direction) {
            case NORTH:
                if (this.y == 0) return false;

                Tile selectedTile = region.getTile(this.x, this.y - 1);
                if (selectedTile.blocksPassage()) return false;

                Mob tempMob = null;
                if (selectedTile.mob != null) tempMob = selectedTile.mob;

                selectedTile.mob = this;
                region.getTile(this.x, this.y).mob = null;

                if (tempMob != null) region.getTile(this.x, this.y).mob = tempMob;

                this.y = this.y - 1;

                return true;
        }

        return false;
    }
}
