package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test Validation of UpdateEmailData
 */
public class UpdateEmailDataTest {

    @Test
    public void testValid() {
        UpdateEmailData updateEmailData = new UpdateEmailData();
        updateEmailData.setNewEmail("max@muster.ch");
        Assertions.assertTrue(updateEmailData.isValid());
    }

    @Test
    public void testInvalid() {
        UpdateEmailData updateEmailData = new UpdateEmailData();

        updateEmailData.setNewEmail("max");
        Assertions.assertFalse(updateEmailData.isValid());

        updateEmailData.setNewEmail("max@");
        Assertions.assertFalse(updateEmailData.isValid());

        updateEmailData.setNewEmail("max@muster");
        Assertions.assertFalse(updateEmailData.isValid());
    }
}
