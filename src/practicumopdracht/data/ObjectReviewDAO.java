package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Review;

import java.io.*;

public class ObjectReviewDAO extends ReviewDAO {

    private static final String FILE_NAME = "review.obj";

    @Override
    public boolean save() {
        File reviewFile = new File(FILE_NAME);

        try (FileOutputStream fileOutputStream = new FileOutputStream(reviewFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){

            objectOutputStream.writeInt(getAll().size());

            for (int i = 0; i < getAll().size(); i++) {
                int belongsTo = MainApplication.getAnimeDAO().getIdFor(getAll().get(i).getBelongsTo());
                objectOutputStream.writeInt(belongsTo);
                objectOutputStream.writeObject(getAll().get(i));
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

        try (FileInputStream fileInputStream = new FileInputStream(reviewFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            int reviewSize = objectInputStream.readInt();

            for (int i = 0; i < reviewSize; i++) {

                int belongsTo = objectInputStream.readInt();
                Review review = (Review) objectInputStream.readObject();
                review.setBelongsTo(MainApplication.getAnimeDAO().getByID(belongsTo));
                addOrUpdate(review);
            }

            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
