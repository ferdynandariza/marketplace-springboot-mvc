#!/bin/bash

# Start SQL Server in background
/opt/mssql/bin/sqlservr &

# Wait until SQL Server is ready
echo "Waiting for SQL Server to start..."
sleep 20

# Run all .sql files
for f in /init-sql/*.sql
do
  echo "Running $f..."
  /opt/mssql-tools/bin/sqlcmd -S db,${DB_PORT} -U ${DB_USER} -P ${DB_PASS} -d master -i $f;
done

wait
