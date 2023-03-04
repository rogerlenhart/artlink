import { RouterProvider } from "react-router-dom";
import { router } from "./router";

import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { GlobalUserProvider } from "./context/user";

function App() {
  return (
    <GlobalUserProvider>
        <RouterProvider router={router} />
        <ToastContainer theme="dark" />
    </GlobalUserProvider>
  );
}

export default App;
