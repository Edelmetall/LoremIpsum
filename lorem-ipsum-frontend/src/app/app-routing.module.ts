import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataOutputComponent } from './data-output/data-output.component';
import { GridComponent } from './grid/grid.component';
import { LoginComponent } from './login/login.component';
import { TemplateListComponent } from './template-list/template-list.component';

const routes: Routes = [
  { path: '', component: GridComponent },
  { path: 'output', component: DataOutputComponent },
  { path: 'templates', component: TemplateListComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
