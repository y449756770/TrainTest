package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.data.BinaryTree;

/**
 * 数据结构
 */

public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_binaryTree;
    Button btn_bubbon_sort;
    Button btn_quick_sort;
    int[] nameSort;
    Button btn_linklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btn_binaryTree = (Button) findViewById(R.id.btn_binary_tree);
        btn_binaryTree.setOnClickListener(this);

        btn_bubbon_sort = (Button) findViewById(R.id.btn_bubbon_sort);
        btn_bubbon_sort.setOnClickListener(this);

        btn_quick_sort = (Button) findViewById(R.id.btn_quick_sort);
        btn_quick_sort.setOnClickListener(this);

        btn_linklist = (Button) findViewById(R.id.btn_linklist);
        btn_linklist.setOnClickListener(this);


        nameSort = new int[]{21, 3, 23, 54, 45, 65, 35, 31, 98};


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_binary_tree:
                testBinaryTree();

                break;
            /**
             * 冒泡排序
             */
            case R.id.btn_bubbon_sort:
                bubbonSort(nameSort);

                break;

            /**
             * 快速排序
             */
            case R.id.btn_quick_sort:
                quickSort(0, nameSort.length - 1);

                for (int i = 0; i < nameSort.length; i++) {
                    Log.i("quickSort", nameSort[i] + "");

                }

                break;

            /**
             * 单链表
             */
            case R.id.btn_linklist:
                testLinkList();

                break;


        }

    }

    /**
     * 实现二叉树，用中序遍历
     */

    public void testBinaryTree() {
        BinaryTree bt = new BinaryTree();
        bt.add(3);
        bt.add(2);
        bt.add(14);
        bt.add(5);
        bt.add(6);
        bt.add(54);
        bt.add(43);
        bt.add(46);
        bt.add(12);
        bt.add(15);
        bt.add(13);
        bt.add(7);
        bt.print();
    }

    /**
     * 冒泡排序
     *
     * @param sort
     */

    public void bubbonSort(int[] sort) {
        int temp;
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = 0; j < sort.length - 1 - i; j++) {
                if (sort[j] < sort[j + 1]) {
                    temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;

                }


            }
        }
        for (int i = 0; i < sort.length; i++) {
            Log.i("sort", sort[i] + "");

        }


    }

    /**
     * 快速排序
     */
    public void quickSort(int left, int right) {
        int i, j, t, temp;
        if (left > right) {
            return;
        }
        temp = nameSort[left];
        i = left;
        j = right;

        /**
         * 不相遇
         */
        while (i != j) {
            //顺序很重要，从右往左
            while (nameSort[j] >= temp && i < j)
                j--;
            while (nameSort[i] <= temp && i < j)
                i++;

            if (i < j) { //相遇
                t = nameSort[i];
                nameSort[i] = nameSort[j];
                nameSort[j] = t;
            }
        }


        /**
         * 相遇
         */
        nameSort[left] = nameSort[i];
        nameSort[i] = temp;

        quickSort(left, i - 1);
        quickSort(i + 1, right);


    }

    /**
     * 节点
     */
    class Node {
        public Node next;
        public int data;

        public Node(int data) {
            this.data = data;
        }

        //显示此节点
        public void display() {
            System.out.print(data + " ");
        }

    }


    /**
     * 单链表
     */
    public class LinkList {
        public Node first; // 定义一个头结点
        private int pos = 0;// 节点的位置

        public LinkList() {
            this.first = null;
        }

        // 插入一个头节点
        public void addFirstNode(int data) {
            Node node = new Node(data);
            node.next = first;
            first = node;
        }

        // 删除一个头结点,并返回头结点
        public Node deleteFirstNode() {
            Node tempNode = first;
            first = tempNode.next;
            return tempNode;
        }

        // 在任意位置插入节点 在index的后面插入
        public void add(int index, int data) {
            Node node = new Node(data);
            Node current = first;
            Node previous = first;
            while (pos != index) {
                previous = current;
                current = current.next;
                pos++;
            }
            node.next = current;
            previous.next = node;
            pos = 0;
        }

        // 删除任意位置的节点
        public Node deleteByPos(int index) {
            Node current = first;
            Node previous = first;
            while (pos != index) {
                pos++;
                previous = current;
                current = current.next;
            }
            if (current == first) {
                first = first.next;
            } else {
                pos = 0;
                previous.next = current.next;
            }
            return current;
        }

        // 根据节点的data删除节点(仅仅删除第一个)
        public Node deleteByData(int data) {
            Node current = first;
            Node previous = first; //记住上一个节点
            while (current.data != data) {
                if (current.next == null) {
                    return null;
                }
                previous = current;
                current = current.next;
            }
            if (current == first) {
                first = first.next;
            } else {
                previous.next = current.next;
            }
            return current;
        }

        // 显示出所有的节点信息
        public void displayAllNodes() {
            Node current = first;
            while (current != null) {
                current.display();
                current = current.next;
            }
            System.out.println();
        }

        // 根据位置查找节点信息
        public Node findByPos(int index) {
            Node current = first;
            if (pos != index) {
                current = current.next;
                pos++;
            }
            return current;
        }

        // 根据数据查找节点信息
        public Node findByData(int data) {
            Node current = first;
            while (current.data != data) {
                if (current.next == null)
                    return null;
                current = current.next;
            }
            return current;
        }


    }

    /**
     * 测试链表
     */
    public void testLinkList() {
        LinkList linkList = new LinkList();
        linkList.addFirstNode(20);
        linkList.displayAllNodes();

//        linkList.addFirstNode(21);
//        linkList.addFirstNode(19);
        //19,21,20
//        linkList.add(1, 22); //19,22,21,20
//        linkList.add(2, 23); //19,22,23,21,20
//        linkList.add(3, 99); //19,22,23,99,21,20
//        linkList.displayAllNodes();
//        Node node = linkList.deleteFirstNode();
//        System.out.println("node : " + node.data);
//        linkList.displayAllNodes();
//        node = linkList.deleteByPos(2);
//        System.out.println("node : " + node.data);
//        linkList.displayAllNodes();
//        linkList.deleteFirstNode();

//        Node node = linkList.deleteByData(19);

//        Node node = linkList.deleteByPos(0);

//        System.out.println("node : " + node.data);
//        linkList.displayAllNodes();
//        Node node1 = linkList.findByPos(0);
//        System.out.println("node1: " + node1.data);
//        Node node2 = linkList.findByData(22);
//        System.out.println("node2: " + node2.data);
    }
}
