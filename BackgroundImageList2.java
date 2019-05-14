package brownBaguette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JList;

public class BackgroundImageList2 extends JList {
        private BufferedImage background;

        public BackgroundImageList2(Object[] objects) {
            super(objects);
            try {
                background = ImageIO.read(new File("cro.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
            setForeground(Color.WHITE);
        }

        protected void paintComponent(Graphics g) {
            if (background != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                int x = getWidth() - background.getWidth();
                int y = getHeight() - background.getHeight();
                g2d.drawImage(background, x, y, this);
                g2d.dispose();
            }
            super.paintComponent(g);
        }

    }
