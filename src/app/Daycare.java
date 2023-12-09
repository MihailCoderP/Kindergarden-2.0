package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daycare {
    private static final String URL = "jdbc:sqlite:child_care_system.db";
    private List<Group> groups;

    public Daycare() {
        this.groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String insertGroupQuery = String.format("INSERT INTO groups (group_name, group_number) VALUES ('%s', %d)",
                    group.getGroupName(), group.getGroupNumber());

            statement.executeUpdate(insertGroupQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGroup(int groupNumber) {
        Group groupToRemove = null;
        for (Group group : groups) {
            if (group.getGroupNumber() == groupNumber) {
                groupToRemove = group;
                break;
            }
        }
        if (groupToRemove != null) {
            groups.remove(groupToRemove);
        }
    }

    public void editGroup(int oldGroupNumber, String newGroupName, int newGroupNumber) {
        for (Group group : groups) {
            if (group.getGroupNumber() == oldGroupNumber) {
                group.setGroupName(newGroupName);
                group.setGroupNumber(newGroupNumber);
                break;
            }
        }
    }

    public void addChild(int groupId, Child child) {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String insertChildQuery = String.format("INSERT INTO children (name, last_name, age, gender) VALUES ('%s', '%s', %d, '%s')",
                    child.getName(), child.getLastName(), child.getAge(), child.getGender());

            statement.executeUpdate(insertChildQuery);

            String insertGroupChildQuery = String.format("INSERT INTO group_children (group_id, child_id) VALUES (%d, last_insert_rowid())",
                    groupId);

            statement.executeUpdate(insertGroupChildQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChild(int groupNumber, int childId) {
        for (Group group : groups) {
            if (group.getGroupNumber() == groupNumber) {
                group.removeChild(childId);
                break;
            }
        }
    }

    public void showInfoPanel() {
        System.out.println("Информация о группах и детях:");
        for (Group group : groups) {
            System.out.println("Группа: " + group.getGroupName() + ", Номер: " + group.getGroupNumber());
            System.out.println("Дети в группе:");
            for (Child child : group.getChildren()) {
                System.out.println("ID: " + child.getId() + ", Имя: " + child.getName() + ", Фамилия: "
                        + child.getLastName() + ", Возраст: " + child.getAge() + ", Пол: " + child.getGender());
            }
            System.out.println("----------------------");
        }
    }

    public void editChildInfo(int groupNumber, int childId, String newChildName, String newChildLastName, int newChildAge, String newChildGender) {
        for (Group group : groups) {
            if (group.getGroupNumber() == groupNumber) {
                group.editChildInfo(childId, newChildName, newChildLastName, newChildAge, newChildGender);
                break;
            }
        }
    }

    public List<Group> getGroups() {
        return groups;
    }
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        // Реализация метода закрытия соединения
        System.out.println("Закрытие соединения...");
        // Добавьте код закрытия соединения по необходимости
    }
}
