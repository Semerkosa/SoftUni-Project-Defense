import { ICoach } from "../coach";
import { IWorkoutProgram } from "../workout-program";

export interface ICreateUserDto {
    email: string;
    firstName: string;
    password: string;
    lastName?: string;
    purchasedWorkoutPrograms: IWorkoutProgram[] | [];
    coach: ICoach | {};
    isAdmin: boolean;
}