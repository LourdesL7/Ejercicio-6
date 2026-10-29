package Modelo;
import java.awt.BorderLayout;
import javax.swing.*;

public class AlertWidget
        implements InterfazRenderizable, InterfazInteractivo {

    private int id;
    private String titulo;
    private String mensaje;
    private String prioridad;
    private boolean leida;
    private double memoriaKB;
    private JPanel panel;
    private JTextArea texto;
    private JButton boton;

    public AlertWidget(int id, String titulo, String mensaje,
                       String prioridad, double memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.memoriaKB = memoriaKB;

        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        texto = new JTextArea(Compartir());
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        boton = new JButton("Marcar como leída");
        boton.addActionListener(e -> interactuar());
        panel.add(texto, BorderLayout.CENTER);
        panel.add(boton, BorderLayout.SOUTH);
    }

    @Override
    public void interactuar() {
        leida = true;
        texto.setText(Compartir());
        boton.setEnabled(false);
    }

    @Override
    public void DibujarContenido(String contenido) {
        panel.setToolTipText(contenido);
        texto.setText(Compartir());
    }

    @Override
    public String Compartir() {
        return mensaje + "\nPrioridad: " + prioridad
                + "\nEstado: " + (leida ? "Leída" : "Pendiente de leer");
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