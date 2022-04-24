import { OptionDto } from "./optionDto.model";

export class RowTemplateDto {
  // Technisch
  id?: bigint;

  // Fachliche
  index!: number;
  dataType!: string;
  name!: string;
  example!: string;
  option: OptionDto[] = [];
  input!: string;
  regex!: string;

  constructor(dataType: string,
              name: string,
              example: string,
              option: OptionDto[],
              input: string,
              regex: string) {
    this.dataType = dataType;
    this.name = name;
        this.example = example;
        this.option = option;
        this.input = input;
        this.regex = regex;
    }

    public static createEmptyRowTemplateDto(): RowTemplateDto {
      return new RowTemplateDto('', '', '', [], '', '');
    }
}
