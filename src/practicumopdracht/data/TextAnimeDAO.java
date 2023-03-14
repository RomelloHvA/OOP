package practicumopdracht.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextAnimeDAO extends AnimeDAO{

    private static final String FILE_NAME = "anime.txt";

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {

        File file = new File(FILE_NAME);
        try (Scanner input = new Scanner(file)){
            while (input.hasNext()){

            }
        } catch (FileNotFoundException e) {
            System.err.println("Werkt niet bro");
            return false;
        }
        return false;
    }
}
