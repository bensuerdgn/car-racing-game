package carracinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author hp
 */
public class Surface extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private RaceCar raceCar; //kırmızı araba
    private ArrayList<OtherCars> otherCars; //karşıdan gelen diğer arabalar
    private ArrayList<Rectangle> line; //yol çizgileri
    private int WIDTH = 600;
    private int HEIGHT = 1000;
    private int space = 300;
    private int speed = 5;
    private int move = 20;
    private int skor;
    private int time;
    private int racecarX = this.WIDTH / 2 - 120;
    private Random rand;
    private BufferedImage roadImage;
    private BufferedImage roadImage2;

    public Surface() {
        initSurface();
    }

    public void initSurface() {

        raceCar = new RaceCar(racecarX, this.HEIGHT - 200);
        otherCars = new ArrayList<>();
        line = new ArrayList<Rectangle>();
        rand = new Random();
        timer = new Timer(20, this);
        timer.start();

        try {
            roadImage = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\hp\\Desktop\\road.jpg")));
            roadImage2 = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\hp\\Desktop\\road2.jpg")));
        } catch (IOException ex) {
            Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, null, ex);
        }

        addKeyListener(this);

        setFocusable(true);

        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);

        addOtherCars(true);
        addOtherCars(true);
        addOtherCars(true);
        addOtherCars(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (control()) {
            drawGameOver(g);
        } else {
            drawObjects(g);
        }
    }

    private void drawObjects(Graphics g) {

        //oyun ekranı
        Graphics2D g2d = (Graphics2D) g;

        time += 4;
        skor += time;

        g2d.drawImage(roadImage, 0, 0, 600, 1000, this);
        g2d.drawImage(roadImage2, WIDTH / 2 - 8, 0, 15, HEIGHT, this);

        //yol çizgileri
        g2d.setColor(Color.white);
        for (Rectangle line : line) {
            g2d.fillRect(line.x, line.y, line.width, line.height);
        }

        //kırmızı araba
        g2d.drawImage(raceCar.getImage(), raceCar.x, this.HEIGHT - 205, raceCar.width, raceCar.height, this);

        //siyah arabalar
        for (OtherCars oc : otherCars) {
            g2d.drawImage(oc.getImage(), oc.x, oc.y, oc.width, oc.height, this);
        }

    }

    private void drawGameOver(Graphics g) {

        //oyun bittiğinde çizilicek resim ve yazılar
        timer.stop();

        g.drawImage(roadImage, 0, 0, 600, 1000, this);
        g.drawImage(roadImage2, WIDTH / 2 - 8, 0, 15, HEIGHT, this);

        String message = "GAME OVER ";
        String message2 = "SKOR : " + skor / 1000;
        String message3 = "TIME : " + (int) time / 100.0;

        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, this.WIDTH / 2 - 155, this.HEIGHT / 2 - 100);
        g.drawString(message2, this.WIDTH / 2 - 140, this.HEIGHT / 2);
        g.drawString(message3, this.WIDTH / 2 - 140, this.HEIGHT / 2 + 100);

    }

    public void addOtherCars(boolean first) {

        //karşıdan gelen arabalar bu metodla ekleniyor
        int positionx = rand.nextInt() % 2;
        int x = 0;
        int y = 0;
        if (positionx == 0) {
            x = WIDTH / 2 - 120;
        } else {
            x = WIDTH / 2 + 60;
        }
        if (first) {
            otherCars.add(new OtherCars(x, y - 100 - (otherCars.size() * space)));
        } else {
            otherCars.add(new OtherCars(x, otherCars.get(otherCars.size() - 1).y - 300));
        }
    }

    public void addLine(boolean first) {

        //yol çizgileri bu metodla ekleniyor
        int x = WIDTH / 2 - 8;
        int y = line.size() * 200;
        if (first) {
            line.add(new Rectangle(x, y - otherCars.size(), 15, 70));
        } else {
            line.add(new Rectangle(x, line.get(line.size() - 1).y - 200, 15, 70));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action();
        repaint();
    }

    public void action() {
        Rectangle rect;
        OtherCars oc;

        //karşıdan gelen arabaların ilerlemesini sağlıyor
        for (int i = 0; i < otherCars.size(); i++) {
            oc = otherCars.get(i);
            oc.y += speed + 1;
        }

        //karşıdan gelen arabalr ekran dışına çıkınca siliniyor 
        for (int i = 0; i < otherCars.size(); i++) {
            oc = otherCars.get(i);
            if (oc.y + oc.height > this.HEIGHT) {
                otherCars.remove(oc);
                addOtherCars(false);
            }
        }

        //yol çizgilerinin ilerlemesini sağlıyor
        for (int i = 0; i < line.size(); i++) {
            rect = line.get(i);
            rect.y += speed;
        }

        for (int i = 0; i < line.size(); i++) {
            rect = line.get(i);
            if (rect.y + rect.height >= this.HEIGHT) {
                line.remove(rect);
                addLine(false);
            }
        }
    }

    public boolean control() {

        //Bu metodda arabaların çarpışıp çarpışmadığı kontrol ediliyor
        Rectangle r;
        Rectangle r2;
        for (OtherCars oc : otherCars) {
            r = oc.getBounds();
            r2 = raceCar.getBounds();
            if (r.intersects(r2)) {
                return true;
            }
        }
        return false;
    }

    public void moveleft() {

        //Sol yön tuşuna basınca arabanın sola hareket etmesini sağlıyor
        if (raceCar.x - move < WIDTH / 2 - 150) {
            System.out.println("\b");
        } else {
            raceCar.x -= move;
        }
    }

    public void moveright() {

        //Sağ yön tuşuna basınca arabanın sağa hareket etmesini sağlıyor
        if (raceCar.x + move > WIDTH / 2 + 80) {
            System.out.println("\b");
        } else {
            raceCar.x += move;
        }
    }

    public void moveup() {

        //Yukarı yön tuşuna basınca arabanın hızlanmasını sağlıyor
        if (speed > 10) {
            System.out.println("\b");
        } else {
            speed++;
        }
    }

    public void movedown() {

        //Aşağı yön tuşuna basınca arabanın yavaşlamasını sağlıyor
        if (speed < 2) {
            System.out.println("\b");
        } else {
            speed--;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moveleft();
        }
        if (key == KeyEvent.VK_RIGHT) {
            moveright();
        }
        if (key == KeyEvent.VK_DOWN) {
            movedown();
        }
        if (key == KeyEvent.VK_UP) {
            moveup();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
