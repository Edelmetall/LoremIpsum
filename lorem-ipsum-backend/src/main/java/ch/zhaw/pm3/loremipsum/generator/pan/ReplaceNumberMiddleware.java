package ch.zhaw.pm3.loremipsum.generator.pan;

import ch.zhaw.pm3.loremipsum.generator.common.Middleware;
import ch.zhaw.pm3.loremipsum.generator.common.StringSolverUtil;

public class ReplaceNumberMiddleware extends Middleware<String> {
    @Override
    public String handle(String field, String businessWrapper) {
        return handleNext(StringSolverUtil.resolve(field), businessWrapper);
    }
}
