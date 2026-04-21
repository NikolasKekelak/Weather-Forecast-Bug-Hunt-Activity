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

        content.add(createLabel(null, ImageRegistry.getImage(weather.getState()), 0, 0));
        content.add(Box.createVerticalStrut(20));
        content.add(createLabel(String.format("%.1f°C", weather.getTemperature()), null, 48, Font.BOLD));
        content.add(createLabel(weather.getDescription(), null, 24, Font.PLAIN));
        content.add(Box.createVerticalStrut(15));
        content.add(createLabel(String.format("Wind: %.1f km/h, Direction: %.0f°", weather.getWindSpeed(), weather.getWindDirection()), null, 18, Font.PLAIN));
        content.add(createLabel(weather.isDay() ? "Daytime" : "Nighttime", null, 18, Font.PLAIN));
        content.add(Box.createVerticalStrut(10));
        content.add(createLabel("Time: " + weather.getTime().replace("T", " "), null, 16, Font.ITALIC));

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

    private JLabel createLabel(String text, Icon icon, int size, int style) {
        JLabel label = icon != null ? new JLabel(icon) : new JLabel(text);
        if (size > 0) label.setFont(new Font("JetBrains Mono", style, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getNewName() {
        return newName;
    }
}