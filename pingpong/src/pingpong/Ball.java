package pingpong;

import java.awt.*;

public class Ball {
    private int x, y, diameter;
    private int xVelocity = 1, yVelocity = 1;
    private Color color;

    public Ball(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }
}
