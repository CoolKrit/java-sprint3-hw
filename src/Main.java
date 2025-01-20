import model.Epic;
import model.Subtask;
import model.Task;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Task task = new Task("Игра","Выполнить квест в Skyrim","NEW");
        manager.addTask(task);

        Epic epic = new Epic("Учёба","Нужно выполнить финальный проект 3 спринта до 22 февраля","NEW");
        manager.addEpic(epic);

        Subtask subtask1 = new Subtask(2, "Теория","Прочитать теорию","NEW");
        Subtask subtask2 = new Subtask(2, "Практическая","Выполнить практические части после теории","NEW");
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        System.out.println("До изменений:");
        System.out.println(manager.getTaskHashMapValues());
        System.out.println(manager.getEpicHashMapValues());
        System.out.println(manager.getSubtaskHashMapValues());
        System.out.println();

        manager.updateTask(new Task(1, "Пробежка","Сходи потрогай трвау!","DONE"));
        manager.updateEpic(new Epic(2,"Учёба","Нужно выполнить финальный проект 3 спринта до 22 февраля","DONE"));
        manager.updateSubtask(new Subtask(2, 5, "0_0","Что-то?!","DONE"));

        System.out.println("После изменений:");
        System.out.println(manager.getTaskHashMapValues());
        System.out.println(manager.getEpicHashMapValues());
        System.out.println(manager.getSubtaskHashMapValues());
        System.out.println();

        manager.removeSubtaskById(4);
        manager.removeTaskById(1);

        System.out.println("После удалений:");
        System.out.println(manager.getTaskHashMapValues());
        System.out.println(manager.getEpicHashMapValues());
        System.out.println(manager.getSubtaskHashMapValues());
        System.out.println();

        System.out.println("Получение задачи по идентификатору:");
        System.out.println(manager.getEpicById(2));
        System.out.println();

        System.out.println("Получение всех подзадач определённого эпика:");
        System.out.println(manager.getEpicsSubtasks(2));
    }
}