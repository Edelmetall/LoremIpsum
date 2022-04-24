import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { GenDto } from "../models/genDto.model";
import { TemplateDto } from "../models/templateDto.model";

@Injectable()
export class TemplateService {

  constructor(private httpClient: HttpClient) {
  }

  getAllTemplates(): Observable<TemplateDto[]> {
    return this.httpClient.get<TemplateDto[]>(`/api/template`);
  }

  getTemplateById(id: number): Observable<TemplateDto> {
    return this.httpClient.get<TemplateDto>(`/api/template/byId?id=${id}`);
  }

  getAvailableDataFormats(): Observable<any> {
    return this.httpClient.get("api/template/availableDataFormats");
  }

  generateTemplate(genDto: GenDto): Observable<string> {
    return this.httpClient.post("/api/template/generate", genDto, { responseType: "text" });
  }

  saveTemplate(templateDto: TemplateDto): Observable<TemplateDto> {
    return this.httpClient.post<TemplateDto>("/api/template/save", templateDto);
  }

  deleteTemplate(tempalteId: bigint): Observable<boolean> {
    return this.httpClient.post<boolean>("/api/template/delete", tempalteId);
  }

}
