import { ColDef } from "ag-grid-community";
import * as _ from "lodash";
import { AutocompleteCellEditorComponent } from "../shared/cell-editor/autocomplete-cell-editor/autocomplete-cell-editor.component";
import { MultiSelectCellEditorComponent } from "../shared/cell-editor/multi-select-cell-editor/multi-select-cell-editor.component";
import { IconCellRendererComponent } from "../shared/cell-renderer/icon-cell-renderer/icon-cell-renderer.component";
import { MatChipsCellRendererComponent } from "../shared/cell-renderer/mat-chips-cell-renderer/mat-chips-cell-renderer.component";

export class RowModel {
    dataType: string;
    name: string;
    example: string;
    option: string;
    input: string;
    regex: string;

    constructor(dataType: string,
        name: string,
        example: string,
        option: string,
        input: string,
        regex: string) {
        this.dataType = dataType;
        this.name = name;
        this.example = example;
        this.option = option;
        this.input = input;
        this.regex = regex;
    }

    public static createEmptyRow(): RowModel {
        return new RowModel('', '', '', '', '', '');
    }
}

export class GridConfig {
    static dataTypeColDef: ColDef = {
        field: 'dataType', headerName: 'Datatype',
        cellEditor: AutocompleteCellEditorComponent, cellEditorParams: { options: [] }
    };

    static optionColDef: ColDef = {
        field: 'option',
        headerName: 'Option',
        cellRenderer: MatChipsCellRendererComponent,
        cellEditor: MultiSelectCellEditorComponent,
        editable: this.optionCellEditable,
        cellEditorParams: { options: [] }
    };

    static optionCellEditable(params: any): boolean {
        const dataType = params.node.data.dataType;
        return !_.isNil(dataType) && !_.isEmpty(dataType);
    }

    static columnDefs: ColDef[] = [
        { field: '', sortable: false, rowDrag: true, editable: false, maxWidth: 42 },
        this.dataTypeColDef,
        { field: 'name', headerName: 'Name' },
        this.optionColDef,
        {
            field: '',
            headerName: '',
            cellRenderer: IconCellRendererComponent,
            editable: false,
            cellStyle: { 'padding': '0' },
            sortable: false,
            maxWidth: 42,
            cellRendererParams: { iconName: 'delete' }
        },
    ];

    static defaultColDef: ColDef = {
        lockPosition: true,
        editable: true,
        resizable: true,
        sortable: true,
        autoHeight: true
    };
}
