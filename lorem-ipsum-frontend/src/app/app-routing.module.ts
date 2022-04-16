import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataOutputComponent } from './data-output/data-output.component';
import { GridComponent } from './grid/grid.component';
import { LoginComponent } from './login/login.component';
import { TemplateListComponent } from './template-list/template-list.component';
import { SignUpComponent } from "./sign-up/sign-up.component";
import { AuthGuardService } from "./auth/auth-guard.service";

const routes: Routes = [
  { path: '', component: GridComponent },
  { path: 'template/:templateId', component: GridComponent },
  { path: 'templates', component: TemplateListComponent, canActivate: [AuthGuardService] },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
