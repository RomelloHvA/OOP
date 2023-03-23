package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

import java.io.*;
import java.time.LocalDate;

public class BinaryReviewDAO extends ReviewDAO{
    private static final String FILE_NAME = "review.dat";
    @Override
    public boolean save() {

        File reviewFile = new File(FILE_NAME);

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(reviewFile))){
            dataOutputStream.writeInt(getAll().size());

            for (int i = 0; i < getAll().size(); i++) {

                dataOutputStream.writeInt(MainApplication.getAnimeDAO().getIdFor(getAll().get(i).getBelongsTo()));
                dataOutputStream.writeUTF(getAll().get(i).getWrittenBy());
                dataOutputStream.writeUTF(getAll().get(i).getWriteDate().toString());
                dataOutputStream.writeDouble(getAll().get(i).getRating());
                dataOutputStream.writeUTF(getAll().get(i).getReview());
                dataOutputStream.writeBoolean(getAll().get(i).isRecommended());
            }
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean load() {
        File reviewFile = new File(FILE_NAME);

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(reviewFile))){
            int reviewSize = dataInputStream.readInt();

            for (int i = 0; i < reviewSize; i++) {

                Anime belongsTo = MainApplication.getAnimeDAO().getByID(dataInputStream.readInt());
                String writtenBy = dataInputStream.readUTF();
                LocalDate writeDate = LocalDate.parse(dataInputStream.readUTF());
                double rating = dataInputStream.readDouble();
                String reviewText = dataInputStream.readUTF();
                boolean recommended = dataInputStream.readBoolean();

                Review review = new Review(belongsTo,writtenBy,writeDate,rating,reviewText,recommended);
                addOrUpdate(review);

            }
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
