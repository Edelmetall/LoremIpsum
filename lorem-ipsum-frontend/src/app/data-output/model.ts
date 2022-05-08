export class Response {
  data!: Data[];
}

export class Data {
  attrName!: string;
  attrValue!: string;
}

// TODO: considering adding a third attribute for file extension (file extension and displayedText are not the same for C# or Typescript)
export class OutputEnum {
  public static XML = {name: 'XML', displayedText: 'XML'};
  public static JSON = {name: 'JSON', displayedText: 'JSON'};
  public static JAVA = {name: 'JAVA', displayedText: 'Java'};
  public static SQL = {name: 'SQL', displayedText: 'SQL'};
  public static PHP = {name: 'PHP', displayedText: 'PHP'};
  public static CSHARP = {name: 'CSHARP', displayedText: 'C#'};
  public static CSV = {name: 'CSV', displayedText: 'CSV'};
}
