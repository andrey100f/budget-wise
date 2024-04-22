// react-reoater-dom functions
import { Outlet, useLoaderData } from "react-router-dom";

// assets
import wave from "../assets/wave.svg";

// Components
import Nav from "../components/Nav";

// helper functions
import { fetchData } from "../helpers";

// loader
export function mainLoader() {
    const username = fetchData("username");
    return { username };

}

function Main() {
    const { username } = useLoaderData();

    return (
        <div className="layout">
            <Nav username={username} />
            <main>
                <Outlet />
            </main>
            <img src={wave} alt="" />

        </div>
    );
}

export default Main;