# signal-flow-graph
Signal Flow Graph Solver is a program that provides signal flow graph representation of different control systems and calculates the overall transfer function through an interactive user interface.
## Main Features 
- Given the total number of nodes in the graph, with all connecting branches and gains,this program can fully analyse the system by representing all given information as a “Signal-Flow-Graph”.  
- Complete analysis of the system includes the following procedures:  
  1. Detecting all forward paths, with the corresponding gains.  
  2. Detecting all loops, with the corresponding gains.  
  3. Detecting and Grouping all non-touching loops, i.e; (single loops, each 2 non-touching loops, each 3 non-touching loops, ...etc).  
  4. Detecting and Grouping all non-touching loops with all forward paths.  
  5. Calculating 𝜟.  
  6. Calculating 𝜟i, where i = 1, 2, ...m, as mentioned above.   
  7. Calculating the overall system transfer function.  

## User Guide  
- First, you need to enter total number of nodes in the SFG.  
- To add any node to your SFG, enter its name in the box labeled “Add New Node”,then click the “Add” button.  
- To add any edge (branch) to your SFG, you need to enter 3 information:  
  1. The node where it comes from, in the box labeled “Add Edge From”.  
  2. The node it goes to, in the box labeled “To”.  
  3. The gain of that branch, in the box labeled “Gain”. Then click the “Add” button.  
- Determine both source and sink nodes in the boxes labeled “Source”, “Sink” correspondingly.  
- To solve the SFG and analyse the system, click the “Solve” button.  

## Credits:
- Graphstream library for drawing graphs (http://graphstream-project.org/).
