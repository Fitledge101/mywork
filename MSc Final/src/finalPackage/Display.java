/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalPackage;

import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager.*;
//music stuff
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

/**
 * Create a GUI and manage the creating and bouncing of BouncingBalls in an
 * ArrayList as well as control and create the Midi music
 *
 *
 * @author Ed Taylor based on original work by
 * @author Michael Kölling and David J. Barnes
 * @version 2017.08.21
 */
public final class Display {

    private int canvasWidth;//width of canvas
    private int canvasHeight;
    private ArrayList<BouncingBall> balls;
    private Random random;
    private int startCounter;
    private int startCounterY;
    private JFrame frame;
    private JPanel jPanel;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;
    private JPanel gui;
    private String stringAmount;
    private JTextField userInput;
    private JTextField userInput2;
    private JTextField userInput3;
    private JTextField userInput4;
    private int diameterRange;//multiplier for ball diameter
    private int diameter;//base size of balls
    private String stringColour;

    //music 
    private Synthesizer synthesizer;
    private MidiChannel[] midiChannels;
    private int instrumentChoice;
    private boolean muted;

    /**
     * Constructor for class Display
     */
    public Display() {
        initialise();
        setVisible(true);

    }

    /**
     * Initialise GUI and other variable
     */
    private void initialise() {

        //set nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

//music 
        muted = false;
        instrumentChoice = 0;
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        this.midiChannels = synthesizer.getChannels();

        Soundbank bank = synthesizer.getDefaultSoundbank();

        synthesizer.loadAllInstruments(bank);
        synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
        synthesizer.getChannels()[0].programChange(instrumentChoice);
        //end of music
        //initialise variables
        canvasWidth = 900;
        canvasHeight = 600;
        balls = new ArrayList<>();
        random = new Random();
        startCounter = 1;
        startCounterY = 1;
        backgroundColor = Color.black;
        stringAmount = "not initialised";
        stringColour = "";
        diameterRange = 1;//generate diameter value for ball
        //build gui
        frame = new JFrame();
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        canvas = new CanvasPane();
        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        gui = new JPanel();
        gui.setPreferredSize(new Dimension(canvasWidth, canvasHeight / 8));//menu 
        JButton jButton = new JButton("Start");
        JButton jButton2 = new JButton("Update Diameter");
        JButton jButton3 = new JButton("Change Colour");
        JButton jButton4 = new JButton("Toggle Volume");
        JButton jButton5 = new JButton("Select Instrument");
        jButton.addActionListener(new StartBounce());
        jButton2.addActionListener(new ChangeSize());
        jButton3.addActionListener(new ChangeColour());
        jButton4.addActionListener(new Mute());
        jButton5.addActionListener(new InstrumentSelect());
        userInput = new JTextField("No. of Balls(max 50)", 15);
        userInput2 = new JTextField("Diameter Range(int up to 30)", 15);
        userInput3 = new JTextField("Colour (String)", 15);
        userInput4 = new JTextField("Choose Instrument(max 100)");
        gui.add(jButton);
        gui.add(userInput);
        gui.add(jButton2);
        gui.add(userInput2);
        gui.add(jButton3);
        gui.add(userInput3);
        gui.add(jButton4);
        gui.add(jButton5);
        gui.add(userInput4);
        jPanel.add(canvas);
        jPanel.add(gui);
        jPanel.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        frame.setContentPane(jPanel);
        frame.setTitle("Collision Music Generator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

    }

    /**
     * Add balls to an ArrayList
     *
     * @param amount of balls to create
     */
    public void addBalls(int amount) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    startCounter = 8;
                    balls = new ArrayList<>();

                    while (balls.size() < amount) {
                        int i = 0;
                        diameter = random.nextInt(30) + 10 + diameterRange;//generate diameter value for ball
                        startCounter = random.nextInt(800) + 100;//generate x start pos for ball
                        startCounterY = random.nextInt(400) + 100;//generate y start pos for ball
                        BouncingBall ball = makeBall(startCounter, startCounterY, diameter, i);
                        if (Physics.Collision(ball, balls) || ball.getXPosition() > 850 || ball.getYPosition() > 550) {//avoid starting collisions

                            continue;
                        }

                        balls.add(ball);
                        i++;

                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });

    }

    /**
     * Make the balls bounce
     *
     */
    public void bounceLater() {
        wait(20);//without wait the speed is too high
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setVisible(true);

                    for (BouncingBall ball : balls) {

                        //move ball according to its location
                        //also handles ball collisions
                        if (Physics.Collision(ball, balls) && !muted) {
                            midiChannels[0].noteOn(ball.getNote(), 600);

                            midiChannels[0].noteOff(ball.getNote(), 600);

                        }
                        ball.move(balls);

                    }
                    bounceLater();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create a BouncingBall
     *
     * @param randomStartPos of the balls
     * @param randomStartPosY of the balls
     * @param diameter of the ball
     * @param i the ID of the ball
     * @return the created ball
     */
    public BouncingBall makeBall(int randomStartPos, int randomStartPosY, int diameter, int i) {

        BouncingBall ball = new BouncingBall(randomStartPos, randomStartPosY,
                diameter, this, i, canvasWidth,
                canvasHeight, stringColour);

        return ball;
    }

    /**
     * ActionListener to start animation with user specified number of balls
     */
    class StartBounce implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        stringAmount = userInput.getText();
                        int amount = Integer.parseInt(stringAmount);
                        if (amount >= 50) {//limit max amount of balls 
                            amount = 50;
                        }

                        addBalls(amount);
                        bounceLater();
                        jPanel.revalidate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * ActionListener to change the average diameter of the balls for future
     * animations
     */
    class ChangeSize implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        String stringAmount2 = userInput2.getText();

                        diameterRange = Integer.parseInt(stringAmount2);
                        if (diameterRange > 30) {//limit max size
                            diameterRange = 30;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * ActionListener to change the colour of the balls for future animations
     */

    class ChangeColour implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        stringColour = userInput3.getText();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * ActionListener to mute and un-mute the animation
     */
    class Mute implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        if (muted == true) {
                            muted = false;
                        } else {

                            muted = true;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * ActionListener to change current instrument based on int selection Not
     * all work
     */
    class InstrumentSelect implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        String InstrumentChoice = userInput4.getText();
                        instrumentChoice = Integer.parseInt(InstrumentChoice);
                        if (instrumentChoice > 100) {
                            instrumentChoice = 100;
                        }
                        synthesizer.getChannels()[0].programChange(instrumentChoice);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    //below here are the default methods belonging to the old BallDemo Class
    //except a slight alteration to the CanvasPane inner class to implement double buffering
    //left in as they allow for theoretical future adaptions/improvements
    //some are required for proper functioning
    public void setVisible(boolean visible) {
        if (graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        } else {
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);

        }
        frame.setVisible(true);
    }

    /**
     * Provide information on visibility of the Canvas.
     *
     * @return true if canvas is visible, false otherwise
     */
    public boolean isVisible() {
        return frame.isVisible();
    }

    /**
     * Draw the outline of a given shape onto the canvas.
     *
     * @param shape the shape object to be drawn on the canvas
     */
    public void draw(Shape shape) {
        graphic.draw(shape);
        canvas.repaint();
    }

    /**
     * Fill the internal dimensions of a given shape with the current foreground
     * color of the canvas.
     *
     * @param shape the shape object to be filled
     */
    public void fill(Shape shape) {
        graphic.fill(shape);
        canvas.repaint();
    }

    /**
     * Fill the internal dimensions of the given rectangle with the current
     * foreground color of the canvas. This is a convenience method. A similar
     * effect can be achieved with the "fill" method.
     */
    public void fillRectangle(int xPos, int yPos, int width, int height) {
        fill(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase the whole canvas.
     */
    public void erase() {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erase the internal dimensions of the given circle. This is a convenience
     * method. A similar effect can be achieved with the "erase" method.
     */
    public void eraseCircle(int xPos, int yPos, int diameter) {
        Ellipse2D.Double circle = new Ellipse2D.Double(xPos, yPos, diameter, diameter);
        erase(circle);
    }

    /**
     * Erase the internal dimensions of the given rectangle. This is a
     * convenience method. A similar effect can be achieved with the "erase"
     * method.
     */
    public void eraseRectangle(int xPos, int yPos, int width, int height) {
        erase(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase a given shape's interior on the screen.
     *
     * @param shape the shape object to be erased
     */
    public void erase(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.fill(shape);              // erase by filling background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erases a given shape's outline on the screen.
     *
     * @param shape the shape object to be erased
     */
    public void eraseOutline(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.draw(shape);  // erase by drawing background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws an image onto the canvas.
     *
     * @param image the Image object to be displayed
     * @param x x co-ordinate for Image placement
     * @param y y co-ordinate for Image placement
     * @return returns boolean value representing whether the image was
     * completely loaded
     */
    public boolean drawImage(Image image, int x, int y) {
        boolean result = graphic.drawImage(image, x, y, null);
        canvas.repaint();
        return result;
    }

    /**
     * Draws a String on the Canvas.
     *
     * @param text the String to be displayed
     * @param x x co-ordinate for text placement
     * @param y y co-ordinate for text placement
     */
    public void drawString(String text, int x, int y) {
        graphic.drawString(text, x, y);
        canvas.repaint();
    }

    /**
     * Erases a String on the Canvas.
     *
     * @param text the String to be displayed
     * @param x x co-ordinate for text placement
     * @param y y co-ordinate for text placement
     */
    public void eraseString(String text, int x, int y) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, x, y);
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws a line on the Canvas.
     *
     * @param x1 x co-ordinate of start of line
     * @param y1 y co-ordinate of start of line
     * @param x2 x co-ordinate of end of line
     * @param y2 y co-ordinate of end of line
     */
    /**
     * Sets the foreground color of the Canvas.
     *
     * @param newColor the new color for the foreground of the Canvas
     */
    public void setForegroundColor(Color newColor) {
        graphic.setColor(newColor);
    }

    /**
     * Returns the current color of the foreground.
     *
     * @return the color of the foreground of the Canvas
     */
    public Color getForegroundColor() {
        return graphic.getColor();
    }

    /**
     * Sets the background color of the Canvas.
     *
     * @param newColor the new color for the background of the Canvas
     */
    public void setBackgroundColor(Color newColor) {
        backgroundColor = newColor;
        graphic.setBackground(newColor);
    }

    /**
     * Returns the current color of the background
     *
     * @return the color of the background of the Canvas
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the size of the canvas.
     *
     * @param width new width
     * @param height new height
     */
    public void setSize(int width, int height) {
        canvas.setPreferredSize(new Dimension(width, height));
        Image oldImage = canvasImage;
        canvasImage = canvas.createImage(width, height);
        graphic = (Graphics2D) canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, width, height);
        graphic.drawImage(oldImage, 0, 0, null);
        frame.pack();
    }

    /**
     * Returns the size of the canvas.
     *
     * @return The current dimension of the canvas
     */
    public Dimension getSize() {
        return canvas.getSize();
    }

    /**
     * Waits for a specified number of milliseconds before finishing. This
     * provides an easy way to specify a small delay which can be used when
     * producing animations.
     *
     * @param milliseconds the number
     */
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * **********************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {

        private Image dbImage;//double buffer the image 
        private Graphics dbGraphics;

        @Override
        public void paint(Graphics g) {
            dbImage = createImage(getWidth(), getHeight());
            dbGraphics = dbImage.getGraphics();
            paintComponent(dbGraphics);
            g.drawImage(dbImage, 0, 0, this);

        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Display window = new Display();

                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
