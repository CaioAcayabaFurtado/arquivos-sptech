#!/bin/bash
set -e

# ==========================================
# Script de provisionamento EC2 - IaC
# ==========================================

echo "Iniciando provisionamento da infraestrutura..."

REGION="us-east-1"
KEY_NAME="minhachave"
SG_NAME="launch-wizard-42"
INSTANCE_NAME="ec2-iac-atividade"
AMI_ID="ami-0360c520857e3138f"
INSTANCE_TYPE="t3.small"

# ==========================================
# 1 - Obter VPC padrão
# ==========================================
VPC_ID=$(aws ec2 describe-vpcs \
    --filters Name=isDefault,Values=true \
    --query "Vpcs[0].VpcId" \
    --output text \
    --region $REGION)

echo "VPC padrão: $VPC_ID"

# ==========================================
# 2 - Obter Subnet padrão
# ==========================================
SUBNET_ID=$(aws ec2 describe-subnets \
    --filters Name=default-for-az,Values=true \
    --query "Subnets[0].SubnetId" \
    --output text \
    --region $REGION)

echo "Subnet padrão: $SUBNET_ID"

# ==========================================
# 3 - Criar par de chaves
# ==========================================
aws ec2 create-key-pair \
    --key-name $KEY_NAME \
    --region $REGION \
    --query 'KeyMaterial' \
    --output text > $KEY_NAME.pem

chmod 400 $KEY_NAME.pem

echo "Chave criada: $KEY_NAME.pem"

# ==========================================
# 4 - Criar Security Group
# ==========================================
SG_ID=$(aws ec2 create-security-group \
    --group-name $SG_NAME \
    --description "Security Group atividade IaC" \
    --vpc-id $VPC_ID \
    --tag-specifications "ResourceType=security-group,Tags=[{Key=Name,Value=$SG_NAME}]" \
    --query 'GroupId' \
    --output text \
    --region $REGION)

echo "Security Group criado: $SG_ID"

# ==========================================
# 5 - Liberar porta 22 (SSH)
# ==========================================
aws ec2 authorize-security-group-ingress \
    --group-id $SG_ID \
    --protocol tcp \
    --port 22 \
    --cidr 0.0.0.0/0 \
    --region $REGION

# ==========================================
# 6 - Liberar porta 80 (HTTP)
# ==========================================
aws ec2 authorize-security-group-ingress \
    --group-id $SG_ID \
    --protocol tcp \
    --port 80 \
    --cidr 0.0.0.0/0 \
    --region $REGION

echo "Regras de segurança configuradas."

# ==========================================
# 7 - Criar Instância EC2
# ==========================================
aws ec2 run-instances \
    --image-id $AMI_ID \
    --count 1 \
    --instance-type $INSTANCE_TYPE \
    --key-name $KEY_NAME \
    --security-group-ids $SG_ID \
    --subnet-id $SUBNET_ID \
    --block-device-mappings '[
        {
          "DeviceName": "/dev/sda1",
          "Ebs": {
            "VolumeSize": 20,
            "VolumeType": "gp3",
            "DeleteOnTermination": true
          }
        }
    ]' \
    --tag-specifications "ResourceType=instance,Tags=[{Key=Name,Value=$INSTANCE_NAME}]" \
    --region $REGION

echo "Instância EC2 criada com sucesso."
echo "Provisionamento finalizado."