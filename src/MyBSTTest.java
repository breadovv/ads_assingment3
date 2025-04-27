import java.util.Random;

public class MyBSTTest {
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();
        Random random = new Random();

        System.out.println("Inserting random elements into BST:");

        for (int i = 0; i < 10; i++) {
            int key = random.nextInt(100);
            tree.put(key, "Value" + key);
            System.out.println("Inserted key = " + key + ", value = Value" + key);
        }

        System.out.println("\nTraversing tree in-order:");

        for (var node : tree) {
            System.out.println("key is " + node.getKey() + " and value is " + node.getValue());
        }

        System.out.println("\nTotal size of tree: " + tree.size());
    }
}
