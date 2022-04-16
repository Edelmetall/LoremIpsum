import {RowTemplateDto} from "./rowTemplateDto.model";
import {TemplateDto} from "./templateDto.model";

export class GenDto {
  templateDto: TemplateDto = new TemplateDto();
  outputName: string;
  outputOption?: string;

  constructor(outputName: string, rowTemplateDtoSet: RowTemplateDto[], outputOption?: string) {
    this.outputName = outputName;
    this.templateDto.rowTemplateDtoSet = rowTemplateDtoSet;
    this.outputOption = outputOption;
  }
}
