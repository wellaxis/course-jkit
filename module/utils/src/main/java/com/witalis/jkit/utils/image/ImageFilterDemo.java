package com.witalis.jkit.utils.image;

import com.witalis.jkit.utils.image.filter.PlugInFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class ImageFilterDemo extends Frame implements ActionListener  {
    private Image img;
    private PlugInFilter pif;
    private Image fimg;
    private Image curImg;
    private LoadedImage lim;
    private Label lab;
    private Button reset;
    private String[] filters = {
        "Grayscale",
        "Invert",
        "Contrast",
        "Blur",
        "Sharpen"
    };

    public ImageFilterDemo() throws HeadlessException {
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);

        // reset
        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);

        // filter
        for (String fstr: filters) {
            Button b = new Button(fstr);
            b.addActionListener(this);
            p.add(b);
        }

        // label
        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        // image
        try {
            URL imageUrl = getClass().getClassLoader().getResource("image/pepper.jpg");
            assert imageUrl != null : "Image file has not been found!";
            File imageFile = new File(imageUrl.getFile());
            img = ImageIO.read(imageFile);
        } catch (IOException ioe) {
            System.err.println("Cannot load image file");
            System.exit(0);
        }

        // load
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);

        // window
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    public void actionPerformed(ActionEvent ae) {
        String a = "";
        try {
            a = ae.getActionCommand();
            if (a.equals("Reset")) {
                lim.set(img);
                lab.setText("Normal");
            } else {
                var filterPackage = getClass().getPackage().getName() + ".filter.";
                pif = (PlugInFilter) (Class.forName(filterPackage + a)).getConstructor().newInstance();
                fimg = pif.filter(this, img);
                lim.set(fimg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        } catch (ClassNotFoundException cnfe) {
            lab.setText(a + " not found");
            lim.set(img);
            repaint();
        } catch (InstantiationException ie) {
            lab.setText("couldn't new " + a);
        } catch (IllegalAccessException iae) {
            lab.setText("no access: " + a);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            lab.setText("Filter creation error: " + e);
        }
    }

    public static void main(String[] args) {
        var demo = new ImageFilterDemo();
        demo.setSize(new Dimension(910, 620));
        demo.setTitle("Image Filter Demo");
        demo.setVisible(true);
    }
}
