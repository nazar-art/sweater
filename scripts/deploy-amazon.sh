#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/sweater-frankfurt.pem \
    target/sweater-1.0.jar \
    ec2-user@ec2-18-194-243-47.eu-central-1.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i ~/.ssh/sweater-frankfurt.pem ec2-user@ec2-18-194-243-47.eu-central-1.compute.amazonaws.com << EOF
pgrep java | xargs kill -9
nohup java -jar sweater-1.0.jar > log.txt &
EOF

echo 'Bye'