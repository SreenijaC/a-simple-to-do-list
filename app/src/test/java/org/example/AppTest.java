package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class AppTest {

  // small helper for tag lists
  private List<String> tagList(String... tags) {
    List<String> out = new ArrayList<>();
    for (String t : tags)
      out.add(t);
    return out;
  }


  @Test
  void testAddBasicTasks() {
    TodoList list = new TodoList();
    list.add("Buy milk");
    list.add("Buy eggs");

    List<Task> all = list.all();
    assertEquals(2, all.size());
    assertEquals("Buy milk", all.get(0).getDescription());
    assertEquals("Buy eggs", all.get(1).getDescription());
  }

  @Test
  void testCompleteByDescription() {
    TodoList list = new TodoList();
    list.add("Call mom");
    list.complete("Call mom");
    assertEquals(1, list.complete().size());
  }

  @Test
  void testCompleteById() {
    TodoList list = new TodoList();
    list.add("Walk dog");
    int id = list.all().get(0).getID(); // grab auto-generated ID
    list.complete(id);
    assertTrue(list.complete().get(0).isCompleted());
  }

// invalid / edge inputs 
  @Test
  void testRejectBlankTask() {
    TodoList list = new TodoList();
    list.add("   ");
    list.add("");
    assertEquals(0, list.all().size());
  }

  @Test
  void testRejectNumberOnlyTask() {
    TodoList list = new TodoList();
    list.add("12345"); // numbers-only
    assertEquals(0, list.all().size());
  }

  @Test
  void testRejectSymbolsOnlyTask() {
    TodoList list = new TodoList();
    list.add("@@@###"); // symbols-only
    assertEquals(0, list.all().size());
  }

  @Test
  void testRejectDuplicateIncompleteTask() {
    TodoList list = new TodoList();
    list.add("Buy milk");
    list.add("buy milk"); // duplicate (case-insensitive)
    assertEquals(1, list.all().size());
  }

  @Test
  void testAllowDuplicateIfCompleted() {
    TodoList list = new TodoList();
    list.add("Buy eggs");
    list.complete("Buy eggs");
    list.add("Buy eggs"); // allowed now
    assertEquals(2, list.all().size());
  }

//tags & clear 

  @Test
  void testTaggedTasks() {
    TodoList list = new TodoList();
    list.add("Buy milk", tagList("food"));
    list.add("Plant basil", tagList("garden"));
    list.add("Read", tagList("relax"));

    List<Task> food = list.taggedWith("FOOD"); // case-insensitive
    List<Task> none = list.taggedWith("music");

    assertEquals(1, food.size());
    assertEquals("Buy milk", food.get(0).getDescription());
    assertEquals(0, none.size());
  }

  @Test
  void testClearList() {
    TodoList list = new TodoList();
    list.add("Task 1");
    list.add("Task 2");
    list.clear();
    assertEquals(0, list.all().size());
    list.clear(); // clearing when already empty
    assertEquals(0, list.all().size());
  }
}
