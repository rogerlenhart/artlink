import { useEffect } from "react";
import { Navigate, Outlet, useNavigate } from "react-router-dom";
import useGlobalUser from "../context/user";
import { useAuth } from "../hooks";

export function PrivateRoute() {
  const [user, setUser] = useGlobalUser();

  const { updateUser } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    updateUser({}, {
      onError: (error) => setUser(null) && navigate("/login")
    })
  }, []) // eslint-disable-line

  if (!user) {
    return <Navigate to={"/login"} />;
  }

  return <Outlet/>
}
