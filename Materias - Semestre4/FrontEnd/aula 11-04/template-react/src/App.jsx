import { routes } from "./routes.jsx"
import { RouterProvider } from "react-router-dom"

export function App() {
   return (
      <>   
      <RouterProvider router={routes} /> 
      </>
   )
}