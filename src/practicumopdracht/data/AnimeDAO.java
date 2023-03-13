package practicumopdracht.data;

import practicumopdracht.models.Anime;

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

        return true;
    }

}
