import { Routes } from '@angular/router';
import { CustomerListComponent } from './core/customers/customer-list/customer-list.component';
import { AddCustomerComponent } from './core/customers/add-customer/add-customer.component';
import { CustomerDetailComponent } from './core/customers/customer-detail/customer-detail.component';
import { confirmLeaveGuard } from './confirm-leave.guard';

export const routes: Routes = [
    {path: 'customers', component: CustomerListComponent},
    {path: 'add', component: AddCustomerComponent},
    {path: 'customer/:id', component: CustomerDetailComponent},
    {path: 'add', component: AddCustomerComponent, canDeactivate: [confirmLeaveGuard]},
    {path: '', redirectTo: 'customers', pathMatch: 'full'} // Checks for empty URL (pathMatch: 'full' means no regex, full ONLY)
];
