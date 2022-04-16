import { RowTemplateDto } from "./rowTemplateDto.model";
import { TemplateDto } from "./templateDto.model";

export class GenDto {
    templateDto: TemplateDto = new TemplateDto();
    output: string;
    outputOption?: string;

    constructor(output: string, rowTemplateDtoSet: RowTemplateDto[], outputOption?: string) {
        this.output = output;
        this.templateDto.rowTemplateDtoSet = rowTemplateDtoSet;
        this.outputOption = outputOption;
    }
}