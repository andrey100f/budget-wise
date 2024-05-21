import { LoaderFunctionArgs } from "react-router-dom";

import { fetchData, setColors } from "./helpers";
import { getBudgetById, getAllBudgets, getAllExpenses } from "./api";
import { BudgetLoaderParams } from "./interfaces";

export function editLoader() {
    const username = fetchData("username");
    return { username };
}

export async function budgetLoader({ params } : LoaderFunctionArgs | { params: BudgetLoaderParams }) {
    const budget = await getBudgetById(params.id as string);
    const expenses = budget.expenses;

    if(!budget) {
        throw new Error("The budget you're triying to find does not exist...");
    }

    return { budget, expenses }
}

export async function dashboardLoader() {
    const username = fetchData("username");
    const budgets = await getAllBudgets();
    setColors(budgets);
    const expenses = await getAllExpenses();

    return { username, budgets, expenses };
}

export async function expensesLoader() {
    const expenses = await getAllExpenses();
    return { expenses };
}

export function mainLoader() {
    const username = fetchData("username");
    return { username };

}
