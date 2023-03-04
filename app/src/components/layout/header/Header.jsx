import { Link } from "react-router-dom";
import useGlobalUser from "../../../context/user"
import { HeaderLink } from "./HeaderLink";

const MENU_LINKS = [
  { texto: "Página Inicial", to: "/" },
  { texto: "Pesquisar", to: "/pesquisar" },
  { texto: "Amigos", to: "/amigos" },
  { texto: "Perfil", to: "/perfil" },
];

export function Header() {
  const [user] = useGlobalUser();

  return (
    <header className="w-full flex items-center bg-zinc-800 h-20 px-8 gap-6 border-b border-b-zinc-700 sticky top-0 z-10">
      <h1 className="text-2xl h-full flex">
        <span className="font-semibold flex items-center">ART</span>
        <span className="text-purple-600 flex items-center">LINK</span>
      </h1>
      <ul className="flex flex-1 gap-6 h-full">
        {MENU_LINKS.map(({ texto, to }, index) => <HeaderLink key={index} to={to}>{texto}</HeaderLink>)}
      </ul>

      <span className="font-semibold">{user.nome}</span>

      <Link to="/logout">
        <div className="rounded px-4 py-2 font-bold text-lg outline-none bg-zinc-700 hover:bg-zinc-500 focus:bg-zinc-500">
          SAIR
        </div>
      </Link>
    </header>
  )
}
