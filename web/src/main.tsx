import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import App from './App.tsx';
import './index.css';
import Home from './pages/Home.tsx';
import ThreadPage from './components/ThreadPage.tsx';
import CreateThreadPanel from './components/CreateThreadPanel.tsx';

const myRouter = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { path: "/", element: <Home />, index: true },
      { path: "/thread/create", element: <CreateThreadPanel /> },
      { path: "/thread/:id", element: <ThreadPage /> },
    ]
  }
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={myRouter} />
  </StrictMode>,
)
