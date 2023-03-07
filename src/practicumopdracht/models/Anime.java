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

    public String toStringConfirmMessage() {
        return
                "name: " + name + '\n' +
                "releaseDate: " + releaseDate +"\n" +
                "episodes: " + episodes +  "\n" +
                "downloaded: " + downloaded + "\n" +
                "watched: " + watched;
    }

    @Override
    public String toString() {
        return String.format("%s , Episodes: %d , Release date: %s",name,episodes,releaseDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
