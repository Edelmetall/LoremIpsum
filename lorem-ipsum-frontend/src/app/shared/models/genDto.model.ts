import {RowTemplateDto} from "./rowTemplateDto.model";
import {TemplateDto} from "./templateDto.model";
import {OptionDto} from "./optionDto.model";

export class GenDto {
  templateDto: TemplateDto = new TemplateDto();
  outputName: string;
  outputOption?: OptionDto;

  constructor(outputName: string, rowTemplateDtoSet: RowTemplateDto[], outputOption?: OptionDto) {
    this.outputName = outputName;
    this.templateDto.rowTemplateDtoSet = rowTemplateDtoSet;
    this.outputOption = outputOption;
  }
}
