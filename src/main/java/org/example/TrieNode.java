package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a single node inside the Trie data structure.
 * Each node can hold:
 *   - a map of its child nodes (for next characters),
 *   - a flag to mark if it completes a full word,
 *   - and a set of document IDs that contain this word.
 */
public class TrieNode {

    // Stores references to the next character nodes
    private Map<Character, TrieNode> children;

    // Marks if the path ending at this node forms a complete word
    private boolean isEndOfWord;

    // Keeps track of which document IDs contain the word ending here
    private Set<Integer> documentIds;

    /**
     * Constructor initializes an empty Trie node.
     * By default, it's not the end of any word and has no children or documents linked yet.
     */
    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.documentIds = new HashSet<>();
    }

    /**
     * @return the map of child nodes (one for each character)
     */
    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    /**
     * @return true if this node represents the end of a complete word
     */
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    /**
     * Sets whether this node marks the end of a word.
     * @param endOfWord true if this node completes a word
     */
    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    /**
     * @return a set of document IDs that contain the word represented by this node
     */
    public Set<Integer> getDocumentIds() {
        return documentIds;
    }

    /**
     * Adds a document ID to this node’s set.
     * This means that the word leading to this node appears in that document.
     * @param docId ID of the document where the word was found
     */
    public void addDocumentId(int docId) {
        this.documentIds.add(docId);
    }
}
