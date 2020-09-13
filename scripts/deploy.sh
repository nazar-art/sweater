#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/sweater-1.0.jar \
    dru@192.168.0.107:/home/dru/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa dru@192.168.0.107 << EOF

pgrep java | xargs kill -9
nohup java -jar -Dgmail-account-name=... -Dgmail-account-pass=... -Drecaptcha.secret=...  sweater-1.0.jar > sweater_log.txt &

EOF

echo 'Bye'