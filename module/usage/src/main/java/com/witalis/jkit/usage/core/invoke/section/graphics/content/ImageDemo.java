package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Desc: Image Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageDemo extends Frame {
    Image image;

    public ImageDemo() {
        setBackground(Color.cyan);
        setForeground(Color.gray);
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        initialize();
    }

    public void initialize() {
        try {
            // image
            URL imageUrl = getClass().getClassLoader().getResource("image/pepper.jpg");
            assert imageUrl != null : "Image file has not been found!";
            File imageFile = new File(imageUrl.getFile());
            // load
            image = ImageIO.read(imageFile);
            log.info("Image: {}", imageFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("Image [load] errors: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, getInsets().left, getInsets().top, null);
    }
}
