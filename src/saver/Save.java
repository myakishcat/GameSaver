package saver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Save extends File {

    private final LocalDate date;

    //TODO: specify date with time
    public Save(File pathname) {
        super(pathname.toURI());
        this.date = LocalDate.now();

        try{
            createNewFile();
        }catch (IOException ex){
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }

    }

    public LocalDate getDate() {
        return date;
    }
}
