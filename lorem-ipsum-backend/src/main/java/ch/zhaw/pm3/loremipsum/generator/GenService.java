package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import org.springframework.stereotype.Service;

@Service
public class GenService {


    public String generateStuff(GenDto genDto) {
        return "Hello World" + genDto.getOutput();
    }


}
