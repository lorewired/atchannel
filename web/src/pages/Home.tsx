import { useContext } from "react";
import { Link } from "react-router-dom";
import ThreadItem from "../components/ThreadItem";
import { ThreadsContext } from "../contexts/ThreadsContext";
import OpacityPanel from "../components/OpacityPanel";

const Home = () => {
  const { threads } = useContext(ThreadsContext);

  return (
    <OpacityPanel>
      <Link to="/thread/create" className="fixed top-0 right-0 py-2 px-3 cursor-pointer text-blue-500 underline">
          New Thread
      </Link>
      <div className="w-full flex flex-col items-center">
        <div className="grid grid-cols-5 mt-3">
          {threads !== undefined && threads.length > 0 ? (
            threads.map((t, key) => (
              <div key={key}>
                <ThreadItem thread={t} />
              </div>
            ))
          ) : (
            <div>No threads found.</div>
          )}
        </div>
      </div>
    </OpacityPanel>
  );
};

export default Home;