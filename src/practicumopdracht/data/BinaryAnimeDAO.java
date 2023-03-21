package practicumopdracht.data;

import practicumopdracht.models.Anime;

import java.io.*;
import java.time.LocalDate;

public class BinaryAnimeDAO extends AnimeDAO{

    private static String FILE_NAME = "anime.dat";
    @Override
    public boolean save() {
        File animeFile = new File(FILE_NAME);


        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(animeFile))){

            dataOutputStream.write(getAll().size());

            for (int i = 0; i < getAll().size(); i++) {
               dataOutputStream.writeUTF(getAll().get(i).getName());
               dataOutputStream.writeInt(getAll().get(i).getEpisodes());
               dataOutputStream.writeUTF(getAll().get(i).getReleaseDate().toString());
               dataOutputStream.writeUTF(getAll().get(i).getSynopsis());
               dataOutputStream.writeBoolean(getAll().get(i).isDownloaded());
               dataOutputStream.writeBoolean(getAll().get(i).isWatched());

            }
            return true;
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILE_NAME);
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))){
            int animeSize = dataInputStream.readInt();
            for (int i = 0; i < animeSize; i++) {
                String animeName = dataInputStream.readUTF();
                int episodeCount = dataInputStream.readInt();
                LocalDate releaseDate = LocalDate.parse(dataInputStream.readUTF());
                String synopsis = dataInputStream.readUTF();
                boolean isDownloaded = dataInputStream.readBoolean();
                boolean isWatched = dataInputStream.readBoolean();

                Anime anime = new Anime(animeName,releaseDate,episodeCount,synopsis,isDownloaded,isWatched);
                addOrUpdate(anime);

            }
            return true;
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return false;
    }
}
