package Vista;

import Modelo.InterfazRenderizable;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class DashboardView extends JFrame {

    private final JPanel cuadricula =
            new JPanel(new GridLayout(0, 2, 15, 15));

    private final JTextField consulta = new JTextField(18);

    private final JButton listar = new JButton("Listar todos");
    private final JButton buscar = new JButton("Buscar");
    private final JButton ordenar = new JButton("Ordenar por memoria");
    private final JButton interactuar = new JButton("Interactuar por ID");

    private final JLabel mensaje = new JLabel(" ");

    public DashboardView() {
        setTitle("Dashboard modular");
        setSize(1050, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel principal = new JPanel(new BorderLayout(10, 10));

        principal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel titulo = new JLabel("Panel de control empresarial");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

        JPanel filaBusqueda = new JPanel();
        filaBusqueda.add(new JLabel("ID o nombre:"));
        filaBusqueda.add(consulta);
        filaBusqueda.add(buscar);
        filaBusqueda.add(interactuar);

        JPanel filaAcciones = new JPanel();
        filaAcciones.add(listar);
        filaAcciones.add(ordenar);

        JPanel controles = new JPanel(new GridLayout(2, 1));
        controles.add(filaBusqueda);
        controles.add(filaAcciones);

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.add(titulo, BorderLayout.NORTH);
        cabecera.add(controles, BorderLayout.CENTER);

        principal.add(cabecera, BorderLayout.NORTH);
        principal.add(new JScrollPane(cuadricula), BorderLayout.CENTER);
        principal.add(mensaje, BorderLayout.SOUTH);

        setContentPane(principal);
    }

    public void mostrarWidgets(List<InterfazRenderizable> widgets) {
        cuadricula.removeAll();

        for (InterfazRenderizable widget : widgets) {
            JPanel tarjeta = new JPanel(new BorderLayout());

            JLabel datos = new JLabel(
                    "ID: " + widget.getID()
                    + " | Memoria: " + widget.getMemoriaKB() + " KB"
            );

            tarjeta.add(datos, BorderLayout.NORTH);
            tarjeta.add(widget.obtenerVista(), BorderLayout.CENTER);

            cuadricula.add(tarjeta);
        }

        int filas = Math.max(1, (widgets.size() + 1) / 2);

        cuadricula.setPreferredSize(
                new Dimension(950, filas * 240)
        );

        cuadricula.revalidate();
        cuadricula.repaint();

        mostrarMensaje(widgets.size() + " widgets visibles.");
    }

    public String getConsulta() {
        return consulta.getText().trim();
    }

    public void mostrarMensaje(String texto) {
        mensaje.setText(texto);
    }

    public void alListar(ActionListener accion) {
        listar.addActionListener(accion);
    }

    public void alBuscar(ActionListener accion) {
        buscar.addActionListener(accion);
    }

    public void alOrdenar(ActionListener accion) {
        ordenar.addActionListener(accion);
    }

    public void alInteractuar(ActionListener accion) {
        interactuar.addActionListener(accion);
    }
}