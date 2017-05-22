### HDFS Like File-System, With Java RMI:
this is part of a homework that aims to explain HDFS File-System.
The Workflow is :
1. The Name Node receives a file.
2. The Name Node cuts the file to N blocks of the same length.
3. The Name Node distributes the blocks to the Data Nodes according to the Round [Robin Algorithm.](https://en.wikipedia.org/wiki/Round-robin_scheduling)

## Execution:
First run the Data Nodes (src/dataNodes/DataNodes.java)
Then run (src/nameNode/NameNode.java)
