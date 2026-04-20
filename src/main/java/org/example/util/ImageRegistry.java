package org.example.util;

import org.example.model.WeatherState;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

public class ImageRegistry {
    private static final Map<WeatherState, ImageIcon> cache = new EnumMap<>(WeatherState.class);

    static {
        for (WeatherState state : WeatherState.values()) {
            cache.put(state, loadImage(state));
        }
    }

    public static ImageIcon getImage(WeatherState state) {
        return cache.get(state);
    }

    private static ImageIcon loadImage(WeatherState state) {
        String path = "/org/example/assets/" + state.getKey() + ".png";
        java.net.URL imgUrl = ImageRegistry.class.getResource(path);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage();
            int newWidth = (int) (icon.getIconWidth() * 0.6);
            int newHeight = (int) (icon.getIconHeight() * 0.6);
            Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } else {
            System.err.println("Couldn't find file: " + path);
            return createPlaceholder(state);
        }
    }

    private static ImageIcon createPlaceholder(WeatherState state) {
        int size = (int) (150 * 0.6);
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        
        switch (state) {
            case DAY_CLEAR: g.setColor(Color.YELLOW); break;
            case DAY_CLOUDY: g.setColor(Color.LIGHT_GRAY); break;
            case DAY_RAIN: g.setColor(Color.BLUE); break;
            case NIGHT_CLEAR: g.setColor(Color.BLACK); break;
            case NIGHT_CLOUDY: g.setColor(Color.DARK_GRAY); break;
            case NIGHT_RAIN: g.setColor(new Color(0, 0, 128)); break;
            case THUNDERSTORM: g.setColor(new Color(128, 0, 128)); break;
            case SNOWING: g.setColor(Color.CYAN); break;
            case DAY_HOT: g.setColor(Color.ORANGE); break;
        }
        
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        g.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        FontMetrics fm = g.getFontMetrics();
        int x = (size - fm.stringWidth(state.name())) / 2;
        int y = (size - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(state.name(), x, y);
        g.dispose();
        return new ImageIcon(img);
    }
}