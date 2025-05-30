package patika_14_final_project.dao;

import java.util.List;

public interface BaseDAO <T>{

    long save(T t);

    T findById(long id);

    List<T> findAll(int size);

    void update(T t);

    void delete(long id);

}
