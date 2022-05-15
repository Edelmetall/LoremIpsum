package ch.zhaw.pm3.loremipsum.generator.iban.middleware;

import ch.zhaw.pm3.loremipsum.generator.common.StringSolverUtil;
import ch.zhaw.pm3.loremipsum.generator.iban.IBANWrapper;

public class ReplaceAccountNumberMiddleware extends Middleware<IBANWrapper> {

    @Override
    public String handle(String field, IBANWrapper businessWrapper) {
        return handleNext(StringSolverUtil.resolve(field), businessWrapper);
    }
}
