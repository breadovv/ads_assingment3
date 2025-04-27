import java.util.Random;

public class MyTestingClass {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public String toString() {
        return name + "-" + id;
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(50); // 50 buckets

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            String randomName = "Name" + random.nextInt(10000);
            int randomId = random.nextInt(10000);
            MyTestingClass key = new MyTestingClass(randomName, randomId);
            table.put(key, i);
        }

        System.out.println("Total number of elements: " + table.size());

        for (int i = 0; i < 50; i++) {
            System.out.println("Bucket " + i + " size: " + table.getBucketSize(i));
        }
    }
}
