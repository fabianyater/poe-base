package main.lib.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.UUID;

public class Utils {

    private Utils() {
    }

    public static void centerComponent(JComponent component, Dimension size) {
        Dimension labelSize = component.getPreferredSize();

        int x = (size.width - labelSize.width) / 2;
        int y = (size.height - labelSize.height) / 2;

        component.setBounds(x, y, labelSize.width, labelSize.height);
    }

    public static void centerComponentHorizontally(JComponent component, Dimension size) {
        Dimension dimension = component.getPreferredSize();

        int x = ((size.width - dimension.width) / 2) - 7;
        int y = component.getY();

        component.setBounds(x, y, dimension.width, dimension.height);
    }

    public static String generateUniqueID() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }

    public static ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static String updateTotalVehiclesLabel(int totalVehicles) {
        if (totalVehicles <= 0) {
            return "0 vehicles found";
        }

        String vehicleText = totalVehicles == 1 ? "vehicle" : "vehicles";
        return String.format("%d %s found", totalVehicles, vehicleText);
    }
}
