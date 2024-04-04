#!/bin/bash
cd "$PWD/referee-1.0/bin/" ;
./referee &

cd - ; "$PWD/ping-1.0/bin/" ;
./ping &

cd - ; "$PWD/pong-1.0/bin/" ;
./pong &

