package main;

import main.lib.components.CustomButtonFactory;
import main.lib.components.CustomLabelFactory;
import main.lib.enums.FontEnums;
import main.lib.enums.FontNames;
import main.lib.utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ventana extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final Color color = Color.decode("#039179");
    private static final FontNames fontTitle = FontNames.TAHOMA;
    private String titleText = "simple way to manage";
    private String subtitle = "your vehicles";

    JLabel titleLabel, subtitleLabel;
    JButton startBtn;

    public Ventana(String title) {
        setTitle(title);
        setUpFrame();

        titleLabel = new CustomLabelFactory()
                .createDefaultLabel(titleText)
                .setFontName(fontTitle)
                .setFontSize(FontEnums.LabelFontSize.XXXX_LARGE)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setTextTransform(FontEnums.TextTransform.CAPITALIZE)
                .setColor(Color.WHITE)
                .setY(200)
                .build();

        subtitleLabel = new CustomLabelFactory()
                .createDefaultLabel(subtitle)
                .setFontName(fontTitle)
                .setFontSize(FontEnums.LabelFontSize.XXXX_LARGE)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setTextTransform(FontEnums.TextTransform.CAPITALIZE)
                .setColor(Color.WHITE)
                .setY(260)
                .build();


        startBtn = new CustomButtonFactory()
                .createDefaultButton("Get Started")
                .setBorderPainted(false)
                .setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .setOpaque(true)
                .setBackground(Color.BLACK)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setColor(Color.WHITE)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setSize(new Dimension(150, 40))
                .setY(330)
                .setActionListener(e -> {
                    dispose();
                    new Dashboard("Dashboard", true);
                })
                .setMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        startBtn.setBackground(Color.decode("#0d0d0d"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        startBtn.setBackground(Color.BLACK);
                    }
                })
                .build();

        Utils.centerComponentHorizontally(titleLabel, getSize());
        Utils.centerComponentHorizontally(subtitleLabel, getSize());
        Utils.centerComponentHorizontally(startBtn, getSize());

        add(titleLabel);
        add(subtitleLabel);
        add(startBtn);

        getContentPane().setBackground(color);

        setVisible(true);
    }

    public void setUpFrame() {
        setLayout(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
