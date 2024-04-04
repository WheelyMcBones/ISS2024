%====================================================================================
% ping description   
%====================================================================================
dispatch( ball, ball(D) ).
event( miss, miss(D) ).
event( startgame, start(V) ).
event( endgame, end(V) ).
%====================================================================================
context(ctxping, "localhost",  "TCP", "8014").
context(ctxpong, "127.0.0.1",  "TCP", "8015").
context(ctxreferee, "127.0.0.1",  "TCP", "8016").
 qactor( pong, ctxpong, "external").
  qactor( referee, ctxreferee, "external").
  qactor( ping, ctxping, "it.unibo.ping.Ping").
 static(ping).
