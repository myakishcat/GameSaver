import game.Character;
import game.GameParameter;
import saver.SaveManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final List<GameParameter> currentParameters = new ArrayList<>();

    public static void main(String[] args) {
        Character character = new Character();
        currentParameters.add(character);

        SaveManager.createSave(currentParameters);

        character.take("food");
        character.take("water");
        character.go(new Point(1, 3));
        SaveManager.createSave(currentParameters);

        character.drop("dog");
        character.sleep();
        SaveManager.rewriteSave(currentParameters);

        System.out.println(SaveManager.loadSave().toString());

        saver.SaveManager.deleteSave();
    }
}
