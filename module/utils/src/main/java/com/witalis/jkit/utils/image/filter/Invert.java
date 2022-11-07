package com.witalis.jkit.utils.image.filter;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Invert extends RGBImageFilter implements PlugInFilter {

    public Invert() {
    }

    public Image filter(Frame f, Image i) {
        return f.createImage(
            new FilteredImageSource(i.getSource(), this)
        );
    }

    public int filterRGB(int x, int y, int rgb) {
        int r = 0xff - (rgb >> 16) & 0xff;
        int g = 0xff - (rgb >> 8) & 0xff;
        int b = 0xff - rgb & 0xff;
        return (0xff000000 | r << 16 | g << 8 | b);
    }
}
