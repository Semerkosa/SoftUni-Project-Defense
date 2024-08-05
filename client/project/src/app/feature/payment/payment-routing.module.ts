import { RouterModule, Routes } from '@angular/router';
import { CardDetailsComponent } from './card-details/card-details.component';

const routes: Routes = [
	{
		path: 'card-details',
		component: CardDetailsComponent
	},
];

export const PaymentRoutingModule = RouterModule.forChild(routes);
