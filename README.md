# Sweater - analog to Twitter based on Spring Boot

## Main info
### Branches

- master - configured for Windows
- development - configured for Linux Mint

## Instructions
### DB

create `sweater` DB at Postgres with

    sudo -u postgres psql
    
Also, update credentials to DB at `application.yml`.    

---

You need to set Gmail account info for Application & url to created recaptcha (see additional links):

    gmail-account-name
    gmail-account-pass
    recaptcha-secret
    
Set program parameter for working with `dev` profile:

    --spring.profiles.active=dev    
    
Allow access to less safe application for this account as well:

    https://myaccount.google.com/lesssecureapps
    
Also, temp mail could be used to verify account activation for users:

    https://temp-mail.org/    
    
---
#### Additional links:

[Recaptcha link](https://www.google.com/recaptcha/admin/site/432757547#list)    