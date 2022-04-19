package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SignUpDataTest {

    @Test
    public void testValid() {
        Assertions.assertTrue(create("Max", "Muster", "max@muster.ch", "Muster12").isValid());
    }

    @Test
    public void testEmptyFirstName() {
        Assertions.assertFalse(create(null, "Muster", "max@muster.ch", "Muster12").isValid());
        Assertions.assertFalse(create(" ", "Muster", "max@muster.ch", "Muster12").isValid());
        Assertions.assertFalse(create("", "Muster", "max@muster.ch", "Muster12").isValid());
    }

    @Test
    public void testEmptyLastName() {
        Assertions.assertFalse(create("Max", null, "max@muster.ch", "Muster12").isValid());
        Assertions.assertFalse(create("Max", " ", "max@muster.ch", "Muster12").isValid());
        Assertions.assertFalse(create("Max", "", "max@muster.ch", "Muster12").isValid());
    }

    @Test
    public void testEmptyEmail() {
        Assertions.assertFalse(create("Max", "Muster", null, "Muster12").isValid());
        Assertions.assertFalse(create("Max", "Muster", " ", "Muster12").isValid());
        Assertions.assertFalse(create("Max", "Muster", "", "Muster12").isValid());
    }

    @Test
    public void testEmptyPassword() {
        Assertions.assertFalse(create("Max", "Max", "max@muster.ch", null).isValid());
        Assertions.assertFalse(create("Max", "Max", "max@muster.ch", " ").isValid());
        Assertions.assertFalse(create("Max", "Max", "max@muster.ch", "").isValid());
    }

    private SignUpData create(String firstName, String lastName, String email, String password) {
        SignUpData signUpData = new SignUpData();
        signUpData.setFirstName(firstName);
        signUpData.setLastName(lastName);
        signUpData.setEmail(email);
        signUpData.setPassword(password);
        return signUpData;
    }
}
