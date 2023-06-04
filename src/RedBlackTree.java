public class RedBlackTree {
    private class Node{
        private int value;
        private Color color;
        private Node leftChild;
        private Node rigthChild;

        @Override
        public String toString() {
            return "Node{"+
                    "value="+ value +
                    ", color" + color +
                    "}";
        }
    }
    private enum Color{
        RED,BLACK;
    }
    private Node root;

    public boolean add (int value){
        if (root != null){
            boolean result = addNode(root,value);
            root = rebalnce(root);
            root.color=Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color=Color.BLACK;
            root.value=value;
            return true;
        }
    }



    private boolean addNode (Node node, int value){
        if (node.value==value){
            return false;
        } else {
            if (node.value>value){
                if (node.leftChild !=null){
                    boolean result = addNode(node.leftChild,value);
                    rebalnce(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else{
                    if (node.rigthChild != null) {
                        boolean result = addNode(node.rigthChild, value);
                        node.leftChild = rebalnce(node.rigthChild);
                        return result;
                    } else {
                        node.rigthChild = new Node();
                        node.rigthChild.color = Color.RED;
                        node.rigthChild.value = value;
                        return true;
                    }
            }
        }
    }
    private Node rebalnce (Node node){
        Node result =node;
        boolean needBalance;
        do {
            needBalance = false;
            if (result.rigthChild !=null && result.rigthChild.color==Color.RED &&
                    (result.leftChild ==null || result.leftChild.color == Color.BLACK)){
                needBalance =true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color==Color.RED&&
                result.leftChild.leftChild !=null && result.leftChild.leftChild.color == Color.RED){
                needBalance =true;
                result = leftSwap(result);
            }
            if (result.leftChild !=null && result.leftChild.color == Color.RED &&
                result.rigthChild !=null && result.rigthChild.color == Color.RED){
                needBalance =true;
                colorSwap(result);
            }
        } while (needBalance);
        return result;
    }
    private Node rightSwap (Node node){
        Node rightChild =node.rigthChild;
        Node betweenChild= rightChild.leftChild;
        rightChild.leftChild=node;
        node.rigthChild=betweenChild;
        rightChild.color=Color.RED;
        return rightChild;
    }
    private Node leftSwap (Node node){
        Node leftChild = node.leftChild;
        Node betweenChild=leftChild.rigthChild;
        leftChild.rigthChild =node;
        node.leftChild =betweenChild;
        leftChild.color=node.color;
        node.color= Color.RED;
        return leftChild;
    }
    private void colorSwap (Node node){
        node.rigthChild.color=Color.BLACK;
        node.leftChild.color=Color.BLACK;
        node.color=Color.RED;

    }
}

