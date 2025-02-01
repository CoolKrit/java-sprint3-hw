package model;

public class Subtask extends Task {
    private int epicId;

    public Subtask(int epicId, String name, String description, String status) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public Subtask(int epicId, int id, String name, String description, String status) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return this.epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + getName() +
                "', discription='" + getDescription() +
                "', identification='" + getId() +
                "', status='" + getStatus() +
                "', epicId=" + this.epicId +
                "'}";
    }
}