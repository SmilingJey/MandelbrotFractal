package mandelbrotfractal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int vxn, vyn, vxk, vyk;
    private double xmin, ymin, xmax;
    private Double scale;
    private BufferedImage buffer;
    private int fractalColor;
    private int iterationsCount = 200;
    private int moveX, moveY;
    private double mouseX, mouseY;

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
            public void mouseMoved(MouseEvent me) {
                mouseX = xmin + me.getX() * scale;
                mouseY = ymin + (getHeight() - me.getY()) * scale;
                repaint();
            }

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

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                drawMandelborFractal();
            }
        });

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, moveX, moveY, null);
        //g.drawString("x " + mouseX + ", y " + mouseY, 10, 10);
    }

    public void setColor(int fractalColor) {
        this.fractalColor = fractalColor;
    }

    public void setIterationsCount(int iterationsCount) {
        this.iterationsCount = iterationsCount;
    }

    public void setCoordinats(double xmin, double ymin, double xmax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        calcScale();
    }

    public void calcScale() {
        scale = (xmax - xmin) / (this.getWidth());
    }

    public void savaImage() {
        BufferedImage outImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = outImg.createGraphics();
        gr.drawImage(buffer, 0, 0, null);
        JFileChooser fc = new JFileChooser();
        File file = new File("image.bmp");
        fc.setSelectedFile(file);
        int rc = fc.showDialog(null, "Save image");
        if (rc == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            try {
                ImageIO.write(outImg, "BMP", file);
            } catch (IOException e) {

            }
        }
    }

    public void Move(double dx, double dy) {
        xmin = xmin + dx * scale;
        xmax = xmax + dx * scale;
        ymin = ymin + dy * scale;
    }

    public void ZoomIn(int x, int y) {
        if (x == -1 || y == -1) {
            xmin = xmin + scale * 100;
            ymin = ymin + scale * 100;
            xmax = xmax - scale * 100;
        } else {
            double xCursor = xmin + x * scale;
            double yCurcor = ymin + (this.getHeight() - y) * scale;
            xmin = (xCursor + xmin) * 0.5;
            ymin = (yCurcor + ymin) * 0.5;
            xmax = (xmax + xCursor) * 0.5;
        }
        calcScale();
        drawMandelborFractal();
    }

    public void ZoomOut(int x, int y) {
        if (x == -1 || y == -1) {
            xmin = xmin - scale * 100;
            ymin = ymin - scale * 100;
            xmax = xmax + scale * 100;
        } else {
            double xCursor = xmin + x * scale;
            double yCurcor = ymin + (this.getHeight() - y) * scale;

            xmin = xmin - (xCursor - xmin) * 1.25;
            ymin = ymin - (yCurcor - ymin) * 1.25;
            xmax = xmax + (xmax - xCursor) * 1.25;
        }
        calcScale();
        drawMandelborFractal();
    }

    public void drawMandelborFractal() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int width = getWidth();
                int height = getHeight();
                buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graph = buffer.createGraphics();
                int px, py, iter = iterationsCount, i;
                double dx_i, dy_i, dx_io, dy_io, len;
                for (px = 0; px <= width; px++) {
                    for (py = 0; py <= height; py++) {
                        dx_i = 0;
                        dy_i = 0;
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
                            graph.setColor(new Color(255 * (iter - i) / iter, 255
                                    * (iter - i) / iter, 255 * (iter - i) / iter));
                        } else if (fractalColor == 1) {
                            int rr, gg, bb;
                            if (len < 4) {
                                rr = 0;
                                gg = 0;
                                bb = 0;
                            } else {
                                double a1 = 255, a2 = 255, a3 = 255, b1 = 0, b2 = 0.87;
                                double b3 = 1.54, c1 = 0, c2 = 0, c3 = 0;
                                rr = Math.abs((int) (a1 * Math.sin(len + b1) + c1));
                                gg = Math.abs((int) (a2 * Math.sin(len + b2) + c2));
                                bb = Math.abs((int) (a3 * Math.sin(len + b3) + c3));
                            }
                            graph.setColor(new Color(rr, gg, bb));
                        } else if (fractalColor == 2) {
                            int rr = 0, gg = 0, bb = 0;
                            if (len < 4) {
                                rr = 0;
                                gg = 0;
                                bb = 0;
                            }

                            if (len >= 4 && len < 6) {
                                rr = 0;
                                gg = (int) (255 * len / 6);
                                bb = 255;
                            }
                            if (len < 12 && len > 6) {
                                rr = 0;
                                gg = 255;
                                bb = 255 - (int) (255 * len / 12);
                            }
                            if (len < 18 && len > 12) {
                                rr = 255 - (int) (200 * len / 18);
                                gg = 150;
                                bb = 0;
                            }
                            if (len > 18) {
                                rr = 255;
                                gg = 255 - ((len < 28) ? (int) (255 * len / 28) : 255);
                                bb = 0;
                            }
                            graph.setColor(new Color(rr, gg, bb));
                        } else if (fractalColor == 3) {
                            graph.setColor(new Color(i * 19 % 255, i * 18 % 255, i * 17 % 255));
                        }
                        graph.drawLine(px, py, px, py);
                    }
                }
                repaint();
            }
        });

    }
}
