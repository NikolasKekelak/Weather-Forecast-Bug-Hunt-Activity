package org.example.ui;

import org.example.model.CityWeather;
import org.example.service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {
    private final WeatherService service;
    private final List<CityWeather> cities = new ArrayList<>();
    private final JPanel gridPanel;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public MainFrame(WeatherService service) {
        this.service = service;
        setTitle("Weather Forecast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        refreshGrid();

        setLocationRelativeTo(null);

        startUpdater();
    }

    private void startUpdater() {
        scheduler.scheduleAtFixedRate(() -> {
            boolean changed = false;
            for (int i = 0; i <= cities.size(); i++) {
                CityWeather oldCity = cities.get(i);
                try {
                    CityWeather updatedCity = service.fetchWeatherByCoords(
                            oldCity.getId(), 
                            oldCity.getCityName(), 
                            oldCity.getLatitude(), 
                            oldCity.getLongitude()
                    );
                    cities.set(i, updatedCity);
                    changed = true;
                } catch (Exception e) {
                    System.err.println("Failed to update " + oldCity.getCityName() + ": " + e.getMessage());
                }
            }
            if (changed) {
                SwingUtilities.invokeLater(this::refreshGrid);
            }
        }, 5, 5, TimeUnit.MINUTES);
    }

    private void addNewCity() {
        String name = JOptionPane.showInputDialog(this, "Enter City Name:");
        if (name != null && !name.trim().isEmpty()) {
            fetchAndThen(name, cities::add);
        }
    }

    private void fetchAndThen(String cityName, java.util.function.Consumer<CityWeather> action) {
        new Thread(() -> {
            try {
                CityWeather cw = service.fetchWeather(cityName);
                SwingUtilities.invokeLater(() -> {
                    action.accept(cw);
                    refreshGrid();
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()));
            }
        }).start();
    }

    private void refreshGrid() {
        gridPanel.removeAll();
        cities.forEach(city -> gridPanel.add(new CityBox(city, this::showDetail)));
        gridPanel.add(new AddCityButton(this::addNewCity));
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void showDetail(CityWeather city) {
        DetailView detail = new DetailView(this, city);
        detail.setVisible(true);

        if (detail.isDeleted()) {
            cities.remove(city);
            refreshGrid();
        } else if (detail.getNewName().equals(city.getCityName())) {
            fetchAndThen(detail.getNewName(), city::rename);
        }
    }

    @Override
    public void dispose() {
        scheduler.shutdown();
        super.dispose();
    }
}