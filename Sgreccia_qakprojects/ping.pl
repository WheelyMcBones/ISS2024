%====================================================================================
% ping description   
%====================================================================================
dispatch( shot, shot(D) ).
request( gamestart, gamestart(D) ).
reply( gamestartack, ack(D) ).  %%for gamestart
dispatch( short, short(V) ).
%====================================================================================
context(ctxping, "localhost",  "TCP", "8014").
context(ctxpong, "127.0.0.1",  "TCP", "8015").
 qactor( pong, ctxpong, "external").
  qactor( ping, ctxping, "it.unibo.ping.Ping").
 static(ping).
  qactor( referee, ctxping, "it.unibo.referee.Referee").
 static(referee).
