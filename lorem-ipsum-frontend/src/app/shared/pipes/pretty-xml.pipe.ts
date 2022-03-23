import { Pipe, PipeTransform } from '@angular/core';

declare var require: any;

@Pipe({ name: 'xml' })
export class PrettyXmlPipe implements PipeTransform {
    format = require('xml-formatter')
    transform(value: string): string {
        return this.format(value);
    }
}