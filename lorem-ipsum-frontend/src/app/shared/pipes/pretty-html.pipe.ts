import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'html' })
export class PrettyHtmlPipe implements PipeTransform {
    transform(value: string): string {
        return value;
    }
}