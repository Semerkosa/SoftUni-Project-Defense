export interface ICreateUserDto {
    email: string;
    firstName: string;
    password: string;
    lastName?: string;
}