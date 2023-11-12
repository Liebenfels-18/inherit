package ru.netology.inherit;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TasksTest {
    @Test
    public void testMatchesReturnsFalseWhenQueryNotInSubtasks() {
        Epic epic = new Epic(1, new String[]{"Задача 1", "Задача 2", "Задача 3"});
        String query = "Задача 4";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInSubtasks() {
        String[] subtasks = {"Задача 1", "Задача 2", "Задача 3"};
        Epic epic = new Epic(1, subtasks);
        String query = "Задача 2";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Simple task");
        String query = "task";
        boolean expected = true;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsFalseWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(1, "Simple task");
        String query = "epic";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSearchingWithNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {};
        Task[] actual = todos.search("Покормить собаку");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfTasksWhenSearchingWithOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко")};
        Task[] actual = todos.search("Купить молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfTasksWhenSearchingWithMultiplyMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Покормить собаку"));
        todos.add(new Epic(2, new String[]{"Выгулять собаку"}));
        Task[] expected = {new SimpleTask(1, "Покормить собаку"), new Epic(2, new String[]{"Выгулять собаку"})};
        Task[] actual = todos.search("собаку");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfAllTasksWhenSearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Сделать домашнее задание"})};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void testMatchesReturnsTrueWhenQueryInTopic() {
        Meeting meeting = new Meeting(1, "Тема", "Проект", "сегодня после 14:00");
        String query = "Тема";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInProject() {
        Meeting meeting = new Meeting(1, "Тема", "Проект", "сегодня после 14:00");
        String query = "Проект";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsFalseWhenQueryNotInTopicOrProject() {
        Meeting meeting = new Meeting(1, "Тема", "Проект", "сегодня после 14:00");
        String query = "Завтра";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }
}
