export const wait = () => new Promise(res => setTimeout(res, Math.random() * 2000));

// colors
const generateRandomColor = () => {
    const existingBudgetLength = fetchData("budgets")?.length ?? 0;
    return `${existingBudgetLength * 34} 65% 50%`;
}


// Local storage
export const fetchData = (key: string) => {
    return JSON.parse(localStorage.getItem(key));
}

// Get all items from local storage
export const getAllMatchingItems = ({category, key, value}) => {
    const data = fetchData(category) ?? [];

    return data.filter(item => item[key] === value);
}

export const deleteItem = ({key, id}) => {
    const existingData = fetchData(key);
    
    if(id) {
        const newData = existingData.filter(item => item.id !== id);
        return localStorage.setItem(key, JSON.stringify(newData));
    }

    return localStorage.removeItem(key);
}

// create budget
export const createBudget = ({name, ammount}) => {
    const newItem = {
        id: crypto.randomUUID(),
        name: name,
        createdAt: Date.now(),
        ammount: Number(ammount),
        color: generateRandomColor()
    }

    const existingBudgets = fetchData("budgets") ?? [];

    return localStorage.setItem("budgets", JSON.stringify([...existingBudgets, newItem]));
}

// create expense
export const createExpense = ({name, ammount, budgetId}) => {
    const newItem = {
        id: crypto.randomUUID(),
        name: name,
        createdAt: Date.now(),
        ammount: Number(ammount),
        budgetId: budgetId
    }

    const existingExpenses = fetchData("expenses") ?? [];

    return localStorage.setItem("expenses", JSON.stringify([...existingExpenses, newItem]));
}

// total spent by budget
export const calculateSpentByBudget = (budgetId) => {
    const expenses = fetchData("expenses") ?? [];
    
    const budgetSpent = expenses.reduce((acc, expenese) => {
        // check if the expense.id === budgetId I passed in
        if(expenese.budgetId !== budgetId) {
            return acc;
        }

        // add the current amount to my total
        return acc + expenese.ammount;
    }, 0);

    return budgetSpent;
}

// Formating
export const formatDateToLocalString = (epoch) => {
   return new Date(epoch).toLocaleDateString();
}

// Formating procentages
export const formatPercentage = (amount) => {
    return amount.toLocaleString(undefined, {
        style: "percent",
        minimumFractionDigits: 0,
    });
}

// Format currency
export const formatCurrency = (ammount) => {
    return ammount.toLocaleString(undefined, {
        style: "currency",
        currency: "USD"
    });
} 