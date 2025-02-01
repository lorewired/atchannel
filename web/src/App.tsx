import MainContainer from "./components/MainContainer";
import { ThreadsContextProvider } from "./contexts/ThreadsContext";

const App = () => {
  return (
    <ThreadsContextProvider>
      <MainContainer />;
    </ThreadsContextProvider>
  )
}

export default App