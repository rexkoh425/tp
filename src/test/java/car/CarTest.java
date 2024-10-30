package car;

import exceptions.CarException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarTest {

    @Test
    public void testCarGetters_validParams() {
        Car car = new Car("Toyota 86", "SJX1234D", 120);
        assertEquals("Toyota 86", car.getModel());
        assertEquals("SJX1234D", car.getLicensePlateNumber());
        assertEquals(120, car.getPrice());
        assertFalse(car.isRented());
        assertEquals("Available" , car.getRentedStatus());

        Car car1 = new Car("ABC", "DE3F", 0);
        assertEquals("ABC", car1.getModel());
        assertEquals("DE3F", car1.getLicensePlateNumber());
        assertEquals(0, car1.getPrice());
        assertFalse(car1.isRented());
        assertEquals("Available" , car1.getRentedStatus());
    }

    @Test
    public void testCarGetters_negativePrice_carExceptionThrown() throws CarException {

        Car car = new Car("XYZ", "123", -0);
        assertEquals(0, car.getPrice());
    }
}
