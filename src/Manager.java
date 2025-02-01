import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;

public class Manager {
    private int id;
    private Map<Integer, Task> taskHashMap = new HashMap<>();;
    private Map<Integer, Epic> epicHashMap = new HashMap<>();;
    private Map<Integer, Subtask> subtaskHashMap = new HashMap<>();;

    public Manager() {
        this.id = 0;
    }

    public void addTask(Task task) {
        task.setId(++id);
        taskHashMap.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        epic.setId(++id);
        epicHashMap.put(epic.getId(), epic);
    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(++id);
        subtaskHashMap.put(subtask.getId(), subtask);
        epicHashMap.get(subtask.getEpicId()).addSubtaskId(subtask.getId());
    }

    public List<Task> getTaskHashMapValues() {
        return new ArrayList<>(taskHashMap.values());
    }

    public List<Task> getEpicHashMapValues() {
        if (!epicHashMap.isEmpty()) return new ArrayList<>(epicHashMap.values());
        else return new ArrayList<>();
    }

    public List<Task> getSubtaskHashMapValues() {
        return new ArrayList<>(subtaskHashMap.values());
    }

    public void clearTaskHashMap() {
        taskHashMap.clear();
    }

    public void clearEpicHashMap() {
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    public void clearSubtaskHashMap() {
        for (Subtask subtask : subtaskHashMap.values()) {
            epicHashMap.get(subtask.getEpicId()).removeSubtask(subtask.getId());
            updateEpicStatus(epicHashMap.get(subtask.getEpicId()));
        }
        subtaskHashMap.clear();
    }

    private void updateEpicStatus(Epic epic) {
        int newScore = 0;
        int doneScore = 0;
        for (int subtaskId : epic.getSubtasksIds()) {
            if (subtaskHashMap.get(subtaskId).getStatus().equals("NEW")) {
                newScore++;
            } else if (subtaskHashMap.get(subtaskId).getStatus().equals("DONE")) {
                doneScore++;
            }
        }

        if (epic.getSubtasksIds().isEmpty() || newScore == epic.getSubtasksIds().size()) {
            epic.setStatus("NEW");
        } else if (doneScore == epic.getSubtasksIds().size()) {
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }

    public Task getTaskById(int id) {
        if (taskHashMap.containsKey(id)) return taskHashMap.get(id);
        return null;
    }

    public Epic getEpicById(int id) {
        if (epicHashMap.containsKey(id)) return epicHashMap.get(id);
        return null;
    }

    public Subtask getSubtaskById(int id) {
        if (subtaskHashMap.containsKey(id)) return subtaskHashMap.get(id);
        return null;
    }

    public void updateTask(Task newTask) {
        taskHashMap.put(newTask.getId(), newTask);
    }

    public void updateEpic(Epic newEpic) {
        Epic epic = epicHashMap.get(newEpic.getId());
        boolean isSameDetails = Objects.equals(newEpic.getName(), epic.getName()) &&
                Objects.equals(newEpic.getDescription(), epic.getDescription());

        if (isSameDetails && "DONE".equals(newEpic.getStatus()) && !newEpic.getStatus().equals(epic.getStatus())) {
            epic.setStatus("DONE");
            for (Subtask subtask : subtaskHashMap.values()) {
                if (subtask.getEpicId() == epic.getId()) {
                    subtask.setStatus("DONE");
                }
            }
        } else if (!newEpic.equals(epic)) {
            removeEpicsSubtasks(epic.getId());
            epicHashMap.put(newEpic.getId(), newEpic);
        }
    }

    public void updateSubtask(Subtask newSubtask) {
        Epic epic = epicHashMap.get(newSubtask.getEpicId());
        if (epic != null && epic.getSubtasksIds().contains(newSubtask.getId())) {
            subtaskHashMap.put(newSubtask.getId(), newSubtask);
            updateEpicStatus(epic);
        } else {
            System.out.println("Подзадачи с идентификатором - " + newSubtask.getId() + " нет!");
        }
    }

    public void removeTaskById(int id) {
        if (taskHashMap.containsKey(id)) taskHashMap.remove(id);
        else System.out.println("Задачи с идентификатором - " + id + " нет!");
    }

    public void removeEpicById(int id) {
        if (epicHashMap.containsKey(id)) {
            removeEpicsSubtasks(id);
            epicHashMap.remove(id);
        } else System.out.println("Эпик с идентификатором - " + id + " нет!");
    }

    public void removeSubtaskById(int id) {
        if (subtaskHashMap.containsKey(id)) {
            epicHashMap.get(subtaskHashMap.get(id).getEpicId()).removeSubtask(id);
            subtaskHashMap.remove(id);
        } else System.out.println("Подзадачи с идентификатором - " + id + " нет!");
    }

    private void removeEpicsSubtasks(int id) {
        ArrayList<Integer> keysToRemove = new ArrayList<>();
        for (Subtask subtask : subtaskHashMap.values()) {
            if (subtask.getEpicId() == id) {
                keysToRemove.add(subtask.getId());
            }
        }

        for (Integer key : keysToRemove) {
            subtaskHashMap.remove(key);
        }
    }

    public ArrayList<Subtask> getEpicsSubtasks(int id) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Subtask subtask : subtaskHashMap.values()) {
            if (subtask.getEpicId() == epicHashMap.get(id).getId()) {
                subtasks.add(subtask);
            }
        }
        return subtasks;
    }
}