package Util;

public interface Crudable<O> extends Serviceable<O>{
    boolean update(O updatedObject);
    boolean delete();
}