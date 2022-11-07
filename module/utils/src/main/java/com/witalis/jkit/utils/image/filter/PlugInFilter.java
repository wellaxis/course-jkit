package com.witalis.jkit.utils.image.filter;

import java.awt.*;

public interface PlugInFilter {
    Image filter(Frame frame, Image image);
}
