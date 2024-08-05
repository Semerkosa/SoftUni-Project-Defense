export interface IWorkoutProgram {
    id: number;
    name: string;
    price: number; // one-time purchase
    description: string;
    reviews: string[];
    details: string;
    customers: number[]; // userIds
}