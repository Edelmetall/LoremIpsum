import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {GenDto} from "../models/genDto.model";

@Injectable()
export class GenerateService {

  constructor(private httpClient: HttpClient) {
  }

  generateTemplate(genDto: GenDto): Observable<string> {
    return this.httpClient.post("/api/template/generate", genDto, {responseType: "text"});
  }
}
