import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { AuthModule } from './auth/auth.module';
import { RouterModule } from '@angular/router';
import { PagesModule } from './feature/pages/pages.module';
import { CoachesModule } from './feature/coaches/coaches.module';
import { WorkoutProgramsModule } from './feature/workout-programs/workout-programs.module';
import { PaymentModule } from './feature/payment/payment.module';

@NgModule({
	declarations: [
		AppComponent,
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		CoreModule.forRoot(),
		AuthModule,
		RouterModule,
		AppRoutingModule,
		PagesModule,
		CoachesModule,
		WorkoutProgramsModule,
		PaymentModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
