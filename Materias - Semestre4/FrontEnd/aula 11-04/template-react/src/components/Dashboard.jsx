import {Link, useParams} from "react-router-dom"
import { useEffect, useState } from "react";
export default function Dashboard() {
    const [params, setParams] = useState({});

    useEffect(() => {
        console.log(params)
    }, []);
    
   return (
      <>
         <h1 className="bg-green-500">Dashboard</h1>
         <h2>{params.user}</h2>
         <Link to={"/login"}>Voltar</Link>
      </>
   )
}
