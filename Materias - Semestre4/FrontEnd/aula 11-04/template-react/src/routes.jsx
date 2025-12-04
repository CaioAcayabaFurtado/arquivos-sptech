import { createBrowserRouter } from "react-router-dom"
import Home from "./components/Home.jsx"
import Login from "./components/Login.jsx"
import Dashboard from "./components/Dashboard.jsx"

export const routes = createBrowserRouter([
   {
      path: "/",
      element: <Home />
   },
   {
      path: "/login",
      element: <Login />
   },
   {
      path: "/dashboard",
      element: <Dashboard />
   }
])