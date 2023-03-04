import { Outlet } from "react-router-dom"
import { Section } from "./basic/Section"

export function AuthLayout() {
  return (
    <div className="min-h-screen flex flex-col">
      <main className="flex flex-1">
        <Section containerClassName="pt-24">
          <Outlet />
        </Section>
      </main>
    </div>
  )
}
