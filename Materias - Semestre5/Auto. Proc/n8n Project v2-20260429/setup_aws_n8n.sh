#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./setup_aws_n8n.sh <domain-or-public-ip>
# Example:
#   ./setup_aws_n8n.sh 18.220.10.15

if [[ "${EUID}" -eq 0 ]]; then
  echo "Run this script as a regular user with sudo privileges (not as root)."
  exit 1
fi

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV_FILE="${PROJECT_DIR}/.env"
COMPOSE_FILE="${PROJECT_DIR}/docker-compose.yml"
DOMAIN_OR_IP="${1:-}"

if [[ -z "${DOMAIN_OR_IP}" ]]; then
  echo "Usage: $0 <domain-or-public-ip>"
  echo "Example: $0 18.220.10.15"
  exit 1
fi

if [[ ! -f "${COMPOSE_FILE}" ]]; then
  echo "docker-compose.yml not found in ${PROJECT_DIR}"
  exit 1
fi

if [[ ! -f "${ENV_FILE}" ]]; then
  cat > "${ENV_FILE}" <<'EOF'
DOMAIN_NAME=localhost
N8N_PROTOCOL=http
N8N_PORT=5678
GENERIC_TIMEZONE=America/Sao_Paulo
EOF
fi

echo "[1/6] Installing Docker prerequisites..."
sudo apt-get update -y
sudo apt-get install -y ca-certificates curl gnupg

echo "[2/6] Configuring Docker repository..."
sudo install -m 0755 -d /etc/apt/keyrings
if [[ ! -f /etc/apt/keyrings/docker.asc ]]; then
  sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
fi
sudo chmod a+r /etc/apt/keyrings/docker.asc

ARCH="$(dpkg --print-architecture)"
CODENAME="$(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}")"

sudo tee /etc/apt/sources.list.d/docker.list >/dev/null <<EOF
deb [arch=${ARCH} signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu ${CODENAME} stable
EOF

echo "[3/6] Installing Docker engine + compose plugin..."
sudo apt-get update -y
sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

if ! getent group docker >/dev/null; then
  sudo groupadd docker
fi
sudo usermod -aG docker "$USER" || true

sudo systemctl enable docker
sudo systemctl start docker

echo "[4/6] Updating .env with instance address..."
if grep -q '^DOMAIN_NAME=' "${ENV_FILE}"; then
  sed -i.bak "s/^DOMAIN_NAME=.*/DOMAIN_NAME=${DOMAIN_OR_IP}/" "${ENV_FILE}"
else
  printf '\nDOMAIN_NAME=%s\n' "${DOMAIN_OR_IP}" >> "${ENV_FILE}"
fi

if ! grep -q '^N8N_PROTOCOL=' "${ENV_FILE}"; then
  printf 'N8N_PROTOCOL=http\n' >> "${ENV_FILE}"
fi
if ! grep -q '^N8N_PORT=' "${ENV_FILE}"; then
  printf 'N8N_PORT=5678\n' >> "${ENV_FILE}"
fi
if ! grep -q '^GENERIC_TIMEZONE=' "${ENV_FILE}"; then
  printf 'GENERIC_TIMEZONE=America/Sao_Paulo\n' >> "${ENV_FILE}"
fi

echo "[5/6] Starting containers..."
cd "${PROJECT_DIR}"
sudo docker compose pull
sudo docker compose up -d

echo "[6/6] Showing container status..."
sudo docker compose ps

echo

echo "Setup complete. Access URLs:"
echo "- n8n:    http://${DOMAIN_OR_IP}"
echo "- pgAdmin: http://${DOMAIN_OR_IP}:8080"
echo
echo "n8n login: user / password"
echo "pgAdmin login: admin@admin.com / admin"
echo
echo "If Docker group permission is not active yet, log out and log in again before running docker commands without sudo."
