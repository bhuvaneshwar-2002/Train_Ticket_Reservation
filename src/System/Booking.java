package System;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Booking {
    private static List<Passenger> lowerBerth = new ArrayList<>();
    private static List<Passenger> middleBerth = new ArrayList<>();
    private static List<Passenger> upperBerth = new ArrayList<>();
    private static List<Passenger> waitingList = new ArrayList<>();
    private static List<Passenger> unreserved = new ArrayList<>();
    private static int ticketCounter = 1;
    private static final Queue<Passenger> upgradeQueue = new LinkedList<>(); // Queue for upgrades
    private static int lowerBerthSize = 2;
    private static int middleBerthSize = 1;
    private static int upperBerthSize = 1;
    private static int waitingListSize = 1;
    private static int unreservedSize = 1;
    Booking booking = new Booking(lowerBerthSize, middleBerthSize, upperBerthSize,waitingListSize,unreservedSize);
    public Booking(int lowerBerthSize, int middleBerthSize, int upperBerthSize, int waitingListSize, int unreservedSize
    ) {
        lowerBerth = new ArrayList<>(lowerBerthSize);
        middleBerth = new ArrayList<>(middleBerthSize);
        upperBerth = new ArrayList<>(upperBerthSize);
        waitingList = new ArrayList<>(waitingListSize);
        unreserved = new ArrayList<>(unreservedSize);
        // Initialize other data structures and properties here
    }


    public static boolean bookTicket(Passenger passenger) {
        String berthPreference = passenger.getBerthPreference();

        if (berthPreference.equalsIgnoreCase("lower")) {
            if (lowerBerth.size() < lowerBerthSize) {
                String berthLabel = "L" + (lowerBerth.size() + 1);
                passenger.setTicketId(generateTicketId());
                lowerBerth.add(passenger);
                System.out.println("Ticket booked in Lower Berth " + berthLabel + ". Status: Confirm."+"\n"+" Ticket ID: " + passenger.getTicketId());
                return true;
            }
        } else if (berthPreference.equalsIgnoreCase("middle")) {
            if (middleBerth.size() < middleBerthSize) {
                String berthLabel = "M" + (middleBerth.size() + 1);
                passenger.setTicketId(generateTicketId());
                middleBerth.add(passenger);
                System.out.println("Ticket booked in Middle Berth " + berthLabel + ". Status: Confirm."+"\n"+" Ticket ID: " + passenger.getTicketId());
                return true;
            }
        } else if (berthPreference.equalsIgnoreCase("upper")) {
            if (upperBerth.size() < upperBerthSize) {
                String berthLabel = "U" + (upperBerth.size() + 1);
                passenger.setTicketId(generateTicketId());
                upperBerth.add(passenger);
                System.out.println("Ticket booked in Upper Berth " + berthLabel + ". Status: Confirm."+"\n"+" Ticket ID: " + passenger.getTicketId());
                return true;
            }
        }
        addToWaitingList(passenger);
            return true;
        }

    private static void addToUnreserved(Passenger passenger) {
        String urLabel = "UR" + (unreserved.size() + 1);
        passenger.setTicketId(generateTicketId());
        unreserved.add(passenger);
        System.out.println("Ticket booked in UnReserved " + urLabel + ". Status: Confirm."+"\n"+" Ticket ID: " + passenger.getTicketId());
    }

    private static void addToWaitingList(Passenger passenger) {
        if (waitingList.size() < waitingListSize) {
            String waitingLabel = "WL" + (waitingList.size() + 1);
            passenger.setTicketId(generateTicketId());
            waitingList.add(passenger);
            System.out.println("Ticket booked in Waiting List " + waitingLabel + ". Status: Confirm."+"\n"+" Ticket ID: " + passenger.getTicketId());
        } else {
            addToUnreserved(passenger);
        }
    }

    public static boolean cancelTicket(String ticketId) {
        Passenger canceledPassenger = null;
        // Check in lower berth
        for (Passenger passenger : lowerBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                canceledPassenger = passenger;
                lowerBerth.remove(passenger);
                System.out.println("Ticket ID: " + passenger.getTicketId() + " canceled from Lower Berth.");
                autoUpgradeLowerBerth();
                break;
            }
        }
        for (Passenger passenger : middleBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                middleBerth.remove(passenger);
                System.out.println("Ticket ID: " + passenger.getTicketId() + " canceled from Middle Berth.");
                autoUpgradeMiddleBerth();
                break;
            }
        }
        for (Passenger passenger : upperBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                upperBerth.remove(passenger);
                System.out.println("Ticket ID: " + passenger.getTicketId() + " canceled from Upper Berth.");
                autoUpgradeUpperBerth();
                break;
            }
        }
        for (Passenger passenger : waitingList) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                waitingList.remove(passenger);
                System.out.println("Ticket ID: " + passenger.getTicketId() + " canceled from WaitingList.");
                autoUpgradeWaitingList();
                break;
            }
        }
        for (Passenger passenger : unreserved) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                unreserved.remove(passenger);
                System.out.println("Ticket ID: " + passenger.getTicketId() + " canceled from UnReserved.");
                autoUpgradeWaitingList();
                break;
            }
        }


//        if (canceledPassenger != null) {
//            autoUpgradeLowerBerth();
////            // Check in middle berth (if canceled passenger was in lower berth)
////            if (canceledPassenger.getBerthPreference().equalsIgnoreCase("lower")) {
////            }
////            // Check in upper berth (if canceled passenger was in lower berth)
////            if (canceledPassenger.getBerthPreference().equalsIgnoreCase("lower")) {
////            }
////            if (canceledPassenger.getBerthPreference().equalsIgnoreCase("waiting")) {
////            }
//////            // Check if waiting list needs to be upgraded to lower berth
//////            if (lowerBerth.size() < lowerBerthSize && !upgradeQueue.isEmpty()) {
//////                Passenger upgradedPassenger = upgradeQueue.poll();
//////                upgradedPassenger.setTicketId(generateTicketId());
//////                lowerBerth.add(upgradedPassenger);
//////                System.out.println("Upgraded " + upgradedPassenger.getName() + "  Status: Confirm. Ticket ID: " + upgradedPassenger.getTicketId());
//////            }else if (middleBerth.size() < middleBerthSize && !upgradeQueue.isEmpty()) {
//////                Passenger upgradedPassenger = upgradeQueue.poll();
//////                upgradedPassenger.setTicketId(generateTicketId());
//////                middleBerth.add(upgradedPassenger);
//////                System.out.println("Upgraded " + upgradedPassenger.getName() + "  Status: Confirm. Ticket ID: " + upgradedPassenger.getTicketId());
//////            }else if (upperBerth.size() < upperBerthSize && !upgradeQueue.isEmpty()) {
//////                Passenger upgradedPassenger = upgradeQueue.poll();
//////                upgradedPassenger.setTicketId(generateTicketId());
//////                upperBerth.add(upgradedPassenger);
//////                System.out.println("Upgraded " + upgradedPassenger.getName() + "  Status: Confirm. Ticket ID: " + upgradedPassenger.getTicketId());
//////            }
//            return true;
//        }
        return true;
    }
    public static void autoUpgradeLowerBerth() {
        while (lowerBerth.size() < lowerBerthSize && !waitingList.isEmpty()) {
            Passenger upgradedPassenger = waitingList.get(0);
            waitingList.remove(0);
            lowerBerth.add(upgradedPassenger);
        }
    }
    public static void autoUpgradeMiddleBerth() {
        while (middleBerth.size() < middleBerthSize && !waitingList.isEmpty()) {
            Passenger upgradedPassenger = waitingList.get(0);
            waitingList.remove(0);
            middleBerth.add(upgradedPassenger);
        }
    }
    public static void autoUpgradeUpperBerth() {
        while (upperBerth.size() < upperBerthSize && !waitingList.isEmpty()) {
            Passenger upgradedPassenger = waitingList.get(0);
            waitingList.remove(0);
            upperBerth.add(upgradedPassenger);
        }
    }
    public static void autoUpgradeWaitingList() {
        while (waitingList.size() < waitingListSize && !unreserved.isEmpty()) {
            Passenger upgradedPassenger = unreserved.get(0);
            unreserved.remove(0);
            waitingList.add(upgradedPassenger);
        }
    }

    private static String generateTicketId() {
        return "T" + ticketCounter++;
    }
    public static void printAllTickets() {
        System.out.println("All Booked Tickets:");

        for (Passenger passenger : lowerBerth) {
            System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Lower");
        }

        for (Passenger passenger : middleBerth) {
            System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Middle");
        }

        for (Passenger passenger : upperBerth) {
            System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Upper");
        }

        for (Passenger passenger : waitingList) {
            System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Status: Waitlisted");
        }

        for (Passenger passenger : unreserved) {
            System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Unreserved");
        }
    }
    public static void checkTicketStatus(String ticketId) {
        // Check if waiting list needs to be upgraded to lower berth
        if (lowerBerth.size() < lowerBerthSize && !upgradeQueue.isEmpty()) {
            Passenger upgradedPassenger = upgradeQueue.poll();
            upgradedPassenger.setTicketId(generateTicketId());
            lowerBerth.add(upgradedPassenger);
            System.out.println("Upgraded " + upgradedPassenger.getName() + " from Waiting List to Lower Berth. Status: Confirm. Ticket ID: " + upgradedPassenger.getTicketId());
        }
        for (Passenger passenger : lowerBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Lower, Status: Confirm");
                return;
            }
        }

        for (Passenger passenger : middleBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Middle, Status: Confirm");
                return;
            }
        }

        for (Passenger passenger : upperBerth) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Upper, Status: Confirm");
                return;
            }
        }

        for (Passenger passenger : waitingList) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Status: Waitlisted");
                return;
            }
        }

        for (Passenger passenger : unreserved) {
            if (passenger.getTicketId().equalsIgnoreCase(ticketId)) {
                System.out.println("Ticket ID: " + passenger.getTicketId() + ", Name: " + passenger.getName() + ", Berth: Unreserved, Status: Confirm");
                return;
            }
        }

        System.out.println("Ticket ID: " + ticketId + " not found. Status: Invalid");
    }
}
