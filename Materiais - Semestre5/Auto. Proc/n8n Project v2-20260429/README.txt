-- Setup --

1. Para instalar o Docker: install_docker.sh
2. Para configurar o Docker: comandos da parte "Post installation Docker steps"
3. Para provisionar os containers: docker compose up -d
4. Para acessar o n8n: http://IP_instancia
5. Para acessar o PgAdmin http://IP_instancia:8080
6. Configure o projeto com o arquivo "qrcodegen_original.json"
7. Leia o arquivo "n8n-PostgreSQL-PgAdmin.pdf"


-- Post installation docker steps --

sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker
sudo systemctl start docker.service
sudo systemctl enable docker.service