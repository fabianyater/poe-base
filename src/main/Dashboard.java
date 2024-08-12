package main;

import main.lib.components.*;
import main.lib.enums.FontEnums;
import main.models.Vehicle;
import main.services.VehicleService;
import main.services.VehicleServiceImpl;
import main.types.VehicleType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static main.lib.utils.Utils.createScaledIcon;
import static main.lib.utils.Utils.updateTotalVehiclesLabel;

public class Dashboard extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    public static final int ICON_WIDTH = 22;
    public static final int ICON_HEIGHT = 22;
    public static final Color BORDER_LIGHT_GRAY = Color.decode("#cfcfcf");
    private static final Color PRIMARY = Color.decode("#2d47e3");

    JPanel sidePanel;
    JPanel groupSearchPanel;
    JPanel statePanel;
    JPanel citiesPanel;
    JPanel vehicleTypesPanel;
    JPanel mainPanel;
    JPanel cardBodyPanel;
    JPanel cardFooterPanel;
    JLabel searchIconLabel;
    JLabel sidePanelTitleLabel;
    JLabel statePanelTitleLabel;
    JLabel vehicleTypesPanelTitleLabel;
    JLabel cityPlateLabel;
    JLabel mainTitleLabel;
    JLabel resetFiltersLabel;
    JTextField searchTextField;
    List<JCheckBox> vehicleTypesCheckBox;
    JComboBox<String> listCitiesComboBox;
    JButton selectedButton;
    JButton filterBtn;
    JButton addNewVehicleBtn;
    DefaultListModel<Card> cardListModel;
    JList<Card> cardList;
    ButtonGroup stateBtnGroup;
    ImageIcon searchIcon;
    ImageIcon addBtn;
    ImageIcon iconFilterBtn;
    String[] cities = {
            "Select city", "Florencia", "Albania", "Belén de los Andaquíes", "Cartagena del Chairá", "Curillo",
            "El Doncello", "El Paujil", "La Montañita", "Milán", "Morelia", "Puerto Rico",
            "San José del Fragua", "San Vicente del Caguán", "Solano", "Solita", "Valparaíso"
    };
    VehicleService vehicleService = new VehicleServiceImpl();
    int totalVehicles;

    public Dashboard(String title, boolean isVisble) {
        setTitle(title);
        vehicleService.loadData();
        totalVehicles = vehicleService.totalVehicles();

        createSidePanel();
        createMainPanel();

        setUpFrame();
        setVisible(isVisble);
    }

    private void createMainPanel() {
        mainPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(880, 740))
                .setX(292)
                .setY(10)
                .build();

        mainTitleLabel = new CustomLabelFactory()
                .createDefaultLabel(updateTotalVehiclesLabel(totalVehicles))
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .setSize(new Dimension(140, 20))
                .setAlignment(SwingConstants.LEFT)
                .setY(10)
                .build();

        addBtn = createScaledIcon("./images/plus.png", ICON_WIDTH, ICON_HEIGHT);
        addNewVehicleBtn = new CustomButtonFactory()
                .createButtonWithTextAndIcon("Add new vehicle", addBtn, BORDER_LIGHT_GRAY, PRIMARY)
                .setX(321)
                .build();

        cardListModel = new DefaultListModel<>();

        for (Vehicle vehicle : vehicleService.getFilteredVehicles()) {
            Card vehicleCard = new Card(vehicle);
            cardListModel.addElement(vehicleCard);
        }

        cardList = new JList<>(cardListModel);
        cardList.setCellRenderer(new CardRenderer());

        JScrollPane scrollPane = new JScrollPane(cardList);
        scrollPane.setBounds(0, 50, 480, 690);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);


        mainPanel.add(mainTitleLabel);
        mainPanel.add(addNewVehicleBtn);
        mainPanel.add(scrollPane);

        add(mainPanel);
    }

    private void createSidePanel() {
        sidePanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(280, 800))
                .setOpaque(true)
                .setY(getY())
                .build();

        sidePanelTitleLabel = new CustomLabelFactory()
                .createDefaultLabel("Filter by:")
                .setX(15)
                .setY(10)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .build();

        createSearchPanel();
        createVehicleStatePanel();
        createListCitiesPanel();
        createVehicleTypesPanel();

        resetFiltersLabel = new CustomLabelFactory()
                .createDefaultLabel("Reset all")
                .setX(216)
                .setY(11)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setColor(Color.GRAY)
                .setUnderline(true)
                .setMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        searchTextField.setText(null);
                        resetButtonSelection();
                        listCitiesComboBox.setSelectedIndex(0);
                        vehicleTypesCheckBox.forEach(jCheckBox -> jCheckBox.setSelected(false));
                        mainTitleLabel.setText(updateTotalVehiclesLabel(vehicleService.totalVehicles()));
                        groupSearchPanel.setBorder(BorderFactory.createEmptyBorder());
                        updateCardsList(vehicleService.getFilteredVehicles());
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        resetFiltersLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                })
                .build();

        iconFilterBtn = createScaledIcon("./images/filter.png", ICON_WIDTH, ICON_HEIGHT);
        filterBtn = new CustomButtonFactory()
                .createButtonWithTextAndIcon("Filter", iconFilterBtn, BORDER_LIGHT_GRAY, PRIMARY)
                .setX(60)
                .setY(700)
                .setActionListener(e -> {
                    String plate = Objects.equals(searchTextField.getText(), "") ? null : searchTextField.getText();
                    String state = selectedButton != null ? selectedButton.getText() : null;
                    String city = (String) listCitiesComboBox.getSelectedItem();
                    List<String> vehicleTypes = vehicleTypesCheckBox.stream()
                            .filter(JCheckBox::isSelected)
                            .map(JCheckBox::getText)
                            .toList();

                    List<Vehicle> filteredVehicles = vehicleService.filterVehicles(plate, city, vehicleTypes, state);

                    totalVehicles = filteredVehicles.size();
                    mainTitleLabel.setText(updateTotalVehiclesLabel(totalVehicles));
                    updateCardsList(filteredVehicles);
                })
                .build();

        sidePanel.add(sidePanelTitleLabel);
        sidePanel.add(groupSearchPanel);
        sidePanel.add(statePanel);
        sidePanel.add(citiesPanel);
        sidePanel.add(vehicleTypesPanel);
        sidePanel.add(filterBtn);

        add(sidePanel);
    }

    private void updateCardsList(List<Vehicle> vehicleService) {
        cardListModel.clear();

        for (Vehicle vehicle : vehicleService) {
            Card vehicleCard = new Card(vehicle);
            cardListModel.addElement(vehicleCard);
        }

        cardList.revalidate();
        cardList.repaint();
    }

    private void createSearchPanel() {
        groupSearchPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(250, 40))
                .setBackground(Color.decode("#f8f9fa"))
                .setOpaque(true)
                .setX(15)
                .setY(40)
                .setBorder(BorderFactory.createLineBorder(BORDER_LIGHT_GRAY))
                .build();

        searchIcon = createScaledIcon("./images/search.png", ICON_WIDTH, ICON_HEIGHT);
        searchIconLabel = new CustomLabelFactory()
                .createLabelWithIcon(searchIcon)
                .setX(4)
                .setY(9)
                .build();

        searchTextField = new CustomTextFieldFactory()
                .createDefaultTexfield()
                .setLayout(null)
                .setSize(new Dimension(230, 38))
                .setY(1)
                .setX(30)
                .setBorder(BorderFactory.createEmptyBorder())
                .build();

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                updateResetLabel();
                String text = searchTextField.getText();
                char c = e.getKeyChar();

                if (text.length() >= 6 ||
                        (text.length() < 3 && !Character.isUpperCase(c)) ||
                        (text.length() >= 3 && !Character.isDigit(c))) {
                    e.consume();
                    groupSearchPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                } else {
                    groupSearchPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }
            }
        });

        groupSearchPanel.add(searchIconLabel);
        groupSearchPanel.add(searchTextField);
    }

    private void createVehicleStatePanel() {
        statePanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(250, 70))
                .setY(100)
                .setX(15)
                .build();

        statePanelTitleLabel = new CustomLabelFactory()
                .createDefaultLabel("Vehicle state")
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .build();

        statePanel.add(statePanelTitleLabel);

        String[] states = {"Active", "Inactive", "Paused"};
        stateBtnGroup = new ButtonGroup();

        for (int i = 0; i < states.length; i++) {
            int x = i * 85;
            JButton button = createCommonButton(states[i], x);
            stateBtnGroup.add(button);
            statePanel.add(button);
        }
    }


    private void createListCitiesPanel() {
        citiesPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(250, 70))
                .setY(190)
                .setX(15)
                .build();

        cityPlateLabel = new CustomLabelFactory()
                .createDefaultLabel("City of the plate")
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .build();

        listCitiesComboBox = new JComboBox<>(cities);
        listCitiesComboBox.setSelectedIndex(0);
        listCitiesComboBox.setBounds(0, 30, citiesPanel.getWidth(), 30);
        listCitiesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateResetLabel();
            }
        });

        citiesPanel.add(cityPlateLabel);
        citiesPanel.add(listCitiesComboBox);
    }

    private void createVehicleTypesPanel() {
        vehicleTypesPanel = new CustomPanelFactory()
                .createDefaultPanel()
                .setSize(new Dimension(250, 140))
                .setY(270)
                .setX(15)
                .build();

        vehicleTypesPanelTitleLabel = new CustomLabelFactory()
                .createDefaultLabel("Vehicle type")
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.MEDIUM)
                .build();

        vehicleTypesCheckBox = new ArrayList<>();
        String[] vehicleTypes = {VehicleType.SEDAN.toString(), VehicleType.SUV.toString(), VehicleType.HATCHBACK.toString(),
                VehicleType.PICKUP.toString(), VehicleType.VAN.toString(), VehicleType.COUPE.toString(), VehicleType.TRUCK.toString()};

        for (int i = 0; i < vehicleTypes.length; i++) {
            int x = (i % 2) * 125;
            int y = (i / 2) * 30 + 30;
            vehicleTypesCheckBox.add(createCommonCheckBox(vehicleTypes[i], x, y));
        }

        vehicleTypesPanel.add(vehicleTypesPanelTitleLabel);

        for (JCheckBox checkBox : vehicleTypesCheckBox) {
            vehicleTypesPanel.add(checkBox);
        }
    }

    private JCheckBox createCommonCheckBox(String text, int x, int y) {
        JCheckBox checkBox = new CustomButtonFactory().createCustomCheckBoxButton(text, PRIMARY, BORDER_LIGHT_GRAY, x, y);

        checkBox.addItemListener(e -> {
            updateResetLabel();

            if (checkBox.isSelected()) {
                checkBox.setFont(checkBox.getFont().deriveFont(Font.BOLD));
            } else {
                checkBox.setFont(checkBox.getFont().deriveFont(Font.PLAIN));
            }
        });
        return checkBox;
    }

    private JButton createCommonButton(String text, int x) {
        CustomButtonFactory buttonFactory = new CustomButtonFactory();
        JButton button = buttonFactory.createCustomRadioButton(text, BORDER_LIGHT_GRAY, x);

        button.addActionListener(e -> {
            if (selectedButton != button) {
                Collections.list(stateBtnGroup.getElements()).forEach(btn -> {
                    btn.setEnabled(true);
                    btn.setBackground(Color.WHITE);
                    btn.setOpaque(true);
                    btn.setForeground(Color.BLACK);
                });

                button.setEnabled(false);
                button.setBackground(PRIMARY);
                button.setForeground(Color.WHITE);
                button.setOpaque(true);
                selectedButton = button;
                selectedButton.setText(buttonFactory.getParsedText(button.getText()));
            }

            updateResetLabel();
        });

        return button;
    }

    private void updateResetLabel() {
        if (showResetButton()) {
            if (resetFiltersLabel.getParent() == null) {
                sidePanel.add(resetFiltersLabel);
                sidePanel.revalidate();
                sidePanel.repaint();
            }
        } else {
            if (resetFiltersLabel.getParent() != null) {
                sidePanel.remove(resetFiltersLabel);
                sidePanel.revalidate();
                sidePanel.repaint();
            }
        }
    }

    private boolean showResetButton() {
        boolean isSelectedButtonActive = selectedButton != null && selectedButton.getText() != null && !selectedButton.getText().isEmpty();
        boolean isSearchTextFieldActive = !searchTextField.getText().isEmpty();
        boolean isCitySelected = listCitiesComboBox.getSelectedIndex() != 0;
        boolean isVehicleTypesCheckBoxActive = vehicleTypesCheckBox.stream().anyMatch(JCheckBox::isSelected);

        return isSelectedButtonActive || isSearchTextFieldActive || isCitySelected || isVehicleTypesCheckBoxActive;
    }

    private void resetButtonSelection() {
        if (selectedButton != null) {
            selectedButton.setEnabled(true);
            selectedButton.setBackground(Color.WHITE);
            selectedButton.setForeground(Color.BLACK);
            selectedButton.setOpaque(true);
            selectedButton = null;
        }

        Collections.list(stateBtnGroup.getElements()).forEach(btn -> {
            btn.setEnabled(true);
            btn.setBackground(Color.WHITE);
            btn.setOpaque(true);
            btn.setForeground(Color.BLACK);
        });

        updateResetLabel();
    }

    public void setUpFrame() {
        setLayout(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
