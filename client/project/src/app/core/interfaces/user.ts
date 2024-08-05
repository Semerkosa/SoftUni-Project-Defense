import { IBase } from "./base";
import { ICoach } from "./coach";
import { IWorkoutProgram } from "./workout-program";

export interface IUser extends IBase {
    email: string;
    password: string;
    purchasedWorkoutPrograms: IWorkoutProgram[];
    coach: ICoach;
    isAdmin: boolean; // An admin should be able to add, edit and delete workout programs and coaches
}