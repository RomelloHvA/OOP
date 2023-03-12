package practicumopdracht.data;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();

    void addOrUpdate(T model);
    void delete(T model);

    boolean save();
    boolean load();
}
