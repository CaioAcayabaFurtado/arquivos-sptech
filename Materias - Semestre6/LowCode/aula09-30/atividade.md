Atividade: Todo-list do Docker Hub até a nuvem
 
Nesta atividade, você vai criar uma página de lista de tarefas (todo-list), empacotá-la em uma imagem Docker, publicá-la no Docker Hub e executá-la em uma máquina virtual na Azure, acessível pela internet.

Página
todo-list
→
Imagem
Docker
→
Docker Hub
registry público
→
VM Azure
acesso pelo IP
Etapa	Objetivo	Entrega
1	Criar a página, gerar a imagem e publicar no Docker Hub.	URL pública da imagem.
2	Executar a imagem em uma VM na Azure.	Print do navegador com o IP da VM visível.
Etapa 1 · Da página ao Docker Hub
Requisitos
Requisito	Descrição
Identificação	Seu nome completo e seu RA visíveis na página.
Funcionalidades	Adicionar, concluir e remover tarefas.
Servidor	A página deve ser servida pelo nginx, na porta 80 do container.
Publicação	Imagem em um repositório público no Docker Hub, com a tag latest.
Entrega
Envie somente a URL pública da imagem, no formato:

https://hub.docker.com/r/seu-usuario/nome-da-imagem
Na correção, a imagem será executada com:

docker run -d -p 8080:80 seu-usuario/nome-da-imagem:latest
Etapa 2 · Do Docker Hub à Azure
Requisitos
Requisito	Descrição
VM	Máquina virtual Linux criada na Azure, com IP público.
Rede	Portas 22 (SSH) e 80 (HTTP) liberadas.
Docker	Docker instalado e configurado para ser usado sem sudo (usuário no grupo docker).
Aplicação	A imagem da etapa 1, baixada do Docker Hub, rodando na porta 80 da VM.
Entrega
Anexe um print do navegador em que apareçam, ao mesmo tempo:

1. A barra de endereço com o IP público da VM.
2. A página carregada, com o seu nome completo e RA.
3. Pelo menos uma tarefa adicionada na lista.
Depois de entregar
Pare ou exclua a VM para não consumir seus créditos da Azure.

Checklist antes de entregar
Etapa 1
☐ Meu nome completo e RA aparecem na página.
☐ Consigo adicionar, concluir e remover tarefas.
☐ Testei a imagem localmente e a página abriu.
☐ A imagem está no Docker Hub com a tag latest.
☐ O repositório está público.
Etapa 2
☐ A VM tem as portas 22 e 80 liberadas.
☐ Rodo comandos Docker na VM sem sudo.
☐ A página abre em http://IP_DA_VM.
☐ O print mostra o IP, meu nome, RA e uma tarefa na lista.
☐ Parei ou excluí a VM depois da entrega.
Documentação
Docker — docs.docker.com

Imagem oficial do nginx — hub.docker.com/_/nginx

Máquinas virtuais na Azure — learn.microsoft.com/azure/virtual-machines