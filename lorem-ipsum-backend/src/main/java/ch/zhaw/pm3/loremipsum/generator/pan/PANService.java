package ch.zhaw.pm3.loremipsum.generator.pan;

import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.common.Middleware;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PANService extends AbstractEntryGenService {

    private final String panFormat = "##################z";

    private static Middleware<String> middleware;

    public PANService() {
        super();
        middleware = new ReplaceNumberMiddleware().linkWith(new LuhnAlgoMiddleware());
    }

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return middleware.handle(panFormat, "");
    }
}
