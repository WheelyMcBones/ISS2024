%====================================================================================
% synchservice description   
%====================================================================================
request( calculate, calculate(N) ).
%====================================================================================
context(ctx, "localhost",  "TCP", "8015").
 qactor( provider, ctx, "it.unibo.provider.Provider").
 static(provider).
  qactor( client, ctx, "it.unibo.client.Client").
 static(client).
