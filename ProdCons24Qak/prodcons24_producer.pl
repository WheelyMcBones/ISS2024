%====================================================================================
% prodcons24_producer description   
%====================================================================================
dispatch( distance, distance(D) ).
request( distance, distance(D) ).
reply( distanceack, ack(D) ).  %%for distance
dispatch( short, short(V) ).
%====================================================================================
context(ctxproducer, "localhost",  "TCP", "8014").
context(ctxconsumer, "130.136.113.239",  "TCP", "8015").
 qactor( consumer, ctxconsumer, "external").
  qactor( producer1, ctxproducer, "it.unibo.producer1.Producer1").
 static(producer1).
  qactor( producer2, ctxproducer, "it.unibo.producer2.Producer2").
 static(producer2).
  qactor( otherobserver, ctxproducer, "it.unibo.otherobserver.Otherobserver").
 static(otherobserver).
