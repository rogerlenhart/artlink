# TCC - ArtLink

API para gestão de uma rede social voltada para artistas.

## Funcionalidades

### Publicações
- Listar publicações (paginado): `GET /publicacoes`
- Detalhar publicação: `GET /publicacoes/{id}`
- Incluir publicação: `POST /publicacoes`
- Adicionar comentário: `POST /publicacoes/{id}/comentarios`
- Curtir publicação: `POST /publicacoes/{id}/curtidas`
- Remover curtida: `DELETE /publicacoes/{id}/curtidas`

### Perfil

- Editar perfil: `PUT /perfil`
- Detalhar usuário autenticado: `GET /perfil`
- Detalhar usuário: `GET /perfil/{id}`
- Pesquisar usuários (paginado): `GET /perfil/pesquisar`
- Listar amigos (paginado): `GET /perfil/amizades`

### Amizades
- Listar pedidos de amizade: `GET /perfil/amizades/pedidos`
- Solicitar amizade: `POST /perfil/{id}/amizades`
- Listar amigos (paginado): `PUT /perfil/{id}/amizades`
- Listar amigos (paginado): `DELETE /perfil/{id}/amizades`

## Dependências

- Criar objetos de banco: `data/schema.sql`
- Carga inicial: `data/insert.sql`
- Collection do postman: `data/postman/tcc-java.postman_collection.json`
- Environment do postman: `data/postman/tcc-java.postman_collection.json`

