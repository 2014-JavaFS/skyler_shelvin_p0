package Util;

import java.util.List;

public interface Serviceable<O>{
    List<O> findAll();
    O create(O newObject);

    O findById(int number);

    O findByEmail(String email);
}
