import { useContext, useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Title } from "./Title";
import { ThreadsContext } from "../contexts/ThreadsContext";

const MainContainer = () => {
  const [op, setOp] = useState("opacity-0");

  const { threads } = useContext(ThreadsContext);
  
  useEffect(() => {
    setTimeout(() => setOp("opacity-1"), 1000);
  }, [threads]);

  return (
    <div className={`w-screen h-screen flex flex-col items-center transition duration-500 ${op}`}>
      <Title />
      <Outlet />
    </div>
  )
}

export default MainContainer