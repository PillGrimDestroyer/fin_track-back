#!/usr/bin/env bash
set -e

#
#  ВНИМАНИЕ!  Этот файл запускается только тогда,
#             когда папка volumes отсутствует при запуске docker-compose up
#

psql -v ON_ERROR_STOP=1 --username postgres --dbname postgres <<-EOSQL

    ALTER ROLE postgres WITH ENCRYPTED PASSWORD '111';

    CREATE USER rise_security WITH ENCRYPTED PASSWORD '111';
    CREATE DATABASE rise_security WITH OWNER rise_security;
    GRANT ALL ON DATABASE rise_security TO rise_security;

EOSQL
