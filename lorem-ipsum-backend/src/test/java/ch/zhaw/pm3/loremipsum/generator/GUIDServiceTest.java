package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUIDServiceTest extends AbstractSpringBootTest {

    @Autowired
    private GUIDService guuidService;

    @Test
    public void testValidGUIDFormat() {
        Assertions.assertTrue(isValidGUID(guuidService.getData(null, Set.of())));
    }

    public static boolean isValidGUID(String str) {
        String regex
                = "^[{]?[0-9a-fA-F]{8}"
                + "-([0-9a-fA-F]{4}-)"
                + "{3}[0-9a-fA-F]{12}[}]?$";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
