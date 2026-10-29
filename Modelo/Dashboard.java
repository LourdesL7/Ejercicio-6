package Modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Dashboard {

    // Colección polimórfica de widgets.
    private final List<InterfazRenderizable> widgets =
            new ArrayList<>();

    public void init() {
        widgets.clear();

        agregarWidget(new WeatherWidget(
                1, "Clima Guatemala",
                "Ciudad de Guatemala", 25, 128
        ));

        agregarWidget(new WeatherWidget(
                2, "Clima Antigua",
                "Antigua Guatemala", 22, 120
        ));

        agregarWidget(new TaskWidget(
                3, "Tareas del equipo",
                Arrays.asList(
                        "Revisar informes",
                        "Preparar reunión"
                ),
                256
        ));

        agregarWidget(new TaskWidget(
                4, "Tareas personales",
                Arrays.asList(
                        "Entregar ejercicio",
                        "Repasar interfaces"
                ),
                192
        ));

        agregarWidget(new AlertWidget(
                5, "Alerta del servidor",
                "Espacio en disco bajo", "Alta", 64
        ));

        agregarWidget(new AlertWidget(
                6, "Aviso de mantenimiento",
                "Revisar copia de seguridad", "Media", 72
        ));

        agregarWidget(new ClockWidget(
                7, "Reloj principal", 32
        ));

        agregarWidget(new ClockWidget(
                8, "Reloj del equipo", 32
        ));

        agregarWidget(new QuoteWidget(
                9, "Frase del día",
                "Cada paso cuenta.", "Anónimo", 16
        ));

        agregarWidget(new QuoteWidget(
                10, "Motivación",
                "Aprender también es avanzar.", "Anónimo", 20
        ));
    }

    public void agregarWidget(InterfazRenderizable widget) {
        if (widget == null) {
            throw new IllegalArgumentException(
                    "El widget no puede ser null."
            );
        }

        if (buscarPorID(widget.getID()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un widget con ese ID."
            );
        }

        widgets.add(widget);
    }

    public List<InterfazRenderizable> listarWidgets() {
        // Devuelve una copia para proteger la lista interna.
        return new ArrayList<>(widgets);
    }

    public InterfazRenderizable buscarPorID(int id) {
        for (InterfazRenderizable widget : widgets) {
            if (widget.getID() == id) {
                return widget;
            }
        }

        return null;
    }

    public List<InterfazRenderizable> buscarPorNombre(
            String nombre) {

        List<InterfazRenderizable> encontrados =
                new ArrayList<>();

        String consulta = nombre.trim()
                .toLowerCase(Locale.ROOT);

        for (InterfazRenderizable widget : widgets) {
            String titulo = widget.getTitulo()
                    .toLowerCase(Locale.ROOT);

            if (titulo.contains(consulta)) {
                encontrados.add(widget);
            }
        }

        return encontrados;
    }

    public boolean interactuarPorID(int id) {
        InterfazRenderizable widget = buscarPorID(id);

        if (widget instanceof InterfazInteractivo) {
            InterfazInteractivo interactivo =
                    (InterfazInteractivo) widget;

            interactivo.interactuar();
            return true;
        }

        return false;
    }

    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }
}