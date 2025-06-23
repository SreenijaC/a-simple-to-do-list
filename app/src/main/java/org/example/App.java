package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Todo List!");

        boolean running = true;

        while (running) {
            // show menu options
            System.out.println("\nChoose an option:");
            System.out.println("1. Add task");
            System.out.println("2. Complete task");
            System.out.println("3. Show all tasks");
            System.out.println("4. Show completed tasks");
            System.out.println("5. Show incomplete tasks");
            System.out.println("6. Show tasks by tag");
            System.out.println("7. Clear all tasks");
            System.out.println("8. Exit");

            System.out.print("Enter your choice (1-8): ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                // add task
                System.out.print("Enter task description: ");
                String desc = scanner.nextLine();

                System.out.print("Enter tags separated by commas (or leave blank): ");
                String tagsInput = scanner.nextLine();
                List<String> tags = new ArrayList<>();

                // split it on commas
                if (!tagsInput.trim().isEmpty()) {
                    String[] splitTags = tagsInput.split(",");
                    for (String t : splitTags) {
                        tags.add(t.trim()); // trim spaces and add each tag to the list
                    }
                }

                todoList.add(desc, tags);

            } else if (choice.equals("2")) {
                // complete task by description
                System.out.print("Enter task description to complete: ");
                String desc = scanner.nextLine();
                todoList.complete(desc);

            } else if (choice.equals("3")) {
                // show all tasks
                todoList.all();

            } else if (choice.equals("4")) {
                // show completed tasks
                todoList.complete();

            } else if (choice.equals("5")) {
                // show incomplete tasks
                todoList.incomplete();

            } else if (choice.equals("6")) {
                // show tasks by tag
                System.out.print("Enter tag to filter by: ");
                String tag = scanner.nextLine();
                todoList.taggedWith(tag);

            } else if (choice.equals("7")) {
                // clear all tasks
                System.out.print("Are you sure? This will delete all tasks. (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("yes")) {
                    todoList.clear();
                } else {
                    System.out.println("Clear is cancelled.");
                }

            } else if (choice.equals("8")) {
                // exit program
                System.out.println("Goodbye!");
                running = false;

            } else {
                // invalid input
                System.out.println("Invalid choice, please enter a number 1-8.");
            }
        }

        scanner.close();
    }
}
