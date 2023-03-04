import { createBrowserRouter, Navigate } from "react-router-dom";

import { AuthLayout, MainLayout } from "../components/layout";

import {
  LoginScreen, HomeScreen, LogoutScreen, PostDetail
} from "../screens";

import { PrivateRoute } from "./PrivateRoute";

export const router = createBrowserRouter([
  {
    element: <AuthLayout />,
    children: [
      {
        path: '/login',
        element: <LoginScreen />
      },
    ],
  },
  {
    element: <PrivateRoute />,
    children: [
      {
        element: <MainLayout />,
        children: [
          {
            path: '/',
            element: <HomeScreen />
          },
          {
            path: '/posts/:id',
            element: <PostDetail />
          },
          {
            path: "/logout",
            element: <LogoutScreen />
          },
        ]
      }
    ]
  },
  {
    path: '*',
    element: <Navigate to="/" />
  }
])