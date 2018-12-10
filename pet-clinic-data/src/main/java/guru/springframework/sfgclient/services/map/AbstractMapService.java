package guru.springframework.sfgclient.services.map;

import guru.springframework.sfgclient.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {
    protected Map<Long,T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<> (map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if (object != null) {
            map.put(this.getNextId(), object);
        }else{
            throw new RuntimeException("Object not found!!!");
        }
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry ->entry.getValue().equals(object));
    }

    private Long getNextId(){
        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        }catch(NoSuchElementException e){
            nextId = 1L;
        }
        return nextId;
    }
}
