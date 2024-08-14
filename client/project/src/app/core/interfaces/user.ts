import { ICoach } from "./coach";
import { IWorkoutProgram } from "./workout-program";

export interface IUser {
    id: string;
    workoutPrograms: IWorkoutProgram[];
    coach: ICoach;
}