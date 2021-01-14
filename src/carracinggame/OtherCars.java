package carracinggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author hp
 */
public class OtherCars extends Variable {

    private int move = 0;

    public OtherCars(int x, int y) {
        super(x, y);
        initOtherCars();
    }

    public void initOtherCars() {
        loadImage("C:\\Users\\hp\\Desktop\\black-car.png");
        getImageDimensions();
    }

}
