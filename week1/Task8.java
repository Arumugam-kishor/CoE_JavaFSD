package week1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTreeSerializer {
    
    public String serialize(TreeNode root) {
        if (root == null) return "#,"; 
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals("#")) return null;  
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(nodes);  
        node.right = buildTree(nodes); 
        return node;
    }
}

public class Task8 {
    public static void main(String[] args) {
        BinaryTreeSerializer serializer = new BinaryTreeSerializer();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serializedData = serializer.serialize(root);
        System.out.println("Serialized Tree: " + serializedData);

        TreeNode deserializedRoot = serializer.deserialize(serializedData);
        System.out.println("Tree Deserialized Successfully");
    }
}

