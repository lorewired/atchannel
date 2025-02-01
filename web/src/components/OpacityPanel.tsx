import { useEffect, useState } from "react"

const OpacityPanel = ({ children }: React.PropsWithChildren) => {
    const [op, setOp] = useState("0");

    useEffect(() => {
        setInterval(() => setOp("1"), 10);
    });

    return (
        <div
            style={{transition: "all .3s", opacity: op}}
            className="w-screen"
        >
            { children }
        </div>
    )
}

export default OpacityPanel