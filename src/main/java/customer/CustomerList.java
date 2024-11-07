package customer;

import java.util.ArrayList;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
        System.out.println("Customer added");
        System.out.println(customer.toString());
    }

    public static ArrayList<Customer> getCustomerList(){
        return customers;
    }
    public static void addCustomerWithoutPrintingInfo(Customer customer){
        customers.add(customer);
    }

    public static void removeCustomer(String username){
        for(Customer customer : customers){
            if(customer.getUsername().equals(username)){
                customers.remove(customer);
                System.out.println("User " + username + " has been removed");
                return;
            }
        }
        System.out.println("User " + username + " was not found");
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void printCustomers() {
        System.out.println("_".repeat(60));
        for (int i = 0 ; i < customers.size(); i++) {
            System.out.print((i+1) + ") ");
            Customer customer = customers.get(i);
            System.out.println(customer.toString());
        }
        System.out.println("_".repeat(60));
    }

    public static String customerListToFileString(){
        StringBuilder customerData = new StringBuilder();
        for (Customer customer : customers) {
            customerData.append(customer.toFileString());
            customerData.append("\n");
        }
        return customerData.toString();
    }
}

