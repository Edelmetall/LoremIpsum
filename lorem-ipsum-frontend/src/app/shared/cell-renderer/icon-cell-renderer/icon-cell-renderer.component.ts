import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';

@Component({
  selector: 'app-icon-cell-renderer',
  templateUrl: './icon-cell-renderer.component.html',
  styleUrls: ['./icon-cell-renderer.component.scss']
})
export class IconCellRendererComponent implements ICellRendererAngularComp {
  private params!: ICellRendererParams;
  private id!: string;
  iconName!: string;

  refresh(params: ICellRendererParams): boolean {
    return true;
  }

  agInit(params: ICellRendererParams): void {
    this.params = params;
    this.id = String(params.node.id);
  }

  remove() {
    var rowNode = this.params.api.getRowNode(this.id)!;
    this.params.api.applyTransaction({ remove: [rowNode.data] });
  }
}
