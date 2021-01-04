/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carracinggame;

/**
 *
 * @author hp
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

public class CarRacingGame extends JFrame {

    public CarRacingGame() {
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("Car Racing Game");
        setSize(600, 1000);
       // setResizable(false); //Sayfanın yeniden boyutlandırılmamasını sağlıyor
        setLocationRelativeTo(null);  //Açılan pencerenin ortada açılmasını sağlıyor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programın sonlandırılmasını sağlıyor
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                CarRacingGame ex = new CarRacingGame();
                ex.setVisible(true);
            }
        });
    }
}

