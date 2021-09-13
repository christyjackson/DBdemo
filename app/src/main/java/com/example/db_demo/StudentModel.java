package com.example.db_demo;

public class StudentModel {
    private String name;
    private int regno;
    private int id;

    //Constructors

    public StudentModel() {
    }

    public StudentModel(String name, int regno, int id) {
        this.name = name;
        this.regno = regno;
        this.id = id;
    }

    // toString method is needed for printing the contents of a class object


    @Override
    public String toString() {
       return "name='" + name + '\'' + " regno=" + regno + " id=" + id ;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
