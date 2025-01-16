package de.tum.cit.fop.maze.gameobject;

public class Human extends GameObject {
    protected float speed;

    public Human(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }
}
