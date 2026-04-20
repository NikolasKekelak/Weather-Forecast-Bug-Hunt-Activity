package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddCityButton extends JPanel {
    public AddCityButton(Runnable onClick) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(250, 150));
        setBackground(Color.WHITE);

        JLabel label = new JLabel("+");
        label.setFont(new Font("JetBrains Mono", Font.BOLD, 80));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JLabel textLabel = new JLabel("Add City");
        textLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(textLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(240, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });

    }
}
