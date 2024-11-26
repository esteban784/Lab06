import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoTragamonedas{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tragamonedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        frame.add(panelPrincipal);

        JPanel panelSuperior = new JPanel(new GridLayout(1, 3));
        JLabel puntos = new JLabel("Puntos: 1000", SwingConstants.CENTER);
        JLabel vidas = new JLabel("Vidas: 3", SwingConstants.CENTER);
        JLabel apuesta = new JLabel("Apuesta: 100", SwingConstants.CENTER);

        panelSuperior.add(puntos);
        panelSuperior.add(vidas);
        panelSuperior.add(apuesta);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelSlots = new JPanel(new GridLayout(1, 3, 20, 20));
        JLabel slot1 = new JLabel(scaleImage("slot1.png", 200, 200));
        JLabel slot2 = new JLabel(scaleImage("slot2.png", 200, 200));
        JLabel slot3 = new JLabel(scaleImage("slot3.png", 200, 200));

        panelSlots.setPreferredSize(new Dimension(600, 200));
        panelSlots.add(slot1);
        panelSlots.add(slot2);
        panelSlots.add(slot3);

        panelPrincipal.add(panelSlots, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JButton spinButton = new JButton("SPIN");
        JButton resetButton = new JButton("Volver a Jugar");

        panelInferior.add(spinButton);
        panelInferior.add(resetButton);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        String[] simbolos = {
            "fruta1.png", "fruta2.png", "fruta3.png",
            "numero7.png", "diamante.png", "campana.png"
        };

        spinButton.addActionListener(new ActionListener() {
            Random random = new Random();

            @Override
            public void actionPerformed(ActionEvent e) {
                slot1.setIcon(scaleImage(simbolos[random.nextInt(simbolos.length)], 200, 200));
                slot2.setIcon(scaleImage(simbolos[random.nextInt(simbolos.length)], 200, 200));
                slot3.setIcon(scaleImage(simbolos[random.nextInt(simbolos.length)], 200, 200));

                int puntosActuales = Integer.parseInt(puntos.getText().split(" ")[1]);
                int vidasActuales = Integer.parseInt(vidas.getText().split(" ")[1]);

                if (slot1.getIcon() != null && 
                    slot1.getIcon().toString().equals(slot2.getIcon().toString()) && 
                    slot2.getIcon().toString().equals(slot3.getIcon().toString())) {
                    
                    puntosActuales += 500;
                    puntos.setText("Puntos: " + puntosActuales);
                    JOptionPane.showMessageDialog(frame, "¡Felicidades! Ganaste 500 puntos.", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    puntosActuales -= 100;

                    if (puntosActuales <= 0) {
                        vidasActuales -= 1;
                        vidas.setText("Vidas: " + vidasActuales);

                        if (vidasActuales > 0) {
                            puntosActuales = 1000;
                            JOptionPane.showMessageDialog(frame, "Perdiste una vida. Se reiniciaron tus puntos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "¡Se te acabaron las vidas! Fin del juego.", "Game Over", JOptionPane.ERROR_MESSAGE);
                            spinButton.setEnabled(false);
                        }
                    }

                    puntos.setText("Puntos: " + puntosActuales);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.setText("Puntos: 1000");
                vidas.setText("Vidas: 3");
                spinButton.setEnabled(true);
                JOptionPane.showMessageDialog(frame, "El juego se ha reiniciado. ¡Buena suerte!", "Reinicio", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
