package org.example.ui;

import org.example.model.CityWeather;
import org.example.util.ImageRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class CityBox extends JPanel {
    private final CityWeather weather;

    public CityBox(CityWeather weather, Consumer<CityWeather> onClick) {
        this.weather = weather;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(250, 150));

        JLabel nameLabel = new JLabel(weather.getCityName());
        nameLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel iconLabel = new JLabel(ImageRegistry.getImage(weather.getState()));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(nameLabel);
        add(Box.createVerticalStrut(10));
        add(iconLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.accept(weather);
            }
        });
    }
}