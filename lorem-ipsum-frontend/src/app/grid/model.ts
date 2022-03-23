export class RowModel {
    dataType: string;
    name: string;
    example: string;
    option: string;
    input: string;
    regex: string;

    constructor(dataType: string,
        name: string,
        example: string,
        option: string,
        input: string,
        regex: string) {
        this.dataType = dataType;
        this.name = name;
        this.example = example;
        this.option = option;
        this.input = input;
        this.regex = regex;
    }

    public static createEmptyRow(): RowModel {
        return new RowModel('', '', '', '', '', '');
    }
}