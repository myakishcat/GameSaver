
public class GameManager {

    //private final SaveManager saveManager = new SaveManager();
    private static String currentParameters = "some parameters";

    public static void main(String[] args) {
        SaveManager.createSave(currentParameters);
        currentParameters = "parameters changed";
        SaveManager.createSave(currentParameters);

        currentParameters = "rewritten parameters";
        SaveManager.rewriteSave(0, currentParameters);

        System.out.println(SaveManager.loadSave(0));

        SaveManager.deleteSave(1);
    }
}
