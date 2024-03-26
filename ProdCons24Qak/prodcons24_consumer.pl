%====================================================================================
% prodcons24_consumer description   
%====================================================================================
dispatch( distance, distance(D) ).
request( distance, distance(D) ).
reply( distanceack, ack(D) ).  %%for distance
dispatch( info, info(SOURCE,TERM) ).
dispatch( short, short(V) ).
%====================================================================================
context(ctxconsumer, "localhost",  "TCP", "8015").
 qactor( consumer, ctxconsumer, "it.unibo.consumer.Consumer").
 static(consumer).
  qactor( consumerobserver, ctxconsumer, "it.unibo.consumerobserver.Consumerobserver").
 static(consumerobserver).
