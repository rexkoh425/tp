package customer;

import car.Car;

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

    public static void removeCustomer(String customerName){
        for(Customer customer : customers){
            if(customer.getCustomerName().equals(customerName)){
                customers.remove(customer);
                System.out.println("User " + customerName + " has been removed");
                return;
            }
        }
        System.out.println("User " + customerName + " was not found");
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void printCustomers() {
        if (CustomerList.getCustomers().isEmpty()) {
            System.out.println("Customer list is empty.");
        } else {
            System.out.println("Here are all the customers: ");
            for (int i = 0; i < customers.size(); i++) {
                System.out.print((i + 1) + ") ");
                Customer customer = customers.get(i);
                System.out.println(customer.toString());
            }
        }
    }

    public static boolean isExistingCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(customerName)) {
                return true;
            }
        }
        return false;
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

