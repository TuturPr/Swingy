package xyz.tuturprdev.swingy.model.entities;

import xyz.tuturprdev.swingy.model.entities.classes.Hero;

public class Player< T extends Hero> {
    private final T HeroType;
    private final String Name;
    private int Level;

    public Player(T Class, String name) {
        this.HeroType = Class;
        this.Name = name;
        this.Level = 1;
    }
}
