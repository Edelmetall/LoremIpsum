export class Response {
    data!: Data[];
}

export class Data {
    attrName!: string;
    attrValue!: string;
}

export class TabLabels {
    public static HTML = "HTML";
    public static CSV = "CSV";
    public static JSON = "JSON";
    public static XML = "XML";
}