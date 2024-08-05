import { RouterModule, Routes } from '@angular/router';
import { CoachListComponent } from './coach-list/coach-list.component';
import { CoachReviewPageComponent } from './coach-review-page/coach-review-page.component';

const routes: Routes = [
	{
		path: 'coaches',
		component: CoachListComponent
	},
	{
		path: 'coaches/:coachId',
		component: CoachReviewPageComponent
	}
];

export const CoachesRoutingModule = RouterModule.forChild(routes);
