import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Save extends File {

    private final int id;
    private final LocalDate date;

    public Save(File pathname, int id) {
        super(pathname.toURI());
        this.id = id;
        this.date = LocalDate.now();

        try{
            createNewFile();
        }catch (IOException ex){
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }

    }
}
