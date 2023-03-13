package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

import java.time.LocalDate;

public class ReviewDummyDAO extends ReviewDAO {
    @Override
    public boolean load() {
        Anime bleach = MainApplication.getAnimeDAO().getAll().get(0);
        Anime naruto = MainApplication.getAnimeDAO().getAll().get(1);
        Anime onePiece = MainApplication.getAnimeDAO().getAll().get(2);

        Review bleachReview = new Review(bleach,"Romello", LocalDate.now(),4.9,"Nice samurais man", true );
        Review narutoReview = new Review(naruto,"Romello", LocalDate.now(),5.0,"Nice ninjas man", true );
        Review onePieceReview = new Review(onePiece,"Romello", LocalDate.now(),2.5,"Nice pirates man", false );

        addOrUpdate(bleachReview);
        addOrUpdate(narutoReview);
        addOrUpdate(onePieceReview);

        return super.load();
    }
}
