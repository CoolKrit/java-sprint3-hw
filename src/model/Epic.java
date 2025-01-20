package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksIds;

    public Epic(String name, String description, String status) {
        super(name, description, status);
        this.subtasksIds = new ArrayList<>();
    }

    public Epic(int id, String name, String description, String status) {
        super(id, name, description, status);
    }

    public void addSubtaskId(int subtaskId) {
        this.subtasksIds.add(subtaskId);
    }

    public void removeSubtask(Integer id) {
        this.subtasksIds.remove(id);
    }

    public ArrayList<Integer> getSubtasksIds() {
        return subtasksIds;
    }

    @Override
    public String toString() {
        String subTask = "";
        if (subtasksIds != null) {
            for (Integer subTaskId : subtasksIds) {
                subTask = subTask + "," + subTaskId;
            }
        } else {
            subTask = null;
        }

        return "Epic{" +
                "name='" + getName() +
                "', discription='" + getDescription() +
                "', identification='" + getId() +
                "', status='" + getStatus() +
                "', subtaskId='[" + subTask +
                "]'}";
    }
}