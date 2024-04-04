%====================================================================================
% referee description   
%====================================================================================
dispatch( ball, ball(D) ).
dispatch( ballview, ballview(V) ).
event( miss, miss(N) ).
event( startgame, start(V) ).
event( endgame, end(V) ).
%====================================================================================
context(ctxpong, "127.0.0.1",  "TCP", "8015").
context(ctxping, "127.0.0.1",  "TCP", "8014").
context(ctxreferee, "localhost",  "TCP", "8016").
 qactor( ping, ctxping, "external").
  qactor( pong, ctxpong, "external").
  qactor( referee, ctxreferee, "it.unibo.referee.Referee").
 static(referee).
