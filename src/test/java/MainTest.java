import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testForIllegalProgramArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Main.main(new String[] {"illegal_argument"});
        });
    }
}