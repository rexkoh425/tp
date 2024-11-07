package customer;

/**
 * Represents customers of CliRental
 */
public class Customer {

    public static final int NUMBER_OF_PARAMETERS = 3;
    private String customerName;
    private int age;
    private String contactNumber;

    public Customer(String customerName, int age, String contactNumber){
        this.customerName = customerName;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String toString(){
        return this.getCustomerName() + " | "
                + this.getAge() + " | "
                + this.getContactNumber();
    }

    public String toFileString(){
        return this.getCustomerName() + " | " + this.getAge() + " | " + this.getContactNumber();
    }

    public String toDisplayString(){
        return "Username : " + getCustomerName() + "\nAge : " + getAge() + "\nContact Number :"
                + getContactNumber();
    }
}
