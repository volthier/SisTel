#!/bin/bash
set -e

POSTGRES="psql --username postgres"

echo "Creating database role: postgres"

$POSTGRES <<EOSQL
CREATE DATABASE dbditel;
\connect dbditel;
CREATE SCHEMA IF NOT EXISTS dbditel AUTHORIZATION postgres;
EOSQL