/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carracinggame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author hp
 */
public class Variable {

    protected int width;
    protected int height;
    protected int WIDTH;
    private int HEIGHT;
    protected Image image;
    protected int x;
    protected int y;

    public Variable(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 70;
        this.WIDTH = 600;
        this.HEIGHT = 1000;
    }

    protected void getImageDimensions() {

        //width = image.getWidth(null);
        //  height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
