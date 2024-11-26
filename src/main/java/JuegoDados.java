import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoDados {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Juego de Dados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        
        JLabel titulo = new JLabel("Juego de Dados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);


        JPanel panelDados = new JPanel();
        panelDados.setLayout(new GridLayout(1, 2, 20, 20));
        JButton dado1 = new JButton(new ImageIcon("dado1.png"));
        JButton dado2 = new JButton(new ImageIcon("dado1.png"));
        panelDados.add(dado1);
        panelDados.add(dado2);

        panel.add(panelDados, BorderLayout.CENTER);


        JLabel resultadoJugador1 = new JLabel("Jugador 1: ", SwingConstants.CENTER);
        JLabel resultadoJugador2 = new JLabel("Jugador 2: ", SwingConstants.CENTER);
        JLabel ganador = new JLabel("", SwingConstants.CENTER);

        JPanel panelResultados = new JPanel(new GridLayout(4, 1));
        panelResultados.add(resultadoJugador1);
        panelResultados.add(resultadoJugador2);
        panelResultados.add(ganador);


        JButton volverAJugar = new JButton("Volver a Jugar");
        volverAJugar.setVisible(false); 
        panelResultados.add(volverAJugar);

        panel.add(panelResultados, BorderLayout.SOUTH);

       
        dado1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado = (int) (Math.random() * 6 + 1);
                dado1.setIcon(new ImageIcon("dado" + resultado + ".png"));
                resultadoJugador1.setText("Jugador 1 lanzó: " + resultado);
            }
        });

        dado2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado = (int) (Math.random() * 6 + 1);
                dado2.setIcon(new ImageIcon("dado" + resultado + ".png"));
                resultadoJugador2.setText("Jugador 2 lanzó: " + resultado);
            }
        });

        
        JButton determinarGanador = new JButton("Determinar Ganador");
        panel.add(determinarGanador, BorderLayout.EAST);

        determinarGanador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto1 = resultadoJugador1.getText().replace("Jugador 1 lanzó: ", "").trim();
                String texto2 = resultadoJugador2.getText().replace("Jugador 2 lanzó: ", "").trim();

                try {
                    int jugador1 = Integer.parseInt(texto1);
                    int jugador2 = Integer.parseInt(texto2);

                    if (jugador1 > jugador2) {
                        ganador.setText("Ganador: Jugador 1");
                    } else if (jugador2 > jugador1) {
                        ganador.setText("Ganador: Jugador 2");
                    } else {
                        ganador.setText("Resultado: ¡Empate!");
                    }
                    determinarGanador.setEnabled(false); 
                    volverAJugar.setVisible(true); 
                } catch (NumberFormatException ex) {
                    ganador.setText("Lanza ambos dados primero.");
                }
            }
        });

       
        volverAJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                resultadoJugador1.setText("Jugador 1: ");
                resultadoJugador2.setText("Jugador 2: ");
                ganador.setText("");
                dado1.setIcon(new ImageIcon("dado1.png"));
                dado2.setIcon(new ImageIcon("dado1.png"));
                determinarGanador.setEnabled(true);
                volverAJugar.setVisible(false);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
