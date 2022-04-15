import { RowTemplateDto } from "./rowTemplateDto.model";

export class TemplateDto {
    id!: bigint;
    name!: string;
    rowTemplateDtoSet: RowTemplateDto[] = new Array();
}