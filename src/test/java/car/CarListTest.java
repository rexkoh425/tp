package car;

import org.junit.jupiter.api.Test;


public class CarListTest {

    @Test
    public void testCarList() {
        CarList.addCar(new Car("Honda Civic" , "SGE1234T" , 123));
        CarList.addCar(new Car("Hyundai" , "SFR1224T" , 133));
        CarList.printCarList();
    }
}
