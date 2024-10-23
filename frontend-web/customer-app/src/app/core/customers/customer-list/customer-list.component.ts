import {Component, inject, OnInit} from '@angular/core';
import {FilterComponent} from "../filter/filter.component";
import {CustomerItemComponent} from "../customer-item/customer-item.component";
import {Customer} from "../../../shared/models/customer.model";
import {Filter} from "../../../shared/models/filter.model";
import {AddCustomerComponent} from "../add-customer/add-customer.component";
import {CustomerService} from "../../../shared/services/customer.service";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [FilterComponent, CustomerItemComponent, AddCustomerComponent],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})

export class CustomerListComponent implements OnInit {
  customerService: CustomerService = inject(CustomerService);
  customers!: Customer[];
  filteredData!: Customer[];

  ngOnInit(): void {
    this.customers = this.customerService.getCustomers();
    this.customers[1].isLoyal = true;
    this.filteredData = this.customers;
  }

  handleFilter(filter: Filter){
    this.filteredData = this.customerService.filterCustomers(filter);
  }

  processAdd(customer: Customer){
    this.customerService.addCustomer(customer);
    this.filteredData = this.customerService.getCustomers();
  }
}
