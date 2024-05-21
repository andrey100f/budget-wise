import { toast } from 'react-toastify';
import { createUser } from '../utils/api';
import { createExpense, deleteExpense, createBudget } from '../utils/api';
import { budgetLoader, dashboardLoader, expensesLoader } from '../utils/loaders';

export async function registerAction({ request } : { request: Request }) {
    const data = await request.formData();
    const {_action, ...values} = Object.fromEntries(data);

    if(_action === "registerUser") {
        try {
            const user = {
                username: values.username as string,
                password: values.password as string
            };
            await createUser(user);

            return toast.success(`Welcome ${values.username}!`);
        }
        catch (error) {
            throw new Error("There was a problem creating your account...");
        }
    }
}

export async function budgetAction({ request } : { request: Request }) {
    const data = await request.formData();
    const {_action, ...values} = Object.fromEntries(data);

    if(_action === "createExpense") {
        try {
            const expense = {
                name: values.newExpense as string,
                amount: Number.parseFloat(values.newExpenseAmount as string),
                budgetId: values.newExpenseBudget as string
            };

            createExpense(expense);
            budgetLoader({params: {id: values.newExpenseBudget}});

            return toast.success(`Expense ${values.newExpense} created!`);
        }
        catch(error) {
            throw new Error("There was a problem creating your expense..."); 
        }
    }

    if(_action === "deleteExpense") {
        try {
            await deleteExpense(values.expenseId as string);
            budgetLoader({params: {id: values.budgetId}});

            return toast.success(`Expense deleted!`);
        }
        catch(error) {
            throw new Error("There was a problem deleting your expense..."); 
        }
    }
}

export async function dashboardAction({ request } : { request: Request }) {
    const data = await request.formData();
    const {_action, ...values} = Object.fromEntries(data);

    if(_action === "newUser") {
        try {
            localStorage.setItem("username", JSON.stringify(values.username));
            return toast.success(`Welcome ${values.username}!`);
        }
        catch (error) {
            throw new Error("There was a problem creating your account...");
        }
    }

    if(_action === "createBudget") {
        try {
            const budget = {
                name: values.newBudget as string,
                amount: Number.parseFloat(values.newBudgetAmount as string)
            }

            await createBudget(budget);
            dashboardLoader();

            return toast.success(`Budget created!`);

        }
        catch(error) {
            throw new Error("There was a problem creating your budget..."); 
        }
    }

    if(_action === "createExpense") {
        try {
            const expense = {
                name: values.newExpense as string,
                amount: Number.parseFloat(values.newExpenseAmount as string),
                budgetId: values.newExpenseBudget as string
            }

            await createExpense(expense);
            dashboardLoader();

            return toast.success(`Expense ${values.newExpense} created!`);
        }
        catch(error) {
            throw new Error("There was a problem creating your expense..."); 
        }
    }

    if(_action === "deleteExpense") {
        try {
            await deleteExpense(values.expenseId as string);
            dashboardLoader();

            return toast.success(`Expense deleted!`, {theme: "colored"});
        }
        catch(error) {
            throw new Error("There was a problem deleting your expense..."); 
        }
    }
}

export async function expensesAction({ request } : { request: Request }) {
    const data = await request.formData();
    const {_action, ...values} = Object.fromEntries(data);

    if(_action === "deleteExpense") {
        try {
            await deleteExpense(values.expenseId as string);
            expensesLoader();

            return toast.success(`Expense deleted!`);
        }
        catch(error) {
            throw new Error("There was a problem deleting your expense..."); 
        }
    }
}
