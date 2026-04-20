package org.example.ui;

import org.example.model.CityWeather;
import org.example.util.ImageRegistry;

import javax.swing.*;
import java.awt.*;

public class DetailView extends JDialog {
    private final CityWeather weather;
    private boolean deleted = false;
    private String newName = null;

    public DetailView(Frame owner, CityWeather weather) {
        super(owner, "City Details: " + weather.getCityName(), true);
        this.weather = weather;
        this.newName = weather.getCityName();

        setLayout(new BorderLayout());
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel iconLabel = new JLabel(ImageRegistry.getImage(weather.getState()));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel tempLabel = new JLabel(String.format("%.1f°C", weather.getTemperature()));
        tempLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 48));
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel(weather.getDescription());
        descLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 24));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel windLabel = new JLabel(String.format("Wind: %.1f km/h, Direction: %.0f°", weather.getWindSpeed(), weather.getWindDirection()));
        windLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
        windLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel("Time: " + weather.getTime().replace("T", " "));
        timeLabel.setFont(new Font("JetBrains Mono", Font.ITALIC, 16));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dayLabel = new JLabel(weather.isDay() ? "Daytime" : "Nighttime");
        dayLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(iconLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(tempLabel);
        content.add(descLabel);
        content.add(Box.createVerticalStrut(15));
        content.add(windLabel);
        content.add(dayLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(timeLabel);

        add(content, BorderLayout.CENTER);

        JPanel actions = new JPanel();
        actions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton renameBtn = new JButton("Rename");
        JButton deleteBtn = new JButton("Delete");

        renameBtn.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));
        deleteBtn.setFont(new Font("JetBrains Mono", Font.PLAIN, 20));

        renameBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter new name:", weather.getCityName());
            if (name != null && !name.trim().isEmpty()) {
                this.newName = name;
                dispose();
            }
        });

        deleteBtn.addActionListener(e -> {
            deleted = true;
            dispose();
        });

        actions.add(renameBtn);
        actions.add(deleteBtn);
        add(actions, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getNewName() {
        return newName;
    }
}