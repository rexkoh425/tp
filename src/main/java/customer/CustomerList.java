package customer;

import java.util.ArrayList;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
        System.out.println("Customer added");
        System.out.println(customer.toString());
    }

    public static boolean removeCustomer(String Username){
        for(Customer customer : customers){
            if(customer.getUsername().equals(Username)){
                customers.remove(customer);
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void printCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
            System.out.println("____________________________________________________________");
        }
    }
}

