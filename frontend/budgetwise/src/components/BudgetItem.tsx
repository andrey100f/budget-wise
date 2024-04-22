// helper functions
import { Form, Link } from "react-router-dom";
import { calculateSpentByBudget, formatCurrency, formatPercentage } from "../helpers";
import { BanknotesIcon, TrashIcon } from "@heroicons/react/24/solid";

function BudgetItem({ budget, showDelete = false }) {
    const { id, name, ammount, color } = budget;
    const spent = calculateSpentByBudget(id);

    return (
        <div className="budget" style={{"--accent": color}}>
            <div className="progress-text">
                <h3>{name}</h3>
                <p>{formatCurrency(ammount)} Budgeted</p>
            </div>

            <progress max={ammount} value={spent}>
                {formatPercentage(spent / ammount)}
            </progress>

            <div className="progress-text">
                <small>{formatCurrency(spent)} spent</small>
                <small>{formatCurrency(ammount - spent)} remaining</small>
            </div>

            {showDelete ? (
                <div className="flex-sm">
                    <Form method="post" action="delete" onSubmit={(event) => {
                        if(!confirm("Are you sure you want to delete this budget?")) {
                            event.preventDefault();
                        }
                    }}>
                        <button type="submit" className="btn">
                            <span>Delete Budget</span>
                            <TrashIcon width={20} />
                        </button>
                    </Form>
                </div>
            ) : (
                <div className="flex-sm">
                    <Link to={`/budget/${id}`} className="btn">
                        <span>View details</span>
                        <BanknotesIcon width={20} />
                    </Link>
                </div>
            )}
        </div>
    );
}

export default BudgetItem;