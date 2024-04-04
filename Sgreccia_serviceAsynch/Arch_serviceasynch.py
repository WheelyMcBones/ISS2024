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
with Diagram('serviceasynchArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx', graph_attr=nodeattr):
          provider=Custom('provider','./qakicons/symActorSmall.png')
          client=Custom('client','./qakicons/symActorSmall.png')
          calculator=Custom('calculator','./qakicons/symActorDynamic.png')
     client >> Edge(color='magenta', style='solid', decorate='true', label='<calculate &nbsp; >',  fontcolor='magenta') >> provider
     provider >> Edge(color='magenta', style='dotted', decorate='true', label='<currentMsg &nbsp; >',  fontcolor='black') >> calculator
diag
