package practicumopdracht.comparators;

import practicumopdracht.models.Anime;

import java.util.Comparator;

public class NameComparator implements Comparator<Anime> {
    @Override
    public int compare(Anime anime1, Anime anime2) {
        return anime2.getName().compareTo(anime1.getName());
    }
}
