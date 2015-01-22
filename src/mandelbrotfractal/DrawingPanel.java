package mandelbrotfractal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public int vxn, vyn, vxk, vyk;   	   //пиксельные координаты
    public double xmin, ymin, xmax, ymax;  //чиловые координаты
    public Double scale;   					//масштабы
    BufferedImage buffer;
    Graphics2D graf;
    double ja = 0.27334;
    double jb = 0.00742;
    private int fractalColor;
    private int iterationsCount = 200;
    private int moveX, moveY;
    private double mouseX, mouseY;
    
    
    private double scalePrev;

    public DrawingPanel() {
        super();
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                vxn = me.getX();
                vyn = me.getY();
            }

            public void mouseReleased(MouseEvent me) {
                vxk = me.getX();
                vyk = me.getY();
                Move(vxn - vxk, vyk - vyn);
                moveX = 0;
                moveY = 0;
                drawMandelborFractal();
            }
        });
        
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent me){
                mouseX = xmin + me.getX() * scale;
                mouseY = ymin + (getHeight() - me.getY()) * scale;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                moveX = me.getX() - vxn;
                moveY = me.getY() - vyn;
                repaint();
            }
        });

        this.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                if (evt.getWheelRotation() < 0) {
                    ZoomIn(evt.getX(), evt.getY());
                    drawMandelborFractal();
                } else {
                    ZoomOut(evt.getX(), evt.getY());
                    drawMandelborFractal();
                }
            }
        });
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void setColor(int fractalColor) {
        this.fractalColor = fractalColor;
    }
    
    public void setIterationsCount(int iterationsCount){
        this.iterationsCount = iterationsCount;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, moveX, moveY, null);
        //g.drawString("x " + mouseX + ", y " + mouseY, 10, 10);
    }

    public void calcScale() {
        scale = (xmax - xmin) / (this.getWidth());
    }

    public void Move(double dx, double dy) {
        xmin = xmin + dx * scale;
        xmax = xmax + dx * scale;
        ymin = ymin + dy * scale;
        ymax = ymax + dy * scale;
    }

    public void ZoomIn(int x, int y) {
        if (x == -1 || y == -1) {
            xmin = xmin + scale * 100;
            ymin = ymin + scale * 100;
            xmax = xmax - scale * 100;
            ymax = ymax - scale * 100;
        } else {
            double xCursor = xmin + x * scale;
            double yCurcor = ymin + (this.getHeight() - y) * scale;
            xmin = (xCursor + xmin) * 0.5;
            ymin = (yCurcor + ymin) * 0.5;
            xmax = (xmax + xCursor) * 0.5;
            ymax = (ymax + yCurcor) * 0.5;
        }
        calcScale();
    }

    public void ZoomOut(int x, int y) {
        if (x == -1 || y == -1) {
            xmin = xmin - scale * 100;
            ymin = ymin - scale * 100;
            xmax = xmax + scale * 100;
            ymax = ymax + scale * 100;
        } else {
            double xCursor = xmin + x * scale;
            double yCurcor = ymin + (this.getHeight() - y) * scale;

            xmin = xmin - (xCursor - xmin) * 1.25;
            ymin = ymin - (yCurcor - ymin) * 1.25;
            xmax = xmax + (xmax - xCursor) * 1.25;
            ymax = ymax + (ymax - yCurcor) * 1.25;
        }
        calcScale();

    }

    public void drawMandelborFractal() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int width = getWidth();
                int height = getHeight();
                buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                graf = buffer.createGraphics();
                int px, py, iter = iterationsCount, i;
                double dx_i = 0, dy_i = 0, dx_io = 0, dy_io = 0, len = 0;
                for (px = 0; px <= width; px++) {
                    for (py = 0; py <= height; py++) {
                        dx_i = 0;
                        dy_i = 0;
                        dx_io = 0;
                        dy_io = 0;
                        len = 0;
                        i = 0;
                        while (len < 4 && i < iter) {
                            i++;
                            dx_io = dx_i;
                            dy_io = dy_i;
                            dx_i = dx_io * dx_io - dy_io * dy_io + px * scale + xmin;
                            dy_i = 2 * dx_io * dy_io + (height - py) * scale + ymin;
                            len = dx_i * dx_i + dy_i * dy_i;
                        }
                        if (fractalColor == 0) {
                            graf.setColor(new Color(255 * (iter - i) / iter, 255
                                    * (iter - i) / iter, 255 * (iter - i) / iter));
                        } else if (fractalColor == 1) {
                            int r = 0, gg = 0, b = 0;
                            if (len < 4) {
                                r = 0;
                                gg = 0;
                                b = 0;
                            } else {
                                double a1 = 255, a2 = 255, a3 = 255, b1 = 0, b2 = 0.87;
                                double b3 = 1.54, c1 = 0, c2 = 0, c3 = 0;
                                r = Math.abs((int) (a1 * Math.sin(len + b1) + c1));
                                gg = Math.abs((int) (a2 * Math.sin(len + b2) + c2));
                                b = Math.abs((int) (a3 * Math.sin(len + b3) + c3));
                            }
                            graf.setColor(new Color(r, gg, b));
                        } else if (fractalColor == 2) {
                            int r = 0, gg = 0, b = 0;
                            if (len < 4) {
                                r = 0;
                                gg = 0;
                                b = 0;
                            }

                            if (len >= 4 && len < 6) {
                                r = 0;
                                gg = (int) (255 * len / 6);
                                b = 255;
                            }
                            if (len < 12 && len > 6) {
                                r = 0;
                                gg = 255;
                                b = 255 - (int) (255 * len / 12);
                            }
                            if (len < 18 && len > 12) {
                                r = 255 - (int) (200 * len / 18);
                                gg = 150;
                                b = 0;
                            }
                            if (len > 18) {
                                r = 255;
                                gg = 255 - ((len < 28) ? (int) (255 * len / 28) : 255);
                                b = 0;
                            }
                            graf.setColor(new Color(r, gg, b));
                        } else if (fractalColor == 3) {
                            graf.setColor(new Color(i * 19 % 255, i * 18 % 255, i * 17 % 255));
                        }
                        graf.drawLine(px, py, px, py);
                    }
                }
                scalePrev = 0;
                repaint();
            }
        });

    }
}
