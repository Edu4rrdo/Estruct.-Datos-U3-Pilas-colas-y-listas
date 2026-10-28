package PilasYColas;

import java.util.List;

/**
 * ============================================================================
 * CLASE ABSTRACTA: EstructuraLineal
 * ============================================================================
 * Clase base y modelo comun para estructuras de datos lineales enlazadas
 * (Pilas y Colas dinamicas).
 *
 * Agrupa la logica compartida:
 * - Apuntador inicial 'P' que senala el inicio o tope de la estructura.
 * - Metodos comunes para saber si esta vacia o para limpiarla.
 * - Declaracion de metodos polimorficos (insertar, eliminar, aLista, etc.)
 *   que las clases hijas implementan con sus propias reglas (LIFO o FIFO).
 * - Textos descriptivos para personalizar la interfaz visual (PanelBase).
 *
 * @author eduardo-b
 * ============================================================================
 */
public abstract class EstructuraLineal {

    /**
     * Apuntador o referencia de entrada a la estructura:
     * - En una Pila representa el TOPE (cima).
     * - En una Cola representa el inicio de la cadena de nodos.
     */
    public Nodo P;

    /**
     * Constructor base: inicializa la estructura completamente vacia.
     */
    public EstructuraLineal() {
        this.P = null;
    }

    /**
     * Comprueba si la estructura carece de elementos enlazados.
     *
     * @return true si esta vacia (P apunta a null), false si contiene al menos un nodo.
     */
    public boolean estaVacia() {
        return this.P == null;
    }

    /**
     * Elimina todos los elementos de la estructura liberando la referencia inicial.
     */
    public void vaciar() {
        this.P = null;
    }

    /**
     * Inserta un nuevo elemento entero en la estructura.
     * En una Pila se inserta en el tope (Push).
     * En una Cola se ingresa segun la regla de encolado.
     *
     * @param valorAInsertar Numero entero a almacenar.
     */
    public abstract void insertar(int valorAInsertar);

    /**
     * Elimina y retorna el elemento correspondiente segun la regla de la estructura:
     * - En Pila: elimina el ultimo en entrar (Pop).
     * - En Cola: elimina el primero en entrar (Desencolar).
     *
     * @return El numero entero extraido, o null si la estructura se encontraba vacia.
     */
    public abstract Integer eliminar();

    /**
     * Convierte y recopila todos los datos enlazados en una lista secuencial de Java
     * para facilitar su dibujo visual en la interfaz grafica.
     *
     * @return Lista de enteros en el orden didactico correspondiente.
     */
    public abstract List<Integer> aLista();

    /**
     * Titulo que se muestra en el encabezado de la pantalla grafica.
     *
     * @return Texto representativo (ej. "Pila Dinamica (LIFO)" o "Cola Dinamica (FIFO)").
     */
    public abstract String getTitulo();

    /**
     * Texto informativo del boton para insertar (ej. "Insertar (Push)" o "Insertar (Encolar)").
     */
    public abstract String getTextoInsertar();

    /**
     * Texto informativo del boton para eliminar (ej. "Eliminar (Pop)" o "Eliminar (Desencolar)").
     */
    public abstract String getTextoEliminar();

    /**
     * Indicador visual superior para los recuadros de nodos.
     */
    public abstract String getIndicadorArriba();

    /**
     * Indicador visual inferior para los recuadros de nodos.
     */
    public abstract String getIndicadorAbajo();
}
