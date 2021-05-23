package ua.com.alevel.dao;

import ua.com.alevel.models.AbstractModel;

import java.util.List;

public interface AbstractDao <DATA extends AbstractModel>{
    void create(DATA data);
    DATA read(int id);
    List<DATA> read();
    void update(DATA data);
    void delete(int id);
}
