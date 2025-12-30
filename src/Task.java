import java.time.LocalDate;

public class Task {
    private String name;
    private LocalDate dueDate;
    private LocalDate creationDate;
    private LocalDate completionDate;
    private boolean completed;

    public Task(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
        this.creationDate = LocalDate.now();
        this.completed = false;
    }

    public void completeTask() {
        this.completed = true;
        this.completionDate = LocalDate.now();
    }

    public String toString() {
        return name + " (" + dueDate + ")" + (completed ? " completada en " + completionDate : " pendiente");
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }
}
