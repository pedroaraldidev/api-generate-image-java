# API Java - Geração de Imagem com Texto

Este projeto é uma API Java que permite gerar uma imagem com texto sobreposto. Você pode enviar uma solicitação para a rota `/generate-image`, passando uma série de parâmetros para personalizar o texto e a imagem gerada. A API cria a imagem com o texto fornecido, aplicando o estilo, cor e posição desejados, além de outras opções de configuração, como o redimensionamento e o escurecimento da imagem.

## Endpoints

### POST `/generate-image`

Este endpoint gera uma imagem com o texto especificado. A solicitação deve ser feita via `POST` com os parâmetros necessários no corpo da requisição no formato `form-data`.

#### Parâmetros (Form-Data)

- **`text`** (String):  
  O texto que será inserido na imagem.

- **`backgroundImage`** (String):  
  A imagem de fundo em formato Base64.

- **`textColor`** (String):  
  A cor do texto no formato hexadecimal (ex: `#FFFFFF` para branco).

- **`textPosition`** (String):  
  A posição do texto na imagem. Pode ser:
  - `top`
  - `bottom`
  - `left`
  - `right`

- **`textType`** (String):  
  O estilo do texto. Pode ser:
  - `title`
  - `subtitle`
  - `body`
  - Outros estilos personalizados.

- **`darkenImage`** (Boolean):  
  Se `true`, a imagem de fundo será escurecida para melhorar a visibilidade do texto.

- **`width`** (Integer):  
  A largura da imagem gerada (em pixels).

- **`height`** (Integer):  
  A altura da imagem gerada (em pixels).
