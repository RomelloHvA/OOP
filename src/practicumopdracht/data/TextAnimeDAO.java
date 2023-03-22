package practicumopdracht.data;

import practicumopdracht.models.Anime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class TextAnimeDAO extends AnimeDAO{

    private static final String FILE_NAME = "anime.txt";

    @Override
    public boolean save() {
        File animeFile = new File(FILE_NAME);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(animeFile);
            printWriter.println(getAll().size());

            for (int i = 0; i < getAll().size(); i++) {
                printWriter.println(getAll().get(i).getName());
                printWriter.println(getAll().get(i).getEpisodes());
                printWriter.println(getAll().get(i).getReleaseDate());
                printWriter.println(getAll().get(i).getSynopsis());
                printWriter.println(getAll().get(i).isDownloaded());
                printWriter.println(getAll().get(i).isWatched());
            }
            return true;
        }catch (IOException exception){
            System.err.println(exception.getCause());

        } finally {

            assert printWriter != null;
            printWriter.close();
        }
        return false;
    }

    @Override
    public boolean load() {

        File animeFile = new File(FILE_NAME);
        Scanner scanner = null;
        try {

            scanner = new Scanner(animeFile);

            int animeSize = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < animeSize; i++) {

                String animeName = scanner.nextLine();
                int episodeCount = Integer.parseInt(scanner.nextLine());
                LocalDate releaseDate = LocalDate.parse(scanner.nextLine());
                String synopsis = scanner.nextLine();
                boolean isDownloaded = scanner.nextBoolean();
                boolean isWatched = scanner.nextBoolean();
                scanner.nextLine();

                Anime anime = new Anime(animeName,releaseDate,episodeCount,synopsis,isDownloaded,isWatched);
                addOrUpdate(anime);
            }
            return true;

        } catch (FileNotFoundException exception){
            System.err.println("File not Found");


        } catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Illegal argument found");

        } catch (Exception exception){

            System.err.println("corrupt file");

        }finally {
            assert scanner != null;
            scanner.close();
        }

        return false;
    }
}
