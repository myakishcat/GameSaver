import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: params validation
public class SaveManager {
    private static final File path = new File(System.getProperty("user.dir") + "/saves");  //current directory of the project
    private static List<Save> saves = new ArrayList<>();
    //TODO: resolve problem with IDs after deleting save

    public static void createSave(String params)
    {
        if (!path.exists())
            path.mkdir();
            //TODO: throw exception if not created

        int lastId = saves.size();
        //TODO: if saves has nulls, get id of null
        File savePath = new File(path, "save " + lastId + ".txt");
        Save newSave = new Save(savePath, lastId);
        saves.add(newSave);

        writeInSave(newSave, params);
    }

    public static void rewriteSave(int id, String params)
    {
        Save changingSave = saves.get(id);
        writeInSave(changingSave, params);
    }

    public static void deleteSave(int id)
    {
        Save deletedSave = saves.get(id);
        deletedSave.delete();
        saves.set(id, null);
    }

    public static String loadSave(int id)
    {
        Save loadingSave = saves.get(id);
        return readFromSave(loadingSave);
    }

    private static String readFromSave(Save save)
    {
        String result;
        try {
            try(Scanner scanner = new Scanner(save, "Cp1251")){
                result = scanner.nextLine();
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    private static void writeInSave(Save save, String params)
    {
        try
        {
            try (OutputStream os = new FileOutputStream(save))
            {
                try (PrintWriter printWriter =
                        new PrintWriter(new OutputStreamWriter
                                (new BufferedOutputStream(os), "Cp1251")))
                {
                    printWriter.println(params);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
