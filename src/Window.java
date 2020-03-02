import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.glass.ui.Cursor.setVisible;

public class Window extends JFrame {
    public static void main(String[] args) {
        new Window().start();
    }

    public void start() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);

        setVisible(true);
    }

}
