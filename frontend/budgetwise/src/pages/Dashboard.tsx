// react-reoater-dom functions
import { Link, useLoaderData } from "react-router-dom";

import Intro from "../components/Intro";
import AddBudgetForm from "../components/AddBudgetForm";
import AddExpenseFrom from "../components/AddExpenseForm";
import BudgetItem from "../components/BudgetItem";
import Table from "../components/Table";

import { DashboardLoaderData } from "../utils/interfaces";

function Dashboard() {
    const { username, budgets, expenses } = useLoaderData() as DashboardLoaderData;

    return (
        <>
            {username ? (
                <div className="dashboard">
                    <h1>Welcome back, <span className="accent">{username}</span></h1>
                    <div className="grid-sm">
                        {
                            budgets && budgets.length > 0 ? (
                            <div className="grid-lg">
                                <div className="flex-lg">
                                    <AddBudgetForm />
                                    <AddExpenseFrom budgets={budgets} />
                                </div>

                                <h2>Existing Budgets</h2>
                                <div className="budgets">
                                    {
                                        budgets.map((budget) => (
                                            <BudgetItem key={budget.id} budget={budget} />
                                        ))
                                    }
                                </div>
                                 {
                                    expenses && expenses.length > 0 && (
                                        <div className="grid-md">
                                            <h2>Recent Expenses</h2>
                                            <Table expenses={expenses.sort((a, b) => Date.parse(b.createdAt) - Date.parse(a.createdAt)).slice(0, 8)} />

                                            {expenses.length > 8 && (
                                                <Link to="/expenses" className="btn btn--dark">View all expenses</Link>
                                            )}
                                        </div>
                                    )
                                }
                            </div>)
                            : (
                                <div className="grid-sm">
                                    <p>Personal budgeting is the secret to financial freedom.</p>
                                    <p>Create a budget to get started!!</p>
                                    <AddBudgetForm />
                                </div>
                            )
                        }
                    </div>
                </div>
            ): <Intro />
            }
        </>
    );
}

export default Dashboard;
