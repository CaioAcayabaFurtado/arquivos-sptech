package sptech.projeto04

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Projeto04Application

fun main(args: Array<String>) {
	runApplication<Projeto04Application>(*args)
}

/*

Respostas bem-sucedidas
200 OK
A solicitação foi bem-sucedida. O resultado e o significado de "sucesso" dependem do método HTTP:

GET: O recurso foi buscado e transmitido no corpo da mensagem.
HEAD: Os cabeçalhos de representação são incluídos na resposta sem nenhum corpo de mensagem.
PUTou POST: O recurso que descreve o resultado da ação é transmitido no corpo da mensagem.
TRACE: O corpo da mensagem contém a solicitação conforme recebida pelo servidor.
201 Created
A solicitação foi bem-sucedida e um novo recurso foi criado como resultado. Essa é tipicamente a resposta enviada após POSTsolicitações, ou algumas PUTsolicitações.

202 Accepted
A solicitação foi recebida, mas ainda não foi atendida. Ela não é comprometedora, pois não há como no HTTP enviar posteriormente uma resposta assíncrona indicando o resultado da solicitação. Ela é destinada a casos em que outro processo ou servidor manipula a solicitação, ou para processamento em lote.

203 Non-Authoritative Information
Este código de resposta significa que os metadados retornados não são exatamente os mesmos que estão disponíveis no servidor de origem, mas são coletados de uma cópia local ou de terceiros. Isso é usado principalmente para espelhos ou backups de outro recurso. Exceto para esse caso específico, a 200 OKresposta é preferida a esse status.

204 No Content
Não há conteúdo para enviar para esta solicitação, mas os cabeçalhos são úteis. O agente do usuário pode atualizar seus cabeçalhos em cache para este recurso com os novos.

205 Reset Content
Diz ao agente do usuário para redefinir o documento que enviou esta solicitação.

206 Partial Content
Este código de resposta é usado em resposta a uma solicitação de intervalo quando o cliente solicitou uma parte ou partes de um recurso.

207 Multi-Status( WebDAV )
Transmite informações sobre vários recursos, para situações em que vários códigos de status podem ser apropriados.

208 Already Reported( WebDAV )
Usado dentro de um <dav:propstat>elemento de resposta para evitar enumerar repetidamente os membros internos de várias ligações à mesma coleção.

226 IM Used( codificação HTTP Delta )
O servidor atendeu a uma GETsolicitação do recurso e a resposta é uma representação do resultado de uma ou mais manipulações de instância aplicadas à instância atual.

Mensagens de redirecionamento
300 Multiple Choices
Na negociação de conteúdo orientada por agente , a solicitação tem mais de uma resposta possível e o agente do usuário ou o usuário deve escolher uma delas. Não há uma maneira padronizada para os clientes escolherem automaticamente uma das respostas, então isso raramente é usado.

301 Moved Permanently
A URL do recurso solicitado foi alterada permanentemente. A nova URL é fornecida na resposta.

302 Found
Este código de resposta significa que o URI do recurso solicitado foi alterado temporariamente . Outras alterações no URI podem ser feitas no futuro, então o mesmo URI deve ser usado pelo cliente em solicitações futuras.

303 See Other
O servidor enviou esta resposta para direcionar o cliente a obter o recurso solicitado em outro URI com uma GETsolicitação.

304 Not Modified
Isto é usado para fins de cache. Ele informa ao cliente que a resposta não foi modificada, então o cliente pode continuar a usar a mesma versão em cache da resposta.

305 Use Proxy Obsoleto
Definido em uma versão anterior da especificação HTTP para indicar que uma resposta solicitada deve ser acessada por um proxy. Foi descontinuado devido a preocupações de segurança relacionadas à configuração in-band de um proxy.

306 unused
Este código de resposta não é mais usado; mas é reservado. Ele foi usado em uma versão anterior da especificação HTTP/1.1.

307 Temporary Redirect
O servidor envia esta resposta para direcionar o cliente a obter o recurso solicitado em outro URI com o mesmo método que foi usado na solicitação anterior. Isso tem a mesma semântica do 302 Foundcódigo de resposta, com a exceção de que o agente do usuário não deve alterar o método HTTP usado: se a POSTfoi usado na primeira solicitação, a POSTdeve ser usado na solicitação redirecionada.

308 Permanent Redirect
Isso significa que o recurso agora está permanentemente localizado em outro URI, especificado pelo Locationcabeçalho de resposta. Isso tem a mesma semântica do 301 Moved Permanentlycódigo de resposta HTTP, com a exceção de que o agente do usuário não deve alterar o método HTTP usado: se a POSTfoi usado na primeira solicitação, a POSTdeve ser usado na segunda solicitação.

Respostas de erro do cliente
400 Bad Request
O servidor não pode ou não processará a solicitação devido a algo que é percebido como um erro do cliente (por exemplo, sintaxe de solicitação malformada, enquadramento de mensagem de solicitação inválido ou roteamento de solicitação enganoso).

401 Unauthorized
Embora o padrão HTTP especifique "não autorizado", semanticamente essa resposta significa "não autenticado". Ou seja, o cliente deve se autenticar para obter a resposta solicitada.

402 Payment Required
O propósito inicial deste código era para sistemas de pagamento digital, no entanto este código de status raramente é usado e não existe nenhuma convenção padrão.

403 Forbidden
O cliente não tem direitos de acesso ao conteúdo; ou seja, ele não é autorizado, então o servidor está se recusando a dar o recurso solicitado. Diferentemente de 401 Unauthorized, a identidade do cliente é conhecida pelo servidor.

404 Not Found
O servidor não consegue encontrar o recurso solicitado. No navegador, isso significa que a URL não é reconhecida. Em uma API, isso também pode significar que o endpoint é válido, mas o recurso em si não existe. Os servidores também podem enviar essa resposta em vez de 403 Forbiddenocultar a existência de um recurso de um cliente não autorizado. Esse código de resposta é provavelmente o mais conhecido devido à sua ocorrência frequente na web.

405 Method Not Allowed
O método de solicitação é conhecido pelo servidor, mas não é suportado pelo recurso de destino. Por exemplo, uma API pode não permitir DELETEum recurso, ou o TRACEmétodo inteiramente.

406 Not Acceptable
Esta resposta é enviada quando o servidor web, após executar a negociação de conteúdo orientada pelo servidor , não encontra nenhum conteúdo que esteja em conformidade com os critérios fornecidos pelo agente do usuário.

407 Proxy Authentication Required
Isso é semelhante, 401 Unauthorizedmas a autenticação precisa ser feita por um proxy.

408 Request Timeout
Esta resposta é enviada em uma conexão ociosa por alguns servidores, mesmo sem nenhuma solicitação anterior do cliente. Isso significa que o servidor gostaria de desligar esta conexão não utilizada. Esta resposta é usada muito mais, pois alguns navegadores usam mecanismos de pré-conexão HTTP para acelerar a navegação. Alguns servidores podem desligar uma conexão sem enviar esta mensagem.

409 Conflict
Esta resposta é enviada quando uma solicitação entra em conflito com o estado atual do servidor. Na criação remota de web WebDAV409 , as respostas são erros enviados ao cliente para que um usuário possa resolver um conflito e reenviar a solicitação.

410 Gone
Esta resposta é enviada quando o conteúdo solicitado foi permanentemente excluído do servidor, sem endereço de encaminhamento. Espera-se que os clientes removam seus caches e links para o recurso. A especificação HTTP pretende que este código de status seja usado para "serviços promocionais por tempo limitado". As APIs não devem se sentir obrigadas a indicar recursos que foram excluídos com este código de status.

411 Length Required
O servidor rejeitou a solicitação porque o Content-Lengthcampo de cabeçalho não está definido e o servidor o exige.

412 Precondition Failed
Em solicitações condicionais , o cliente indicou pré-condições em seus cabeçalhos que o servidor não atende.

413 Content Too Large
O corpo da solicitação é maior que os limites definidos pelo servidor. O servidor pode fechar a conexão ou retornar um Retry-Aftercampo de cabeçalho.

414 URI Too Long
O URI solicitado pelo cliente é maior do que o servidor está disposto a interpretar.

415 Unsupported Media Type
O formato de mídia dos dados solicitados não é suportado pelo servidor, então o servidor está rejeitando a solicitação.

416 Range Not Satisfiable
Os intervalos especificados pelo Rangecampo de cabeçalho na solicitação não podem ser atendidos. É possível que o intervalo esteja fora do tamanho dos dados do recurso de destino.

417 Expectation Failed
Este código de resposta significa que a expectativa indicada pelo Expectcampo de cabeçalho da solicitação não pode ser atendida pelo servidor.

418 I'm a teapot
O garçom se recusa a tentar preparar café com um bule de chá.

421 Misdirected Request
A solicitação foi direcionada a um servidor que não é capaz de produzir uma resposta. Isso pode ser enviado por um servidor que não está configurado para produzir respostas para a combinação de esquema e autoridade que estão incluídos no URI da solicitação.

422 Unprocessable Content( WebDAV )
A solicitação estava bem formulada, mas não pôde ser atendida devido a erros semânticos.

423 Locked( WebDAV )
O recurso que está sendo acessado está bloqueado.

424 Failed Dependency( WebDAV )
A solicitação falhou devido à falha de uma solicitação anterior.

425 Too Early Experimental
Indica que o servidor não está disposto a correr o risco de processar uma solicitação que pode ser repetida.

426 Upgrade Required
O servidor se recusa a executar a solicitação usando o protocolo atual, mas pode estar disposto a fazê-lo após o cliente atualizar para um protocolo diferente. O servidor envia um Upgradecabeçalho em uma resposta 426 para indicar o(s) protocolo(s) necessário(s).

428 Precondition Required
O servidor de origem requer que a solicitação seja condicional . Esta resposta tem a intenção de evitar o problema de 'atualização perdida', onde um cliente GETsalva o estado de um recurso, o modifica e PUTo envia de volta ao servidor, quando, enquanto isso, um terceiro modificou o estado no servidor, levando a um conflito.

429 Too Many Requests
O usuário enviou muitas solicitações em um determinado período de tempo ( limitação de taxa ).

431 Request Header Fields Too Large
O servidor não está disposto a processar a solicitação porque seus campos de cabeçalho são muito grandes. A solicitação pode ser reenviada após reduzir o tamanho dos campos de cabeçalho da solicitação.

451 Unavailable For Legal Reasons
O agente do usuário solicitou um recurso que não pode ser fornecido legalmente, como uma página da web censurada por um governo.

Respostas de erro do servidor
500 Internal Server Error
O servidor encontrou uma situação com a qual não sabe como lidar. Este erro é genérico, indicando que o servidor não consegue encontrar um 5XXcódigo de status mais apropriado para responder.

501 Not Implemented
O método request não é suportado pelo servidor e não pode ser manipulado. Os únicos métodos que os servidores são obrigados a suportar (e, portanto, que não devem retornar este código) são GETand HEAD.

502 Bad Gateway
Essa resposta de erro significa que o servidor, enquanto trabalhava como um gateway para obter uma resposta necessária para lidar com a solicitação, recebeu uma resposta inválida.

503 Service Unavailable
O servidor não está pronto para lidar com a solicitação. Causas comuns são um servidor que está inativo para manutenção ou que está sobrecarregado. Observe que, junto com essa resposta, uma página amigável explicando o problema deve ser enviada. Essa resposta deve ser usada para condições temporárias e o Retry-Aftercabeçalho HTTP deve, se possível, conter o tempo estimado antes da recuperação do serviço. O webmaster também deve tomar cuidado com os cabeçalhos relacionados ao cache que são enviados junto com essa resposta, pois essas respostas de condições temporárias geralmente não devem ser armazenadas em cache.

504 Gateway Timeout
Essa resposta de erro é dada quando o servidor está agindo como um gateway e não consegue obter uma resposta a tempo.

505 HTTP Version Not Supported
A versão HTTP usada na solicitação não é suportada pelo servidor.

506 Variant Also Negotiates
O servidor tem um erro de configuração interna: durante a negociação de conteúdo, a variante escolhida é configurada para se envolver na negociação de conteúdo, o que resulta em referências circulares ao criar respostas.

507 Insufficient Storage( WebDAV )
O método não pôde ser executado no recurso porque o servidor não conseguiu armazenar a representação necessária para concluir a solicitação com sucesso.

508 Loop Detected( WebDAV )
O servidor detectou um loop infinito ao processar a solicitação.

510 Not Extended
A solicitação do cliente declara uma extensão HTTP ( RFC 2774 ) que deve ser usada para processar a solicitação, mas a extensão não é suportada.

511 Network Authentication Required
Indica que o cliente precisa se autenticar para obter acesso à rede.

*/
