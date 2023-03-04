import { useEffect } from "react"
import { listarPublicacoesQuery } from "../api/publicacoes"
import { Pagination, Post, PostSkeleton } from "../components"
import { Section } from "../components/layout/basic"
import { usePagination } from "../hooks"
import { useQuery } from "../hooks/useQuery"

export function HomeScreen() {
  const { page, handleGoToPage, handleNextPage, handlePreviousPage } = usePagination()

  const [publicacoes, refetchPublicacoes] = useQuery(() => listarPublicacoesQuery({ page: page }))

  useEffect(() => {
    refetchPublicacoes({ page })
  }, [page]) // eslint-disable-line

  function renderPublicações() {
    return publicacoes.data.content.map(publicacao => (
      <Post key={publicacao.id} publicacao={publicacao} />
    ))
  }

  function renderSkeletons() {
    return [...Array(15)].map((item, index) => <PostSkeleton key={index} />)
  }

  return (
    <Section>
      <div className="flex flex-col gap-4 p-6 w-full">
        <div className="w-full h-full mx-auto place-content-start posts-grid min-h-[700px]">
          {publicacoes.loading ? renderSkeletons() : renderPublicações()}
        </div>
        <Pagination
          page={page}
          handleGoToPage={handleGoToPage}
          handleNextPage={handleNextPage}
          handlePreviousPage={handlePreviousPage}
          totalPages={publicacoes.data.totalPages ?? 1}
        />
      </div>
    </Section>
  )
}
