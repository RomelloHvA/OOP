package practicumopdracht.models;

import java.time.LocalDate;

public class Anime {
    private String name;
    private LocalDate releaseDate;
    private int episodes;
    private String synopsis;
    private boolean downloaded;
    private boolean watched;

    public Anime(String name, LocalDate releaseDate, int episodes, String synopsis, boolean downloaded, boolean watched) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.episodes = episodes;
        this.synopsis = synopsis;
        this.downloaded = downloaded;
        this.watched = watched;
    }

    @Override
    public String toString() {
        return
                "name: " + name + '\n' +
                "releaseDate: " + releaseDate +"\n" +
                "episodes: " + episodes +  "\n" +
                "synopsis: " + synopsis + '\n' +
                "downloaded: " + downloaded + "\n" +
                "watched: " + watched;
    }

    public String getName() {
        return name;
    }
}
