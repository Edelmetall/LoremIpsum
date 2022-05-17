package ch.zhaw.pm3.loremipsum.generator.pan;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import ch.zhaw.pm3.loremipsum.generator.iban.IBANService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PanServiceTest extends AbstractSpringBootTest {



    @Autowired
    private PANService panService;

    @Test
    public void testPan() {
        String panNr = panService.getData(null, null);
        Assertions.assertTrue(checkLuhn(panNr));
    }

    private boolean checkLuhn(String ccNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

}
