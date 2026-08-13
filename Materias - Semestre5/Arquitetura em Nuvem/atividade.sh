#!/bin/bash

# ============================================================================
# LAB03 - ARQUITETURA WEB COM NGINX
# ============================================================================
# Descrição: Provisiona uma arquitetura simples na AWS com:
#   - 1 VPC
#   - 1 Subnet pública
#   - 1 Internet Gateway
#   - 1 Route Table pública (0.0.0.0/0 -> IGW)
#   - 1 Security Group (SSH 22 e HTTP 80)
#   - 1 EC2 com Nginx instalado automaticamente (user-data)
# ============================================================================

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source "$SCRIPT_DIR/app/lib/utils.sh"
source "$SCRIPT_DIR/app/lib/validacao.sh"
source "$SCRIPT_DIR/app/lib/aws_helpers.sh"

# ============================================================================
# CONFIGURAÇÕES LAB03
# ============================================================================
REGIAO="us-east-1"
ZONA_PRIMARIA="us-east-1a"

export AWS_DEFAULT_REGION="$REGIAO"

# Arquitetura da imagem
VPC_CIDR="192.168.0.0/22"
SUBNET_PUBLICA_CIDR="192.168.1.0/24"

VPC_NOME="LAB03-VPC"
SUBNET_PUBLICA_NOME="LAB03-Subnet-Publica-Web"
IGW_NOME="LAB03-IGW"
RT_PUBLICA_NOME="LAB03-RT-Publica"
SG_NOME="launch-wizard-42"
INSTANCE_NOME="vm-web.server-01"

IMAGE_ID="ami-0b6c6ebed2801a5cb"
INSTANCE_TYPE="t2.micro"

PASTA_PAR_CHAVES="$HOME/Downloads/par de chaves EC2"

# ============================================================================
# FUNÇÕES AUXILIARES
# ============================================================================
validar_pre_requisitos() {
	msg_info "Validando pré-requisitos..."

	command -v aws > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		msg_erro "AWS CLI não encontrado. Instale e configure antes de continuar."
		return 1
	fi

	aws sts get-caller-identity --output text > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		msg_erro "Credenciais AWS não configuradas/válidas. Execute: aws configure"
		return 1
	fi

	msg_sucesso "Pré-requisitos OK"
	return 0
}

criar_user_data_nginx() {
	local arquivo_user_data="$1"

	cat <<'EOF' > "$arquivo_user_data"
#!/bin/bash
set -e

apt-get update -y
apt-get install -y nginx

cat <<'HTML' > /var/www/html/index.html
<!doctype html>
<html lang="pt-BR">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>LAB03 - Nginx</title>
  <style>
	body { font-family: Arial, sans-serif; margin: 40px; background: #f5f7fb; color: #1f2937; }
	.card { background: #ffffff; border-radius: 12px; padding: 24px; box-shadow: 0 8px 20px rgba(0,0,0,0.08); max-width: 720px; }
	h1 { margin-top: 0; }
	code { background: #eef2ff; padding: 2px 6px; border-radius: 6px; }
  </style>
</head>
<body>
  <div class="card">
	<h1>LAB03 - Web Server no ar</h1>
  </div>
</body>
</html>
HTML

systemctl enable nginx
systemctl restart nginx
EOF
}

criar_instancia_web_lab() {
	local nome="$1"
	local image_id="$2"
	local instance_type="$3"
	local key_name="$4"
	local subnet_id="$5"
	local sg_id="$6"
	local arquivo_user_data="$7"

	msg_info "Criando instância EC2 '$nome' com Nginx via user-data..."
	pausar 1

	local instance_id=$(aws ec2 run-instances \
		--image-id "$image_id" \
		--instance-type "$instance_type" \
		--key-name "$key_name" \
		--subnet-id "$subnet_id" \
		--security-group-ids "$sg_id" \
		--associate-public-ip-address \
		--user-data "file://$arquivo_user_data" \
		--tag-specifications "ResourceType=instance,Tags=[{Key=Name,Value=$nome}]" \
		--query 'Instances[0].InstanceId' \
		--output text)

	if [ $? -eq 0 ] && [ -n "$instance_id" ]; then
		msg_sucesso "Instância criada: $instance_id"
		echo "$instance_id"
		return 0
	fi

	msg_erro "Falha ao criar instância EC2 web"
	return 1
}

# ============================================================================
# INÍCIO
# ============================================================================
exibir_banner
echo "LAB03 - Provisionamento de arquitetura web com Nginx" >&2
echo "Região: $REGIAO | Zona: $ZONA_PRIMARIA" >&2
separador

if ! validar_pre_requisitos; then
	exit 1
fi

if ! confirmar_acao "Este script irá criar recursos AWS cobrados (EC2, VPC e rede)."; then
	exit 0
fi

separador

# ============================================================================
# ETAPA 1: VPC
# ============================================================================
titulo_etapa "1" "Criando VPC ($VPC_CIDR)"
separador

vpc_id=$(criar_vpc "$VPC_CIDR" "$VPC_NOME")
if [ $? -ne 0 ] || [ -z "$vpc_id" ]; then
	msg_erro "Falha ao criar VPC. Abortando."
	exit 1
fi

# ============================================================================
# ETAPA 2: SUBNET PÚBLICA
# ============================================================================
titulo_etapa "2" "Criando Subnet Pública ($SUBNET_PUBLICA_CIDR)"
separador

subnet_publica_id=$(criar_subnet "$vpc_id" "$SUBNET_PUBLICA_CIDR" "$ZONA_PRIMARIA" "$SUBNET_PUBLICA_NOME")
if [ $? -ne 0 ] || [ -z "$subnet_publica_id" ]; then
	msg_erro "Falha ao criar Subnet Pública. Abortando."
	exit 1
fi

habilitar_ip_publico_subnet "$subnet_publica_id"

# ============================================================================
# ETAPA 3: INTERNET GATEWAY
# ============================================================================
titulo_etapa "3" "Criando Internet Gateway"
separador

igw_id=$(criar_internet_gateway "$vpc_id" "$IGW_NOME")
if [ $? -ne 0 ] || [ -z "$igw_id" ]; then
	msg_erro "Falha ao criar Internet Gateway. Abortando."
	exit 1
fi

# ============================================================================
# ETAPA 4: ROUTE TABLE PÚBLICA
# ============================================================================
titulo_etapa "4" "Criando Route Table Pública e rota para internet"
separador

rt_publica_id=$(criar_route_table "$vpc_id" "$RT_PUBLICA_NOME")
if [ $? -ne 0 ] || [ -z "$rt_publica_id" ]; then
	msg_erro "Falha ao criar Route Table Pública. Abortando."
	exit 1
fi

criar_rota "$rt_publica_id" "0.0.0.0/0" "gateway" "$igw_id"
if [ $? -ne 0 ]; then
	msg_erro "Falha ao criar rota padrão para internet. Abortando."
	exit 1
fi

associar_route_table "$rt_publica_id" "$subnet_publica_id"
if [ $? -ne 0 ]; then
	msg_erro "Falha ao associar Route Table à Subnet. Abortando."
	exit 1
fi

# ============================================================================
# ETAPA 5: PAR DE CHAVES
# ============================================================================
titulo_etapa "5" "Configurando par de chaves EC2"
separador

nome_par_chaves=$(gerenciar_par_chaves "$PASTA_PAR_CHAVES")
if [ $? -ne 0 ] || [ -z "$nome_par_chaves" ]; then
	msg_erro "Falha ao configurar par de chaves. Abortando."
	exit 1
fi

# ============================================================================
# ETAPA 6: SECURITY GROUP
# ============================================================================
titulo_etapa "6" "Criando Security Group (SSH 22 e HTTP 80)"
separador

sg_id=$(criar_security_group_custom "$SG_NOME" "SG LAB03 - acesso web e SSH" "$vpc_id")
if [ $? -ne 0 ] || [ -z "$sg_id" ]; then
	msg_erro "Falha ao criar Security Group. Abortando."
	exit 1
fi

adicionar_regra_ingress "$sg_id" "tcp" "22" "0.0.0.0/0"
adicionar_regra_ingress "$sg_id" "tcp" "80" "0.0.0.0/0"

msg_sucesso "Security Group criado: $sg_id"

# ============================================================================
# ETAPA 7: EC2 COM NGINX
# ============================================================================
titulo_etapa "7" "Criando instância EC2 web + instalando Nginx"
separador

arquivo_user_data="$(mktemp)"
criar_user_data_nginx "$arquivo_user_data"

ec2_web_id=$(criar_instancia_web_lab \
	"$INSTANCE_NOME" \
	"$IMAGE_ID" \
	"$INSTANCE_TYPE" \
	"$nome_par_chaves" \
	"$subnet_publica_id" \
	"$sg_id" \
	"$arquivo_user_data")

rm -f "$arquivo_user_data"

if [ $? -ne 0 ] || [ -z "$ec2_web_id" ]; then
	msg_erro "Falha ao criar instância EC2 web. Abortando."
	exit 1
fi

msg_info "Aguardando instância ficar em estado 'running'..."
aws ec2 wait instance-running --instance-ids "$ec2_web_id"

ip_publico=$(obter_ip_publico_instancia "$ec2_web_id")

# ============================================================================
# RESUMO
# ============================================================================
echo "" >&2
echo "═══════════════════════════════════════════════════════════════════════" >&2
echo "                   LAB03 CRIADO COM SUCESSO!" >&2
echo "═══════════════════════════════════════════════════════════════════════" >&2
echo "" >&2
echo "  VPC:                 $vpc_id ($VPC_CIDR)" >&2
echo "  Subnet Pública:      $subnet_publica_id ($SUBNET_PUBLICA_CIDR)" >&2
echo "  Internet Gateway:    $igw_id" >&2
echo "  Route Table Pública: $rt_publica_id" >&2
echo "  Security Group:      $sg_id (22/80)" >&2
echo "  EC2 Web:             $ec2_web_id ($INSTANCE_NOME)" >&2
if [ -n "$ip_publico" ]; then
	echo "  IP Público EC2:      $ip_publico" >&2
fi
echo "" >&2

CAMINHO_PAR_CHAVES="$PASTA_PAR_CHAVES/${nome_par_chaves}.pem"
msg_info "Comando SSH:"
echo "  ssh -i \"$CAMINHO_PAR_CHAVES\" ubuntu@$ip_publico" >&2
echo "" >&2

msg_info "Teste HTTP no navegador/curl (aguarde ~1-2 min para cloud-init finalizar):"
echo "  http://$ip_publico" >&2
echo "  curl -I http://$ip_publico" >&2
echo "" >&2
echo "═══════════════════════════════════════════════════════════════════════" >&2
echo "" >&2
