package org.example;

import org.example.service.WeatherService;
import org.example.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            WeatherService service = new WeatherService();
            MainFrame frame = new MainFrame(service);
            frame.setVisible(true);
        });

    }
}