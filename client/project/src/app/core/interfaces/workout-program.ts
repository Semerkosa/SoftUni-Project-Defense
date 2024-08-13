export interface IWorkoutProgram {
    id: string;

    name: string;
    price: number; // one-time purchase
    description: string;
    details: string;
    
    reviews: string[];
    customers: string[]; // userIds
}