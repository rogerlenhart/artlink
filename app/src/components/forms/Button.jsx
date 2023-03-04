import classNames from "classnames"

export function Button({ children, handleClick, disabled = false, full = false }) {

  return (
    <button
      className={classNames("rounded px-5 py-3 font-bold text-lg outline-none bg-purple-600 hover:not enabled:hover:bg-purple-400 focus:bg-purple-400 disabled:opacity-50 disabled:cursor-not-allowed transition duration-200", {
        "w-full": full
      })}
      onClick={handleClick}
      disabled={disabled}
    >
      {children}
    </button>
  )
}
