package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import exceptions.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerListTest {

    @BeforeEach
    void setUp() {
        CustomerList.removeAllCustomers();
    }

    @Test
    public void testAddCustomer() {
        Customer customer1 = new Customer("John", 18, "88414916");
        Customer customer2 = new Customer("Mary", 20, "88411416");
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        assertEquals(2, CustomerList.getCustomers().size());
        assertEquals(customer1, CustomerList.getCustomers().get(0));
        assertEquals(customer2, CustomerList.getCustomers().get(1));
    }

    @Test
    public void testAddCustomerWithDuplicateName() {
        Customer customer1 = new Customer("John", 18, "88414916");
        Customer customer2 = new Customer("john", 20, "88411416");
        CustomerList.addCustomer(customer1);
        assertThrows(CustomerException.class, () -> {
            CustomerList.addCustomer(customer2);
        });
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInName() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John@Doe", 25, "92345678");
        });

        assertEquals("The customer name \"John@Doe\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInNameUnderscore() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John_Doe", 25, "92345678");
        });

        assertEquals("The customer name \"John_Doe\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidCharactersInNameWithNumbers() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John123", 25, "92345678");
        });

        assertEquals("The customer name \"John123\" contains invalid characters. " +
                "Only alphabetic characters and spaces are allowed.", exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberTooShort() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "12345");
        });

        assertEquals("invalid contact number. Format for contact number is +[7 OR MORE DIGITS]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberTooLong() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "12345678901");
        });

        assertEquals("invalid contact number. Format for contact number is +[7 OR MORE DIGITS]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithInvalidContactNumberStartingWithInvalidDigit() {
        CustomerException exception = assertThrows(CustomerException.class, () -> {
            new Customer("John Doe", 25, "71234567");
        });

        assertEquals("invalid contact number. Format for contact number is +[7 OR MORE DIGITS]",
                exception.getMessage());
    }

    @Test
    public void testAddCustomerWithValidName() {
        Customer customer = new Customer("John Doe", 25, "92345678");
        CustomerList.addCustomer(customer);
        assertEquals(1, CustomerList.getCustomers().size());
        assertEquals("John Doe", CustomerList.getCustomers().get(0).getCustomerName());
    }
}
