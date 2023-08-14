CREATE extension IF NOT EXISTS pgcrypto;

UPDATE usr set password = crypt(password, gen_salt('bf', 8));

