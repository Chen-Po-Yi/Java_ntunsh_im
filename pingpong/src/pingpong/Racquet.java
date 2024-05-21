package pingpong;

import java.awt.*;

public class Racquet {
    private int x, y, width, height;
    private int speed = 1; // Initial speed of the racquet
    private Color color;

    public Racquet(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void move(boolean leftPressed, boolean rightPressed, int windowWidth) {
        if (leftPressed) {
            x -= speed;
        }
        if (rightPressed) {
            x += speed;
        }

        if (x < 0) {
            x = 0;
        }
        if (x + width > windowWidth) {
            x = windowWidth - width;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
