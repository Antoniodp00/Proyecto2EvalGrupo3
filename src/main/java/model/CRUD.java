package model;

import java.util.List;

public class CRUD <T>{
    List<T> lista;
    public CRUD(List<T> lista){
        this.lista = lista;
    }
    public List getElemento(){
        return lista;
    }
    public void setElemento(List elemento){
        this.lista = elemento;
    }
    public boolean addElemento(T elemento){
       return lista.add(elemento);
    }
    public boolean removeElemento(T elemento){
        return lista.remove(elemento);
    }
    public boolean updateElemento(T elemento){
        boolean update = false;
        int index = lista.indexOf(elemento);
        if(index != -1){
            lista.set(index, elemento);
            update=true;
        }
        return update;
    }
}
