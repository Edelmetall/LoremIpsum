package ch.zhaw.pm3.loremipsum.generator.iban;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.generator.iban.middleware.FormatIBANMiddleware;
import org.junit.jupiter.api.Test;

public class FormatIBANMiddlewareTest {

    FormatIBANMiddleware ibanMiddleware = new FormatIBANMiddleware();


    @Test
    public void test(){
        String bla =  "CHppbbbbb############";
        ibanMiddleware.handle(bla, new IBANWrapper("CHppbbbbb############", "00700", LandEnum.SWITZERLAND));
    }


}
