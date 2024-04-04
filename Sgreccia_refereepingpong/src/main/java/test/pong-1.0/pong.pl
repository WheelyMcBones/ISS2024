%====================================================================================
% pong description   
%====================================================================================
dispatch( ball, ball(D) ).
event( miss, miss(D) ).
event( startgame, start(V) ).
event( endgame, end(V) ).
%====================================================================================
context(ctxpong, "localhost",  "TCP", "8015").
context(ctxping, "127.0.0.1",  "TCP", "8014").
context(ctxreferee, "127.0.0.1",  "TCP", "8016").
 qactor( ping, ctxping, "external").
  qactor( referee, ctxreferee, "external").
  qactor( pong, ctxpong, "it.unibo.pong.Pong").
 static(pong).
