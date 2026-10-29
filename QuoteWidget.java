import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

public class QuoteWidget implements InterfazRenderizable {

    private int id;
    private String titulo;
    private String frase;
    private String autor;
    private double memoriaKB;
    private JPanel panel;
    private JTextArea texto;

    public QuoteWidget(int id, String titulo, String frase,
                       String autor, double memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.frase = frase;
        this.autor = autor;
        this.memoriaKB = memoriaKB;

        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        texto = new JTextArea(Compartir());
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setFont(new Font("SansSerif", Font.ITALIC, 20));
        texto.setBorder(BorderFactory.createEmptyBorder(25, 20, 20, 20));
        panel.add(texto, BorderLayout.CENTER);
    }

    @Override
    public void DibujarContenido(String contenido) {
        panel.setToolTipText(contenido);
        texto.setText(Compartir());
    }

    @Override
    public String Compartir() {
        return frase + "\n\n— " + autor;
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
