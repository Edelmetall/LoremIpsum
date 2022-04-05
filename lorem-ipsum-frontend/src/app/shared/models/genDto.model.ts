import { TemplateDto } from "./templateDto.model";

export class GenDto {
    templateDto: TemplateDto = new TemplateDto();

    output!: string;
    outputOption?: string;
}