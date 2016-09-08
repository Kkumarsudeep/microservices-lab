#!/bin/bash
for (( i=1; i <= 10000; i++ ))
do
 echo "";
 curl http://localhost:8082/service
done