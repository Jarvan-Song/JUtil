package util.leetcode;

import java.util.Base64;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by songpanfei on 2020-08-05.
 * 字典树
 */
public class Trie {

    public static void main(String[] args)throws Exception{
        Trie trie = new Trie();
        trie.insert("test");
        trie.insert("word");
        System.out.println(trie.search("test"));
        System.out.println(trie.search("tes"));
        System.out.println(trie.startsWith("tes"));

        //测试CompletableFuture
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Trie::getMoreData);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println("0000000000"+ e);
        });
        System.out.println(f.get());
//        System.in.read();
    }


    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("begin to start compute");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        if((System.currentTimeMillis()/1000)%2 ==0 ){
            throw new RuntimeException("test");
        }
        return rand.nextInt(1000);
    }



    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        TrieNode() {}
        TrieNode(char c){
            this.val = c;
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode(c);
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true; //设置末尾节点为一个完整字符
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(char c: prefix.toCharArray()){
            if(cur.children[c-'a']==null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return true;
    }
}
