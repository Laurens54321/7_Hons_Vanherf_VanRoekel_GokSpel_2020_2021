package model;

import java.util.ArrayList;
import java.util.List;

public class GeneriekeList <T> {
    private List <T> lijst = new ArrayList<>();

    public void voegToe (T element){
        lijst.add(element);
    }

    public List<T> getAll(){
        return lijst;
    }
}
