import binary_tree.BinaryTree;
import binary_tree.Node;
import double_linked_list.DoubleLinkedList;
import single_linked_list.ListItem;
import single_linked_list.SingleLinkedList;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void main(String[] args) {
        System.out.println("====== SingleList =======");
        SingleLinkedList list = new SingleLinkedList();
        list.push(new ListItem("Fourth"));
        list.push(new ListItem("Third"));
        list.push(new ListItem("Second"));
        list.push(new ListItem("First"));
        list.print();
        list.removeLast();
        list.print();

        System.out.println("====== DoubleList =======");
        DoubleLinkedList doubleList = new DoubleLinkedList();
        doubleList.addToTail(new double_linked_list.ListItem("Fifth"));
        doubleList.addToHead(new double_linked_list.ListItem("Fourth"));
        doubleList.addToHead(new double_linked_list.ListItem("Third"));
        doubleList.addToHead(new double_linked_list.ListItem("Second"));
        doubleList.addToHead(new double_linked_list.ListItem("First"));
        doubleList.addToTail(new double_linked_list.ListItem("Sixth"));
        System.out.println("Head: " +
                (doubleList.getHeadElement() == null ? "Null" : doubleList.getHeadElement().getData()) +
                " Tail: " +
                (doubleList.getTailElement() == null ? "Null" : doubleList.getTailElement().getData()));
        doubleList.print();
        System.out.println(doubleList.popTailElement().getData());
        doubleList.print();

        System.out.println("====== BinaryTree =======");
        BinaryTree tree = new BinaryTree();
        tree.addNode("Alex");
        tree.addNode("Aleksey");
        tree.addNode("Kate");
        tree.addNode("Jhone");
        tree.addNode("Aleksey");
        tree.addNode("Pr");
        tree.print(tree.getRoot(), "root");

        List<Node> list1 = tree.searchNodes("Aleksey");
        System.out.print("Find elements: ");
        for (Node n : list1) {
            System.out.print(n.getData() + " ");
        }
    }
}
