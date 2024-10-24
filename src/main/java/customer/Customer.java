package customer;

/**
 * Represents customers of CliRental
 */
public class Customer {

    public static final int NUMBER_OF_PARAMETERS = 3;
    private String username;
    private int age;
    private String contactNumber;

    public Customer(String username, int age, String contactNumber){
        this.username = username;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String toString(){
        return "Username : " + this.getUsername() + "\n"
                + "Age : " + this.getAge() + "\n"
                + "Contact Number : " + this.getContactNumber();
    }

    public String toFileString(){
        return this.getUsername() + " | " + this.getAge() + " | " + this.getContactNumber();
    }
}
