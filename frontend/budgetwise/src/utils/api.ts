import axios from "axios";

const budgetsUrl = "http://localhost:8222/api/budgets";
const expensesUrl = "http://localhost:8222/api/expenses";
const usersUrl = "http://localhost:8222/api/users";

const config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

export const getAllBudgets = async () => {
    try {
        const res = await axios.get(budgetsUrl);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const getBudgetById = async (id: string) => {
    try {
        const res = await axios.get(`${budgetsUrl}/${id}`);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const getAllExpenses = async () => {
    try {
        const res = await axios.get(`${expensesUrl}`);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const deleteExpense = async (id : string) => {
    try {
        const res = await axios.delete(`${expensesUrl}/${id}`);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const createBudget = async ({name, amount} : {name: string, amount: number}) => {
    try {
        const res = await axios.post(budgetsUrl, {name, amount}, config);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const createExpense = async ({name, amount, budgetId} : {name: string, amount: number, budgetId: string}) => {
    try {
        const res = await axios.post(expensesUrl, {name, amount: amount, budgetId}, config);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const createUser = async({username, password} : {username: string, password: string}) => {
    try {
        const res = await axios.post(`${usersUrl}`, {username, password}, config);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}

export const deleteBudget = async(id : string) => {
    try {
        const res = await axios.delete(`${budgetsUrl}/${id}`);
        return Promise.resolve(res.data);
    }
    catch(err) {
        return Promise.reject(err);
    }
}
