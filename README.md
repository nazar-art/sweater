# Sweater - analog to Twitter based on Spring Boot

## Instructions
### DB

create `sweater` DB at Postgres with

    sudo -u postgres psql
    
Also, update credentials to DB at `application.yml`.    

---

You need to set Gmail account info for Application:

    gmail-account-name
    gmail-account-pass
    
Allow access to less safe application for this account as well:

    https://myaccount.google.com/lesssecureapps
    
Also, temp mail could be used:

    https://temp-mail.org/    
    
---

[Recaptcha link](https://www.google.com/recaptcha/admin/site/432757547#list)    