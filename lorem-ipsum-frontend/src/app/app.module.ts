import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ClipboardModule } from '@angular/cdk/clipboard';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon'
import { AgGridModule } from 'ag-grid-angular';
import { GridComponent } from './grid/grid.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatChipsCellRendererComponent } from './shared/cell-renderer/mat-chips-cell-renderer/mat-chips-cell-renderer.component';
import { MatChipsCellEditorComponent } from './shared/cell-editor/mat-chips-cell-editor/mat-chips-cell-editor.component';
import { AutocompleteCellEditorComponent } from './shared/cell-editor/autocomplete-cell-editor/autocomplete-cell-editor.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MatBadgeModule } from '@angular/material/badge';
import { IconCellRendererComponent } from './shared/cell-renderer/icon-cell-renderer/icon-cell-renderer.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DataOutputComponent } from './data-output/data-output.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSelectModule } from '@angular/material/select';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'
import { HttpClientModule } from '@angular/common/http';
import { TemplateListComponent } from './template-list/template-list.component';
import { LoginComponent } from './login/login.component';
import { TemplateService } from './shared/services/template.service';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NotificationService } from './shared/services/notification.service';
import { ConfirmDialogComponent } from './shared/dialogs/confirm-dialog/confirm-dialog.component';
import { CustomerService } from './shared/services/customer.service';
import { CommunicationService } from './shared/services/communication.service';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { MultiSelectCellEditorComponent } from './shared/cell-editor/multi-select-cell-editor/multi-select-cell-editor.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    AppComponent,
    GridComponent,
    MatChipsCellRendererComponent,
    MatChipsCellEditorComponent,
    AutocompleteCellEditorComponent,
    NavbarComponent,
    IconCellRendererComponent,
    DataOutputComponent,
    TemplateListComponent,
    LoginComponent,
    SignUpComponent,
    ConfirmDialogComponent,
    ProfileComponent,
    ConfirmDialogComponent,
    MultiSelectCellEditorComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatGridListModule,
    MatButtonToggleModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    AgGridModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
    MatCardModule,
    MatChipsModule,
    MatToolbarModule,
    MatBadgeModule,
    MatSidenavModule,
    ClipboardModule,
    MatTooltipModule,
    MatTabsModule,
    MatExpansionModule,
    HttpClientModule,
    MatSelectModule,
    MatListModule,
    MatTableModule,
    MatSortModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    ToastrModule.forRoot()
  ],
  providers: [TemplateService, NotificationService, CustomerService, CommunicationService, ToastrService],
  bootstrap: [AppComponent]
})
export class AppModule { }
