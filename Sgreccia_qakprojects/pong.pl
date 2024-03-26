%====================================================================================
% pong description   
%====================================================================================
dispatch( shot, shot(D) ).
request( gamestart, gamestart(D) ).
reply( gamestartack, ack(D) ).  %%for gamestart
dispatch( short, short(V) ).
%====================================================================================
context(ctxpong, "localhost",  "TCP", "8015").
context(ctxping, "127.0.0.1",  "TCP", "8014").
 qactor( ping, ctxping, "external").
  qactor( pong, ctxpong, "it.unibo.pong.Pong").
 static(pong).
  qactor( refereepong, ctxpong, "it.unibo.refereepong.Refereepong").
 static(refereepong).
