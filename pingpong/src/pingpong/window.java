package pingpong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class window extends JFrame implements ActionListener, KeyListener {
    static int score;
    private Ball ball;
    private Racquet racquet;
    private Timer timer;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public window() {
        setTitle("Ping Pong");
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        int ballSize = 20;
        Color ballColor = Color.RED;
        ball = new Ball(getWidth() / 2 - ballSize / 2, getHeight() / 2 - ballSize / 2, ballSize, ballColor);

        int racquetWidth = 80;
        int racquetHeight = 10;
        int racquetY = getHeight() - 50;
        Color racquetColor = Color.BLUE;
        racquet = new Racquet(getWidth() / 2 - racquetWidth / 2, racquetY, racquetWidth, racquetHeight, racquetColor);

        int delay = 10;
        timer = new Timer(delay, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        ball.draw(g);
        racquet.draw(g);

        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);

        int x = getWidth() - 100;
        int y = 50;
        g2d.drawString("Score: " + score, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBallPosition();
        updateRacquetPosition();
        checkCollision();
        repaint();
    }

    private void updateBallPosition() {
        ball.setX(ball.getX() + ball.getXVelocity());
        ball.setY(ball.getY() + ball.getYVelocity());

        if (ball.getX() < 0 || ball.getX() + ball.getDiameter() > getWidth()) {
            ball.setXVelocity(-ball.getXVelocity());
        }
        if (ball.getY() < 0 || ball.getY() + ball.getDiameter() > getHeight()) {
            ball.setYVelocity(-ball.getYVelocity());
        }
    }

    private void updateRacquetPosition() {
        racquet.move(leftPressed, rightPressed, getWidth());
    }

    private void checkCollision() {
        if (ball.getBounds().intersects(racquet.getBounds())) {
            ball.setYVelocity(-ball.getYVelocity());
            score++;
            // Increase ball speed by 10%
            ball.setXVelocity((int) (ball.getXVelocity() * 1.1));
            ball.setYVelocity((int) (ball.getYVelocity() * 1.1));
            // Increase racquet speed by 10%
            racquet.setSpeed((int) (racquet.getSpeed() * 1.1));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new window();
    }
}
