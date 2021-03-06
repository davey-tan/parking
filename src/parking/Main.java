/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Crestfall
 */
public class Main {

    // stupid workaround
    int currentid = 0;
    // ArrayList inits
    private static ArrayList<Vehicle> vehicleList = new ArrayList();
    private static ArrayList<Parking> parkingList = new ArrayList();

    // checks if a Vehicle in vehicleList is classified as deleted (by looking at the string)
    public static boolean checkVehicle(int i) {
        if ((vehicleList.get(i).getVehicleModel()).equalsIgnoreCase("DELETED")) {
            return true;
        }
        return false;
    }

    // adds a Vehicle to vehicleList
    public static void addVehicle(String model, String type, String wheel) {
        Vehicle vhc = new Vehicle(model, type, wheel);
        vehicleList.add(vhc);
        System.out.println("Succesfully added!");
        selectVehicle(vehicleList.size() - 1);
        System.out.println("");
    }

    // prints index of i from vehicleList
    public static void selectVehicle(int i) {
        System.out.format("%3d%25s%15s%15s\n", i, vehicleList.get(i).getVehicleModel(), vehicleList.get(i).getVehicleType(), vehicleList.get(i).getVehicleWheel());
    }

    // list all vehicles in vehicleList
    public static void listVehicle() {
        System.out.format("%3s%25s%15s%15s\n", "id", "model", "type", "wheels");
        for (int i = 0; i < vehicleList.size(); i++) {
            selectVehicle(i);
        }
        System.out.println("");
    }

    // checks if a slot in parkingList is occupied
    public static boolean checkParking(int i) {
        return parkingList.get(i).occupied;
    }

    // adds an empty parking slot
    public static void addParkingSlot() {
        Parking park = new Parking("", vehicleList.get(0), "");
        parkingList.add(park);
    }

    // prints index of i from parkingList
    public static void selectParking(int i) {
        System.out.format("%3d%10b%40s%25s%15s\n", i, checkParking(i), parkingList.get(i).getParkingName(), parkingList.get(i).getParkingVehicle().getVehicleModel(), parkingList.get(i).getParkingPlate());
    }

    // list all parking slots in parkingList
    public static void listParking() {
        System.out.format("%3s%10s%40s%25s%15s\n", "id", "occupied", "name", "vehicle", "plate");
        for (int i = 0; i < parkingList.size(); i++) {
            selectParking(i);
        }
        System.out.println("");
    }

    // add customer to parkingList
    public static void addParking(int i, String name, Vehicle vhc, String plate) {
        parkingList.get(i).setParkingName(name);
        parkingList.get(i).setParkingVehicle(vhc);
        parkingList.get(i).setParkingPlate(plate);
        parkingList.get(i).setParkingOccupied("true");
    }

    public static void delParking(int i) {
        parkingList.get(i).setParkingName("");
        parkingList.get(i).setParkingVehicle(vehicleList.get(0));
        parkingList.get(i).setParkingPlate("");
        parkingList.get(i).setParkingOccupied("false");
    }

    // Main Class
    public static void main(String[] args) {

        // runtime initialization
        System.out.println("Added dummy empty vehicle.");
        addVehicle("Empty", "", "");
        System.out.println("Added dummy vehicle, used if vehicle isn't registered yet.");
        addVehicle("Dummy", "", "");

        // CMD Bootleg 1.0 by Davey
        // Initial Creation
        Scanner scanner = new Scanner(System.in);
        var command = "help";

        // Commands
        while (true) {
            switch (command) {
                // if the user input wrong command DUH
                default:
                    System.out.println("Unknown command.");
                    System.out.println("Refer to 'help' for list of commands.");
                    System.out.println("");
                    break;
                // shows all commands
                case "help":
                    System.out.println("help: List of available commands.");
                    System.out.println("credits: Show credits.");
                    System.out.println("vhc_list: Show vehicle list.");
                    System.out.println("vhc_add: Create new vehicle model.");
                    System.out.println("vhc_del: Delete a vehicle model.");
                    System.out.println("park_list: Show parking list.");
                    System.out.println("park_slot: Add empty parking slot.");
                    System.out.println("park_add: Add customer into a parking slot.");
                    System.out.println("park_del: Remove customer from parking slot.");
                    System.out.println("BROKEN DO NOT USE park_edit: Edit a customer in parking slot.");
                    System.out.println("");
                    break;
                // maybe i should credit stack overflow too
                case "credits":
                    System.out.println("Basic Vehicle Parking");
                    System.out.println("by: Davey");
                    System.out.println("BID: 2301907252");
                    System.out.println("");
                    break;
                // list of vehicles
                case "vhc_list":
                    listVehicle();
                    break;
                // add a vehicle to vehicleList
                case "vhc_add":
                    // model name
                    System.out.println("Insert vehicle model name (ex: Toyota Trueno AE86). Type 'cancel' to cancel.");
                    System.out.print("Vehicle Model: ");
                    var model = scanner.nextLine();
                    if (model.equalsIgnoreCase("cancel")) {
                        break;
                    }
                    // vehicle type
                    System.out.println("Insert vehicle type (ex: truck, sedan, suv, motorcycle). Type 'cancel' to cancel.");
                    System.out.print("Vehicle Type: ");
                    var type = scanner.nextLine();
                    if (type.equalsIgnoreCase("cancel")) {
                        break;
                    }
                    // wheels on vehicle
                    System.out.println("Insert amount of wheels on vehicle. Type 'cancel' to cancel.");
                    System.out.print("Vehicle Wheels: ");
                    var wheel = scanner.nextLine();
                    if (wheel.equalsIgnoreCase("cancel")) {
                        break;
                    }
                    // bam were done
                    addVehicle(model, type, wheel);
                    break;
                // delete a vehicle from vehicleList
                case "vhc_del":
                    // start by listing vehicleList
                    listVehicle();
                    // dont input anything stupid
                    while (true) {
                        System.out.println("Select vehicle to delete. Type 'cancel' to cancel.");
                        System.out.print("ID: ");
                        var delete = scanner.nextLine();
                        // if user wants to cancel
                        if (delete.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        // NO you cant delete this
                        if (delete.equalsIgnoreCase("0") || delete.equalsIgnoreCase("1")) {
                            System.out.println("Can't delete dummy vehicle!");
                            System.out.println("");
                            break;
                        }
                        // just to make sure
                        try {
                            int test = Integer.parseInt(delete);
                            // JUST TO MAKE SURE
                            if (checkVehicle(test)) {
                                System.out.println("Vehicle is already deleted!");
                                System.out.println("");
                            } else {
                                vehicleList.get(test).setVehicleModel("DELETED");
                                vehicleList.get(test).setVehicleType("");
                                vehicleList.get(test).setVehicleWheel("");
                                break;
                            }
                            // are you serious? NUMBERS
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                            System.out.println("Please input numbers only!");
                            System.out.println("");
                            // NO THEY DONT EXIST DUMBASS
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e);
                            System.out.println("Vehicle doesn't exist!");
                            System.out.println("");
                        } catch (NullPointerException e) {
                            System.out.println(e);
                            System.out.println("Input is empty!");
                            System.out.println("");
                        }
                    }
                    break;
                // parking list
                case "park_list":
                    listParking();
                    break;
                // lets add parking slots
                case "park_slot":
                    System.out.println("Insert amount of parking slots to add. Type 'cancel' to cancel.");
                    System.out.print("Number: ");
                    var num = scanner.nextLine();
                    // wanna cancel?
                    if (num.equalsIgnoreCase("cancel")) {
                        break;
                    }
                    // loop through the amount of i, addParkingSlot continuously
                    try {
                        Integer.parseInt(num);
                        for (int i = 0; i < Integer.parseInt(num); i++) {
                            addParkingSlot();
                        }
                        // no just no
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                        System.out.println("Please input numbers only!");
                        System.out.println("");
                    }
                    System.out.println("");
                    break;
                // this is where the fun begins (no its not fun at all)
                case "park_add":
                    // need to init outside of while, where its mainly used
                    int id_vhc = 0;
                    int id_park = 0;
                    boolean dummy = false;
                    String plate = "";
                    // input
                    System.out.println("Insert customer name. Type 'cancel' to cancel.");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    // cancel? okie dokie
                    if (name.equalsIgnoreCase("cancel")) {
                        break;
                    }
                    // list vehicle, cuz human memory is same as goldfish
                    listVehicle();
                    while (true) {
                        System.out.println("Choose customer vehicle. Type 'cancel' to cancel.");
                        System.out.println("If vehicle model is not registered, just type '1'");
                        System.out.println("and edit customer data later.");
                        System.out.print("ID: ");
                        var vhcid = scanner.nextLine();
                        // cancel ok
                        if (vhcid.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        // please use "1" not "0"
                        if (vhcid.equalsIgnoreCase("0")) {
                            System.out.println("Can't use 'Empty' as vehicle!");
                            System.out.println("Using dummy vehicle instead.");
                            vhcid = "1";
                            dummy = true;
                        }
                        try {
                            id_vhc = Integer.parseInt(vhcid);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                            System.out.println("Please input numbers only!");
                            System.out.println("");
                            // i gave up
                        } catch (NullPointerException e) {
                            vhcid = "1";
                            dummy = true;
                            break;
                        }
                    }
                    // serial plate will be skipped if using dummy vehicle
                    if (dummy == false) {
                        System.out.println("Insert plate serial. Type 'cancel' to cancel.");
                        System.out.print("Plate: ");
                        plate = scanner.nextLine();
                        // cancel if you want
                        if (plate.equalsIgnoreCase("cancel")) {
                            break;
                        }
                    }
                    // list parking you goldfish
                    listParking();
                    while (true) {
                        System.out.println("Choose parking slot. Type 'cancel' to cancel. Type 'next' to automatically find empty slots.");
                        System.out.print("ID: ");
                        var parkid = scanner.nextLine();
                        if (parkid.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        // big brain energy by davey
                        if (parkid.equalsIgnoreCase("next")) {
                            for (int i = 0; i < parkingList.size(); i++) {
                                if (!parkingList.get(i).occupied) {
                                    parkid = String.valueOf(i);
                                    break;
                                }
                            }
                        }
                        try {
                            id_park = Integer.parseInt(parkid);
                            break;
                            // input NUMBERS
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                            System.out.println("Please input numbers only!");
                            System.out.println("");
                            // EMPTY IS NOT ACCEPTABLE
                        } catch (NullPointerException e) {
                            System.out.println(e);
                            System.out.println("Input is empty!");
                            System.out.println("");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e);
                            System.out.println("Slot doesn't exist!");
                            System.out.println("");
                        }
                    }
                    // were done, time to wrap it up
                    addParking(id_park, name, vehicleList.get(id_vhc), plate);
                    System.out.println("Succesfully added!");
                    selectParking(id_park);
                    System.out.println("");
                    break;
                case "park_del":
                    // next time i will stop doing this i promise
                    int idp = 0;
                    // list all parking
                    listParking();
                    while (true) {
                        System.out.println("Choose parking slot to remove customer from. Type 'cancel' to cancel.");
                        System.out.print("ID: ");
                        var parkid = scanner.nextLine();
                        // cancel stuff
                        if (parkid.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        if (!checkParking(Integer.parseInt(parkid))) {
                            System.out.println("Empty parking slot!");
                            break;
                        }
                        // delete this (i)
                        try {
                            idp = Integer.parseInt(parkid);
                            delParking(idp);
                            break;
                            // if you dare input anything other than numbers
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                            System.out.println("Please input numbers only!");
                            System.out.println("");
                            // nullpo GAH
                        } catch (NullPointerException e) {
                            System.out.println(e);
                            System.out.println("Input is empty!");
                            System.out.println("");
                            // the slot is a lie
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e);
                            System.out.println("Slot doesn't exist!");
                            System.out.println("");
                        }
                    }
                    break;
                case "park_edit":
                    System.out.println("BROKEN PLEASE IGNORE");
//                    // start by showing list of parking
//                    listParking();
//                    // retarded var name i know
//                    String nama;
//                    // then ask which to edit
//                    while (true) {
//                        System.out.println("Choose which parking slot to edit. Type 'cancel' to cancel.");
//                        System.out.print("ID: ");
//                        var parkid = scanner.nextLine();
//                        if (parkid.equalsIgnoreCase("cancel")) {
//                            break;
//                        }
//                        if (!checkParking(Integer.parseInt(parkid))) {
//                            System.out.println("Empty parking slot!");
//                            break;
//                        }
//                        try {
//                            selectParking(Integer.parseInt(parkid));
//                            break;
//                            // if you dare input anything other than numbers
//                        } catch (NumberFormatException e) {
//                            System.out.println(e);
//                            System.out.println("Please input numbers only!");
//                            System.out.println("");
//                            // nullpo GAH
//                        } catch (NullPointerException e) {
//                            System.out.println(e);
//                            System.out.println("Input is empty!");
//                            System.out.println("");
//                            // the slot is a lie
//                        } catch (IndexOutOfBoundsException e) {
//                            System.out.println(e);
//                            System.out.println("Slot doesn't exist!");
//                            System.out.println("");
//                        }
//                        System.out.println("Insert customer name. Type 'cancel' to cancel. Type 'nochange' to use previous value.");
//                        System.out.print("Name: ");
//                        var parkname = scanner.nextLine();
//                        if (parkname.equalsIgnoreCase("cancel")) {
//                            break;
//                        }
//                        if (!parkname.equalsIgnoreCase("nochange")) {
//                            nama = parkname;
//                        }
//                        listVehicle();
//                        System.out.println("Select vehicle to change to. Type 'cancel' to cancel. Type 'nochange' to use previous value.");
//                        System.out.print("ID: ");
//                        var vhcid = scanner.nextLine();
//                        if (vhcid.equalsIgnoreCase("cancel")) {
//                            break;
//                        }
//                        if (!vhcid.equalsIgnoreCase("nochange")) {
//                            nama = parkname;
//                        }
//                    }
                    break;
            }
            System.out.print("Command: ");
            command = scanner.nextLine();
        }
    }
}
