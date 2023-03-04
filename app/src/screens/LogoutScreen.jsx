import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Loader } from "../components";

import { useAuth } from "../hooks";

export function LogoutScreen() {
  const { logout } = useAuth()
  const navigate = useNavigate();

  useEffect(() => {
    logout({}, {
      onSuccess: (response) => navigate("/login")
    })
  }, []); // eslint-disable-line

  return <Loader />;
}
