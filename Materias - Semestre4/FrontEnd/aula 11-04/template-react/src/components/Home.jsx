import { Link } from "react-router-dom"
export default function Home() {
   return (
      <>
         <h1 className="bg-yellow-500">Home</h1>
         <Link to={"/login"}>Login</Link>
      </>
   )
}
