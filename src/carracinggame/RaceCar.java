/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RaceCar extends Variable {

    public RaceCar(int x, int y) {
        super(x, y);
        initRaceCar();
    }

    public void initRaceCar() {
        loadImage("C:\\Users\\hp\\Desktop\\red-car.png");
        getImageDimensions();
    }

}
