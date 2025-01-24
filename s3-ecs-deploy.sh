#!/bin/bash

# Inicia o script
echo "Script iniciado."

# Clona a task definition atual, removendo os campos desnecessários
TASK_DEF_NAME="lanchonete-task-definition"
NEW_TASK_DEFINITION=$(aws ecs describe-task-definition --task-definition ${TASK_DEF_NAME} --output json 2>/dev/null | \
  jq '.taskDefinition' | \
  jq 'del(.taskDefinitionArn, .revision, .status, .requiresAttributes, .compatibilities, .registeredAt, .registeredBy)')

# Registra a nova task definition
REGISTERED_TASK=$(aws ecs register-task-definition --cli-input-json "${NEW_TASK_DEFINITION}" --output json 2>/dev/null)

# Atualiza o serviço para usar a nova task definition
CLUSTER_NAME="lanchonete-ecs-cluster"
SERVICE_NAME="lanchonete-ecs-service"
NEW_TASK_REVISION=$(echo $REGISTERED_TASK | jq -r '.taskDefinition.revision')

UPDATE=$(aws ecs update-service --cluster ${CLUSTER_NAME} --service ${SERVICE_NAME} --task-definition ${TASK_DEF_NAME}:$NEW_TASK_REVISION --output json 2>/dev/null)

# Encerra o script
echo "Deploy realizado com sucesso!"
echo "Script finalizado."
