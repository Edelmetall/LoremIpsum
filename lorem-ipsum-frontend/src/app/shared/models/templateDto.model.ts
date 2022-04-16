import { RowTemplateDto } from "./rowTemplateDto.model";

export class TemplateDto {
    id?: bigint;
    name!: string;
    ownerId!: bigint;
    rowTemplateDtoSet: RowTemplateDto[] = new Array();
}