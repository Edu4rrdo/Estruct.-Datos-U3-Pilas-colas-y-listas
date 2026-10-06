package PilasDinamicas;

public class ListaPila {
    public Nodo P;
    
    public ListaPila(){
        this.P = null;
    }
    
    //Funcion de insertar que la hace funcionar como una pila
    public void insertarInicio(int valor){
        Nodo Q = new Nodo(valor);
        Q.liga = P;
        P = Q; 
    }
    
    //Funcion para eliminar (pop)
    public Integer eliminarInicio(){
        if (P == null) return null;
        int valor = P.info;
        P = P.liga;
        return valor;
    }

    public void mostrar(){
        Nodo temp = P;
        System.out.println("Elementos de la pila");
        while(temp != null) {
            System.out.println(temp.info);
            temp = temp.liga;
        }
        System.out.println("fin de la pila");
    }
}

