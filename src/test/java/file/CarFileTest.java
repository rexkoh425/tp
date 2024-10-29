package file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CarFileTest {
    @Test
    public void testGetCarDataFilename() {
        assertEquals("carData.txt", CarFile.getCarDataFilename());
    }


}
