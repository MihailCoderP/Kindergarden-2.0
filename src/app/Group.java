package app;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private int groupNumber;
    private List<Child> children;

    public Group(String groupName, int groupNumber) {
        this.groupName = groupName;
        this.groupNumber = groupNumber;
        this.children = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public void removeChild(int childId) {
        Child childToRemove = null;
        for (Child child : children) {
            if (child.getId() == childId) {
                childToRemove = child;
                break;
            }
        }
        if (childToRemove != null) {
            children.remove(childToRemove);
        }
    }

    public void editChildInfo(int childId, String newChildName, String newChildLastName, int newChildAge, String newChildGender) {
        for (Child child : children) {
            if (child.getId() == childId) {
                child.setName(newChildName);
                child.setLastName(newChildLastName);
                child.setAge(newChildAge);
                child.setGender(newChildGender);
                break;
            }
        }
    }
}
