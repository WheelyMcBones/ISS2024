### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
with Diagram('pingArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxping', graph_attr=nodeattr):
          ping=Custom('ping','./qakicons/symActorSmall.png')
          referee=Custom('referee','./qakicons/symActorSmall.png')
     with Cluster('ctxpong', graph_attr=nodeattr):
          pong=Custom('pong(ext)','./qakicons/externalQActor.png')
     ping >> Edge(color='magenta', style='solid', decorate='true', label='<gamestart<font color="darkgreen"> gamestartack</font> &nbsp; >',  fontcolor='magenta') >> pong
     ping >> Edge(color='blue', style='solid',  decorate='true', label='<shot &nbsp; >',  fontcolor='blue') >> pong
     ping >> Edge(color='blue', style='solid',  decorate='true', label='<shot &nbsp; >',  fontcolor='blue') >> ping
     referee >> Edge(color='blue', style='solid',  decorate='true', label='<short &nbsp; >',  fontcolor='blue') >> ping
     ping >> Edge(color='blue', style='solid',  decorate='true', label='<short &nbsp; >',  fontcolor='blue') >> referee
diag
