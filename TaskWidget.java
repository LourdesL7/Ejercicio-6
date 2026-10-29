import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TaskWidget
        implements InterfazRenderizable, InterfazInteractivo {

    private int id;
    private String titulo;
    private List<String> tareasPendientes;
    private List<String> tareasCompletadas;
    private double memoriaKB;
    private JPanel panel;
    private JTextArea texto;
    private JButton boton;

    public TaskWidget(int id, String titulo,
                      List<String> tareas, double memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.tareasPendientes = new ArrayList<>(tareas);
        this.tareasCompletadas = new ArrayList<>();
        this.memoriaKB = memoriaKB;

        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        texto = new JTextArea();
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        boton = new JButton("Completar primera pendiente");
        boton.addActionListener(e -> interactuar());
        panel.add(new JScrollPane(texto), BorderLayout.CENTER);
        panel.add(boton, BorderLayout.SOUTH);
        actualizarVista();
    }

    @Override
    public void interactuar() {
        if (!tareasPendientes.isEmpty()) {
            tareasCompletadas.add(tareasPendientes.remove(0));
        }
        actualizarVista();
    }

    private void actualizarVista() {
        texto.setText(Compartir());
        boton.setEnabled(!tareasPendientes.isEmpty());
    }

    @Override
    public void DibujarContenido(String contenido) {
        panel.setToolTipText(contenido);
        actualizarVista();
    }

    @Override
    public String Compartir() {
        StringBuilder resultado = new StringBuilder("Pendientes:\n");
        for (String tarea : tareasPendientes) resultado.append("• ").append(tarea).append('\n');
        resultado.append("\nCompletadas:\n");
        for (String tarea : tareasCompletadas) resultado.append("✓ ").append(tarea).append('\n');
        return resultado.toString();
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public int getID() { return id; }

    @Override
    public double getMemoriaKB() { return memoriaKB; }

    @Override
    public JComponent obtenerVista() { return panel; }

}