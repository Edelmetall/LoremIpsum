export class Response {
  data!: Data[];
}

export class Data {
  attrName!: string;
  attrValue!: string;
}

export class OutputEnum {
  public static XML = {name: 'XML', displayedText: 'XML'};
  public static JSON = {name: 'JSON', displayedText: 'JSON'};
  public static JAVA = {name: 'JAVA', displayedText: 'Java'};
  public static SQL = {name: 'SQL', displayedText: 'SQL'};
}
