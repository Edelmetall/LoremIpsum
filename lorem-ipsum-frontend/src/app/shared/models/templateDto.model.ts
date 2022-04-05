import { RowTemplateDto } from "./rowTemplateDto.model";

export class TemplateDto {
    id!: bigint;
    name!: string;
    rowTemplateDtoSet: Array<RowTemplateDto> = new Array();
}