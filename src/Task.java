import java.text.SimpleDateFormat;
import java.util.Date;

class Task {
    String description;
    boolean completed;
    Date dueDate;
    int priority;

    Task(String description, Date dueDate, int priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dueDateString = (dueDate != null) ? dateFormat.format(dueDate) : "Not set";
        return String.format("Description: %s\nDue Date: %s\nPriority: %d\nCompleted: %s",
                description, dueDateString, priority, (completed) ? "Yes" : "No");
    }
}