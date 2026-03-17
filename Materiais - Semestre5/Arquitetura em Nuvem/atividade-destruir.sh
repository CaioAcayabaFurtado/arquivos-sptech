#!/bin/bash

# ============================================================================
# LAB03 - DESTRUIÇÃO DA ARQUITETURA WEB COM NGINX
# ============================================================================
# Descrição: Remove os recursos criados pelo atividade.sh:
#   - EC2 vm-web.server-01
#   - Security Group launch-wizard-42
#   - Route Table pública LAB03-RT-Publica
#   - Internet Gateway LAB03-IGW
#   - Subnet LAB03-Subnet-Publica-Web
#   - VPC LAB03-VPC
# ============================================================================

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source "$SCRIPT_DIR/app/lib/utils.sh"
source "$SCRIPT_DIR/app/lib/validacao.sh"
source "$SCRIPT_DIR/app/lib/aws_helpers.sh"

REGIAO="us-east-1"
export AWS_DEFAULT_REGION="$REGIAO"

VPC_NOME="LAB03-VPC"
RT_PUBLICA_NOME="LAB03-RT-Publica"
SG_NOME="launch-wizard-42"
INSTANCE_NOME="vm-web.server-01"

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

exibir_banner
echo "LAB03 - Destruição da arquitetura web com Nginx" >&2
echo "Região: $REGIAO" >&2
separador

if ! validar_pre_requisitos; then
    exit 1
fi

if ! confirmar_acao "Este script irá DESTRUIR os recursos do LAB03 na AWS."; then
    exit 0
fi

separador

# ============================================================================
# ETAPA 1: BUSCAR VPC DO LAB03
# ============================================================================
titulo_etapa "1" "Buscando VPC do LAB03"
separador

vpc_id=$(aws ec2 describe-vpcs \
    --filters "Name=tag:Name,Values=$VPC_NOME" \
    --query 'Vpcs[0].VpcId' \
    --output text 2>/dev/null)

if [ -z "$vpc_id" ] || [ "$vpc_id" == "None" ]; then
    msg_info "VPC '$VPC_NOME' não encontrada. Nada para destruir."
    exit 0
fi

msg_sucesso "VPC encontrada: $vpc_id"

# ============================================================================
# ETAPA 2: ENCERRAR EC2 DO LAB03
# ============================================================================
titulo_etapa "2" "Encerrando instância EC2 do LAB03"
separador

instance_ids=$(aws ec2 describe-instances \
    --filters \
    "Name=vpc-id,Values=$vpc_id" \
    "Name=tag:Name,Values=$INSTANCE_NOME" \
    "Name=instance-state-name,Values=pending,running,stopping,stopped" \
    --query 'Reservations[*].Instances[*].InstanceId' \
    --output text 2>/dev/null)

if [ -n "$instance_ids" ] && [ "$instance_ids" != "None" ]; then
    msg_info "Encerrando instância(s): $instance_ids"
    aws ec2 terminate-instances --instance-ids $instance_ids > /dev/null 2>&1

    msg_info "Aguardando término da(s) instância(s)..."
    aws ec2 wait instance-terminated --instance-ids $instance_ids 2>/dev/null
    msg_sucesso "Instância(s) encerrada(s)!"
else
    msg_info "Nenhuma instância '$INSTANCE_NOME' encontrada na VPC."
fi

# ============================================================================
# ETAPA 3: DELETAR SECURITY GROUP DO LAB03
# ============================================================================
titulo_etapa "3" "Deletando Security Group do LAB03"
separador

sg_id=$(aws ec2 describe-security-groups \
    --filters "Name=vpc-id,Values=$vpc_id" "Name=group-name,Values=$SG_NOME" \
    --query 'SecurityGroups[0].GroupId' \
    --output text 2>/dev/null)

if [ -n "$sg_id" ] && [ "$sg_id" != "None" ]; then
    deletar_security_group "$sg_id"
else
    msg_info "Security Group '$SG_NOME' não encontrado na VPC."
fi

# ============================================================================
# ETAPA 4: DELETAR ROUTE TABLES NÃO-PRINCIPAIS
# ============================================================================
titulo_etapa "4" "Deletando Route Tables do LAB03"
separador

main_rt_id=$(aws ec2 describe-route-tables \
    --filters "Name=vpc-id,Values=$vpc_id" "Name=association.main,Values=true" \
    --query 'RouteTables[0].RouteTableId' \
    --output text 2>/dev/null)

rt_ids=$(aws ec2 describe-route-tables \
    --filters "Name=vpc-id,Values=$vpc_id" "Name=tag:Name,Values=$RT_PUBLICA_NOME" \
    --query 'RouteTables[*].RouteTableId' \
    --output text 2>/dev/null)

if [ -n "$rt_ids" ] && [ "$rt_ids" != "None" ]; then
    for rt_id in $rt_ids; do
        if [ "$rt_id" == "$main_rt_id" ]; then
            continue
        fi

        assoc_ids=$(aws ec2 describe-route-tables \
            --route-table-ids "$rt_id" \
            --query 'RouteTables[0].Associations[?!Main].RouteTableAssociationId' \
            --output text 2>/dev/null)

        for assoc_id in $assoc_ids; do
            [ -z "$assoc_id" ] || [ "$assoc_id" == "None" ] && continue
            msg_info "Desassociando Route Table $rt_id ($assoc_id)..."
            aws ec2 disassociate-route-table --association-id "$assoc_id" > /dev/null 2>&1
        done

        deletar_route_table "$rt_id"
    done
else
    msg_info "Nenhuma Route Table '$RT_PUBLICA_NOME' encontrada."
fi

# ============================================================================
# ETAPA 5: DELETAR INTERNET GATEWAY
# ============================================================================
titulo_etapa "5" "Deletando Internet Gateway"
separador

igw_ids=$(obter_internet_gateways_vpc "$vpc_id")

if [ -n "$igw_ids" ] && [ "$igw_ids" != "None" ]; then
    for igw_id in $igw_ids; do
        deletar_internet_gateway "$igw_id" "$vpc_id"
    done
else
    msg_info "Nenhum Internet Gateway encontrado na VPC."
fi

# ============================================================================
# ETAPA 6: DELETAR SUBNETS
# ============================================================================
titulo_etapa "6" "Deletando subnets da VPC"
separador

subnet_ids=$(aws ec2 describe-subnets \
    --filters "Name=vpc-id,Values=$vpc_id" \
    --query 'Subnets[*].SubnetId' \
    --output text 2>/dev/null)

if [ -n "$subnet_ids" ] && [ "$subnet_ids" != "None" ]; then
    for subnet_id in $subnet_ids; do
        deletar_subnet "$subnet_id"
    done
else
    msg_info "Nenhuma subnet encontrada na VPC."
fi

# ============================================================================
# ETAPA 7: DELETAR VPC
# ============================================================================
titulo_etapa "7" "Deletando VPC"
separador

deletar_vpc "$vpc_id"

separador
echo "═══════════════════════════════════════════════════════════════════════" >&2
echo "                RECURSOS DO LAB03 REMOVIDOS COM SUCESSO" >&2
echo "═══════════════════════════════════════════════════════════════════════" >&2
echo "" >&2
