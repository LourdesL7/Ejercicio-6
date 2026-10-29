import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ClockWidget implements InterfazRenderizable {

    private int id;
    private String titulo;
    private LocalTime horaActual;
    private double memoriaKB;
    private JPanel panel;
    private JLabel etiqueta;
    private Timer temporizador;

    public ClockWidget(int id, String titulo, double memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.memoriaKB = memoriaKB;

        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        etiqueta = new JLabel("", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Monospaced", Font.BOLD, 32));
        panel.add(etiqueta, BorderLayout.CENTER);
        actualizarHora();

        temporizador = new Timer(1000, e -> actualizarHora());
        panel.addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (panel.isShowing()) {
                    actualizarHora();
                    temporizador.start();
                } else {
                    temporizador.stop();
                }
            }
        });
    }

    private void actualizarHora() {
        etiqueta.setText(Compartir());
    }

    @Override
    public void DibujarContenido(String contenido) {
        panel.setToolTipText(contenido);
        actualizarHora();
    }

    @Override
    public String Compartir() {
        horaActual = LocalTime.now();
        return horaActual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
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
