package org.example;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Trie data structure used for building an inverted index.
 * Each word is broken down character by character and stored in connected TrieNodes.
 * Every word points to the document IDs where it appears.
 */
public class Trie {

    // Root node of the Trie (always empty)
    private TrieNode root;

    /**
     * Initializes the Trie with an empty root node.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie and associates it with a specific document ID.
     *
     * For example:
     *   insert("apple", 1)
     * will store the word "apple" in the Trie, and mark that it appears in document #1.
     *
     * @param word  the word to insert
     * @param docId the ID of the document that contains this word
     */
    public void insert(String word, int docId) {
        TrieNode node = root;
        word = word.toLowerCase();  // make it case-insensitive

        // Traverse or create nodes for each character in the word
        for (char c : word.toCharArray()) {
            node.getChildren().putIfAbsent(c, new TrieNode());
            node = node.getChildren().get(c);
        }

        // Mark the end of the word and link the document ID
        node.setEndOfWord(true);
        node.addDocumentId(docId);
    }

    /**
     * Searches for a word in the Trie and returns the set of document IDs
     * where this word is found.
     *
     * If the word doesn't exist, it returns an empty set.
     *
     * Example:
     *   search("apple") → [1, 3, 5]
     *
     * @param word the word to search for
     * @return a set of document IDs containing the word
     */
    public Set<Integer> search(String word) {
        TrieNode node = root;
        word = word.toLowerCase();

        // Traverse the Trie for each character in the word
        for (char c : word.toCharArray()) {
            if (!node.getChildren().containsKey(c)) {
                // Word path doesn't exist → word not found
                return new HashSet<>();
            }
            node = node.getChildren().get(c);
        }

        // Return all document IDs where this word appears
        if (node.isEndOfWord()) {
            return node.getDocumentIds();
        }
        return new HashSet<>();
    }
}
