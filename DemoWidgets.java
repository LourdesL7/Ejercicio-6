import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;

public class DemoWidgets {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame ventana = new JFrame("Dashboard modular");

            JPanel contenedor = new JPanel(
                    new GridLayout(2, 2, 15, 15)
            );

            contenedor.setBorder(
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
            );

            InterfazRenderizable[] widgets = {

                new WeatherWidget(
                        1, "Clima",
                        "Ciudad de Guatemala", 25, 128
                ),

                new ClockWidget(
                        2, "Reloj digital", 32
                ),

                new TaskWidget(
                        3, "Tareas pendientes",
                        Arrays.asList(
                                "Entregar ejercicio",
                                "Repasar interfaces"
                        ),
                        256
                ),

                new QuoteWidget(
                        4, "Frase del día",
                        "Cada paso cuenta.", "Anónimo", 16
                )
            };

            for (InterfazRenderizable widget : widgets) {
                contenedor.add(widget.obtenerVista());
            }

            ventana.add(contenedor);
            ventana.setSize(1000, 650);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}