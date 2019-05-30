package Lab.Windows;

import Lab.ServerClient.Client;
import Lab.Things.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ViewPanel extends JPanel implements MouseListener {

    private final int width = 720;
    private final int height = 460;
    private static int x = 1;
    private static int y = 1;
    private JPanel panel;
    private int countPlaces = 5;

    public ViewPanel(){
        panel = new JPanel(new BorderLayout());
        new Thread(() -> {
            while (true){
                synchronized (this){
                    panel.repaint();
                    panel.revalidate();
                    try {
                        wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        panel.setMinimumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));

        JButton up = new JButton("↑");
        up.addActionListener(e -> {
            y = Math.max(1, y-1);
            panel.repaint();
            panel.revalidate();
        });
        up.setMinimumSize(new Dimension(width, 20));
        up.setPreferredSize(new Dimension(width, 20));
        panel.setMaximumSize(new Dimension(width, 20));
        JButton right = new JButton("→");
        right.addActionListener(e -> {
            x = x+1;
            panel.repaint();
            panel.revalidate();
        });
        right.setMinimumSize(new Dimension(50, height - 40));
        right.setPreferredSize(new Dimension(50, height - 40));
        right.setMaximumSize(new Dimension(50, height - 40));
        JButton down = new JButton("↓");
        down.addActionListener(e -> {
            y = y+1;
            panel.repaint();
            panel.revalidate();
        });
        down.setMinimumSize(new Dimension(width, 20));
        down.setPreferredSize(new Dimension(width, 20));
        down.setMaximumSize(new Dimension(width, 20));
        JButton left = new JButton("←");
        left.addActionListener(e -> {
            x = Math.max(1, x-1);
            panel.repaint();
            panel.revalidate();
        });
        left.setMinimumSize(new Dimension(50, height - 40));
        left.setPreferredSize(new Dimension(50, height - 40));
        left.setMaximumSize(new Dimension(50, height - 40));

        panel.add(up, BorderLayout.NORTH);
        panel.add(right, BorderLayout.EAST);
        panel.add(down, BorderLayout.SOUTH);
        panel.add(left, BorderLayout.WEST);
        panel.add(this, BorderLayout.CENTER);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setBackground(Color.black);
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        ArrayList<Car> cars = new ArrayList<>();

        int width = this.width-100;
        int height = this.height-40;
        ArrayList<Car> carZ = Client.getCars();
        for(Car car : carZ){
            if((car.getY() == y+1 || car.getY() == y) && (car.getX() < x + countPlaces || car.getX() >= x)){
                cars.add(car);
            }
        }

        graphics2D.setColor(Color.WHITE);
        Rectangle2D.Double roadWhiteLine = new Rectangle2D.Double(0, height/3.0 + height/9.0 - 5, width,  10);
        graphics2D.fill(roadWhiteLine);

        graphics2D.setColor(Color.WHITE);
        Rectangle2D.Double roadWhiteLine2 = new Rectangle2D.Double(0, height/3.0 + 2*height/9.0 - 5, width,  10);
        graphics2D.fill(roadWhiteLine2);

        for(int i = 0; i < countPlaces; i++){
            graphics2D.setColor(Color.gray);
            graphics2D.setStroke(new BasicStroke(3));
            Line2D.Double line = new Line2D.Double((i+1)*width/countPlaces,0,(i+1)*width/countPlaces,height/3.0);
            graphics2D.draw(line);
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            String str = (x+i) + "-" + y;
            graphics2D.drawString(str, (i*width/countPlaces + (i+1)*width/countPlaces)/2 - (str.length()-1)*8,  height/6 - 8);
        }

        for(int i = 0; i < countPlaces; i++){
            graphics2D.setColor(Color.gray);
            graphics2D.setStroke(new BasicStroke(3));
            Line2D.Double line = new Line2D.Double((i+1)*width/countPlaces,2*height/3.0,(i+1)*width/countPlaces,height);
            graphics2D.draw(line);
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            String str = (x+i) + "-" + (y+1);
            graphics2D.drawString(str, (i*width/countPlaces + (i+1)*width/countPlaces)/2 - (str.length()-1)*8,  height*5/6 - 8);
        }

        for(Car car : cars){
            int code = 0;
            String owner = car.getOwner();
            for(char c : owner.toCharArray()){
                code += c;
            }
            Color colorHead = new Color(Math.abs((int)Math.pow(owner.length()*50, Math.sin(code))%255), Math.abs((int)Math.pow(owner.length()*Math.cos(owner.length()), 5*code)%255),Math.abs((int)(10*owner.length()*Math.sin(owner.length())))%244);

            Color colorBody = new Color(Math.abs((int)Math.pow(owner.length()*20, Math.cos(code))%233), Math.abs((int)Math.pow(owner.length()*Math.cos(owner.length()), 7*code)%211),Math.abs((int)(10*owner.length()*Math.sin(owner.length())))%244);
            if(y == car.getY()){
                graphics2D.setColor(colorBody);
                Rectangle2D.Double bottomCarPart = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 30, 10, width/countPlaces - 60,height/3.0 - 20);
                graphics2D.fill(bottomCarPart);
                graphics2D.setColor(colorHead);
                Rectangle2D.Double topCarPart = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 40, 35, width/countPlaces - 80,height/3.0 - 70);
                graphics2D.fill(topCarPart);

                graphics2D.setColor(Color.YELLOW);
                Rectangle2D.Double lightOne = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 30,  height/3.0 - 15, 20, 10);
                graphics2D.fill(lightOne);
                Rectangle2D.Double lightTwo = new Rectangle2D.Double((car.getX() - x + 1)*width/countPlaces - 50,  height/3.0 - 15, 20, 10);
                graphics2D.fill(lightTwo);
            } else {
                graphics2D.setColor(colorBody);
                Rectangle2D.Double bottomCarPart = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 30, 2*height/3.0 + 10, width/countPlaces - 60,height - 20);
                graphics2D.fill(bottomCarPart);
                graphics2D.setColor(colorHead);
                Rectangle2D.Double topCarPart = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 40, 2*height/3.0 + 35, width/countPlaces - 80,height/3.0 - 70);
                graphics2D.fill(topCarPart);

                graphics2D.setColor(Color.YELLOW);
                Rectangle2D.Double lightOne = new Rectangle2D.Double((car.getX() - x)*width/countPlaces + 30,  2*height/3.0 + 5, 20, 10);
                graphics2D.fill(lightOne);
                Rectangle2D.Double lightTwo = new Rectangle2D.Double((car.getX() - x + 1)*width/countPlaces - 50,  2*height/3.0 + 5, 20, 10);
                graphics2D.fill(lightTwo);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int width = this.width-100;
        int height = this.height-40;
        int x = e.getX()/(width/countPlaces) + this.x;
        int y = (e.getY()/(height/3))/2 + (e.getY()/(height/3))%2 + this.y;
        ArrayList<Car> carZ = Client.getCars();
        for(Car car : carZ){
            if(car.getY() == y && car.getX() == x){
                new DescCar(car);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void setX(int x) {
        ViewPanel.x = x;
    }

    public static void setY(int y) {
        ViewPanel.y = y;
    }
}
