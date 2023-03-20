package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TextReviewDAO extends ReviewDAO{

    private static final String FILE_NAME = "review.txt";

    @Override
    public boolean save() {
        File reviewFile = new File(FILE_NAME);
        PrintWriter printWriter = null;


        try {
            printWriter = new PrintWriter(reviewFile);
            printWriter.println(getAll().size());

            for (int i = 0; i < getAll().size(); i++) {

                printWriter.println(MainApplication.getAnimeDAO().getIdFor(getAll().get(i).getBelongsTo()));
                printWriter.println(getAll().get(i).getWrittenBy());
                printWriter.println(getAll().get(i).getWriteDate());
                printWriter.println(getAll().get(i).getRating());
                printWriter.println(getAll().get(i).getReview());
                printWriter.println(getAll().get(i).isRecommended());
            }
            return true;
        } catch (FileNotFoundException exception){
            System.err.println("File not found");
        } finally {
            assert printWriter != null;
            printWriter.close();
        }
        return false;
    }

    @Override
    public boolean load() {

        File reviewFile = new File(FILE_NAME);
        Scanner scanner = null;

        try {
            scanner = new Scanner(reviewFile);

            int reviewSize = scanner.nextInt();

            for (int i = 0; i < reviewSize ; i++) {

                Anime belongsTo = MainApplication.getAnimeDAO().getByID(scanner.nextInt());
                scanner.nextLine();
                String writtenBy = scanner.nextLine();
                LocalDate writeDate = LocalDate.parse(scanner.nextLine());
                double rating = Double.parseDouble(scanner.nextLine());
                String reviewText = scanner.nextLine();
                boolean recommended = scanner.nextBoolean();

                Review review = new Review(belongsTo, writtenBy, writeDate, rating, reviewText, recommended);
                addOrUpdate(review);
            }
            return true;
        } catch (FileNotFoundException exception){
            System.err.println("File not found");
        } catch (NullPointerException exception){
            System.err.println("Null pointer exception");
        } catch (Exception exception){
            System.err.println("Unhandled exception or corrupt file");
        } finally {
            assert scanner != null;
            scanner.close();
        }
        return false;
    }
}
