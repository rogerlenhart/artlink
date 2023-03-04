import { NavLink } from "react-router-dom"
import classNames from "classnames"

export function HeaderLink({ children, to }) {
  return (
    <li>
      <NavLink
        to={to}
        className={({ isActive }) =>
          classNames("h-full flex items-center text-base font-semibold text-zinc-400 border-b-2 border-b-purple-600 border-opacity-0 hover:text-zinc-200 hover:border-opacity-100 transition", {
            "text-purple-600 border-b-2 border-opacity-100 hover:text-purple-600": isActive
          })
        }>
        {children}
      </NavLink>
    </li>
  )
}