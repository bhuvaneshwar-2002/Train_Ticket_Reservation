package System;

public class Passenger {
    private String name;
    private int age;
    private String berthPreference;
    private String ticketId;

    public Passenger(String name, int age, String berthPreference) {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBerthPreference() {
        return berthPreference;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}

