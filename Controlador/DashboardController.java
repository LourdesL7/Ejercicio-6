package Controlador;

import Modelo.Dashboard;
import Modelo.InterfazRenderizable;
import Vista.DashboardView;
import java.util.Collections;
import java.util.List;

public class DashboardController {

    private final Dashboard modelo;
    private final DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.alListar(e -> listar());
        vista.alBuscar(e -> buscar());
        vista.alInteractuar(e -> interactuar());

        vista.alOrdenar(e -> {
            modelo.ordenarPorMemoria();
            listar();

            vista.mostrarMensaje(
                    "Widgets ordenados de menor a mayor memoria."
            );
        });
    }

    public void iniciar() {
        modelo.init();
        listar();
        vista.setVisible(true);
    }

    private void listar() {
        vista.mostrarWidgets(modelo.listarWidgets());
    }

    private void buscar() {
        String consulta = vista.getConsulta();

        if (consulta.isEmpty()) {
            listar();
            return;
        }

        List<InterfazRenderizable> resultados;

        try {
            int id = Integer.parseInt(consulta);

            InterfazRenderizable widget =
                    modelo.buscarPorID(id);

            resultados = widget == null
                    ? Collections.emptyList()
                    : Collections.singletonList(widget);

        } catch (NumberFormatException ex) {
            resultados = modelo.buscarPorNombre(consulta);
        }

        vista.mostrarWidgets(resultados);

        if (resultados.isEmpty()) {
            vista.mostrarMensaje("No se encontraron widgets.");
        }
    }

    private void interactuar() {
        try {
            int id = Integer.parseInt(vista.getConsulta());

            if (modelo.buscarPorID(id) == null) {
                vista.mostrarMensaje(
                        "No existe un widget con ese ID."
                );

            } else if (modelo.interactuarPorID(id)) {
                vista.mostrarMensaje("Interacción realizada.");

            } else {
                vista.mostrarMensaje(
                        "Este widget es informativo y no permite interacción."
                );
            }

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje(
                    "Para interactuar, escribe un ID numérico."
            );
        }
    }
}