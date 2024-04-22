// react-router-dom imports
import { Form, NavLink } from "react-router-dom";

// Library
import { TrashIcon } from "@heroicons/react/16/solid";

// assets
import logomark from "../assets/logomark.svg";

function Nav({ username }) {
    return (
        <nav>
            <NavLink to="/" aria-label="Go to home">
                <img src={logomark} alt="" height={30} />
                <span>BudgetWise</span>
            </NavLink>

            {
                username && (
                    <Form method="post" action="logout" onSubmit={(event) => {
                        if(!confirm("Are you sure you want to delete your account?")) {
                            event.preventDefault();
                        }
                    }}>
                        <button type="submit" className="btn btn--warning">
                            <span>Delete User</span>
                            <TrashIcon width={20} />
                        </button>
                    </Form>
                )
            }
        </nav>
    );
}

export default Nav;