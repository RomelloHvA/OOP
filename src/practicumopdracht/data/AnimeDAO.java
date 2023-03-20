package practicumopdracht.data;

import practicumopdracht.controllers.AnimeSelectorController;
import practicumopdracht.models.Anime;
import practicumopdracht.views.AnimeSelectorView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AnimeDAO  implements DAO<Anime> {

    private ArrayList<Anime> animeArrayList;

    public Anime getByID(int index){
        for (int i = 0; i < animeArrayList.size(); i++) {
            if (index == i){
                return animeArrayList.get(i);
            }
        }
        return null;
    }

    public int getIdFor(Anime anime){
        if (!animeArrayList.contains(anime)){
            return -1;
        } else {
            return animeArrayList.indexOf(anime);
        }
    }

    public AnimeDAO(){
        animeArrayList = new ArrayList<>();
    }

    @Override
    public List<Anime> getAll() {
        return animeArrayList;
    }

    @Override
    public void addOrUpdate(Anime anime) {
        if (!animeArrayList.contains(anime)){
            animeArrayList.add(anime);
        } else {
            for (int i = 0; i < animeArrayList.size(); i++) {
                if (i == getIdFor(anime)){
                    animeArrayList.get(i).setName(anime.getName());
                    animeArrayList.get(i).setDownloaded(anime.isDownloaded());
                    animeArrayList.get(i).setEpisodes(anime.getEpisodes());
                    animeArrayList.get(i).setSynopsis(anime.getSynopsis());
                    animeArrayList.get(i).setWatched(anime.isWatched());
                    animeArrayList.get(i).setReleaseDate(anime.getReleaseDate());
                }
            }


        }

    }

    @Override
    public void delete(Anime anime) {
        animeArrayList.remove(anime);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();

}
