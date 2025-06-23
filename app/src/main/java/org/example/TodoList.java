package org.example;

import java.util.ArrayList;
import java.util.List;

/* Beginner-style TodoList that stores tasks, validates input,and lets you view 
`   / complete / clear them. */
public class TodoList {

    // fields
    private final List<Task> tasks = new ArrayList<>(); // all tasks
    private int nextId = 1; // keeps track of next unique ID to assign to new task, starting from 1

    // convenience overload: add a task without any tags.
    public void add(String description) {
        add(description, new ArrayList<>()); // delegate to main add
    }

    // add method: description + optional tag list.
    public void add(String description, List<String> tags) {
        // basic null / blank checks
        if (description == null) {
            System.out.println("Task description cannot be null.");
            return;
        }
        String taskDescription = description.trim(); // remove leading/trailing spaces

        if (taskDescription.isEmpty()) {
            System.out.println("Blank tasks are not allowed.");
            return;
        }

        // check if description is only digits
        boolean onlyDigits = true;
        for (int i = 0; i < taskDescription.length(); i++) {
            if (!Character.isDigit(taskDescription.charAt(i))) {
                onlyDigits = false;
                break;
            }
        }
        if (onlyDigits) {
            System.out.println("Task cannot be only numbers.");
            return;
        }

        // check if description is only symbols (non-alphanumeric)
        boolean onlySymbols = true;
        for (int i = 0; i < taskDescription.length(); i++) {
            char c = taskDescription.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                onlySymbols = false;
                break;
            }
        }
        if (onlySymbols) {
            System.out.println("Task cannot be only special characters.");
            return;
        }

        // check for duplicate incomplete task
        for (Task t : tasks) {
            if (!t.isCompleted() &&
                    t.getDescription().equalsIgnoreCase(taskDescription)) {
                System.out.println("Task already exists and is not yet completed.");
                return;
            }
        }

        // all clear: create new task
        Task newTask = new Task(nextId, taskDescription, tags);
        tasks.add(newTask);
        System.out.println("Task added: [" + nextId + "] " + taskDescription);
        nextId++;
    }

    // complete task by description
    public void complete(String description) {
        if (description == null) {
            System.out.println("Task description cannot be null.");
            return;
        }
        String desc = description.trim();
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(desc)) {
                if (!t.isCompleted()) {
                    t.markComplete();
                    System.out.println("Task \"" + desc + "\" marked complete.");
                } else {
                    System.out.println("Task \"" + desc + "\" was already completed.");
                }
                return;
            }
        }
        System.out.println("No task matches that description.");
    }

    // complete task by ID
    public void complete(int id) {
        for (Task t : tasks) {
            if (t.getID() == id) {
                if (!t.isCompleted()) {
                    t.markComplete();
                    System.out.println("Task ID " + id + " marked complete.");
                } else {
                    System.out.println("Task ID " + id + " was already completed.");
                }
                return;
            }
        }
        System.out.println("No task found with ID " + id);
    }

    // show all tasks
    public List<Task> all() {
        printList("All tasks", tasks);
        return tasks;
    }

    // show completed tasks only
    public List<Task> complete() {
        return showFiltered(true, "Completed");
    }

    // show incomplete tasks only
    public List<Task> incomplete() {
        return showFiltered(false, "Incomplete");
    }

    // clear all tasks
    public void clear() {
        tasks.clear();
        System.out.println("All tasks have been cleared.");
    }

    // get tasks by tag (case-insensitive)
    public List<Task> taggedWith(String tag) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTags() != null)
                for (String tg : t.getTags())
                    if (tg.equalsIgnoreCase(tag)) {
                        result.add(t);
                        break;
                    }
        }
        printList("Tasks tagged with '" + tag + "'", result);
        return result;
    }

    // private helper to filter tasks by completion status
    private List<Task> showFiltered(boolean wantDone, String title) {
        List<Task> out = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == wantDone) {
                out.add(t);
            }
        }
        printList(title + " tasks", out);
        return out;
    }

    // private helper to print task lists nicely
    private void printList(String title, List<Task> list) {
        System.out.println("\n== " + title + " ==");
        if (list.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Task t : list) {
                System.out.println("  " + t);
            }
        }
    }
}
