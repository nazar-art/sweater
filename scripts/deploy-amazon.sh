#!/usr/bin/env bash

USER='ec2-user'
HOSTNAME='ec2-18-194-243-47.eu-central-1.compute.amazonaws.com'

echo 'Build jar:'
mvn clean package

echo 'Copy files:'
scp -i ~/.ssh/sweater-frankfurt.pem \
    target/sweater-1.0.jar \
    USER@HOSTNAME:/home/ec2-user/

echo 'Restart server:'
ssh -i ~/.ssh/sweater-frankfurt.pem USER@HOSTNAME << EOF
pgrep java | xargs kill -9
nohup java -jar sweater-1.0.jar > log.txt &
EOF

echo 'Bye'