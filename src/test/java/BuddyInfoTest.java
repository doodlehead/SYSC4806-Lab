import app.entities.BuddyInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BuddyInfoTest {

    @Test
    public void testEquals() {
        BuddyInfo b1 = new BuddyInfo("carl", "5 main street", "1234567");
        BuddyInfo b2 = new BuddyInfo("carl", "5 main street", "1234567");
        BuddyInfo b3 = new BuddyInfo("alice", "5 main street", "1234567");

        assertEquals(b1, b2);
        assertNotEquals(b1, b3);
    }
}
