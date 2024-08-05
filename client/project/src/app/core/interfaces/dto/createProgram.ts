export interface ICreateProgram {
    name: string;
    price: number;
    description: string;
    details: string;
    reviews: string[] | [];
    customers: number[] | [];
}