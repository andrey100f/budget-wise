import React, { useEffect } from 'react';
import { NavLink, useFetcher, useLoaderData } from 'react-router-dom';
import { ArrowUturnLeftIcon, UserPlusIcon } from '@heroicons/react/24/solid';

import { RegisterLoaderData } from '../utils/interfaces';

function Register() {
    const {username} = useLoaderData() as RegisterLoaderData;
    const fetcher = useFetcher();
    const formRef = React.useRef() as React.MutableRefObject<HTMLFormElement>;
    const isSubmitting = fetcher.state === "submitting";

    useEffect(() => {
        if(!isSubmitting) {
            formRef.current.reset();
        }
    }, [isSubmitting]);


    return (
        <div className="intro">
            { !username ? (
                <div>
                    <h1>Take control of <span className="accent">Your Money</span></h1>
                    <p>Personal budgeting is the secret to financial freedom. Start your journey today.</p>
    
                    <fetcher.Form method="post" ref={formRef}> 
                        <input type="text" name="username" required placeholder="What is your name?" aria-label="Your name" autoComplete="given-name" />
                        <input type="password" name="password" required placeholder="What is your password?" aria-label="Your password" />
                        <input type="hidden" name="_action" value="registerUser" />
                        <div className="login-inputs">
                            <button type="submit" className="btn btn--dark">
                                <span>Register</span>
                                <UserPlusIcon width={20} />
                            </button>
    
                            <NavLink to="/" className="btn btn--dark">
                                <span>Go Back</span>
                                <ArrowUturnLeftIcon width={20} />
                            </NavLink>
                        </div>
                    </fetcher.Form>
                </div>
            ) : (
                <div>
                    <h1>User Setings</h1>
                    <p>Edit your account details</p>

                    <fetcher.Form method="post" ref={formRef}> 
                        <input type="text" name="username" required placeholder="What is your name?" aria-label="Your name" autoComplete="given-name" />
                        <input type="password" name="password" required placeholder="What is your password?" aria-label="Your password" />
                        <input type="hidden" name="_action" value="editUser" />
                        <div className="login-inputs">
                            <button type="submit" className="btn btn--dark">
                                <span>Edit</span>
                                <UserPlusIcon width={20} />
                            </button>
    
                            <NavLink to="/" className="btn btn--dark">
                                <span>Go Back</span>
                                <ArrowUturnLeftIcon width={20} />
                            </NavLink>
                        </div>
                    </fetcher.Form>
                </div>
            )
            }

        </div>
    );
}

export default Register;
