package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the Product Search Application
 * ------------------------------------------------
 * This console-based program reads product details from a CSV file,
 * builds an inverted index using a Trie structure, and allows users
 * to perform fast keyword-based searches across all products.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== PRODUCT SEARCH ENGINE (INVERTED INDEX) ===\n");

        // Step 1: Load data from CSV file
        String csvFile = "products.csv";  // Ensure this file exists in resources/
        System.out.println("Initializing system...");
        System.out.println("Reading product data from file: " + csvFile);

        List<Product> productList = CSVLoader.loadProductsFromCSV(csvFile);

        if (productList == null || productList.isEmpty()) {
            System.out.println("⚠️  No data found! Please check your CSV file and try again.");
            return;
        }

        System.out.println("→ Total Products Loaded: " + productList.size());

        // Step 2: Build the inverted index
        System.out.println("\nBuilding inverted index...");
        InvertedIndex index = new InvertedIndex();

        int counter = 1;
        for (Product item : productList) {
            index.addDocument(counter++, item);
        }

        System.out.println("✓ Index successfully built with " + index.getTotalWords() + " unique entries.");

        // Step 3: Start interactive search
        runSearchInterface(index);
    }

    /**
     * Handles the interactive search loop where users can search for words.
     * The user can type 'exit' anytime to end the program.
     */
    private static void runSearchInterface(InvertedIndex index) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n---------------------------------------------------");
        System.out.println("You can now search any product keyword.");
        System.out.println("Type 'exit' anytime to close the program.");
        System.out.println("---------------------------------------------------");

        while (true) {
            System.out.print("\nEnter keyword to search: ");
            String query = scanner.nextLine().trim();

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("\n✅ Thank you for using the Product Search Engine!");
                break;
            }

            if (query.isEmpty()) {
                System.out.println("⚠️  Please type a valid search word.");
                continue;
            }

            List<Product> searchResults = index.search(query);
            showResults(query, searchResults);
        }

        scanner.close();
    }

    /**
     * Prints formatted search results in a structured output format.
     */
    private static void showResults(String keyword, List<Product> results) {
        System.out.println("\n---------------------------------------------------");
        System.out.println("Search Results for: \"" + keyword + "\"");

        if (results == null || results.isEmpty()) {
            System.out.println("No matching products found.");
        } else {
            System.out.println("Found in " + results.size() + " document(s):");

            int docNumber = 1;
            for (Product p : results) {
                System.out.println("\nDocument " + docNumber++ + ":");
                System.out.println("  Product Name : " + p.getProductName());
                System.out.println("  Price        : " + p.getPrice());
                System.out.println("  Store        : " + p.getStoreName());
            }
        }
        System.out.println("---------------------------------------------------");
    }
}
