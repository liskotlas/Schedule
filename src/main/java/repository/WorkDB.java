package repository;

import java.util.List;

public interface WorkDB<E> {

    public E getRowDB(String id);

    public void updateRowDB();

    public void insertRowDB();

    public void deleteRowDB(String id);

    public List<E> getDB();

}
