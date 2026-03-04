#!/bin/bash
set -euo pipefail

echo "Iniciando provisionamento da infraestrutura..."

REGION="us-east-1"
AZ="us-east-1a"

VPC_CIDR="${VPC_CIDR:-10.0.0.0/16}"
PUBLIC_SUBNET_CIDR="${PUBLIC_SUBNET_CIDR:-10.0.0.0/24}"
PRIVATE_SUBNET_CIDR="${PRIVATE_SUBNET_CIDR:-10.0.1.0/24}"

VPC_NAME="${VPC_NAME:-vpc-atividade-devops}"
PUBLIC_SUBNET_NAME="${PUBLIC_SUBNET_NAME:-subnet-publica-atividade}"
PRIVATE_SUBNET_NAME="${PRIVATE_SUBNET_NAME:-subnet-privada-atividade}"
IGW_NAME="${IGW_NAME:-igw-atividade-devops}"
NAT_NAME="${NAT_NAME:-nat-atividade-devops}"
PUBLIC_RT_NAME="${PUBLIC_RT_NAME:-rt-publica-atividade}"
PRIVATE_RT_NAME="${PRIVATE_RT_NAME:-rt-privada-atividade}"
SG_NAME="${SG_NAME:-atividade-devops-sg}"

INSTANCE_TYPE="${INSTANCE_TYPE:-t2.micro}"
AMI_ID="${AMI_ID:-ami-0360c520857e3138f}"
KEY_NAME="${KEY_NAME:-vockey}"

PUBLIC_INSTANCE_NAME="${PUBLIC_INSTANCE_NAME:-ec2-publica-atividade}"
PRIVATE_INSTANCE_NAME="${PRIVATE_INSTANCE_NAME:-ec2-privada-atividade}"

ROOT_VOLUME_SIZE_GB="${ROOT_VOLUME_SIZE_GB:-8}"

echo "Região: $REGION"
echo "Zona de disponibilidade: $AZ"
echo "VPC CIDR: $VPC_CIDR"

# ==========================================
# 1) Criar VPC
# ==========================================
VPC_ID=$(aws ec2 create-vpc \
	--cidr-block "$VPC_CIDR" \
	--query 'Vpc.VpcId' \
	--output text \
	--region "$REGION")

aws ec2 create-tags \
	--resources "$VPC_ID" \
	--tags Key=Name,Value="$VPC_NAME" \
	--region "$REGION"

aws ec2 modify-vpc-attribute \
	--vpc-id "$VPC_ID" \
	--enable-dns-support \
	--region "$REGION"

aws ec2 modify-vpc-attribute \
	--vpc-id "$VPC_ID" \
	--enable-dns-hostnames \
	--region "$REGION"

echo "VPC criada: $VPC_ID"

# ==========================================
# 2) Criar subnets pública e privada
# ==========================================
PUBLIC_SUBNET_ID=$(aws ec2 create-subnet \
	--vpc-id "$VPC_ID" \
	--cidr-block "$PUBLIC_SUBNET_CIDR" \
	--availability-zone "$AZ" \
	--query 'Subnet.SubnetId' \
	--output text \
	--region "$REGION")

PRIVATE_SUBNET_ID=$(aws ec2 create-subnet \
	--vpc-id "$VPC_ID" \
	--cidr-block "$PRIVATE_SUBNET_CIDR" \
	--availability-zone "$AZ" \
	--query 'Subnet.SubnetId' \
	--output text \
	--region "$REGION")

aws ec2 create-tags \
	--resources "$PUBLIC_SUBNET_ID" \
	--tags Key=Name,Value="$PUBLIC_SUBNET_NAME" \
	--region "$REGION"

aws ec2 create-tags \
	--resources "$PRIVATE_SUBNET_ID" \
	--tags Key=Name,Value="$PRIVATE_SUBNET_NAME" \
	--region "$REGION"

aws ec2 modify-subnet-attribute \
	--subnet-id "$PUBLIC_SUBNET_ID" \
	--map-public-ip-on-launch \
	--region "$REGION"

echo "Subnet pública: $PUBLIC_SUBNET_ID ($PUBLIC_SUBNET_CIDR)"
echo "Subnet privada: $PRIVATE_SUBNET_ID ($PRIVATE_SUBNET_CIDR)"

# ==========================================
# 3) Criar e anexar Internet Gateway
# ==========================================
IGW_ID=$(aws ec2 create-internet-gateway \
	--query 'InternetGateway.InternetGatewayId' \
	--output text \
	--region "$REGION")

aws ec2 create-tags \
	--resources "$IGW_ID" \
	--tags Key=Name,Value="$IGW_NAME" \
	--region "$REGION"

aws ec2 attach-internet-gateway \
	--internet-gateway-id "$IGW_ID" \
	--vpc-id "$VPC_ID" \
	--region "$REGION"

echo "Internet Gateway criado e anexado: $IGW_ID"

# ==========================================
# 4) Criar NAT Gateway na subnet pública
# ==========================================
EIP_ALLOC_ID=$(aws ec2 allocate-address \
	--domain vpc \
	--query 'AllocationId' \
	--output text \
	--region "$REGION")

NAT_GW_ID=$(aws ec2 create-nat-gateway \
	--subnet-id "$PUBLIC_SUBNET_ID" \
	--allocation-id "$EIP_ALLOC_ID" \
	--tag-specifications "ResourceType=natgateway,Tags=[{Key=Name,Value=$NAT_NAME}]" \
	--query 'NatGateway.NatGatewayId' \
	--output text \
	--region "$REGION")

echo "NAT Gateway em criação: $NAT_GW_ID"
echo "Aguardando NAT Gateway ficar disponível..."
aws ec2 wait nat-gateway-available \
	--nat-gateway-ids "$NAT_GW_ID" \
	--region "$REGION"

echo "NAT Gateway disponível: $NAT_GW_ID"

# ==========================================
# 5) Criar tabelas de roteamento e associações
# ==========================================
PUBLIC_RT_ID=$(aws ec2 create-route-table \
	--vpc-id "$VPC_ID" \
	--query 'RouteTable.RouteTableId' \
	--output text \
	--region "$REGION")

PRIVATE_RT_ID=$(aws ec2 create-route-table \
	--vpc-id "$VPC_ID" \
	--query 'RouteTable.RouteTableId' \
	--output text \
	--region "$REGION")

aws ec2 create-tags \
	--resources "$PUBLIC_RT_ID" \
	--tags Key=Name,Value="$PUBLIC_RT_NAME" \
	--region "$REGION"

aws ec2 create-tags \
	--resources "$PRIVATE_RT_ID" \
	--tags Key=Name,Value="$PRIVATE_RT_NAME" \
	--region "$REGION"

aws ec2 create-route \
	--route-table-id "$PUBLIC_RT_ID" \
	--destination-cidr-block 0.0.0.0/0 \
	--gateway-id "$IGW_ID" \
	--region "$REGION"

aws ec2 create-route \
	--route-table-id "$PRIVATE_RT_ID" \
	--destination-cidr-block 0.0.0.0/0 \
	--nat-gateway-id "$NAT_GW_ID" \
	--region "$REGION"

aws ec2 associate-route-table \
	--subnet-id "$PUBLIC_SUBNET_ID" \
	--route-table-id "$PUBLIC_RT_ID" \
	--region "$REGION" >/dev/null

aws ec2 associate-route-table \
	--subnet-id "$PRIVATE_SUBNET_ID" \
	--route-table-id "$PRIVATE_RT_ID" \
	--region "$REGION" >/dev/null

echo "Tabelas de roteamento configuradas."

# ==========================================
# 6) Security Group
# ==========================================
SG_ID=$(aws ec2 create-security-group \
	--group-name "$SG_NAME" \
	--description "Security Group atividade DevOps" \
	--vpc-id "$VPC_ID" \
	--query 'GroupId' \
	--output text \
	--region "$REGION")

aws ec2 authorize-security-group-ingress \
	--group-id "$SG_ID" \
	--protocol tcp \
	--port 22 \
	--cidr 0.0.0.0/0 \
	--region "$REGION"

aws ec2 authorize-security-group-ingress \
	--group-id "$SG_ID" \
	--protocol tcp \
	--port 80 \
	--cidr 0.0.0.0/0 \
	--region "$REGION"

echo "Security Group criado: $SG_ID"

# ==========================================
# 7) Criar 2 instâncias EC2
# ==========================================
PUBLIC_INSTANCE_ID=$(aws ec2 run-instances \
	--image-id "$AMI_ID" \
	--count 1 \
	--instance-type "$INSTANCE_TYPE" \
	--key-name "$KEY_NAME" \
	--security-group-ids "$SG_ID" \
	--subnet-id "$PUBLIC_SUBNET_ID" \
	--associate-public-ip-address \
	--block-device-mappings "[{\"DeviceName\":\"/dev/sda1\",\"Ebs\":{\"VolumeSize\":$ROOT_VOLUME_SIZE_GB,\"VolumeType\":\"gp3\",\"DeleteOnTermination\":true}}]" \
	--tag-specifications "ResourceType=instance,Tags=[{Key=Name,Value=$PUBLIC_INSTANCE_NAME}]" \
	--query 'Instances[0].InstanceId' \
	--output text \
	--region "$REGION")

PRIVATE_INSTANCE_ID=$(aws ec2 run-instances \
	--image-id "$AMI_ID" \
	--count 1 \
	--instance-type "$INSTANCE_TYPE" \
	--key-name "$KEY_NAME" \
	--security-group-ids "$SG_ID" \
	--subnet-id "$PRIVATE_SUBNET_ID" \
	--block-device-mappings "[{\"DeviceName\":\"/dev/sda1\",\"Ebs\":{\"VolumeSize\":$ROOT_VOLUME_SIZE_GB,\"VolumeType\":\"gp3\",\"DeleteOnTermination\":true}}]" \
	--tag-specifications "ResourceType=instance,Tags=[{Key=Name,Value=$PRIVATE_INSTANCE_NAME}]" \
	--query 'Instances[0].InstanceId' \
	--output text \
	--region "$REGION")

echo "Instância pública criada: $PUBLIC_INSTANCE_ID"
echo "Instância privada criada: $PRIVATE_INSTANCE_ID"

echo "Aguardando instâncias entrarem em running..."
aws ec2 wait instance-running --instance-ids "$PUBLIC_INSTANCE_ID" "$PRIVATE_INSTANCE_ID" --region "$REGION"

PUBLIC_IP=$(aws ec2 describe-instances \
	--instance-ids "$PUBLIC_INSTANCE_ID" \
	--query 'Reservations[0].Instances[0].PublicIpAddress' \
	--output text \
	--region "$REGION")

PRIVATE_IP=$(aws ec2 describe-instances \
	--instance-ids "$PRIVATE_INSTANCE_ID" \
	--query 'Reservations[0].Instances[0].PrivateIpAddress' \
	--output text \
	--region "$REGION")

echo ""
echo "Provisionamento finalizado com sucesso!"
echo "Resumo:"
echo "- VPC: $VPC_ID"
echo "- Subnet pública: $PUBLIC_SUBNET_ID"
echo "- Subnet privada: $PRIVATE_SUBNET_ID"
echo "- Internet Gateway: $IGW_ID"
echo "- NAT Gateway: $NAT_GW_ID"
echo "- Route Table pública: $PUBLIC_RT_ID"
echo "- Route Table privada: $PRIVATE_RT_ID"
echo "- Instância pública: $PUBLIC_INSTANCE_ID (IP público: $PUBLIC_IP)"
echo "- Instância privada: $PRIVATE_INSTANCE_ID (IP privado: $PRIVATE_IP)"

