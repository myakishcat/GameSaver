package saver;

import game.GameParameter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: id validation is boilerplate
//TODO: fix re-creation of already existing saves (supposed to be read, not rewritten)

public class SaveManager {
    private static final File path = new File(System.getProperty("user.dir") + "/saves");  //current directory of the project
    private static final List<Save> saves = new ArrayList<>();

    public static void createSave(List<GameParameter> params) {
        if (!path.exists())
            if(!path.mkdir())
                System.out.println("Directory wasn't created");


        int availableId = saves.size();
        File savePath = new File(path, "save " + availableId + ".bin");
        Save newSave = new Save(savePath);
        saves.add(newSave);

        writeInSave(newSave, params);
    }

    public static void rewriteSave(List<GameParameter> params) {

        System.out.println("Which save do you want to rewrite?");
        printSaves();

        int id = getId();
        if(id < 0 || id >= saves.size()){
            System.out.println("Invalid save id. Try again.");
            return;
        }
        Save changingSave = saves.get(id);
        writeInSave(changingSave, params);
    }

    public static void deleteSave() {

        System.out.println("Which save do you want to delete?");
        printSaves();

        int id = getId();
        if(id < 0 || id >= saves.size()){
            System.out.println("Invalid save id. Try again.");
            return;
        }

        Save deletedSave = saves.get(id);
        if (!deletedSave.delete())
            System.out.println("Deletion failed, try again");

        saves.set(id, null);
    }

    public static List<GameParameter> loadSave() {

        System.out.println("Which save do you want to load?");
        printSaves();

        int id = getId();
        if(id < 0 || id >= saves.size()){
            System.out.println("Invalid save id. Try again.");
            return new ArrayList<>();
        }

        Save loadingSave = saves.get(id);
        return readFromSave(loadingSave);
    }

    private static List<GameParameter> readFromSave(Save save) {

        try (InputStream is = new FileInputStream(save)) {

            try(ObjectInputStream ois =
                    new ObjectInputStream(new BufferedInputStream(is))){

                return (List<GameParameter>) ois.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    private static void writeInSave(Save save, List<GameParameter> params) {
        try {
            try (OutputStream os = new FileOutputStream(save)) {
                try (ObjectOutputStream oos =
                        new ObjectOutputStream(new BufferedOutputStream(os))) {

                    oos.writeObject(params);
                    oos.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printSaves(){
        System.out.println("Current saves:\n");
        for (int i = 0; i < saves.size(); i++) {
            System.out.printf("%d. %s\n", i, saves.get(i).getDate());
        }
    }

    private static int getId() {
        Scanner scanner = new Scanner(System.in);
        //return scanner.nextInt();
        String strId = scanner.next();
        try{
            return Integer.parseInt(strId);
        }catch (Exception e){
            e.printStackTrace(System.out);
            return -1;
        }
    }
}
