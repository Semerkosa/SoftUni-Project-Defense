import { IBase } from "./base";

export interface ICoach extends IBase {
    description: string;
    price: number; // monthly price
    reviews: string[];
    clients: number[]; // userIds
}