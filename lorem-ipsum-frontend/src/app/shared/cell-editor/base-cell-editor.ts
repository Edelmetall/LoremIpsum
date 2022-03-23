import { ICellEditorParams } from "ag-grid-community";


export class BaseCellEditor {
    protected params!: ICellEditorParams;

    agInit(params: ICellEditorParams): void {
        this.params = params;
    }

    isPopup(): boolean {
        return true;
    }

    get width(): number {
        return this.params.column.getActualWidth();
    }

}