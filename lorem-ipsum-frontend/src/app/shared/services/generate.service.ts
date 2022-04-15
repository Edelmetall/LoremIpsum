import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { GenDto } from "../models/genDto.model";
import { TemplateDto } from "../models/templateDto.model";
import { _ } from "ag-grid-community";

@Injectable()
export class GenerateService {

  constructor(private httpClient: HttpClient) {
  }

  getAllTemplates(): Observable<TemplateDto[]> {
    return this.httpClient.get<TemplateDto[]>(`/api/template`);
  }

  getTemplateById(id: number): Observable<TemplateDto> {
    return this.httpClient.get<TemplateDto>(`/api/template/byId?id=${id}`);
  }

  getAvailableDataFormats(): Observable<any> {
    return this.httpClient.get("api/template/availableFormats");
  }

  generateTemplate(genDto: GenDto): Observable<string> {
    return this.httpClient.post("/api/template/generate", genDto, { responseType: "text" });
  }

  saveTemplate(templateDto: TemplateDto): Observable<TemplateDto> {
    return this.httpClient.post<TemplateDto>("/api/template/save", templateDto);
  }
}
