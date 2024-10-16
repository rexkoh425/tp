package car;

import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarListTest {

    @Test
    public void addCar_validCarObject() {
        Car car = new Car("ABC", "DEF", 180);
        Car car1 = new Car("XYZ", "456", 250);

        CarList.addCar(car);
        CarList.addCar(car1);

        assertEquals(2, CarList.getCarsList().size());
        assertEquals(car, CarList.getCarsList().get(0));
        assertEquals(car1, CarList.getCarsList().get(1));
    }

    @Test
    public void addCar_invalidCarObject_carExceptionThrown() throws CarException {

        assertThrows(CarException.class,
                () -> CarList.addCar(new Car("ABC", "DEF", -125)));

        assertEquals(0, CarList.getCarsList().size());

    }
}
