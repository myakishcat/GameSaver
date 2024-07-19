package game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Character implements GameParameter {
    private int energy;
    private Point2D position;
    private final List<String> inventory;

    public Character(){
        energy = 10;
        position = new Point(0, 0);
        inventory = new ArrayList<>();
    }

    public void take(String item){
        inventory.add(item);
    }

    public void drop(String item){
        if(inventory.contains(item))
            inventory.remove(item);
        else
            System.out.printf("You don't have object \"%s\" in the inventory. Try again.%n", item);
    }

    public void go(Point2D aim){

        int distance = (int) (abs(aim.getX() - position.getX()) + abs(aim.getY() - position.getY()));
        if (energy > distance){
            energy -= distance;
            position = aim;
        }
        else{
            System.out.println("This character don't have enough energy to go. Sleep to restore energy");
        }
    }

    public void sleep(){
        energy = 10;
    }

    @Override
    public String toString(){
        return String.format("""
                Energy: %d,
                Position: (%d, %d),
                Inventory size: %d
                """, energy, (int)position.getX(), (int)position.getY(), inventory.size());
    }
}
