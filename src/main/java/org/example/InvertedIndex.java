package org.example;

import java.util.*;

/**
 * Inverted Index implementation using a Trie structure.
 * -----------------------------------------------------
 * Each word from product information (like name, category, or store)
 * is indexed in a Trie so we can quickly find all products
 * that contain that word.
 *
 * Example:
 *   If "Apple" appears in two products, searching "apple"
 *   will instantly return those product entries.
 */
public class InvertedIndex {

    private Trie trie;                    // Trie structure to store word-to-document mapping
    private Map<Integer, Product> documents; // Stores all products by their document ID
    private int totalWords;               // Tracks total number of unique words indexed

    /**
     * Constructor initializes the Trie and supporting data structures.
     */
    public InvertedIndex() {
        this.trie = new Trie();
        this.documents = new HashMap<>();
        this.totalWords = 0;
    }

    /**
     * Adds a new product (document) into the inverted index.
     * ------------------------------------------------------
     * 1. Stores the product in the document map.
     * 2. Extracts words from its key text fields (name, category, store).
     * 3. Inserts each unique word into the Trie with its document ID.
     *
     * @param docId   unique identifier for the document (product)
     * @param product the product object containing text fields
     */
    public void addDocument(int docId, Product product) {
        // Step 1: Store the full product details for retrieval
        documents.put(docId, product);

        // Step 2: Collect relevant text fields (skip null values)
        List<String> textFields = Arrays.asList(
                product.getProductName() != null ? product.getProductName() : "",
                product.getCategory() != null ? product.getCategory() : "",
                product.getStoreName() != null ? product.getStoreName() : ""
        );

        // Combine all text fields into a single string for processing
        String allText = String.join(" ", textFields);

        // Step 3: Extract clean words (no punctuation or symbols)
        List<String> words = extractWords(allText);

        // Step 4: Insert each word into the Trie
        // Using a set to avoid counting duplicate words from the same product
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                trie.insert(word, docId);  // link the word to the product ID
                uniqueWords.add(word.toLowerCase());
            }
        }

        // Update word count (only count unique words per product)
        totalWords += uniqueWords.size();
    }

    /**
     * Helper function to extract valid words from text.
     * -------------------------------------------------
     * - Removes special characters and punctuation.
     * - Splits the text by whitespace.
     * - Returns a list of cleaned words.
     *
     * @param text input text (e.g., product name + category)
     * @return list of cleaned, lowercase words
     */
    private List<String> extractWords(String text) {
        // Replace special characters with spaces to isolate words
        String cleanedText = text.replaceAll("[.,!?;:()\\[\\]{}\"']", " ");

        // Split by whitespace and filter out empty strings
        String[] words = cleanedText.split("\\s+");
        List<String> result = new ArrayList<>();

        for (String word : words) {
            String trimmed = word.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }

        return result;
    }

    /**
     * Searches the inverted index for a given word and
     * returns a list of matching product documents.
     *
     * @param word the search keyword
     * @return list of Product objects that contain the word
     */
    public List<Product> search(String word) {
        // Find all document IDs that contain this word
        Set<Integer> docIds = trie.search(word);
        List<Product> results = new ArrayList<>();

        // Sort IDs so output is consistent (helps for testing or display)
        List<Integer> sortedIds = new ArrayList<>(docIds);
        Collections.sort(sortedIds);

        // Retrieve actual product objects for each matching document
        for (int docId : sortedIds) {
            if (documents.containsKey(docId)) {
                results.add(documents.get(docId));
            }
        }

        return results;
    }

    /**
     * Returns total number of unique words indexed across all documents.
     *
     * @return total unique word count
     */
    public int getTotalWords() {
        return totalWords;
    }
}
