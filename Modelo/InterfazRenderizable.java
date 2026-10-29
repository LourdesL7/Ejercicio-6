package Modelo;
import javax.swing.JComponent;

public interface InterfazRenderizable
        extends Comparable<InterfazRenderizable> {

    void DibujarContenido(String contenido);

    String getTitulo();

    int getID();

    double getMemoriaKB();

    String Compartir();

    JComponent obtenerVista();

    @Override
    default int compareTo(InterfazRenderizable otro) {
        return Double.compare(
                getMemoriaKB(),
                otro.getMemoriaKB()
        );
    }
}