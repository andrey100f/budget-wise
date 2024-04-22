import { useLoaderData } from "react-router-dom";
import { createExpense, deleteItem, getAllMatchingItems } from "../helpers";
import BudgetItem from "./BudgetItem";
import AddExpenseForm from "./AddExpenseForm";
import Table from "./Table";
import { toast } from "react-toastify";

export async function budgetLoader({ params }) {
    const budget = await getAllMatchingItems({
        category: "budgets",
        key: "id",
        value: params.id
    })[0];

    const expenses = await getAllMatchingItems({
        category: "expenses",
        key: "budgetId",
        value: params.id
    });

    if(!budget) {
        throw new Error("The budget you're triying to find does not exist...");
    
    }

    return { budget, expenses }
}

export async function budgetAction({ request }) {
    const data = await request.formData();
    const {_action, ...values} = Object.fromEntries(data);

    if(_action === "createExpense") {
        try {
            // create expense
            createExpense({
                name: values.newExpense, 
                ammount: values.newExpenseAmount, 
                budgetId: values.newExpenseBudget
            });

            return toast.success(`Expense ${values.newExpense} created!`);
        }
        catch(error) {
            throw new Error("There was a problem creating your expense..."); 
        }
    }

    if(_action === "deleteExpense") {
        try {
            deleteItem({key: "expenses", id: values.expenseId});
            return toast.success(`Expense deleted!`);
        }
        catch(error) {
            throw new Error("There was a problem deleting your expense..."); 
        }
    }
}

function BudgetPage() {
    const { budget, expenses } = useLoaderData();

    return (
        <div className="grid-lg" style={{"--accent": budget.color}}>
            <h1 className="h2">
                <span className="accent">{budget.name}</span> Overview
            </h1>

            <div className="flex-lg">
                <BudgetItem budget={budget} showDelete={true} />
                <AddExpenseForm budgets={[budget]} />
            </div>

            {
                expenses && expenses.length > 0 && (
                    <div className="grid-md">
                        <h2>
                            <span className="accent">{budget.name}</span> Expenses
                        </h2>
                        <Table expenses={expenses} showBudget={false} />
                    </div>
                )
            }
        </div>
    );
}

export default BudgetPage;