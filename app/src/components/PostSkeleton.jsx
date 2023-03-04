export function PostSkeleton() {
  return (
    <div className="w-full min-h-[200px] bg-zinc-800 animate-pulse flex items-end aspect-square">
      <div className="flex w-full p-2 gap-2">
        <div className="rounded-full w-12 h-12 object-cover bg-zinc-700 shrink-0" />
        <div className="flex flex-col justify-center">
          <div className="h-2.5 bg-zinc-700 rounded-full w-28 mb-4"></div>
          <div className="h-2 bg-zinc-700 rounded-full w-20"></div>
        </div>
      </div>
    </div>
  )
}
