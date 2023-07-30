package com.oreilly.astro;

import java.util.List;

public class AstroResponse {
    private String message;
    private int number;
    private List<Assignment> people;

    public String getMessage() {
        return message;
    }

    public int getNumber() {
        return number;
    }

    public List<Assignment> getPeople() {
        return people;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPeople(List<Assignment> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "AstroResponse{" +
                "message='" + message + '\'' +
                ", number=" + number +
                ", people=" + people +
                '}';
    }

    public static class Assignment {
        private String craft;
        private String name;

        public String getCraft() {
            return craft;
        }

        public String getName() {
            return name;
        }

        public void setCraft(String craft) {
            this.craft = craft;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Assignment{" +
                    "craft='" + craft + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
