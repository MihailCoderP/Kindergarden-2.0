package app;

import javax.swing.*;
import java.awt.*;

public class ChildCareSystemGUI {
    private final Daycare daycare;

    private final JPanel cards;
    private final CardLayout cardLayout;

    public ChildCareSystemGUI() {
        SQLiteDBSetup db = new SQLiteDBSetup();
        daycare = new Daycare();

        JFrame frame = new JFrame("Child Care System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton addGroupButton = new JButton("Добавить группу");
        JButton removeGroupButton = new JButton("Удалить группу");
        JButton editGroupButton = new JButton("Редактировать группу");
        JButton addChildButton = new JButton("Добавить ребенка");
        JButton removeChildButton = new JButton("Удалить ребенка");
        JButton editChildButton = new JButton("Редактировать ребенка");
        JButton showInfoButton = new JButton("Показать информацию");

        mainPanel.add(addGroupButton);
        mainPanel.add(removeGroupButton);
        mainPanel.add(editGroupButton);
        mainPanel.add(addChildButton);
        mainPanel.add(removeChildButton);
        mainPanel.add(editChildButton);
        mainPanel.add(showInfoButton);

        cards.add(mainPanel, "main");

        addGroupButton.addActionListener(e -> openAddGroupPanel());
        removeGroupButton.addActionListener(e -> openRemoveGroupPanel());
        editGroupButton.addActionListener(e -> openEditGroupPanel());
        addChildButton.addActionListener(e -> openAddChildPanel());
        removeChildButton.addActionListener(e -> openRemoveChildPanel());
        editChildButton.addActionListener(e -> openEditChildPanel());
        showInfoButton.addActionListener(e -> showInfoPanel());

        frame.getContentPane().add(cards);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeApplication();
            }
        });
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> cardLayout.show(cards, "main"));
        return backButton;
    }

    private void openAddGroupPanel() {
        JPanel addGroupPanel = new JPanel(new GridLayout(2, 2));
        JTextField groupNameField = new JTextField();
        JTextField groupNumberField = new JTextField();
        JButton addButton = new JButton("Добавить");
        JButton backButton = createBackButton();

        addGroupPanel.add(new JLabel("Название группы:"));
        addGroupPanel.add(groupNameField);
        addGroupPanel.add(new JLabel("Номер группы:"));
        addGroupPanel.add(groupNumberField);
        addGroupPanel.add(addButton);
        addGroupPanel.add(backButton);

        addButton.addActionListener(e -> {
            String groupName = groupNameField.getText();
            int groupNumber = Integer.parseInt(groupNumberField.getText());
            Group group = new Group(groupName, groupNumber);
            daycare.addGroup(group);
            JOptionPane.showMessageDialog(null, "Группа успешно добавлена", "Успех", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(cards, "main");
        });

        cards.add(addGroupPanel, "addGroup");
        cardLayout.show(cards, "addGroup");
    }

    private void openRemoveGroupPanel() {
        JPanel removeGroupPanel = new JPanel(new GridLayout(2, 2));
        JTextField groupNumberField = new JTextField();
        JButton removeButton = new JButton("Удалить");
        JButton backButton = createBackButton();

        removeGroupPanel.add(new JLabel("Номер группы:"));
        removeGroupPanel.add(groupNumberField);
        removeGroupPanel.add(removeButton);
        removeGroupPanel.add(backButton);

        removeButton.addActionListener(e -> {
            try {
                int groupNumber = Integer.parseInt(groupNumberField.getText());
                daycare.removeGroup(groupNumber);
                JOptionPane.showMessageDialog(null, "Группа успешно удалена", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для номера группы", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(removeGroupPanel, "removeGroup");
        cardLayout.show(cards, "removeGroup");
    }

    private void openEditGroupPanel() {
        JPanel editGroupPanel = new JPanel(new GridLayout(3, 2));
        JTextField oldGroupNumberField = new JTextField();
        JTextField newGroupNameField = new JTextField();
        JTextField newGroupNumberField = new JTextField();
        JButton editButton = new JButton("Редактировать");
        JButton backButton = createBackButton();

        editGroupPanel.add(new JLabel("Старый номер группы:"));
        editGroupPanel.add(oldGroupNumberField);
        editGroupPanel.add(new JLabel("Новое название группы:"));
        editGroupPanel.add(newGroupNameField);
        editGroupPanel.add(new JLabel("Новый номер группы:"));
        editGroupPanel.add(newGroupNumberField);
        editGroupPanel.add(editButton);
        editGroupPanel.add(backButton);

        editButton.addActionListener(e -> {
            try {
                int oldGroupNumber = Integer.parseInt(oldGroupNumberField.getText());
                String newGroupName = newGroupNameField.getText();
                int newGroupNumber = Integer.parseInt(newGroupNumberField.getText());
                daycare.editGroup(oldGroupNumber, newGroupName, newGroupNumber);
                JOptionPane.showMessageDialog(null, "Информация о группе успешно отредактирована", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(editGroupPanel, "editGroup");
        cardLayout.show(cards, "editGroup");
    }

    private void openAddChildPanel() {
        JPanel addChildPanel = new JPanel(new GridLayout(4, 2));
        JTextField childNameField = new JTextField();
        JTextField childLastNameField = new JTextField();
        JTextField childAgeField = new JTextField();
        JTextField childGenderField = new JTextField();
        JButton addButton = new JButton("Добавить");
        JButton backButton = createBackButton();

        addChildPanel.add(new JLabel("Имя ребенка:"));
        addChildPanel.add(childNameField);
        addChildPanel.add(new JLabel("Фамилия ребенка:"));
        addChildPanel.add(childLastNameField);
        addChildPanel.add(new JLabel("Возраст ребенка:"));
        addChildPanel.add(childAgeField);
        addChildPanel.add(new JLabel("Пол ребенка:"));
        addChildPanel.add(childGenderField);
        addChildPanel.add(addButton);
        addChildPanel.add(backButton);

        addButton.addActionListener(e -> {
            try {
                String childName = childNameField.getText();
                String childLastName = childLastNameField.getText();
                int childAge = Integer.parseInt(childAgeField.getText());
                String childGender = childGenderField.getText();
                Child child = new Child(childName, childLastName, childAge, childGender);
                daycare.addChild(1, child); // Assuming group number 1 for simplicity, adjust as needed
                daycare.addChild(1, child); // Add to database
                JOptionPane.showMessageDialog(null, "Ребенок успешно добавлен", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для возраста", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(addChildPanel, "addChild");
        cardLayout.show(cards, "addChild");
    }

    private void openRemoveChildPanel() {
        JPanel removeChildPanel = new JPanel(new GridLayout(2, 2));
        JTextField childIdField = new JTextField();
        JButton removeButton = new JButton("Удалить");
        JButton backButton = createBackButton();

        removeChildPanel.add(new JLabel("ID ребенка:"));
        removeChildPanel.add(childIdField);
        removeChildPanel.add(removeButton);
        removeChildPanel.add(backButton);

        removeButton.addActionListener(e -> {
            try {
                int groupNumber = 1; // Assuming group number 1 for simplicity, adjust as needed
                int childId = Integer.parseInt(childIdField.getText());
                daycare.removeChild(groupNumber, childId);
                JOptionPane.showMessageDialog(null, "Ребенок успешно удален", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные для ID ребенка", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(removeChildPanel, "removeChild");
        cardLayout.show(cards, "removeChild");
    }

    private void openEditChildPanel() {
        JPanel editChildPanel = new JPanel(new GridLayout(5, 2));
        JTextField childIdField = new JTextField();
        JTextField newChildNameField = new JTextField();
        JTextField newChildLastNameField = new JTextField();
        JTextField newChildAgeField = new JTextField();
        JTextField newChildGenderField = new JTextField();
        JButton editButton = new JButton("Редактировать");
        JButton backButton = createBackButton();

        editChildPanel.add(new JLabel("ID ребенка:"));
        editChildPanel.add(childIdField);
        editChildPanel.add(new JLabel("Новое имя ребенка:"));
        editChildPanel.add(newChildNameField);
        editChildPanel.add(new JLabel("Новая фамилия ребенка:"));
        editChildPanel.add(newChildLastNameField);
        editChildPanel.add(new JLabel("Новый возраст ребенка:"));
        editChildPanel.add(newChildAgeField);
        editChildPanel.add(new JLabel("Новый пол ребенка:"));
        editChildPanel.add(newChildGenderField);
        editChildPanel.add(editButton);
        editChildPanel.add(backButton);

        editButton.addActionListener(e -> {
            try {
                int groupNumber = 1; // Assuming group number 1 for simplicity, adjust as needed
                int childId = Integer.parseInt(childIdField.getText());
                String newChildName = newChildNameField.getText();
                String newChildLastName = newChildLastNameField.getText();
                int newChildAge = Integer.parseInt(newChildAgeField.getText());
                String newChildGender = newChildGenderField.getText();
                daycare.editChildInfo(groupNumber, childId, newChildName, newChildLastName, newChildAge, newChildGender);
                JOptionPane.showMessageDialog(null, "Информация о ребенке успешно отредактирована", "Успех", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "main");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Введите корректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cards.add(editChildPanel, "editChild");
        cardLayout.show(cards, "editChild");
    }

    private void showInfoPanel() {
        daycare.showInfoPanel();
    }

    private void closeApplication() {
        System.exit(0);
    }
}
