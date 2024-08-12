package main;

import main.lib.components.CustomLabelFactory;
import main.lib.components.CustomPanelFactory;
import main.lib.enums.FontEnums;
import main.models.Vehicle;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static main.Dashboard.*;
import static main.lib.utils.Utils.centerComponent;
import static main.lib.utils.Utils.createScaledIcon;

public class Card {
    Vehicle vehicle;
    private static final Map<String, String[]> colorStates = new HashMap<>();
    private JPanel cardPanel;

    static {
        colorStates.put("active", new String[]{"#e5f4e4", "#42b13f"});
        colorStates.put("inactive", new String[]{"#f4e5e5", "#b13f3f"});
        colorStates.put("paused", new String[]{"#f4f4e5", "#b1b13f"});
    }

    public Card(Vehicle vehicle) {
        this.vehicle = vehicle;
        createCardPanel(vehicle);
    }

    public Vehicle vehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private void createCardPanel(Vehicle vehicle) {
        String stateKey = vehicle.getStateType().getDescription().toLowerCase();
        String[] colors = colorStates.getOrDefault(stateKey, new String[]{"#ffffff", "#000000"});
        ImageIcon vehicleImage;
        JLabel vehicleImageLabel;

        int xImage = 60;
        int yImage = -20;
        if (vehicle.picture().equals("") || vehicle.picture().isEmpty()) {
            vehicleImage = createScaledIcon("./images/photo-off.png", 80, 80);
            xImage = 192;
            yImage = 91;

            vehicleImageLabel = new CustomLabelFactory()
                    .createLabelWithIcon(vehicleImage)
                    .setX(xImage)
                    .setY(yImage)
                    .build();
        } else {
            vehicleImage = createScaledIcon(vehicle.picture(), 350, 262);
            vehicleImageLabel = new CustomLabelFactory()
                    .createLabelWithIcon(vehicleImage)
                    .setX(xImage)
                    .setY(yImage)
                    .build();
        }

        cardPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setBackground(Color.WHITE)
                .setOpaque(true)
                .setSize(new Dimension(480, 329))
                .setBorder(BorderFactory.createLineBorder(BORDER_LIGHT_GRAY))
                .setY(50)
                .build();

        JLabel vehicleStateLabel = new CustomLabelFactory()
                .createDefaultLabel(vehicle.getStateType().getDescription())
                .setBackground(Color.decode(colors[0]))
                .setOpaque(true)
                .setSize(new Dimension(60, 20))
                .setColor(Color.decode(colors[1]))
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setX(15)
                .setY(15)
                .build();

        createCardBodyPanel();
        createCardFooterPanel();

        assert cardPanel != null;
        cardPanel.add(vehicleImageLabel);
        cardPanel.add(vehicleStateLabel);
        cardPanel.add(createCardBodyPanel());
        cardPanel.add(createCardFooterPanel());

    }

    private JPanel createCardBodyPanel() {
        JPanel cardBodyPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(450, 50))
                .setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#e3e9ee")))
                .setY(225)
                .setX(15)
                .build();

        JLabel vehicleBrandAndModelLabel = new CustomLabelFactory()
                .createDefaultLabel(vehicle.brand() + " " + vehicle.model())
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setTextTransform(FontEnums.TextTransform.UPPERCASE)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setColor(Color.decode("#3b3d40"))
                .build();

        JLabel vehicleVersionLabel = new CustomLabelFactory()
                .createDefaultLabel(vehicle.version())
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .setColor(Color.decode("#000"))
                .setY(20)
                .build();

        cardBodyPanel.add(vehicleVersionLabel);
        cardBodyPanel.add(vehicleBrandAndModelLabel);

        return cardBodyPanel;
    }

    private JPanel createCardFooterPanel() {
        JPanel cardFooterPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(450, 25))
                .setBorder(null)
                .setX(15)
                .setY(289)
                .build();

        ImageIcon vehicleIcon = createScaledIcon("./images/vehicle.png", ICON_WIDTH, ICON_HEIGHT);
        ImageIcon colorIcon = createScaledIcon("./images/color.png", ICON_WIDTH, ICON_HEIGHT);
        ImageIcon usersIcon = createScaledIcon("./images/users.png", ICON_WIDTH, ICON_HEIGHT);
        String[] infoTexts = {vehicle.getVehicleType().name(), vehicle.getColor(), String.valueOf(vehicle.getPassengers())};
        ImageIcon[] icons = {vehicleIcon, colorIcon, usersIcon};
        int x = 0;

        for (int i = 0; i < infoTexts.length; i++) {
            JLabel infoLabel = createInfoPanel(infoTexts[i], icons[i], x);
            x += infoLabel.getWidth() + 10;
            cardFooterPanel.add(infoLabel);
        }

        return cardFooterPanel;
    }

    private JLabel createInfoPanel(String text, Icon icon, int x) {
        return new CustomLabelFactory()
                .createLabelWithTextAndIcon(text, icon)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setColor(Color.decode("#77777f"))
                .setX(x)
                .build();
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    private static String abbreviateCity(String city) {
        int maxLength = 10;
        if (city.length() > maxLength) {
            String[] words = city.split(" ");
            StringBuilder abbreviation = new StringBuilder();
            for (String word : words) {
                abbreviation.append(word.charAt(0));
            }
            return abbreviation.toString().toUpperCase();
        }
        return city;
    }
}
