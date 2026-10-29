import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.*;

public class WeatherWidget
        implements InterfazRenderizable, InterfazInteractivo {

    private int id;
    private String titulo;
    private String ciudad;
    private double temperaturaCelsius;
    private boolean mostrarCelsius = true;
    private double memoriaKB;
    private JPanel panel;
    private JLabel etiqueta;

    public WeatherWidget(int id, String titulo, String ciudad,
                         double temperaturaCelsius, double memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.ciudad = ciudad;
        this.temperaturaCelsius = temperaturaCelsius;
        this.memoriaKB = memoriaKB;

        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        etiqueta = new JLabel(Compartir(), SwingConstants.CENTER);
        JButton boton = new JButton("Cambiar °C / °F");
        boton.addActionListener(e -> interactuar());
        panel.add(etiqueta, BorderLayout.CENTER);
        panel.add(boton, BorderLayout.SOUTH);
    }

    @Override
    public void interactuar() {
        mostrarCelsius = !mostrarCelsius;
        etiqueta.setText(Compartir());
    }

    @Override
    public void DibujarContenido(String contenido) {
        panel.setToolTipText(contenido);
        etiqueta.setText(Compartir());
    }

    @Override
    public String Compartir() {
        double valor = mostrarCelsius ? temperaturaCelsius
                : temperaturaCelsius * 9.0 / 5.0 + 32;
        String unidad = mostrarCelsius ? "°C" : "°F";
        return String.format(Locale.ROOT, "%s: %.1f %s", ciudad, valor, unidad);
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
