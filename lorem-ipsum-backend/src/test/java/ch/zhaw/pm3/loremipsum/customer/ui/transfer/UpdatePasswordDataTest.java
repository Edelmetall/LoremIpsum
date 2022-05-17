package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test validation of UpdatePasswordData
 */
public class UpdatePasswordDataTest {

    @Test
    public void testValid() {
        UpdatePasswordData updatePasswordData = new UpdatePasswordData();
        updatePasswordData.setNewPassword("Muster12");
        Assertions.assertTrue(updatePasswordData.isValid());
    }

    @Test
    public void testInvalid() {
        UpdatePasswordData updatePasswordData = new UpdatePasswordData();

        updatePasswordData.setNewPassword("Muster1"); // too short
        Assertions.assertFalse(updatePasswordData.isValid());

        updatePasswordData.setNewPassword("muster12"); // no upper-case
        Assertions.assertFalse(updatePasswordData.isValid());

        updatePasswordData.setNewPassword("MUSTER12"); // no lower-case
        Assertions.assertFalse(updatePasswordData.isValid());

        updatePasswordData.setNewPassword("MusterXX"); // no digit
        Assertions.assertFalse(updatePasswordData.isValid());
    }
}
