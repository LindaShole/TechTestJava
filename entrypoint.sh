#!/bin/sh

export TASK_ID=$(curl --silent "${ECS_CONTAINER_METADATA_URI_V4}/task" | jq -r ".TaskARN" | cut -d "/" -f 3)
echo "Found task ID value {$TASK_ID}"

if [ ! -z "$1" ]
then
  echo "Executing $@"
  exec "$@"
else
  echo "No command set. Running default 'npm run start:prod'"
  npm run start:prod
fi
