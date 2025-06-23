package org.example;

import java.util.List;

public class Task {

    private final int ID; // unique identifier for the task
    private final String description; // what the task is about
    private boolean completed;  // status: completed or not
    private final List<String> tags;  // tags for categorizing tasks

    public Task(int ID, String description, List<String> tags) {
        this.ID = ID;
        this.description = description;
        this.completed = false;  // new tasks start incomplete
        this.tags = tags;
    }

    // mark the task as completed
    public void markComplete() {
        completed = true;
    }

    // check if task is completed
    public boolean isCompleted() {
        return completed;
    }

    // get task description
    public String getDescription() {
        return description;
    }

    // get tags list
    public List<String> getTags() {
        return tags;
    }

    // get task ID
    public int getID() {
        return ID;
    }

    // return string to show task info (ID, description, status, tags)
    @Override
    public String toString() {
        String status = completed ? "completed" : "incomplete";

        String tagText = "";
        if (tags != null && !tags.isEmpty()) {
            tagText = " Tags:" + tags;
        }

        return "[" + ID + "] " + description + " (" + status + ")" + tagText;
    }
}
