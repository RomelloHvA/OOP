package practicumopdracht.data;

import practicumopdracht.models.Anime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimeDAO  implements DAO<Anime> {

    private ArrayList<Anime> animeArrayList;

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
        }

    }

    @Override
    public void delete(Anime anime) {
        animeArrayList.remove(anime);
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {

        Anime firstAnime = new Anime("Bleach", LocalDate.now(),12,"Dead samurai",true,true);
        Anime secondAnime = new Anime("Naruto", LocalDate.now(),700,"God ninja",false,true);
        Anime thirdAnime = new Anime("One Piece", LocalDate.now(),1000,"Pirates",false,false);

        animeArrayList.add(firstAnime);
        animeArrayList.add(secondAnime);
        animeArrayList.add(thirdAnime);

        return true;
    }
}
