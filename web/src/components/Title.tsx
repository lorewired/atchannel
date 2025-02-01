import { Link } from "react-router-dom";

export const Title = () => {
    return <Link
        title="channel"
        to="/"
        className="flex [&>h1]:text-6xl font-bold my-10"
    >
        <h1>@</h1>
        <h1
            className="message_font text-6xl font-bold"
        >
            channel
        </h1>
    </Link>
}

// チャネル