package practicumopdracht.data;

import practicumopdracht.models.Anime;

import java.time.LocalDate;

public class DummyAnimeDAO extends AnimeDAO{
    @Override
    public boolean load() {
        Anime firstAnime = new Anime("Bleach", LocalDate.now(),12,"Dead samurai",true,true);
        Anime secondAnime = new Anime("Naruto", LocalDate.now(),700,"God ninja",false,true);
        Anime thirdAnime = new Anime("One Piece", LocalDate.now(),1000,"Pirates",false,false);

        addOrUpdate(firstAnime);
        addOrUpdate(secondAnime);
        addOrUpdate(thirdAnime);
        return true;
    }

    @Override
    public boolean save() {
        return true;
    }
}
