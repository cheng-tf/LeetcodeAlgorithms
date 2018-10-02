package LinkedListAlgorithms;

import org.junit.Test;

public class JavaMethodParamTransfer_Value {
    /**
     * 探讨Java的值传递问题
     */

    /**
     * 1. 基本数据类型的值传递
     */
    public void add(int a) {
        a = a + 1;
    }

    @Test
    public void test1() {
        int a = 2;
        add(2);
        System.out.println(a);//2
    }

    /**
     * 2. Object引用传递
     */
    public void modifyObject(Object obj) {
        obj = new Object();//IDEA默认提示，obj永远不会赋值为new Object()
    }

    @Test
    public void test2() {
        Object obj1 = new Object();
        Object obj2 = obj1;
        System.out.println(obj1 == obj2);//true指向一个对象
        modifyObject(obj1);//试图让obj1指向新的Object，失败
        System.out.println(obj1 == obj2);//true
        obj1 = new Object();
        System.out.println(obj1 == obj2);//false
    }

    /**
     * 3、 自定义的User对象引用
     */
    public class User {
        String name;
        String sex;

        public User(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name +
                    '\'' + ", sex='" + sex + '\'' + '}';
        }
    }

    public void modifyName(User user) {
        user.name = "中国人";
    }

    @Test
    public void test3() {
        User user = new User("陈红佳", "女");
        System.out.println("user = " + user);
        modifyName(user);
        System.out.println("user = " + user);
    }

    /**
     * 4. 测试链表的头节点引用head
     * 当没有改变的head节点的val、next，就不会对head造成影响
     * 不过，一般避免改变head的next属性；
     * 通常ListNode temp = head;都是用来暂时保存head，
     * 以免后续代码需要访问head。
     */
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 打印链表
     */
    public void printLinkedList(ListNode head){
        if(head == null){
            System.out.println("NULL");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while(head != null){
            sb.append(head.val).append("-->");
            head = head.next;
        }
        System.out.println(sb.toString().substring(0,sb.length()-3));
    }

    @Test
    public void test4(){
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printLinkedList(head);
        printLinkedList(head);
    }
}
