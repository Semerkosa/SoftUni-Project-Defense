export interface IWorkoutProgram {
    id: string;
    name: string;
    price: number; // one-time purchase
    description: string;
    reviews: string[];
    details: string;
    customers: string[]; // userIds
}