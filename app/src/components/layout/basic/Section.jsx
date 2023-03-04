import { Container } from "./Container"

export function Section({ children, containerClassName }) {
  return (
    <section className="flex flex-col items-center w-full my-0 mx-auto">
      <Container className={containerClassName}>
        {children}
      </Container>
    </section>
  )
}
